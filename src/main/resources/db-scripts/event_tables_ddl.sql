

CREATE TABLE public.event_bk2 (
	id varchar(256) NULL,
	"source" varchar(25) NULL,
	display_name text NULL,
	url text NULL,
	address1 varchar(200) NULL,
	address2 varchar(100) NULL,
	city varchar(100) NULL,
	state varchar(200) NULL,
	zip_code varchar(10) NULL,
	country varchar(100) NULL,
	latitude numeric(20,7) NULL,
	longitude numeric(20,7) NULL,
	start_time timestamptz NULL,
	end_time timestamptz NULL,
	price int4 NULL,
	deal_id varchar(30) NULL,
	image_url text NULL,
	category_name text NULL
);



INSERT INTO public."event"
(id, "source", display_name, url, address1, address2, city, state, zip_code, country, latitude, longitude, 
start_time, end_time, price, deal_id, image_url, category_name)
	select  eb_id,'EventBrite', eb_name || '@(' ||venue_name || ')' ,eb_url,address1,address2, city,state,country,zip_code,
	CAST(coalesce(NULLIF(latitude, ''), '0') AS numeric),CAST(coalesce(NULLIF(longitude, ''), '0') AS numeric),event_start_time at time zone  event_timezone,
	event_end_time at time zone  event_timezone, CAST( CASE  when is_free = true THEN 0 END AS integer) as price,null,eb_image_url,category_id
	from eb_event;

	DO
$$
declare
category_name_buff varchar(100);
category_name_to_update varchar(100);
BEGIN
 FOR category_name_buff in select category_name from public."event" loop
      select category_name into category_name_to_update from public.eb_categories where category_id = CAST(category_name_buff AS integer);
      update public."event" set category_name = category_name_to_update where category_name = category_name_buff;
     
 END LOOP;
END
$$



-----

SELECT count(1) 
FROM public.eb_event;
select * from yelp_category;
select * from eb_categories;

select * from eb_event_bk where eb_id = 52360227851;


             
/*
Activity_Id  -pk

*/
INSERT INTO public."event"
(id, "source", display_name, url, address1, address2, city, state, zip_code, country, latitude, longitude, 
start_time, end_time, price, deal_id, image_url, category_name)
	select  eb_id,'EventBrite', eb_name || '@(' ||venue_name || ')' ,eb_url,address1,address2, city,state,country,zip_code,
	CAST(coalesce(NULLIF(latitude, ''), '0') AS numeric),CAST(coalesce(NULLIF(longitude, ''), '0') AS numeric),event_start_time at time zone  event_timezone,
	event_end_time at time zone  event_timezone, CAST( CASE  when is_free = true THEN 0 END AS integer) as price,null,eb_image_url,category_id
	from eb_event;
select  version();

create table event_bk as select * from "event";

DO
$$
declare
category_name_buff varchar(100);
category_name_to_update varchar(100);
BEGIN
 FOR category_name_buff in select category_name from public."event" loop
      select category_name into category_name_to_update from public.eb_categories where category_id = CAST(category_name_buff AS integer);
      update public."event" set category_name = category_name_to_update where category_name = category_name_buff;
     
 END LOOP;
END
$$



INSERT INTO event_bk
(id, "source", display_name, url, address1, address2, city, state, zip_code, country, latitude, longitude, 
start_time, end_time, price, deal_id, image_url, category_name)
	select  eb_id,'EventBrite', eb_name || '@(' ||venue_name || ')' ,eb_url,address1,address2, city,state,country,zip_code,
	CAST(coalesce(NULLIF(latitude, ''), '0') AS numeric),CAST(coalesce(NULLIF(longitude, ''), '0') AS numeric),event_start_time at time zone  event_timezone,
	event_end_time at time zone  event_timezone, CAST( CASE  when is_free = true THEN 0 END AS integer) as price,null,eb_image_url,category_id
	from eb_event;



CREATE TABLE public.event_bk2 (
	id varchar(256) NULL,
	"source" varchar(25) NULL,
	display_name text NULL,
	url text NULL,
	address1 varchar(200) NULL,
	address2 varchar(100) NULL,
	city varchar(100) NULL,
	state varchar(200) NULL,
	zip_code varchar(10) NULL,
	country varchar(100) NULL,
	latitude numeric(20,7) NULL,
	longitude numeric(20,7) NULL,
	start_time timestamptz NULL,
	end_time timestamptz NULL,
	price int4 NULL,
	deal_id varchar(30) NULL,
	image_url text NULL,
	category_name text NULL
);
INSERT INTO event_bk2
(id, "source", display_name, url, address1, address2, city, state, zip_code, country, latitude, longitude, 
start_time, end_time, price, deal_id, image_url, category_name)
	select  eb_id,'EventBrite', eb_name || '@(' ||venue_name || ')' ,eb_url,address1,address2, city,state,country,zip_code,
	CAST(coalesce(NULLIF(latitude, ''), '0') AS numeric),CAST(coalesce(NULLIF(longitude, ''), '0') AS numeric),event_start_time at time zone  event_timezone,
	event_end_time at time zone  event_timezone, CAST( CASE  when is_free = true THEN 0 END AS integer) as price,null,eb_image_url,category_id
	from eb_event;


DO
$$
declare
category_name_buff varchar(100);
category_name_to_update varchar(100);
BEGIN
 FOR category_name_buff in select category_name from public."event" loop
      select category_name into category_name_to_update from public.eb_categories where category_id = CAST(category_name_buff AS integer);
      update public."event" set category_name = category_name_to_update where category_name = category_name_buff;
     
 END LOOP;
END
$$

----------


ALTER TABLE public.yelp_event_with_categories ADD concat_category_name text NULL;



DO
$$
declare
yelp_id_buff varchar(100);
category_name_buff text;
category_name_to_update varchar(100);
begin
	for yelp_id_buff,category_name_buff in
select distinct yelp_id,string_agg(upper(trim(category_name))::text, ',') ||',' || string_agg(upper(trim(category_display_name))::text, ',') cat_tot
from yelp_event_with_categories group by yelp_id
loop
--raise notice 'Integration Id for input customer: %',category_name_buff;
update yelp_event_with_categories set concat_category_name = category_name_buff where yelp_id = yelp_id_buff;

end loop;
END
$$



INSERT INTO public."event"
(id, "source", display_name, url, address1, address2, city, state, zip_code, country, latitude, longitude, 
start_time, end_time, price, deal_id, image_url, category_name)
select yelp_id,'Yelp',name,yelp_url,address1,address2, city,state,country,zip_code,
latitude,longitude,null,null,price,null,image_url,null
from yelp_event;
	select  eb_id,'Yelp', eb_name || '@(' ||venue_name || ')' ,eb_url,address1,address2, city,state,country,zip_code,
	CAST(coalesce(NULLIF(latitude, ''), '0') AS numeric),CAST(coalesce(NULLIF(longitude, ''), '0') AS numeric),event_start_time at time zone  event_timezone,
	event_end_time at time zone  event_timezone, CAST( CASE  when is_free = true THEN 0 END AS integer) as price,null,eb_image_url,category_id
	from eb_event;