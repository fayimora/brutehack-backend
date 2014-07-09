# --- !Ups

create table contests_problems (
  "id" serial not null primary key,
  "contest_id" bigint not null,
  "problem_id" bigint not null
);
alter table contests_problems add constraint problem_fk foreign key(problem_id) references problems(problem_id) on update no action on delete no action;
alter table contests_problems add constraint contest_fk foreign key(contest_id) references contests(contest_id) on update no action on delete no action;

insert into contests_problems (contest_id, problem_id) values (1, 1);
insert into contests_problems (contest_id, problem_id) values (1, 2);
insert into contests_problems (contest_id, problem_id) values (1, 3);
insert into contests_problems (contest_id, problem_id) values (2, 4);
insert into contests_problems (contest_id, problem_id) values (2, 5);
insert into contests_problems (contest_id, problem_id) values (3, 6);


# --- !Downs

drop table if exists contests_problems;

