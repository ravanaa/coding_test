drop table if exists library;
create table library(
 library_id int auto_increment primary key,
library_name varchar not null,
description varchar);
drop table if exists books;
create table books(
isbn int auto_increment primary key,
title varchar not null,
language varchar,
pub_year int,
author varchar,
library_id integer not null)