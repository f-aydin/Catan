create table player_resources (
	playerID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ore INT,
    wool INT,
    brick INT,
    lumber INT,
    grain INT
);

create table player_buildings (
	playerID INT PRIMARY KEY,
    tileID INT,
    buildingType ENUM ('SETTLEMENT', 'CITY')
);

create table building_on_tiles (
	tileID INT PRIMARY KEY,
	token INT,
    resourceType ENUM('LUMBER', 'GRAIN', 'ORE', 'BRICK', 'WOOL')
);

insert into player_resources
	values
    (1, 0, 0, 0, 0, 0),
    (2, 0, 0, 0, 0, 0),
    (3, 0, 0, 0, 0, 0),
    (4, 0, 0, 0, 0, 0);

select * from player_resources;
drop table building_on_tiles;
