CREATE TABLE if NOT EXISTS book
(
    id             integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
    book_name      varchar(255),
    book_style     varchar (255),
    upc            varchar (25),
    quantity_on_hand integer,
    price          decimal,
    created_date   timestamp,
    last_modified_date timestamp
);