
CREATE TABLE IF NOT EXISTS departments (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS employees (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    departmentid int
);

CREATE TABLE IF NOT EXISTS news (
    id int PRIMARY KEY auto_increment,
    content VARCHAR
);

CREATE TABLE IF NOT EXISTS newsdepartmental (
    id int PRIMARY KEY auto_increment,
    content VARCHAR,
    departmentid INTEGER
);

CREATE TABLE IF NOT EXISTS roles (
    id int PRIMARY KEY auto_increment,
    name VARCHAR
);




