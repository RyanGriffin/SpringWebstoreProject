alter table addresses
    drop foreign key addresses_users_id_fk;

alter table addresses
    add constraint addresses_users_id_fk
        foreign key (user_id) references users (id)
            on delete cascade;