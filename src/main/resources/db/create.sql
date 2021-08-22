
CREATE TABLE IF NOT EXISTS departments (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    description VARCHAR,
);


CREATE TABLE IF NOT EXISTS employees (
    id int PRIMARY KEY auto_increment,
    position VARCHAR,
    departmentid INTEGER
);



