INSERT  into usr_status_tbl(user_status) values ('Vip');

INSERT  into usr_tbl (login, password, user_name, user_last_name, user_patronymic, user_date, user_family_status, user_inn, education_enum,status_id)
values ('admin', '$2a$08$I8Qk8j.02z7mB3Fmuhx8oukSXp.smln9fT7rhvmZoRMTvDyLLftHu', 'TestName', 'TestLastName', 'TestPatronymic', '1991-01-30', true, 12345678,'HIGHER', 1 );

insert into user_role (user_id, roles)
values (1, 'USER'),(1, 'ADMIN');