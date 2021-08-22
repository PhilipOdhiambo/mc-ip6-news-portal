
CREATE TABLE IF NOT EXISTS departments (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS employees (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    departmentid INTEGER
);

CREATE TABLE IF NOT EXISTS news (
    id int PRIMARY KEY auto_increment,
    content VARCHAR
);




