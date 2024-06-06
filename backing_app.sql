	create database banking_app;
    Use banking_app;
    create table accounts(
    id INT primary key auto_increment,
    account_number varchar(20)Not null,
    blance decimal(15,2)not null default 0
    );
    create table transactions (
    id INT primary key auto_increment,
    account_number varchar(20) not null,
    amount decimal(15, 2) not null,
    transaction_type enum('DEPOSIT', 'WITHDRAWAL', 'TRANSFER') not null,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);