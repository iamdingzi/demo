DROP TABLE IF EXISTS SERVER_TABLE;
create table  SERVER_TABLE (
SERVER_ID int not null primary key auto_increment,
SERVER_IP varchar(50),
SERVER_STATUS int DEFAULT 0,
VERSION int default 0
);