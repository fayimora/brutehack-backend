# --- !Ups

create table "CONTESTS_PROBLEMS" (
  "ID" SERIAL NOT NULL PRIMARY KEY,
  "CONTEST_ID" BIGINT NOT NULL,
  "PROBLEM_ID" BIGINT NOT NULL
);
alter table "CONTESTS_PROBLEMS" add constraint "PROBLEM_FK" foreign key("PROBLEM_ID") references "PROBLEMS"("PROBLEM_ID") on update NO ACTION on delete NO ACTION;
alter table "CONTESTS_PROBLEMS" add constraint "CONTEST_FK" foreign key("CONTEST_ID") references "CONTESTS"("CONTEST_ID") on update NO ACTION on delete NO ACTION;

insert into "CONTESTS_PROBLEMS" ("CONTEST_ID", "PROBLEM_ID") values (1, 1);
insert into "CONTESTS_PROBLEMS" ("CONTEST_ID", "PROBLEM_ID") values (1, 2);
insert into "CONTESTS_PROBLEMS" ("CONTEST_ID", "PROBLEM_ID") values (1, 3);
insert into "CONTESTS_PROBLEMS" ("CONTEST_ID", "PROBLEM_ID") values (2, 4);
insert into "CONTESTS_PROBLEMS" ("CONTEST_ID", "PROBLEM_ID") values (2, 5);
insert into "CONTESTS_PROBLEMS" ("CONTEST_ID", "PROBLEM_ID") values (3, 6);


# --- !Downs

drop table if exists "CONTESTS_PROBLEMS";

