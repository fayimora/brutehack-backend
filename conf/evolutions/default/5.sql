# --- !Ups

create table access_tokens (
  "token" VARCHAR(254) NOT NULL,
  "refresh_token" VARCHAR(254) NOT NULL,
  "client_id" VARCHAR(254) NOT NULL,
  "user_id" BIGINT NOT NULL,
  "scope" VARCHAR(254) NOT NULL,
  "expires_in" BIGINT NOT NULL,
  "id" BIGSERIAL NOT NULL PRIMARY KEY,
  "created_at" timestamp default current_timestamp,
  "updated_at" timestamp default current_timestamp
);

create table auth_codes (
  "authorization_code" VARCHAR(254) NOT NULL PRIMARY KEY,
  "user_id" BIGINT NOT NULL,
  "redirect_uri" VARCHAR(254),
  "scope" VARCHAR(254),
  "client_id" VARCHAR(254),
  "expires_in" INTEGER NOT NULL,
  "created_at" TIMESTAMP,
  "updated_at" TIMESTAMP
);

# --- !Downs

drop table if exists access_tokens;
drop table if exists auth_codes;

