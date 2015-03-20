# --- !Ups

create table users (
  "id" serial not null primary key,
  "created_at" timestamp default current_timestamp,
  "updated_at" timestamp default current_timestamp,
  "last_visit" timestamp not null,
  "handle" varchar(254) not null unique,
  "firstname" varchar(254) not null,
  "lastname" varchar(254) not null,
  "email" varchar(254) not null unique,
  "password" varchar(254) not null,
  "salt" varchar(254),
  "rating" integer not null,
  "location" varchar(254) not null,
  "shirtsize" varchar(254) not null
);
create unique index user_handle on users (handle);
create unique index user_email on users (email);


-- insert into users
-- ("created_at","updated_at","last_visit","handle","firstname","lastname","email","rating","location","shirtsize","password","salt")
-- values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', '2014-05-16 18:21:54.177', 'fayimora',
--   'Fayimora', 'Femi-Balogun', 'fayi@fayimora.com', 3345, 'London, UK', 'L', "mypassword",
--   "this$is$the$salt");


# --- !Downs

drop table if exists users;
