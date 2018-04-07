create table student (
    id int(11) not null auto_increment,
    name varchar(225),
    score double,
    primary key (id)
);

create table t_student (
    t_id int(11) not null auto_increment,
    t_name varchar(225),
    t_score double,
    primary key (t_id)
);

--For table relations:
CREATE TABLE `City` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `name` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE `Person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_city_on_id` (`city_id`),
  CONSTRAINT `fk_city_on_id` FOREIGN KEY (`city_id`) REFERENCES `City` (`id`)
);

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_id` (`manager_id`),
  CONSTRAINT `fk_employee_id` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`id`)
);

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `customer_to_store` (
  `customer_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  PRIMARY KEY (`customer_id`,`store_id`),
  KEY `key_store_id_in_c_to_s` (`store_id`),
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_store_id` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
);
