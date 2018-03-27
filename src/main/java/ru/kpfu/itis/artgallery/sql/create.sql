create table exhibit
(
  id bigserial not null
    constraint exhibit_pkey
    primary key,
  audio_path varchar(50),
  content varchar(1000),
  count_votes integer default 0,
  name varchar(50),
  sum_votes integer default 0,
  ts timestamp default now(),
  video_path varchar(50),
  author_id bigint not null,
  exposition_id bigint not null
)
;

create table exposition
(
  id bigserial not null
    constraint exposition_pkey
    primary key,
  description varchar(255),
  finish timestamp default now(),
  name varchar(50),
  price integer default 0,
  start timestamp default now()
)
;

alter table exhibit
  add constraint fkvog45037g7jua6qk7787xn4d
foreign key (exposition_id) references exposition
;

create table file
(
  id bigserial not null
    constraint file_pkey
    primary key,
  origin_name varchar(50),
  path varchar(50),
  exhibit_id bigint not null
    constraint fk4owr4l4owvx0o2sq76v4lqmay
    references exhibit,
  message_id bigint not null,
  ticket_id bigint not null
)
;

create table image
(
  id bigserial not null
    constraint image_pkey
    primary key,
  origin_name varchar(50),
  path varchar(50),
  exhibit_id bigint not null
    constraint fkm0gumodbbt4m5dm9mc2ovoiev
    references exhibit
)
;

create table news
(
  id bigserial not null
    constraint news_pkey
    primary key,
  content varchar(3000),
  preview varchar(255),
  ts timestamp default now(),
  author_id bigint not null
)
;

create table post
(
  id bigserial not null
    constraint post_pkey
    primary key,
  content varchar(255),
  ts timestamp default now(),
  topic_id bigint not null,
  author_id bigint not null
)
;

create table private_message
(
  id bigserial not null
    constraint private_message_pkey
    primary key,
  content varchar(500),
  ts timestamp default now(),
  recipient_id bigint not null,
  sender_id bigint not null
)
;

alter table file
  add constraint fk85bjquknj3wk5wdriforljrhe
foreign key (message_id) references private_message
;

create table ticket
(
  id bigserial not null
    constraint ticket_pkey
    primary key,
  content varchar(255),
  header varchar(255),
  ticket_status varchar(255),
  ts timestamp default now(),
  recipient_id bigint not null
)
;

alter table file
  add constraint fkg01s4qlvr0bwp9a66ov1kykte
foreign key (ticket_id) references ticket
;

create table topic
(
  id bigserial not null
    constraint topic_pkey
    primary key,
  name varchar(255)
)
;

alter table post
  add constraint fkk0ab8mjj94yfpyebhmy5kydc9
foreign key (topic_id) references topic
;

create table user_exposition
(
  user_id bigint not null,
  exposition_id bigint not null
    constraint fka6lmq0142of5kwukjb5fyr15t
    references exposition,
  constraint user_exposition_pkey
  primary key (user_id, exposition_id)
)
;

create table users
(
  id bigserial not null
    constraint users_pkey
    primary key,
  avatar_path varchar(255),
  email varchar(50) not null
    constraint uk_6dotkott2kjsp8vw4d0m25fb7
    unique,
  name varchar(50) not null,
  password varchar(255) not null,
  role varchar(255),
  username varchar(50)
)
;

alter table exhibit
  add constraint fkg47tygno2rb4sk8e3jqewo8cw
foreign key (author_id) references users
;

alter table news
  add constraint fkbe42a6fnxcv7xbq2sd33ulmm5
foreign key (author_id) references users
;

alter table post
  add constraint fkn1eg4afbov769ple6qsvmbe4n
foreign key (author_id) references users
;

alter table private_message
  add constraint fk3mjc5gwe04kt18bfek8rpi8w
foreign key (recipient_id) references users
;

alter table private_message
  add constraint fkf6nbmipk0d9vln6rpcml7x883
foreign key (sender_id) references users
;

alter table ticket
  add constraint fkarcj4n5nxpmgv0u5r3irlaef5
foreign key (recipient_id) references users
;

alter table user_exposition
  add constraint fk7f9s0y2klrbwh9mvg83bvme2s
foreign key (user_id) references users
;

