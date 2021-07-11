drop table if exists `hibernate_lesson`.`products_users`;
drop table if exists `hibernate_lesson`.`products`;
drop table if exists `hibernate_lesson`.`contacts`;
drop table if exists `hibernate_lesson`.`users`;

CREATE TABLE IF NOT EXISTS `hibernate_lesson`.`users` (
                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                    `age` integer not null,
                    `username` varchar(512) not null,
                       primary key (id)
);

CREATE TABLE IF NOT EXISTS `hibernate_lesson`.`contacts` (
                                               `id` BIGINT NOT NULL AUTO_INCREMENT,
                                               `contact` VARCHAR(255) NOT NULL,
                                               `type` VARCHAR(255) NOT NULL,
                                               `user_id` BIGINT NOT NULL,
                                               PRIMARY KEY (`id`),
                                               CONSTRAINT `fk_user_contact`
                                                   FOREIGN KEY (`user_id`)
                                                       REFERENCES `hibernate_lesson`.`users` (`id`)
    );

CREATE TABLE IF NOT EXISTS `hibernate_lesson`.`products` (
                         `id` bigint not null auto_increment,
                         `cost` double precision,
                         `title` varchar(255),
                         primary key (id)
);

CREATE TABLE `hibernate_lesson`.`products_users` (
                                                    `users_id` BIGINT NOT NULL,
                                                    `products_id` BIGINT NOT NULL,
                                                    INDEX `fk_product_id_idx` (`products_id` ASC) VISIBLE,
                                                    CONSTRAINT `fk_users_id`
                                                        FOREIGN KEY (`users_id`)
                                                            REFERENCES `hibernate_lesson`.`users` (`id`),
                                                    CONSTRAINT `fk_product_id`
                                                        FOREIGN KEY (`products_id`)
                                                            REFERENCES `hibernate_lesson`.`products` (`id`)
                                                );

insert into `hibernate_lesson`.`users` (age, username) values (10,'vasya'),
                                                              (11, 'petya'),
                                                              (12, 'sveta');

insert into `hibernate_lesson`.`products` (cost, title) values (100,'flower'),
                                                               (110, 'car'),
                                                               (120, 'robot');

insert into `hibernate_lesson`.`contacts` (contact, type, user_id) values ('email','vasya@ya.ru',1),
                                                                          ('email','petya@ya.ru',2),
                                                                          ('email','sveta@ya.ru',3);

insert into `hibernate_lesson`.`products_users` (users_id, products_id) values (1,2),
                                                                              (1,3),
                                                                              (2,2),
                                                                              (2,3),
                                                                              (3,1);