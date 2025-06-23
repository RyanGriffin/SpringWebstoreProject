-- create the profiles table
create table profiles
(
    id             bigint primary key   not null,
    bio            varchar(255)         null,
    phone_number   varchar(25)          null,
    date_of_birth  DATE                 null,
    loyalty_points bigint unsigned      default 0,

    foreign key (id) references users(id)
);