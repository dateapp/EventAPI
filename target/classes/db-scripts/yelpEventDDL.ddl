CREATE TABLE yelp_event (
yelp_id varchar(256),
name varchar NOT NULL,
image_url varchar(256),
yelp_url varchar NOT NULL,
rating NUMERIC,
latitude DECIMAL(20,7),
longitude DECIMAL(20,7),
price INT,
address1 varchar,
address2 varchar,
address3 varchar,
city varchar,
zip_code varchar(5),
country varchar(10),
state varchar(10),
display_address varchar(256),
phone varchar(25),
display_phone varchar(25),
review_count INT,
created_date timestamp not null default CURRENT_TIMESTAMP,
primary key(yelp_id)
);

CREATE TABLE category(
yelp_id varchar NOT NULL,
category_name varchar NOT NULL,
PRIMARY KEY (yelp_id,category_name)
);

CREATE TABLE categoryType(
category_name varchar,
category_display_name varchar, 
PRIMARY KEY (category_name)
);

