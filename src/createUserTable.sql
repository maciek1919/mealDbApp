CREATE TABLE users (
id SERIAL PRIMARY KEY,
username VARCHAR(15) NOT NULL,
password VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL
);