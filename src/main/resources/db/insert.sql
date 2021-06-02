insert into posts (name)
values ('О чем этот форум?');
insert into posts (name)
values ('Правила форума.');

insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$3UEZ72AdVDnRl5.oifUuP.LWz12PFU7qGoNe/TPipXqAi2ZlWLtYm',
        (select id from authorities where authority = 'ROLE_ADMIN'));