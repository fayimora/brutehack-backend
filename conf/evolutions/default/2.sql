# --- !Ups

create table problems (
  "problem_id" serial not null primary key,
  "created_at" timestamp default current_timestamp,
  "updated_at" timestamp default current_timestamp,
  "author" varchar(254) not null,
  "title" varchar(254) not null unique,
  "description" text not null,
  "hint" text not null,
  "inputs" text array not null,
  "outputs" text array not null
);
create unique index problem_title on problems (title);

insert into "problems" ("created_at","updated_at","author","title","description","hint","inputs","outputs") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 1', 'This is the description 1', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "problems" ("created_at","updated_at","author","title","description","hint","inputs","outputs") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 2', 'This is the description 2', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "problems" ("created_at","updated_at","author","title","description","hint","inputs","outputs") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 3', 'This is the description 3', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "problems" ("created_at","updated_at","author","title","description","hint","inputs","outputs") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 4', 'This is the description 4', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "problems" ("created_at","updated_at","author","title","description","hint","inputs","outputs") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 5', 'This is the description 5', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');
insert into "problems" ("created_at","updated_at","author","title","description","hint","inputs","outputs") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Fayimora', 'This is the title 6', 'This is the description 6', 'This is the hint', '{"I1", "I2"}', '{"O1", "O2"}');


# --- !Downs

drop table if exists "PROBLEMS";

