CREATE TABLE exhibit
(
  id            BIGINT NOT NULL
    CONSTRAINT exhibit_pkey
    PRIMARY KEY,
  content       VARCHAR(1000),
  count_votes   INTEGER   DEFAULT 0,
  name          VARCHAR(50),
  sum_votes     INTEGER   DEFAULT 0,
  ts            TIMESTAMP DEFAULT now(),
  author_id     BIGINT NOT NULL
    CONSTRAINT fktn87tbcp62j9v6cvswjiudhkm
    REFERENCES users,
  exposition_id BIGINT
    CONSTRAINT fkf2jx0qqjy8s20p7ne2gkcq303
    REFERENCES exposition,
  picture       VARCHAR(255)
);
CREATE TABLE exposition
(
  id          BIGINT NOT NULL
    CONSTRAINT exposition_pkey
    PRIMARY KEY,
  description VARCHAR(1000),
  finish      DATE,
  name        VARCHAR(50),
  price       INTEGER DEFAULT 0,
  start       DATE,
  owner_id    BIGINT
    CONSTRAINT fkkaiuwhc4aq75a5hx2usxxq5xd
    REFERENCES users,
  picture     VARCHAR(255)
);
CREATE TABLE file
(
  id           BIGINT NOT NULL
    CONSTRAINT file_pkey
    PRIMARY KEY,
  content_type VARCHAR(10),
  file         VARCHAR(255),
  name         VARCHAR(255),
  ts           TIMESTAMP DEFAULT now(),
  exhibit_id   BIGINT
    CONSTRAINT fk21bg6ol8iv9u62yqv9xdhkqoe
    REFERENCES exhibit,
  message_id   BIGINT
    CONSTRAINT fko2qvs3ldcyci1rd3jdyjlee59
    REFERENCES private_message,
  ticket_id    BIGINT
    CONSTRAINT fk6vg3be92y4y3tjbbg7r8vlrcd
    REFERENCES ticket,
  user_id      BIGINT NOT NULL
    CONSTRAINT fke70ql3orpo0ghvfmqccv27ng
    REFERENCES users,
  format       VARCHAR(255)
);
CREATE TABLE news
(
  id        BIGINT NOT NULL
    CONSTRAINT news_pkey
    PRIMARY KEY,
  content   VARCHAR(3000),
  header    VARCHAR(255),
  preview   VARCHAR(255),
  ts        TIMESTAMP DEFAULT now(),
  author_id BIGINT NOT NULL
    CONSTRAINT fk3qvva8ftw201mxkeuirniflgb
    REFERENCES users
);
CREATE TABLE persistent_logins
(
  series    VARCHAR(64) NOT NULL
    CONSTRAINT persistent_logins_pkey
    PRIMARY KEY,
  last_used TIMESTAMP DEFAULT now(),
  token     VARCHAR(64) NOT NULL,
  username  VARCHAR(64) NOT NULL
);
CREATE TABLE post
(
  id        BIGSERIAL NOT NULL
    CONSTRAINT post_pkey
    PRIMARY KEY,
  content   VARCHAR(255),
  ts        TIMESTAMP DEFAULT now(),
  topic_id  BIGINT    NOT NULL
    CONSTRAINT fkg8ln3nj8tjclai0nuvpw5s5uw
    REFERENCES topic
    CONSTRAINT fkk0ab8mjj94yfpyebhmy5kydc9
    REFERENCES topic,
  author_id BIGINT    NOT NULL
    CONSTRAINT fk1mpebp1ayl0twrwm7ruiof778
    REFERENCES users
    CONSTRAINT fkn1eg4afbov769ple6qsvmbe4n
    REFERENCES users
);
CREATE TABLE private_message
(
  id           BIGINT NOT NULL
    CONSTRAINT private_message_pkey
    PRIMARY KEY,
  content      VARCHAR(500),
  ts           TIMESTAMP DEFAULT now(),
  recipient_id BIGINT NOT NULL
    CONSTRAINT fk3mjc5gwe04kt18bfek8rpi8w
    REFERENCES users,
  sender_id    BIGINT NOT NULL
    CONSTRAINT fkf6nbmipk0d9vln6rpcml7x883
    REFERENCES users,
  header       VARCHAR(100)
);
CREATE TABLE ticket
(
  id            BIGSERIAL NOT NULL
    CONSTRAINT ticket_pkey
    PRIMARY KEY,
  content       VARCHAR(255),
  header        VARCHAR(255),
  ticket_status VARCHAR(255),
  ts            TIMESTAMP DEFAULT now(),
  recipient_id  BIGINT    NOT NULL
    CONSTRAINT fkarcj4n5nxpmgv0u5r3irlaef5
    REFERENCES users
    CONSTRAINT fkdklqrqprcbj3lfaqm3dsk4t7q
    REFERENCES users,
  answer        VARCHAR(255),
  manager_id    BIGINT
    CONSTRAINT fks6tsffdrhdtbhry6qc21mutqa
    REFERENCES users
);
CREATE TABLE topic
(
  id   BIGSERIAL NOT NULL
    CONSTRAINT topic_pkey
    PRIMARY KEY,
  name VARCHAR(255)
);
CREATE TABLE user_exposition
(
  user_id       BIGINT NOT NULL
    CONSTRAINT fk7f9s0y2klrbwh9mvg83bvme2s
    REFERENCES users,
  exposition_id BIGINT NOT NULL
    CONSTRAINT fktodaolnvkakhkcdm3cc31ytjp
    REFERENCES exposition,
  CONSTRAINT user_exposition_pkey
  PRIMARY KEY (user_id, exposition_id)
);
CREATE TABLE users
(
  id          BIGSERIAL    NOT NULL
    CONSTRAINT users_pkey
    PRIMARY KEY,
  avatar_path VARCHAR(255),
  name        VARCHAR(50)  NOT NULL,
  password    VARCHAR(255) NOT NULL,
  role        VARCHAR(255),
  login       VARCHAR(50)
);

create table if not exists token
(
  id           bigserial not null
    constraint token_pkey
    primary key,
  expired_date bytea,
  value        varchar(255),
  user_id      bigint
    constraint fkj8rfw4x0wjjyibfqq566j4qng
    references users
);

create table userconnection
(
  userid         varchar(255) not null,
  provideruserid varchar(255) not null,
  providerid     varchar(255) not null,
  accesstoken    varchar(512),
  displayname    varchar(255),
  expiretime     bigint,
  imageurl       varchar(512),
  profileurl     varchar(512),
  rank           integer      not null,
  refreshtoken   varchar(512),
  role           integer,
  secret         varchar(512),
  constraint userconnection_pkey
  primary key (userid, provideruserid, providerid)
);


create function update_changetimestamp_column()
  returns trigger
LANGUAGE plpgsql
AS $$
BEGIN
  NEW.last_used = now();
  RETURN NEW;
END;
$$;

