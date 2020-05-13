#############################################################
# SOURCE=coauthor_credit_share.cpp
# MYPROGRAM=coauthor_credit_share
SOURCE=update_tables.cpp
MYPROGRAM=update_tables
# MYINCLUDES=/usr/lib
# MYLIBRARIES=mysqlcppconn
CC=g++
CPPVERSION=c++17
# FLAGS=-O2
#############################################################

all:$(MYPROGRAM)

$(MYPROGRAM):$(SOURCE)
	$(CC) -o $(MYPROGRAM) $(SOURCE) -std=$(CPPVERSION)

# $(MYPROGRAM):$(SOURCE)
# 	$(CC) -o $(MYPROGRAM) $(SOURCE) -L$(MYINCLUDES) -l$(MYLIBRARIES) -std=$(CPPVERSION)
# clean:
# rm -f $(MYPROGRAM)
