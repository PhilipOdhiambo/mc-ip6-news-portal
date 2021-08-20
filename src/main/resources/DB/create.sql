CREATE TABLE IF NOT EXISTS employees (
    id int serial PRIMARY KEY,
    position VARCHAR,
    departmentid INTEGER
);


CREATE TABLE IF NOT EXISTS news (
    id int serial PRIMARY KEY,
    content text
);

CREATE TABLE IF NOT EXISTS newsdepartmental (
    id int serial PRIMARY KEY,
    content text,
    departmentid INTEGER
);


CREATE TABLE IF NOT EXISTS departments (
    id int serial PRIMARY KEY,
    name VARCHAR,
    description VARCHAR,
);

CREATE TABLE IF NOT EXISTS roles (
    id int serial PRIMARY KEY,
    name VARCHAR
);
