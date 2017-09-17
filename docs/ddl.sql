create table author
(
	id bigint auto_increment
		primary key,
	nick_name varchar(30) null,
	birth_date date not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	version bigint not null
)
engine=InnoDB
;

create table book
(
	id bigint auto_increment
		primary key,
	genre varchar(255) null,
	title varchar(50) not null,
	version bigint not null,
	book_year int null,
	library_id bigint null,
	constraint FKaojxagnfmppd09k35kye5eph5
		foreign key (library_id) references library.library (id)
)
engine=InnoDB
;

create index FKaojxagnfmppd09k35kye5eph5
	on book (library_id)
;

create table book_author
(
	book_id bigint not null,
	author_id bigint not null,
	primary key (book_id, author_id),
	constraint FKhwgu59n9o80xv75plf9ggj7xn
		foreign key (book_id) references library.book (id),
	constraint FKbjqhp85wjv8vpr0beygh6jsgo
		foreign key (author_id) references library.author (id)
)
engine=InnoDB
;

create index FKbjqhp85wjv8vpr0beygh6jsgo
	on book_author (author_id)
;

create table book_review
(
	id bigint auto_increment
		primary key,
	content longtext not null,
	book_fk bigint not null,
	constraint FKbhbtcjd2qiff77tkbkny9auu3
		foreign key (book_fk) references library.book (id)
)
engine=InnoDB
;

create index FKbhbtcjd2qiff77tkbkny9auu3
	on book_review (book_fk)
;

create table library
(
	id bigint auto_increment
		primary key,
	name varchar(50) not null
)
engine=InnoDB
;

