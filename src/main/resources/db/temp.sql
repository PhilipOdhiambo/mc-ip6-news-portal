

CREATE TABLE IF NOT EXISTS newsdepartmental (
    id int PRIMARY KEY auto_increment,
    content VARCHAR,
    departmentid INTEGER
);

CREATE TABLE IF NOT EXISTS roles (
    id int PRIMARY KEY auto_increment,
    name VARCHAR
);

CREATE TABLE IF NOT EXISTS employees-roles (
 id int PRIMARY KEY auto_increment,
 employeeid INTEGER,
 roleid INTEGER
);
