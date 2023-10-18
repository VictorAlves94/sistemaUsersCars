CREATE TABLE users(
    id BIGINT not null auto_increment,
    firstName VARCHAR(100) not null,
    lastName VARCHAR(100) not null,
    email VARCHAR(6) not null unique,
    birthday DATE(100) not null,
    login VARCHAR(100) not null,
    password VARCHAR(100) not null,
    phone VARCHAR(100) not null,
    id_car BIGINT,
    primary key(id),
    FOREIGN KEY (id_cars) REFERENCES Cars(id)
    );

insert into users()values('victor','alves','alves@victor.com',1994-13-09, 'kaveira54', 'kaveira54', '99999-9999')

