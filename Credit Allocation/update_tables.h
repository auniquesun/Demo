// for mysql database connection
// #include "mysql_driver.h"
// #include <mysql_connection.h>
// #include <cppconn/connection.h>
// #include <cppconn/driver.h>
// #include <cppconn/exception.h>
// #include <cppconn/resultset.h>
// #include <cppconn/statement.h>
#include<string>


class UpdateTables{
public:
    UpdateTables(int i);
    ~UpdateTables();

    void insert(std::string table);
    void metadata1();
    void metadata2();
private:
    int num;
    // sql::Driver *m_driver;
    // sql::Connection *m_con;
    // sql::Statement *m_stmt;
};
