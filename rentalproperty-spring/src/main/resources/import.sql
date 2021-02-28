-- USERS
insert into USERS(email)
values ('admin@test.com');
insert into USERS(email)
values ('manager@test.com');
insert into USERS(email)
values ('user@test.com');

insert into privileges(name)
values ('NOTIFICATION_CREATE'),
       ('NOTIFICATION_READ'),
       ('NOTIFICATION_DELETE'),
       ('NOTIFICATION_UPDATE'),
       ('ROLE_CREATE'),
       ('ROLE_READ'),
       ('ROLE_DELETE'),
       ('ROLE_UPDATE'),
       ('USER_CREATE'),
       ('USER_READ'),
       ('USER_DELETE'),
       ('USER_UPDATE'),
       ('CONTENT_CREATE'),
       ('CONTENT_READ'),
       ('CONTENT_DELETE'),
       ('CONTENT_UPDATE');

insert into roles(name)
values ('ADMIN'),
       ('MANAGER'),
       ('USER');

-- ROLE ADMIN
insert into roles_privileges(role_id, privilege_id)
values ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'NOTIFICATION_CREATE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'NOTIFICATION_READ')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'NOTIFICATION_DELETE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'NOTIFICATION_UPDATE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'ROLE_READ')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'ROLE_CREATE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'ROLE_DELETE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'ROLE_UPDATE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'USER_READ')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'USER_CREATE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'USER_DELETE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'USER_UPDATE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'CONTENT_CREATE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'CONTENT_READ')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'CONTENT_DELETE')),
       ((select id from roles where name = 'ADMIN'), (select id from privileges where name = 'CONTENT_UPDATE'));

-- ROLE MANAGER
insert into roles_privileges(role_id, privilege_id)
values ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'NOTIFICATION_CREATE')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'NOTIFICATION_READ')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'NOTIFICATION_DELETE')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'NOTIFICATION_UPDATE')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'ROLE_READ')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'ROLE_CREATE')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'USER_READ')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'USER_UPDATE')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'CONTENT_CREATE')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'CONTENT_READ')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'CONTENT_DELETE')),
       ((select id from roles where name = 'MANAGER'), (select id from privileges where name = 'CONTENT_UPDATE'));

-- ROLE USER
insert into roles_privileges(role_id, privilege_id)
values ((select id from roles where name = 'USER'), (select id from privileges where name = 'NOTIFICATION_READ')),
       ((select id from roles where name = 'USER'), (select id from privileges where name = 'ROLE_READ')),
       ((select id from roles where name = 'USER'), (select id from privileges where name = 'USER_READ')),
       ((select id from roles where name = 'USER'), (select id from privileges where name = 'USER_UPDATE')),
       ((select id from roles where name = 'USER'), (select id from privileges where name = 'CONTENT_READ'));

insert into users_roles(user_id, role_id)
values ((select id from users where email = 'admin@test.com'), (select id from roles where name = 'ADMIN')),
       ((select id from users where email = 'manager@test.com'), (select id from roles where name = 'MANAGER')),
       ((select id from users where email = 'user@test.com'), (select id from roles where name = 'USER'));

