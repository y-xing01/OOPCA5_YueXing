DROP DATABASE IF EXISTS `oop_badminton`;
CREATE DATABASE `oop_badminton`;
USE `oop_badminton`;

DROP TABLE IF EXISTS player;

CREATE TABLE player(
    player_id int(10) not null AUTO_INCREMENT,
	player_world_rank int(10) not null,
	player_name varchar(50) not null,
	player_age int(10) not null,
	player_height float(10) not null,
	player_career_win int(10) not null,

	PRIMARY KEY (player_id)
);
ALTER TABLE `player` ADD UNIQUE(`player_world_rank`);
insert into player values (1, 1, "Viktor Axelsen", 28, 194.50, 421);
insert into player values (2, 2, "Kento Momota", 27, 175.0, 361);
insert into player values (3, 3, "Anders Antonsen", 24, 183.00, 238);
insert into player values (4, 4, "Chou Tien-chen", 32, 180.00, 391);
insert into player values (5, 5, "Anthony Ginting", 25, 171.40, 201);
insert into player values (6, 6, "Chen Long", 33, 187.00, 446);
insert into player values (7, 7, "Lee Zii Jia", 23, 186.00, 188);
insert into player values (8, 8, "J.Christie", 24, 179.50, 228);
insert into player values (9, 9, "Loh Kean Yew", 24, 175.00, 163);
insert into player values (10, 10, "Angus Ng", 27, 181.00, 298);