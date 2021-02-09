--liquibase formatted sql

--changeset zundarik:v1.0-create-sequence-privileges
create sequence privileges_id_seq
    start with 1
    increment by 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
--rollback drop sequence privileges_id_seq;

--changeset zundarik:v1.0-create-sequence-profiles
create sequence profiles_id_seq
    start with 1
    increment by 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
--rollback drop sequence profiles_id_seq;

--changeset zundarik:v1.0-create-sequence-roles
create sequence roles_id_seq
    start with 1
    increment by 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
--rollback drop sequence roles_id_seq;

--changeset zundarik:v1.0-create-sequence-users
create sequence users_id_seq
    start with 1
    increment by 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
--rollback drop sequence users_id_seq;
