create table player (
	playerID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ore INT,
    wool INT,
    brick INT,
    lumber INT,
    grain INT
);

insert into player
	values
    (1, 0, 0, 0, 0, 0),
    (2, 0, 0, 0, 0, 0),
    (3, 0, 0, 0, 0, 0),
    (4, 0, 0, 0, 0, 0);