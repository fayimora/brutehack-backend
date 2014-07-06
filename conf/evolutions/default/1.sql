# --- !Ups

create table "CONTESTS" (
  "CONTEST_ID" SERIAL NOT NULL PRIMARY KEY,
  "CREATED_AT" TIMESTAMP NOT NULL,
  "UPDATED_AT" TIMESTAMP NOT NULL,
  "TITLE" VARCHAR(254) NOT NULL UNIQUE,
  "AUTHOR" VARCHAR(254) NOT NULL,
  "DESCRIPTION" TEXT NOT NULL,
  "START_TIME" TIMESTAMP NOT NULL,
  "DURATION" VARCHAR(254) NOT NULL,
  "PROBLEMS" int4 ARRAY NOT NULL
);

create table "PROBLEMS" (
  "PROBLEM_ID" SERIAL NOT NULL PRIMARY KEY,
  "CREATED_AT" TIMESTAMP NOT NULL,
  "UPDATED_AT" TIMESTAMP NOT NULL,
  "AUTHOR" VARCHAR(254) NOT NULL,
  "TITLE" VARCHAR(254) NOT NULL UNIQUE,
  "DESCRIPTION" TEXT NOT NULL,
  "HINT" TEXT NOT NULL,
  "INPUTS" text ARRAY NOT NULL,
  "OUTPUTS" text ARRAY NOT NULL
);

create table "USERS" (
  "USER_ID" SERIAL NOT NULL PRIMARY KEY,
  "CREATED_AT" TIMESTAMP NOT NULL,
  "UPDATED_AT" TIMESTAMP NOT NULL,
  "LAST_VISIT" TIMESTAMP NOT NULL,
  "HANDLE" VARCHAR(254) NOT NULL UNIQUE,
  "FIRSTNAME" VARCHAR(254) NOT NULL,
  "LASTNAME" VARCHAR(254) NOT NULL,
  "EMAIL" VARCHAR(254) NOT NULL UNIQUE,
  "RATING" INTEGER NOT NULL,
  "LOCATION" VARCHAR(254) NOT NULL,
  "SHIRTSIZE" VARCHAR(254) NOT NULL
);

insert into "CONTESTS" ("CREATED_AT","UPDATED_AT","TITLE","AUTHOR","DESCRIPTION","START_TIME","DURATION","PROBLEMS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Goodbye 2013', 'Fayi QLL', 'This is the description', '2014-07-06 00:24:54.177', '75mins', '{"1","2","3"}');
insert into "CONTESTS" ("CREATED_AT","UPDATED_AT","TITLE","AUTHOR","DESCRIPTION","START_TIME","DURATION","PROBLEMS") values ('2014-02-10 08:00:54.177', '2014-07-06 00:24:54.177', 'Welcome 2014', 'Fayi QLL', 'This is the description', '2014-07-06 00:24:54.177', '75mins', '{"4","5"}');
insert into "CONTESTS" ("CREATED_AT","UPDATED_AT","TITLE","AUTHOR","DESCRIPTION","START_TIME","DURATION","PROBLEMS") values ('2014-03-03 08:00:54.177', '2014-07-06 00:24:54.177', 'Sprint #101', 'Fayi QLL', 'This is the description', '2014-07-06 00:24:54.177', '60mins', '{"6"}');

insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 1', 'This is the description 1', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 2', 'This is the description 2', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 3', 'This is the description 3', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 4', 'This is the description 4', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 5', 'This is the description 5', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 6', 'This is the description 6', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');


insert into "USERS" ("CREATED_AT","UPDATED_AT","LAST_VISIT","HANDLE","FIRSTNAME","LASTNAME","EMAIL","RATING","LOCATION","SHIRTSIZE") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', '2014-05-16 18:21:54.177', 'fayimora', 'Fayimora', 'Femi-Balogun', 'fayi@fayimora.com', 3345, 'London, UK', 'L');

# --- !Downs

drop table "CONTESTS";

drop table "PROBLEMS";

drop table "USERS";
