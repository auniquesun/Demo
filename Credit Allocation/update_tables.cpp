// for mysql database connection
// #include "mysql_driver.h"
#include "update_tables.h"
#include "nlohmann/json.hpp"
#include<dirent.h>
#include<iostream>
#include<fstream>
#include<sstream>
// #include<string>
#include<unordered_set>

using json = nlohmann::json;
// namespace fs = std::filesystem;
using namespace std;

UpdateTables::UpdateTables(int i){
    num = i;
    // m_driver = get_driver_instance();
    // m_con = m_driver->connect("localhost", "root", "Maria_123.");
    // m_con->setSchema("mysql");
    // m_stmt = m_con->createStatement();
}

/*
 * release mysql database connection
 */
UpdateTables::~UpdateTables()
{
    // delete m_stmt;
    // delete m_con;
}

void UpdateTables::insert(string table){
    // string table;
    // cout << "input the table name rows to be inserted into: ";
    // getline(cin, table);    
    
    ifstream inFile("data/aps-dataset-citations-2018/aps-dataset-citations-2018.csv");

    string line, doi, citing_doi, cited_doi;
    // alternate between citing_doi and cited_doi
    int alter;
    // count number of rows inserted
    int count = 0;
    while(getline(inFile, line)){             
        if(count > 78712){
            // get a line from csv file, convert to string stream
            stringstream S(line);

            alter = 0;
            while(getline(S, doi, ',')){
                if(alter == 0)
                    citing_doi = doi;
                else
                    cited_doi = doi;
                ++alter;
            }   

            // you need get a clean sql after every insertion, otherwise sql will append strings
            stringstream sql;
            sql << "insert into " << table << "(citing_doi, cited_doi) values('" << citing_doi << "','" << cited_doi << "');";
            // m_stmt->execute(sql.str());
            // cout << sql.str() << endl;
            // cout << citing_doi << '\t' << cited_doi << endl;
        }
        
        ++count;
        // break;
    }

    inFile.close();

    cout << "[ " << count << " ] rows inserted into " << table << endl;
}

void UpdateTables::metadata1(){
    ifstream inFile("data/aps-dataset-metadata-2018/PRA/1/PhysRevA.1.1674.json");
    json jsonData;
    inFile >> jsonData;
    inFile.close();

    string doi = jsonData["id"].get<string>();
    cout << doi << endl;

    string title = jsonData["title"]["value"].get<string>();
    cout << title << endl;

    string pub_date = jsonData["date"].get<string>();    
    cout << pub_date << endl;

    // auto authors = jsonData["authors"];
    // for(json &author:authors){
    //     int i = 0;  // preparing for auth_order
    //     for(auto it=author.begin(); it!=author.end(); ++it){
    //         const string &key = it.key();
    //         if(key == "name"){
    //             json &val = it.value();
    //             string tmp = val.get<string>();
    //             cout << tmp << endl;
    //             // stringstream sql_aps_authorship;
    //             // sql_aps_authorship << "insert into aps_authorship values('" << doi << "','" << title << "'," << ++i << ");";
    //             // m_stmt->execute(sql_aps_authorship.str());
    //         }
            
    //     }
    // }   
}

void UpdateTables::metadata2(){
    // table <aps_authorship> column: doi, author, auth_order
    // table <aps_papers> column: doi, title, pub_date
    unordered_set<string> dirs({"PR", "PRA", "PRB", "PRC", "PRD", "PRE", "PRL", "RMP", "PRSTAB", "PRSTPER"});

    
    DIR *dir, *subdir, *subsubdir;
    struct dirent *ent, *subent, *subsubent;
    if ((dir = opendir ("data/aps-dataset-metadata-2018/")) != NULL) {       
        bool flag = true;   // for test: insert several rows

        /* print all the files and directories within directory */
        while (flag && (ent = readdir (dir)) != NULL) {

            // printf ("%s\n", ent->d_name);
            if(dirs.find(ent->d_name) != dirs.end()){

                string d_1 = "data/aps-dataset-metadata-2018/" + string(ent->d_name) + "/";

                if ((subdir = opendir(d_1.c_str())) != NULL){

                    while (flag && (subent = readdir(subdir)) != NULL) {

                        if(string(subent->d_name) != "." && string(subent->d_name) != ".."){
                            // printf("%s\t:\t%s\n", ent->d_name, subent->d_name);
                            string d_2 = "data/aps-dataset-metadata-2018/" + string(ent->d_name) + "/" + string(subent->d_name) + "/";

                            if((subsubdir = opendir(d_2.c_str())) != NULL){
                                
                                while(flag && (subsubent = readdir(subsubdir)) != NULL){

                                    if(string(subsubent->d_name) != "." && string(subsubent->d_name) != ".."){
                                        string jsonFile = "data/aps-dataset-metadata-2018/" + string(ent->d_name) + "/" + string(subent->d_name) + "/" + string(subsubent->d_name);

                                        // jsonFile is a SINGLE paper
                                        ifstream inFile(jsonFile);
                                        json jsonData;
                                        inFile >> jsonData;
                                        inFile.close();

                                        // both tables need
                                        string doi = jsonData["id"].get<string>();
                                        // the following two items are what aps_papers needs
                                        // note: the code of getting variable <title> and <pub_date> is without problems
                                        string title = jsonData["title"]["value"].get<string>();
                                        // truncate title
                                        if(title.size() > 100)
                                            title = title.substr(0,50);
                                        string pub_date = jsonData["date"].get<string>();
                                        if(pub_date.size() > 10)
                                            pub_date = pub_date.substr(0,10);
                                        // cout << string(subent->d_name) << '\t' << string(subsubent->d_name) << '\t' << jsonFile << endl;
                                        cout << doi << '\t' << title << '\t' << pub_date << endl;

                                        // stringstream sql_aps_papers;
                                        // sql_aps_papers << "insert into aps_papers values('" << doi << "','" << title << "','" << pub_date << "');";
                                        // m_stmt->execute(sql_aps_papers.str());
                                        // cout << sql_aps_papers.str() << endl;

                                        // int i = 0;  // preparing for auth_order
                                        // auto authors = jsonData["authors"];
                                        // for(json &author:authors){                                            
                                        //     for(auto it=author.begin(); it!=author.end(); ++it){
                                        //         const string &key = it.key();
                                        //         if(key == "name"){
                                        //             json &val = it.value();

                                        //             stringstream sql_aps_authorship;
                                        //             sql_aps_authorship << "insert into aps_authorship values('" << doi << "','" << val.get<string>() << "'," << ++i << ");";
                                        //             m_stmt->execute(sql_aps_authorship.str());
                                        
                                        //             // cout << sql_aps_authorship.str() << endl;
                                        //         }
                                                
                                        //     }
                                        // }

                                        // flag = false;
                                    }
                                }

                                closedir(subsubdir);
                                
                            }

                        }
                    }

                    closedir(subdir);
                }

            }
        }
        closedir(dir);
    } 
    else {
        /* could not open directory */
        printf("[ error! ], nonexistent directory.");
    }
}


int main(int argc, char **argv){
    if(argc != 2){
        cout << "usage: " << argv[0] << " <table_name>" << endl;
        return -1;
    }

    UpdateTables ut(1);

    string table = argv[1];
    ut.metadata2();

    return 0;
}