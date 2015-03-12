# --- !Ups

create table access_tokens (
  "token" VARCHAR(254) NOT NULL,
  "refresh_token" VARCHAR(254) NOT NULL,
  "client_id" VARCHAR(254),
  "user_id" BIGINT NOT NULL,
  "scope" VARCHAR(254),
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
  "created_at" timestamp default current_timestamp,
  "updated_at" timestamp default current_timestamp
);

create table clients (
  "client_id" BIGINT NOT NULL PRIMARY KEY,
  "username" VARCHAR(254) NOT NULL,
  "client_secret" VARCHAR(254) NOT NULL,
  "description" VARCHAR(254) NOT NULL,
  "redirect_uri" VARCHAR(254) NOT NULL,
  "scope" VARCHAR(254) NOT NULL,
  "created_at" timestamp default current_timestamp,
  "updated_at" timestamp default current_timestamp
);

create table grant_types (
  "grant_type" VARCHAR(254) NOT NULL,
  "id" BIGINT NOT NULL PRIMARY KEY
);

create table client_grant_types (
  "client_id" VARCHAR(254) NOT NULL,
  "grant_type_id" BIGINT NOT NULL
);
alter table client_grant_types add constraint "pk_client_grant_type" primary key("client_id", "grant_type_id");

create table confirmation_tokens (
  "uuid" UUID NOT NULL,
  "email" VARCHAR(254) NOT NULL,
  "expiration_time" TIMESTAMP NOT NULL,
  "is_sign_up" BOOLEAN NOT NULL,
  "id" BIGSERIAL NOT NULL PRIMARY KEY,
  "created_at" timestamp default current_timestamp,
  "updated_at" timestamp default current_timestamp
);


# --- !Downs

drop table if exists access_tokens;
drop table if exists auth_codes;
drop table if exists clients;
drop table if exists grant_types;
drop table if exists confirmation_tokens;

