create table player_resources (
	playerID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ore INT,
    wool INT,
    brick INT,
    lumber INT,
    grain INT
);

insert into player_resources
	values
    (1, 0, 0, 0, 0, 0),
    (2, 0, 0, 0, 0, 0),
    (3, 0, 0, 0, 0, 0),
    (4, 0, 0, 0, 0, 0);

select * from player_resources;
drop table player_resources;
