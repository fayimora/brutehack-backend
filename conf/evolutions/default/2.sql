# --- !Ups

create table "PROBLEMS" (
  "PROBLEM_ID" SERIAL NOT NULL PRIMARY KEY,
  "CREATED_AT" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  "UPDATED_AT" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  "AUTHOR" VARCHAR(254) NOT NULL,
  "TITLE" VARCHAR(254) NOT NULL UNIQUE,
  "DESCRIPTION" TEXT NOT NULL,
  "HINT" TEXT NOT NULL,
  "INPUTS" text ARRAY NOT NULL,
  "OUTPUTS" text ARRAY NOT NULL
);
create unique index "PROBLEM_TITLE" on "PROBLEMS" ("TITLE");

insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 1', 'This is the description 1', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 2', 'This is the description 2', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 3', 'This is the description 3', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 4', 'This is the description 4', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 5', 'This is the description 5', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "PROBLEMS" ("CREATED_AT","UPDATED_AT","AUTHOR","TITLE","DESCRIPTION","HINT","INPUTS","OUTPUTS") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 6', 'This is the description 6', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');


# --- !Downs

drop table if exists "PROBLEMS";

