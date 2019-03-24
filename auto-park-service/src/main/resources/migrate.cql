CREATE KEYSPACE application
  WITH REPLICATION = {
  'class' : 'SimpleStrategy',
  'replication_factor' : 1
  };

CREATE TABLE application.user
(
  id                timeuuid,
  email             text,
  first_name        text,
  last_name         text,
  role              text,
  is_enabled        boolean,
  creation_time     timestamp,
  modification_time timestamp,
  version           int,
  PRIMARY KEY ((id), email)
);

CREATE TYPE if not exists application.chat (
  id timeuuid,
  creation_time timestamp,
  modification_time timestamp,
  version int,
  chat_type text,
  participant uuid,
  description text,
  );


CREATE TYPE if not exists application.contact_user (
  id timeuuid,
  email text,
  first_name text,
  last_name text
  );

CREATE TABLE application.chat
(
  id                timeuuid,
  creation_time     timestamp,
  modification_time timestamp,
  version           int,
  chat_type         text,
  participant       uuid,
  description       text,
  user_type_list    frozen<set<contact_user>>,
  primary key ((participant), id, chat_type, user_type_list)
);
CREATE TABLE application.contact
(
  id                timeuuid,
  creation_time     timestamp,
  modification_time timestamp,
  version           int,
  contact_id        uuid,
  user_type_list    frozen<set<contact_user>>,
  primary key ((contact_id), user_type_list, id)
);


CREATE TABLE application.message
(
  id                timeuuid,
  creation_time     timestamp,
  modification_time timestamp,
  version           int,
  message_type      text,
  chatId            uuid,
  message_text      text,
  primary key (chatId, id)
);


