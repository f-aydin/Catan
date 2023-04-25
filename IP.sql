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
    (1, 2, 4, 6, 8, 10),
    (2, 1, 1, 1, 1, 1),
    (3, 12, 4, 5, 2, 1),
    (4, 3, 6, 2, 3, 0);

select * from player_resources;

create table hex_tokens (
	tokenID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    chit INT,
    resources VARCHAR(40)
);

insert into hex_tokens (chit, resources)
	values
    (2, "wool"),
    (3, "ore"),
    (3, "lumber"),
    (4, "wool"),
    (4, "grain"),
    (5, "wool"),
    (5, "brick"),
	(6, "grain"),
    (6, "brick"),
    (8, "ore"),
    (8, "lumber"),
    (9, "grain"),
    (9, "lumber"),
    (10, "ore"),
	(10, "brick"),
    (11, "wool"),
    (11, "lumber"),
    (12, "grain");
    
select * from hex_tokens
    
