#include "coauthor_credit_share.h"

#include <iostream>
#include <fstream>
#include <sstream>
#include <cstdlib>
#include <iomanip>
#include <algorithm>

using namespace std;
using namespace sql;


/*
 * create mysql connection
 */
CCreditShare::CCreditShare( int alloc_type )
{
    /* Create a connection */
    m_driver = get_driver_instance();
    m_con = m_driver->connect("localhost", "root", "Maria_123.");
    m_con->setSchema("mysql"); // select database
    m_stmt = m_con->createStatement();

    m_alloc_type = alloc_type;
}

/*
 * release mysql database connection
 */
CCreditShare::~CCreditShare()
{
    delete m_stmt;
    delete m_con;
}

/*
 * get the authors of a paper, specified by $doi, with results stored in $authors
 *
 */
void CCreditShare::get_authors( const string & doi )
{
    stringstream sql;
    sql << "SELECT author FROM aps_authorship WHERE doi = '" << doi << "' ORDER BY auth_order ASC;";

    sql::ResultSet *res;
    res = m_stmt->executeQuery( sql.str() ); 
    while ( res->next() )
    {
        m_authors.push_back( res->getString("author") );
    }
    delete res;
}

/*
 * sort citing papers by publication date
 *
 */
void CCreditShare::sort_citing_papers_by_date( vector<string> & citing_papers, vector<int> & citing_pubyear )
{
    vector<doi_date> sorted_citing_papers;

    for ( size_t index = 0; index < citing_papers.size(); ++index )
    {
        doi_date dd;
        stringstream sql;
        sql << "SELECT pub_date FROM aps_papers WHERE doi = '" << citing_papers[index] << "';";

        sql::ResultSet *res;
        res = m_stmt->executeQuery( sql.str() ); 
        if ( res->next() )
        {
            dd.doi = citing_papers[index];
            dd.date = res->getString( "pub_date" );
        }
        else
        {
            cout << "Warning: certain citing paper has no publication date.\n"; 
            dd.doi = citing_papers[index];
            dd.date = "2011-00-00";
        }
        delete res;
        
        sorted_citing_papers.push_back( dd );
    }

    sort( sorted_citing_papers.begin(), sorted_citing_papers.end() ); 
    
    citing_pubyear.resize( citing_papers.size() );
    for ( size_t index = 0; index < sorted_citing_papers.size(); ++index )
    {
        citing_pubyear[index] = atoi( ((sorted_citing_papers[index].date).substr(0,4)).c_str() );
        citing_papers[index] = sorted_citing_papers[index].doi;
        //cout << index + 1 << "\t" << sorted_citing_papers[index].date << "\t " << sorted_citing_papers[index].doi << endl;
    }
}

void CCreditShare::get_citing_papers( const string & cited_doi, vector<string> & citing_papers )
{
    stringstream sql;
    sql << "SELECT citing_doi FROM aps_citations WHERE cited_doi = '" << cited_doi << "';";
             
    sql::ResultSet *res;
    res = m_stmt->executeQuery( sql.str() ); 

    //int citation_count = 0;
    while ( res->next() )
    {
     //   ++citation_count;
        citing_papers.push_back( res->getString( "citing_doi" ) );
      //  cout << res->getString( "citing" ) << endl;
    }
    
    delete res;
}

/*
 * compute the vector of allocation according to the authorship of paper $cocited_paper
 *
 */
bool CCreditShare::obtain_allocation_vector_of_cocited_paper( const string & cocited_paper, vector<double> & alloc_vector )
{
    stringstream sql;
    sql << "SELECT author FROM aps_authorship WHERE doi = '" << cocited_paper << "' ORDER BY auth_order ASC;";

    vector<string> authors;
    sql::ResultSet *res;
    res = m_stmt->executeQuery( sql.str() ); 
    while ( res->next() )
    {
        authors.push_back( res->getString("author") );
    }
    delete res;

    bool bFlag = false;

    alloc_vector.resize( m_authors.size(), 0.0 );
    for ( size_t index = 0; index < m_authors.size(); ++index )
    {
        for ( size_t ii = 0; ii < authors.size(); ++ii )
        {
            if ( authors[ii] == m_authors[index] )
            {
                bFlag = true;
                if ( m_alloc_type == 0 )
                    alloc_vector[index] = 1.0;
                else if ( m_alloc_type == 1 )
                    alloc_vector[index] = 1.0 / (int)authors.size();
                else if ( m_alloc_type == 2 )
                {
                    double harmonic_norm = 0.0;
                    for ( int auth_rank = 1; auth_rank <= (int)authors.size(); ++auth_rank )
                        harmonic_norm += 1.0 / auth_rank;

                    alloc_vector[index] = (1.0 / (int)(ii+1)) / harmonic_norm;
                }
                else
                {
                    double axiom_score = 0.0;
                    for ( int auth_rank = ii+1; auth_rank <= (int)authors.size(); ++auth_rank )
                        axiom_score += 1.0 / auth_rank;

                    alloc_vector[index] += axiom_score / (int)authors.size();
                }
            }
        }
    }

    return bFlag;
}

