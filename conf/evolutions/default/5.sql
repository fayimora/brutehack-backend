# --- !Ups

create or replace function update_timestamp() returns trigger as $$
begin
   NEW.UPDATED_AT = now();;
   return NEW;;
end;;
$$ language 'plpgsql';

alter table "CONTESTS" alter column "CREATED_AT" set default CURRENT_TIMESTAMP;
alter table "CONTESTS" alter column "UPDATED_AT" set default CURRENT_TIMESTAMP;
create trigger contests_timestamp_update BEFORE update on "CONTESTS" for each row execute procedure update_timestamp();

alter table "PROBLEMS" alter column "CREATED_AT" set default CURRENT_TIMESTAMP;
alter table "PROBLEMS" alter column "UPDATED_AT" set default CURRENT_TIMESTAMP;
create trigger problems_timestamp_update before update on "PROBLEMS" for each row execute procedure update_timestamp();

alter table "USERS" alter column "CREATED_AT" set default CURRENT_TIMESTAMP;
alter table "USERS" alter column "UPDATED_AT" set default CURRENT_TIMESTAMP;
create trigger users_timestamp_update before update on "USERS" for each row execute procedure update_timestamp();


# --- !Downs

drop function update_timestamp() CASCADE;

alter table "CONTESTS" alter column "CREATED_AT" drop default;
alter table "CONTESTS" alter column "UPDATED_AT" drop default;
drop trigger contests_timestamp_update on "CONTESTS";

alter table "PROBLEMS" alter column "CREATED_AT" drop default;
alter table "PROBLEMS" alter column "UPDATED_AT" drop default;
drop trigger problems_timestamp_update on "PROBLEMS";

alter table "USERS" alter column "CREATED_AT" drop default;
alter table "USERS" alter column "UPDATED_AT" drop default;
drop trigger users_timestamp_update on "USERS";

