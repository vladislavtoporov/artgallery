CREATE TABLE exhibit
(
  id            BIGSERIAL NOT NULL
    CONSTRAINT exhibit_pkey
    PRIMARY KEY,
  audio_path    VARCHAR(50),
  content       VARCHAR(1000),
  count_votes   INTEGER   DEFAULT 0,
  name          VARCHAR(50),
  sum_votes     INTEGER   DEFAULT 0,
  ts            TIMESTAMP DEFAULT now(),
  video_path    VARCHAR(50),
  author_id     BIGINT    NOT NULL,
  exposition_id BIGINT    NOT NULL
);

CREATE TABLE exposition
(
  id          BIGSERIAL NOT NULL
    CONSTRAINT exposition_pkey
    PRIMARY KEY,
  description VARCHAR(255),
  finish      TIMESTAMP DEFAULT now(),
  name        VARCHAR(50),
  price       INTEGER   DEFAULT 0,
  start       TIMESTAMP DEFAULT now()
);

ALTER TABLE exhibit
  ADD CONSTRAINT fkvog45037g7jua6qk7787xn4d
FOREIGN KEY (exposition_id) REFERENCES exposition;

CREATE TABLE file
(
  id          BIGSERIAL NOT NULL
    CONSTRAINT file_pkey
    PRIMARY KEY,
  origin_name VARCHAR(50),
  path        VARCHAR(50),
  exhibit_id  BIGINT    NOT NULL
    CONSTRAINT fk4owr4l4owvx0o2sq76v4lqmay
    REFERENCES exhibit,
  message_id  BIGINT    NOT NULL,
  ticket_id   BIGINT    NOT NULL
);

CREATE TABLE file
(
  id          BIGSERIAL NOT NULL
    CONSTRAINT image_pkey
    PRIMARY KEY,
  origin_name VARCHAR(50),
  path        VARCHAR(50),
  exhibit_id  BIGINT    NOT NULL
    CONSTRAINT fkm0gumodbbt4m5dm9mc2ovoiev
    REFERENCES exhibit
);

CREATE TABLE news
(
  id        BIGSERIAL NOT NULL
    CONSTRAINT news_pkey
    PRIMARY KEY,
  content   VARCHAR(3000),
  preview   VARCHAR(255),
  ts        TIMESTAMP DEFAULT now(),
  author_id BIGINT    NOT NULL
);

CREATE TABLE post
(
  id        BIGSERIAL NOT NULL
    CONSTRAINT post_pkey
    PRIMARY KEY,
  content   VARCHAR(255),
  ts        TIMESTAMP DEFAULT now(),
  topic_id  BIGINT    NOT NULL,
  author_id BIGINT    NOT NULL
);

CREATE TABLE private_message
(
  id           BIGSERIAL NOT NULL
    CONSTRAINT private_message_pkey
    PRIMARY KEY,
  content      VARCHAR(500),
  ts           TIMESTAMP DEFAULT now(),
  recipient_id BIGINT    NOT NULL,
  sender_id    BIGINT    NOT NULL
);

ALTER TABLE file
  ADD CONSTRAINT fk85bjquknj3wk5wdriforljrhe
FOREIGN KEY (message_id) REFERENCES private_message;

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
);

ALTER TABLE file
  ADD CONSTRAINT fkg01s4qlvr0bwp9a66ov1kykte
FOREIGN KEY (ticket_id) REFERENCES ticket;

CREATE TABLE topic
(
  id   BIGSERIAL NOT NULL
    CONSTRAINT topic_pkey
    PRIMARY KEY,
  name VARCHAR(255)
);

ALTER TABLE post
  ADD CONSTRAINT fkk0ab8mjj94yfpyebhmy5kydc9
FOREIGN KEY (topic_id) REFERENCES topic;

CREATE TABLE user_exposition
(
  user_id       BIGINT NOT NULL,
  exposition_id BIGINT NOT NULL
    CONSTRAINT fka6lmq0142of5kwukjb5fyr15t
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
  email       VARCHAR(50)  NOT NULL
    CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7
    UNIQUE,
  name        VARCHAR(50)  NOT NULL,
  password    VARCHAR(255) NOT NULL,
  role        VARCHAR(255),
  username    VARCHAR(50)
);

ALTER TABLE exhibit
  ADD CONSTRAINT fkg47tygno2rb4sk8e3jqewo8cw
FOREIGN KEY (author_id) REFERENCES users;

ALTER TABLE news
  ADD CONSTRAINT fkbe42a6fnxcv7xbq2sd33ulmm5
FOREIGN KEY (author_id) REFERENCES users;

ALTER TABLE post
  ADD CONSTRAINT fkn1eg4afbov769ple6qsvmbe4n
FOREIGN KEY (author_id) REFERENCES users;

ALTER TABLE private_message
  ADD CONSTRAINT fk3mjc5gwe04kt18bfek8rpi8w
FOREIGN KEY (recipient_id) REFERENCES users;

ALTER TABLE private_message
  ADD CONSTRAINT fkf6nbmipk0d9vln6rpcml7x883
FOREIGN KEY (sender_id) REFERENCES users;

ALTER TABLE ticket
  ADD CONSTRAINT fkarcj4n5nxpmgv0u5r3irlaef5
FOREIGN KEY (recipient_id) REFERENCES users;

ALTER TABLE user_exposition
  ADD CONSTRAINT fk7f9s0y2klrbwh9mvg83bvme2s
FOREIGN KEY (user_id) REFERENCES users;

