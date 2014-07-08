# --- !Ups

create or replace function update_timestamp() returns trigger as $$
begin
   NEW.UPDATED_AT = now();;
   return NEW;;
end;;
$$ language 'plpgsql';

create trigger contests_timestamp_update BEFORE update on "CONTESTS" for each row execute procedure update_timestamp();

create trigger problems_timestamp_update before update on "PROBLEMS" for each row execute procedure update_timestamp();

create trigger users_timestamp_update before update on "USERS" for each row execute procedure update_timestamp();


# --- !Downs

drop function update_timestamp() CASCADE;

drop trigger contests_timestamp_update on "CONTESTS";

drop trigger problems_timestamp_update on "PROBLEMS";

drop trigger users_timestamp_update on "USERS";

