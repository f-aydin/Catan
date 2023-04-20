create table player_resources (
	playerID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ore INT,
    wool INT,
    brick INT,
    wood INT,
    grain INT
);

insert into player_resources
	values
    (1, 2, 4, 6, 8, 10),
    (2, 1, 1, 1, 1, 1),
    (3, 12, 4, 5, 2, 1),
    (4, 3, 6, 2, 3, 0);

select * from player_resources;

drop table player_resources;