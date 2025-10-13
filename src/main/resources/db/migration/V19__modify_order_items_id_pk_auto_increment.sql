alter table order_items
    modify id bigint auto_increment;

alter table order_items
    auto_increment = 1;