DROP DATABASE balance_inquiry;
CREATE DATABASE balance_inquiry;

USE balance_inquiry;

CREATE TABLE accounts (id INT(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,
		       account_number VARCHAR(20) NOT NULL,
		       balance DECIMAL(19,4) NOT NULL);

CREATE TABLE logs (id INT(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,
		   account_number VARCHAR(20) NOT NULL,
		   transaction_time TIMESTAMP DEFAULT NOW(),
		   response VARCHAR(20) NOT NULL);

INSERT INTO accounts (account_number, balance) VALUES (1000, 1000);
INSERT INTO accounts (account_number, balance) VALUES (2000, 10000);
INSERT INTO accounts (account_number, balance) VALUES (3000, 100000);
INSERT INTO accounts (account_number, balance) VALUES (4000, 1000000);
INSERT INTO accounts (account_number, balance) VALUES (5000, 10000000);
