CREATE DATABASE news_portal;
\c news_portal;

CREATE TABLE departments (
    id int SERIAL PRIMARY KEY,
    name VARCHAR,
    description VARCHAR
);

CREATE TABLE employees (
    id int SERIAL PRIMARY KEY,
    name VARCHAR,
    departmentid int
);

CREATE TABLE news (
    id int SERIAL PRIMARY KEY,
    content VARCHAR
);

CREATE TABLE newsdepartmental (
      id int SERIAL PRIMARY KEY,
    content VARCHAR,
    departmentid INTEGER
);

CREATE TABLE  roles (
       id int SERIAL PRIMARY KEY,
    name VARCHAR
);


CREATE TABLE employees_roles (
    id int SERIAL PRIMARY KEY,
 employeeid INTEGER,
 roleid INTEGER
);