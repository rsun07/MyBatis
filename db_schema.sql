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

CREATE TABLE `employee_super` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_super_id` (`manager_id`),
  CONSTRAINT `fk_employee_super_id` FOREIGN KEY (`manager_id`) REFERENCES `employee_super` (`id`)
);