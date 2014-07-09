# --- !Ups

create table contests (
  "id" serial not null primary key,
  "created_at" timestamp default current_timestamp,
  "updated_at" timestamp default current_timestamp,
  "title" varchar(254) not null unique,
  "author" varchar(254) not null,
  "description" text not null,
  "start_time" timestamp not null,
  "duration" varchar(254) not null
);
create unique index contest_title on contests (title);

insert into contests ("created_at","updated_at","title","author","description","start_time","duration") values ('2014-01-06 08:00:54.177', '2014-07-06 00:24:54.177', 'Goodbye 2013', 'Fayi QLL', 'This is the description', '2014-07-06 00:24:54.177', '75mins');
insert into contests ("created_at","updated_at","title","author","description","start_time","duration") values ('2014-02-10 08:00:54.177', '2014-07-06 00:24:54.177', 'Welcome 2014', 'Fayi QLL', 'This is the description', '2014-07-06 00:24:54.177', '75mins');
insert into contests ("created_at","updated_at","title","author","description","start_time","duration") values ('2014-03-03 08:00:54.177', '2014-07-06 00:24:54.177', 'Sprint #101', 'Fayi QLL', 'This is the description', '2014-07-06 00:24:54.177', '60mins');


# --- !Downs

drop table if exists contests;

