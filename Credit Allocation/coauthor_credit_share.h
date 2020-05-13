#ifndef _COAUTHOR_CREDIT_SHARE_H_
#define _COAUTHOR_CREDIT_SHARE_H_

#include <string>
#include <map>
#include <set>
#include <vector>
#include <string>

// for mysql database connection
// #include "mysql_driver.h"
#include <mysql_connection.h>
#include <cppconn/connection.h>
#include <cppconn/driver.h>
#include <cppconn/exception.h>
#include <cppconn/resultset.h>
#include <cppconn/statement.h>

struct doi_date 
{
public:
    bool operator < ( const doi_date & s ) const 
    {
        if ( date < s.date )
            return true;
        else
            return false;
    }
public:
    std::string doi;
    std::string date;
};

struct CoCitation
{
public:
    int strength;
    
    std::vector<double> allocation_matrix;
};

class CCreditShare
{
public:
    CCreditShare( int alloc_type );
    ~CCreditShare();

public:
    void coauthor_credit_share( const std::string & doi, std::vector<double> & credit_share );

    void output_cocited_papers();

private:
    void get_authors( const std::string & doi );

    void sort_citing_papers_by_date( std::vector<std::string> & citing_papers, std::vector<int> & citing_pubyear );

    void get_citing_papers( const std::string & cited_doi, std::vector<std::string> & citing_papers );

    void scan_one_citing_paper( const std::string & citing_paper ); 

    bool obtain_allocation_vector_of_cocited_paper( const std::string & cocited_paper, std::vector<double> & alloc_vector );

    void update_credit_share( std::vector<double> & credit_share );

private:
    sql::Driver *m_driver;
    sql::Connection *m_con;
    sql::Statement *m_stmt;
 
private:
    int m_alloc_type; // 0: count; 1: 1/m; 2: 1/i; 3: 1+1/2+...+1/i 

    std::map< std::string, CoCitation > m_cocitations; 

    std::vector< std::string > m_authors;
};

#endif
