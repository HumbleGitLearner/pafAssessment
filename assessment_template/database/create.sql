-- Task 1
-- Write SQL statements in this file to create the brewery database and
-- populate the database with the given SQL files


mysqlsh -u root --sql -p
create database brewery;
use brewery;
source database/beers.sql
source database/breweries.sql
source database/categories.sql
source database/geocodes.sql
source database/styles.sql
