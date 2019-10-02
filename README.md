# Organisation News Portal
### Author: Tona Daphin
## Description
This is a java application that allows individuals in different departments to post and see news in an organisation.

## Technologies used

1. Java 
2. Spark core 2.12
4. Spark Handlebars
5. Junit 5
6. Postgres database

## Database

    CREATE TABLE departments(id SERIAL PRIMARY KEY,name varchar, description varchar, employees int);
    CREATE TABLE users(id SERIAL PRIMARY KEY, name varchar, postion varchar, role varchar);
    CREATE TABLE news(id SERIAL PRIMARY KEY, heading varchar , content varchar);
  

## Live link

[Click here to redirect](https://github.com/TonaDaphin/Organization-news-portal)


## Copyright & License

Tona Daphin <br>
This project is licensed under the MIT License 
