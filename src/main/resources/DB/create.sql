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


CREATE TABLE IF NOT EXISTS departmnets (
    id int serial PRIMARY KEY,
    department VARCHAR,
    detail VARCHAR,

);

CREATE TABLE IF NOT EXISTS roles (
    id int serial PRIMARY KEY,
    detail VARCHAR
);

