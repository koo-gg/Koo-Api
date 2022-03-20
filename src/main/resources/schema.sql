CREATE TABLE IF NOT EXISTS user (
    idx BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userid BIGINT NOT NULL,
    username VARCHAR(35),
    avatar VARCHAR(35),
    discrimicator VARCHAR(4),
);

alter table user
    add constraint USERID_UNIQUE unique (userid)

create table user_authority (
    userid BIGINT NOT NULL,
    authority_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, authority_name)
);