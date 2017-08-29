-- Database: cadpp

CREATE DATABASE cadpp
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;



-- Table: departments

CREATE TABLE departments
(
  id bigint NOT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT departments_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE departments
  OWNER TO postgres;



-- Table: roles

CREATE TABLE roles
(
  name character varying(255) NOT NULL,
  CONSTRAINT roles_pkey PRIMARY KEY (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE roles
  OWNER TO postgres;



-- Table: users

CREATE TABLE users
(
  id bigint NOT NULL, -- identity of user
  name character varying(255) NOT NULL, -- User's name
  surname character varying(255) NOT NULL, -- User's surname
  login character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT users_login_key UNIQUE (login)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;
COMMENT ON COLUMN users.id IS 'identity of user';
COMMENT ON COLUMN users.name IS 'User''s name';
COMMENT ON COLUMN users.surname IS 'User''s surname';



-- Table: user_roles

CREATE TABLE users_roles
(
  user_id bigint NOT NULL,
  role_id character varying(255) NOT NULL,
  CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES roles (name) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users_roles
  OWNER TO postgres;



-- Table: users_departments

CREATE TABLE users_departments
(
  user_id bigint NOT NULL,
  departments_id bigint NOT NULL,
  CONSTRAINT users_departments_pkey PRIMARY KEY (user_id, departments_id),
  CONSTRAINT users_departments_departments_id_fkey FOREIGN KEY (departments_id)
      REFERENCES departments (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT users_departments_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users_departments
  OWNER TO postgres;
  
