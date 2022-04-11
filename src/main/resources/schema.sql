# CREATE TABLE IF NOT EXISTS user (
#     idx BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
#     userid BIGINT NOT NULL,
#     username VARCHAR(35),
#     avatar VARCHAR(35),
#     discriminator VARCHAR(4)
# );
drop table user;
create table user
(
    idx BIGINT NOT NULL AUTO_INCREMENT,
    userid BIGINT NOT NULL,
    username VARCHAR(35),
    avatar VARCHAR(35),
    discriminator VARCHAR(4),
    role VARCHAR(16),
    constraint user_pk
        primary key (idx)
);