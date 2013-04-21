CREATE TABLE board (
   board_seq NUMBER PRIMARY KEY, 
   table_seq NUMber, 
   user_seq NUMBER,
   title VARCHAR2(200), 
   CONTENT CLOB, 
   passwd VARCHAR2(30), 
   rm_yn CHAR(1) DEFAULT 'N', 
   reg_date TIMESTAMP DEFAULT SYSDATE, 
   rm_date TIMESTAMP, 
   inquiry_count NUMBER DEFAULT 0,
   has_file CHAR(1) DEFAULT 'N', 
   ip VARCHAR2(50), 
   reply_count NUMBER DEFAULT 0
); 

CREATE TABLE board_file (
    file_seq NUMBER PRIMARY KEY, 
    board_seq NUMBER,
    user_seq NUMBER, 
    file_path VARCHAR2(200), 
    org_file_name VARCHAR2(200), 
    copy_file_name VARCHAR2(200), 
    file_size NUMBER, 
    file_ext VARCHAR2(10), 
    rm_yn CHAR(1) DEFAULT 'N', 
    reg_date TIMESTAMP DEFAULT SYSDATE, 
    rm_date TIMESTAMP , 
    down_count NUMBER DEFAULT 0
);

CREATE TABLE board_reply (
    reply_seq NUMBER PRIMARY KEY, 
    board_seq NUMBER,
    user_seq NUMBER,
    CONTENT VARCHAR2(400), 
    rm_yn CHAR(1) DEFAULT 'N', 
    reg_date TIMESTAMP DEFAULT SYSDATE,
    rm_date TIMESTAMP, 
    recommend NUMBER DEFAULT 0,
    ip VARCHAR2(30)
);


CREATE TABLE board_table (
    table_seq NUMBER PRIMARY KEY,
    board_id VARCHAR2(20), 
    boar_name VARCHAR2(200), 
    board_desc VARCHAR2(200), 
    rm_yn char(1) default 'N', 
    reg_date TIMESTAMP DEFAULT SYSDATE,
    rm_date TIMESTAMP 
);

CREATE TABLE USERS(
    user_seq NUMBER PRIMARY KEY, 
    ID VARCHAR2(30), 
    passwd VARCHAR2(30), 
    email VARCHAR2(30), 
    NAME VARCHAR2(30), 
    tel VARCHAR2(200), 
    phone VARCHAR2(200), 
    ROLE VARCHAR(3), 
    rm_yn CHAR(1) DEFAULT 'N', 
    reg_date TIMESTAMP DEFAULT SYSDATE, 
    modified_date TIMESTAMP,
    rm_date TIMESTAMP, 
    zip_code VARCHAR2(100), 
    dft_addr VARCHAR2(200), 
    dtl_addr VARCHAR2(200), 
    login_count NUMBER DEFAULT 0
);


CREATE SEQUENCE board_seq;
CREATE SEQUENCE board_file_seq;
CREATE SEQUENCE board_reply_seq;
CREATE SEQUENCE board_table_seq;
CREATE SEQUENCE user_seq;