/*
 * scan one citing paper and update the m_cocitations
 *
 */
void CCreditShare::scan_one_citing_paper( const string & citing_paper ) 
{
    stringstream sql;
    sql << "SELECT cited_doi FROM aps_citations WHERE citing_doi = '" << citing_paper << "';";
    
    sql::ResultSet *res;
    res = m_stmt->executeQuery( sql.str() ); 

    string cocited_doi;
    while ( res->next() )
    {
        cocited_doi = res->getString( "cited_doi" );

        map< string, CoCitation >::iterator iter;

        iter = m_cocitations.find( cocited_doi );
        if ( iter != m_cocitations.end() )
        {
            ++(iter->second).strength;
        }
        else
        {
            CoCitation cc;
            cc.strength = 1;
            bool ret = obtain_allocation_vector_of_cocited_paper( cocited_doi, cc.allocation_matrix);

            // containing target authors
            if ( ret )
                m_cocitations.insert( make_pair(cocited_doi, cc) );
        }
    }
}

/*
 * update $credit_share according to current co-citation information
 *
 */
void CCreditShare::update_credit_share( vector<double> & credit_share )
{
    map< string, CoCitation >::iterator iter;
    for ( iter = m_cocitations.begin(); iter != m_cocitations.end(); ++iter )
    {
        //cout << iter->first << "\t" << (iter->second).strength << "\t:"; 
        for ( size_t index = 0; index < credit_share.size(); ++index )
        {
            credit_share[index] += (iter->second).strength * (iter->second).allocation_matrix[index];
        //    cout << "\t" << (iter->second).allocation_matrix[index]; 
        }
        //cout << "\n";
    }
        
    double norm = 0.0;
    for ( size_t index = 0; index < credit_share.size(); ++index )
        norm += credit_share[index];
    
    for ( size_t index = 0; index < credit_share.size(); ++index )
        credit_share[index] /= norm;
}

/*
 * compute the credit share of the authors of a paper, specified by $doi
 * results are stored in $credit_share
 *
 */
void CCreditShare::coauthor_credit_share( const string & doi, vector<double> & credit_share )
{
    get_authors( doi );
    if ( m_authors.size() == 0 ) {
        cout << "No author.\n";
        return;
    }

    vector<string> citing_papers;
    get_citing_papers( doi, citing_papers );
    cout << "Citations: " << citing_papers.size() << endl;  
    if ( citing_papers.size() == 0 )
        return;

    vector<int> citing_pubyear;
    sort_citing_papers_by_date( citing_papers, citing_pubyear );
       
    int pre_year = citing_pubyear[0];
    int cur_year;
    credit_share.resize( m_authors.size() );
    for ( size_t index = 0; index < citing_papers.size(); ++index )
    {
        cur_year = citing_pubyear[index];
        
        if ( cur_year != pre_year )
        {
            update_credit_share( credit_share );
            cout << index + 1 << "\t" << pre_year; 
            for ( size_t index = 0; index < credit_share.size(); ++index )
            {
                cout << "\t" << setw(8) << credit_share[index];
            }
            cout << "\n";
        }
        scan_one_citing_paper( citing_papers[index] ); 
        if ( index == citing_papers.size() - 1 )
        {
            update_credit_share( credit_share );
            cout << index + 1 << "\t" << cur_year; 
            for ( size_t index = 0; index < credit_share.size(); ++index )
            {
                cout << "\t" << setw(8) << credit_share[index];
            }
            cout << "\n";
        }

        pre_year = cur_year;
    }
}

void CCreditShare::output_cocited_papers()
{
    ofstream outFile( "cocitation_paper.txt" );

    outFile << "#cocitation papers: " <<  m_cocitations.size() << "\n";

    map< string, CoCitation >::iterator iter;
    for ( iter = m_cocitations.begin(); iter != m_cocitations.end(); ++iter )
    {
        outFile << iter->first << "\t" << (iter->second).strength << "\t:"; 
        for ( size_t index = 0; index < m_authors.size(); ++index )
        {
            outFile << "\t" << (iter->second).allocation_matrix[index]; 
        }
        outFile << "\n";
    }
        
    outFile.close();
}


int main( int argc, char **argv )
{
    if ( argc != 3 )
    {
        cout << "Usage: doi " << argv[0] << " doi alloc_type (0: count; 1: fractional; 2: harmonic; 3: axiom.)\n";
        return -1;
    }

    int credit_alloc_type = atoi( argv[2] );
    if ( credit_alloc_type > 4 || credit_alloc_type < 0 )
    {
        cout << "Usage: " << argv[0] << " doi alloc_type (0: count; 1: fractional; 2: harmonic; 3: axiom.)\n";
        return -1;
    }

    string doi = argv[1];

    CCreditShare cs( credit_alloc_type );
    vector<double> credit_share;

    cs.coauthor_credit_share( doi, credit_share );
    for ( size_t index = 0; index < credit_share.size(); ++index )
    {
        cout << setfill('0') << setprecision(6) << credit_share[index] << "\t";
    }
    cout << "\n";

    cs.output_cocited_papers();

    //allocate_author_credit_share( citing_papers, authors, credit_share );

    return 0;
}
