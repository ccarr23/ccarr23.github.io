Drop Database if exists masterBlog;
Create Database masterBlog;

Use masterBlog;

create table `user`(
`id` int primary key auto_increment,
`username` varchar(30) not null unique,
`password` varchar(100) not null,
`enabled` boolean not null);

create table `role`(
`id` int primary key auto_increment,
`role` varchar(30) not null
);

create table `user_role`(
`user_id` int not null,
`role_id` int not null,
primary key(`user_id`,`role_id`),
foreign key (`user_id`) references `user`(`id`),
foreign key (`role_id`) references `role`(`id`));

Create Table Catagory(
catagoryId int primary key auto_increment,
`description` varchar(50)
);

Create Table Post(
postId int primary key auto_increment,
`username` varChar(30) not null,
title varchar(50) not null,
catagoryId int not null,
FOREIGN KEY fk_Catagory_catagoryId(catagoryId) REFERENCES Catagory(catagoryId),
posting varchar(500) not null,
postDate datetime not null,
isApproved tinyint not null,
isStatic tinyint not null,
imagePath varChar(200)
);

Create Table Tags(
tagId int primary key auto_increment,
tagName varChar(50)
);

Create Table PostTags(
ptId int primary key auto_increment,
postId int not null,
tagId int not null,
FOREIGN KEY fk_Tags_tagId(tagId) REFERENCES Tags(tagId),
FOREIGN KEY fk_Post_postId(postId) REFERENCES Post(postId)
);

Create Table DisplayBlogPost(
displayId int primary key auto_increment,
postId int not null,
FOREIGN KEY fk_Post_postId(postId) REFERENCES Post(postId)
);



insert into `user`(`id`,`username`,`password`,`enabled`)
    values(1,"admin", "password", true),
          (2,"user","password",true);

insert into `role`(`id`,`role`)
    values(1,"ROLE_ADMIN"), (2,"ROLE_USER"), (3, "ROLE_EMPLOYEE");
    
insert into `user_role`(`user_id`,`role_id`)
    values(1,1),(1,2),(2,2),(2,3);
    
Insert into Catagory(catagoryId, `description`)
Values(1, "Test"),
(2, "Test Two");
    
UPDATE user SET password = '$2a$10$MUPsKnFzaQpc7nJ.GSHIGO2B.gPMsaefADcz48eSeAizrUsm5XI1G' WHERE id = 1;
UPDATE user SET password = '$2a$10$MUPsKnFzaQpc7nJ.GSHIGO2B.gPMsaefADcz48eSeAizrUsm5XI1G' WHERE id = 2;




