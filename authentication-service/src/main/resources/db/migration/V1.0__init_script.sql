create table if not exists flyway_schema_history
(
  installed_rank integer                 not null
    constraint flyway_schema_history_pk
      primary key,
  version        varchar(50),
  description    varchar(200)            not null,
  type           varchar(20)             not null,
  script         varchar(1000)           not null,
  checksum       integer,
  installed_by   varchar(100)            not null,
  installed_on   timestamp default now() not null,
  execution_time integer                 not null,
  success        boolean                 not null
);

alter table flyway_schema_history
  owner to auto_park_db_user;

create index if not exists flyway_schema_history_s_idx
  on flyway_schema_history (success);

create table if not exists user_accounts
(
  id                         uuid                     not null
    constraint user_accounts_pkey
      primary key,
  creation_time              timestamp with time zone not null,
  email                      varchar(100)             not null
    constraint user_accounts_email_key
      unique,
  first_name                 varchar(100)             not null,
  last_name                  varchar(100)             not null,
  modification_time          timestamp with time zone not null,
  password                   varchar(255),
  role                       varchar(20)              not null,
  sign_in_provider           varchar(20),
  version                    bigint                   not null,
  is_account_non_locked      boolean default true     not null,
  is_account_non_expired     boolean default true     not null,
  is_credentials_non_expired boolean default true     not null,
  is_enabled                 boolean default false    not null,
  verification_code          bigint
);

alter table user_accounts
  owner to auto_park_db_user;

create unique index if not exists user_accounts_verification_code_uindex
  on user_accounts (verification_code);

create table if not exists oauth_client_details
(
  client_id               varchar(256) not null
    constraint oauth_client_details_pkey
      primary key,
  resource_ids            varchar(256),
  client_secret           varchar(256),
  scope                   varchar(256),
  authorized_grant_types  varchar(256),
  web_server_redirect_uri varchar(256),
  authorities             varchar(256),
  access_token_validity   integer,
  refresh_token_validity  integer,
  additional_information  varchar(4096),
  autoapprove             varchar(256)
);

alter table oauth_client_details
  owner to auto_park_db_user;

create table if not exists oauth_client_token
(
  token_id          varchar(256),
  token             bytea,
  authentication_id varchar(256) not null
    constraint oauth_client_token_pkey
      primary key,
  user_name         varchar(256),
  client_id         varchar(256)
);

alter table oauth_client_token
  owner to auto_park_db_user;

create table if not exists oauth_access_token
(
  token_id          varchar(256),
  token             bytea,
  authentication_id varchar(256) not null
    constraint oauth_access_token_pkey
      primary key,
  user_name         varchar(256),
  client_id         varchar(256),
  authentication    bytea,
  refresh_token     varchar(256)
);

alter table oauth_access_token
  owner to auto_park_db_user;

create table if not exists oauth_refresh_token
(
  token_id       varchar(256),
  token          bytea,
  authentication bytea
);

alter table oauth_refresh_token
  owner to auto_park_db_user;

create table if not exists oauth_code
(
  code           varchar(256),
  authentication bytea
);

alter table oauth_code
  owner to auto_park_db_user;

create table if not exists oauth_approvals
(
  userid         varchar(256),
  clientid       varchar(256),
  scope          varchar(256),
  status         varchar(10),
  expiresat      timestamp,
  lastmodifiedat timestamp
);

alter table oauth_approvals
  owner to auto_park_db_user;

create table if not exists clientdetails
(
  appid                  varchar(256) not null
    constraint clientdetails_pkey
      primary key,
  resourceids            varchar(256),
  appsecret              varchar(256),
  scope                  varchar(256),
  granttypes             varchar(256),
  redirecturl            varchar(256),
  authorities            varchar(256),
  access_token_validity  integer,
  refresh_token_validity integer,
  additionalinformation  varchar(4096),
  autoapprovescopes      varchar(256)
);

alter table clientdetails
  owner to auto_park_db_user;

create table if not exists route
(
  id                uuid                     not null
    constraint table_name_pk
      primary key,
  creation_time     timestamp with time zone not null,
  modification_time timestamp with time zone not null,
  name              varchar(256)             not null,
  description       text,
  route_number      integer                  not null,
  version           int
);

alter table route
  owner to auto_park_db_user;

create table if not exists transport
(
  id                uuid                     not null
    constraint transport_pk
      primary key,
  creation_time     timestamp with time zone not null,
  modification_time timestamp with time zone not null,
  name              varchar(256)             not null,
  number_plate      varchar(128)             not null,
  transport_type    varchar(128)             not null,
  route_id          uuid
    constraint transport_route_id_fk
      references route,
  user_id           uuid
    constraint transport_user_accounts_id_fk
      references user_accounts,
  version           integer                  not null
);

alter table transport
  owner to auto_park_db_user;

create unique index if not exists transport_transport_id_uindex
  on transport (id);
alter table user_accounts
  add route_id uuid;
alter table user_accounts
  add constraint user_accounts_route_id_fk
    foreign key (route_id) references route;



INSERT INTO public.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types,
                                         web_server_redirect_uri, authorities, access_token_validity,
                                         refresh_token_validity, additional_information, autoapprove)
VALUES ('cloud-auto-park-service', 'cloud-auto-park-service',
        '$2a$12$oB6/2nGyo2fyjk0COURITun1HYbH8M/TkSeD2AM1jDPynMStFsXSK', 'read,write',
        'client_credentials,refresh_token', '', 'ROLE_DRIVER, ROLE_MANAGER', 12500, 36000, null, null);

INSERT INTO public.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types,
                                         web_server_redirect_uri, authorities, access_token_validity,
                                         refresh_token_validity, additional_information, autoapprove)
VALUES ('cloud-auto-park-service-password-grant-client', 'cloud-auto-park-service-password-grant-client',
        '$2a$12$EgJaPtPAt/Yyr5VVKoyO9uj7Ep.HjdDpaVyLuknHcN9cAVgsFz5Q2', 'read,write',
        'password,refresh_token', '', '', null, null, null, null);


INSERT INTO public.user_accounts (id, creation_time, email, first_name, last_name, modification_time, password, role,
                                  sign_in_provider, version, is_account_non_locked, is_account_non_expired,
                                  is_credentials_non_expired, is_enabled, verification_code, route_id)
VALUES ('c6d39e0f-4c76-11e9-b85c-c5df26c8028a', '2019-03-22 09:47:43.712000', 'derevets.artem@gmail.com', 'artem',
        'derevets', '2019-03-22 10:14:50.649000', '$2a$12$pDxLJJdCg5iWursiQcAzW.lJm02i4w8Wdyj6plWynTyDDHWNWIp4i',
        'ROLE_DRIVER', null, 1, true, true, true, true, null, null);


