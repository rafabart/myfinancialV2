-- public.users definition

-- Drop table

-- DROP TABLE public.users;

create TABLE public.users (
	id int8 NOT NULL,
	created_at timestamp NULL,
	update_at timestamp NULL,
	email varchar(255) NOT NULL,
	"name" varchar(80) NOT NULL,
	"password" varchar(60) NOT NULL,
	CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);


-- public.category definition

-- Drop table

-- DROP TABLE public.category;

create TABLE public.category (
	id int8 NOT NULL,
	created_at timestamp NULL,
	update_at timestamp NULL,
	"name" varchar(40) NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id),
	CONSTRAINT fk7ffrpnxaflomhdh0qfk2jcndo FOREIGN KEY (user_id) REFERENCES users(id)
);


-- public.expense definition

-- Drop table

-- DROP TABLE public.expense;

create TABLE public.expense (
	id int8 NOT NULL,
	created_at timestamp NULL,
	update_at timestamp NULL,
	description varchar(100) NOT NULL,
	due_date date NULL,
	expense_type int4 NOT NULL,
	payment_date date NULL,
	value float8 NOT NULL,
	category_id int8 NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT expense_pkey PRIMARY KEY (id),
	CONSTRAINT fkekyts7i8w5cam119wj1itdom2 FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT fkmvjm59reb5i075vu38bwcaqj6 FOREIGN KEY (category_id) REFERENCES category(id)
);


-- public.user_profile_list definition

-- Drop table

-- DROP TABLE public.user_profile_list;

create TABLE public.user_profile_list (
	user_id int8 NOT NULL,
	profile_list int4 NULL,
	CONSTRAINT fkisn0eyxbbyq84upkg5smdbdkp FOREIGN KEY (user_id) REFERENCES users(id)
);