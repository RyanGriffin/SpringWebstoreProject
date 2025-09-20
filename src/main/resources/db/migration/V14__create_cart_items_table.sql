create table cart_items
(
    id         bigint auto_increment
        primary key,
    cart_id    binary(16)    not null,
    product_id bigint        not null,
    quantity   int default 1 not null,
    constraint cart_items_cart_product_unique
        unique (cart_id, product_id),
    constraint cart_item_product_id__fk
        foreign key (product_id) references products (id)
            on delete cascade,
    constraint cart_items_cart_id__fk
        foreign key (cart_id) references carts (id)
            on delete cascade
);