create table portfolio_projects(
	project_id int primary key auto_increment,
    project_name varchar(500),
    project_description varchar(10000),
    project_link varchar(2000),
    project_category varchar(10),
    project_technology varchar(2000),
    date_time timestamp
);

create table portfolio_media(
	media_id int primary key auto_increment,
    media_type varchar(200),
    media_name varchar(600),
    media_belongs_to VARCHAR(50),
    media_belongs_id INT,
    media_data LONGBLOB,
    date_time TIMESTAMP
);

create table portfolio_blogs(
    id int primary key auto_increment,
    title varchar(200),
    tags varchar(2000),
    subcontent varchar(10000),
    content LONGTEXT
);


create table portfolio_users(
    id int primary key auto_increment,
    user_email varchar(200),
    user_name varchar(200),
    user_profile varchar(1000)
)

CREATE TABLE portfolio_reviews(
	id int primary key auto_increment,
	USER_ID int,
    USER_REVIEW VARCHAR(10000),
    DATE_TIME timestamp
)

CREATE TABLE portfolio_review_and_message(
	id int primary key auto_increment,
    content_type varchar(10),
    company_name varchar(200),
    designation varchar(200),
    message varchar(5000),
    user_id int,
    rating int,
    date_time timestamp
);