INSERT INTO User_Details (id, name, BIRTH_DATE) VALUES (101, 'Alice', current_date);
INSERT INTO User_Details (id, name, BIRTH_DATE) VALUES (102, 'Bob', current_date);
INSERT INTO User_Details (id, name, BIRTH_DATE) VALUES (103, 'Charlie', current_date);

insert into post (id, description,user_id) values (201,  'This is the content of the first post', 101);
insert into post (id, description,user_id) values (202,  'This is the content of the second post', 101);
insert into post (id, description,user_id) values (203,  'This is the content of the first post', 102);
insert into post (id, description,user_id) values (204,  'This is the content of the second post', 102);