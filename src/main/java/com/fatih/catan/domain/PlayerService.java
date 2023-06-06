package com.fatih.catan.domain;

import com.fatih.catan.dto.BuildDTO;
import com.fatih.catan.repository.PlayerRepository;
import com.fatih.catan.repository.TileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TileRepository tileRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TileRepository tileRepository) {
        this.playerRepository = playerRepository;
        this.tileRepository = tileRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> addResources(Integer dice) {
        List<Tile> board = tileRepository.findAll();
        List<Player> players = playerRepository.findAll();
        List<Tile> tiles = board.stream()
                .filter(tile -> tile.getToken() == dice)
                .toList();
        for(Tile tile : tiles) {
            List<Player> playerThatReceivesResources = players.stream()
                    .filter(player -> player.howManyBuildingsOnTile(tile) > 0)
                    .toList();
            for(Player player : playerThatReceivesResources) {
                for(int i = 0; i < player.getBuildings().size(); i++){
                    if(tile.getHasRobber()){
                        continue;
                    }
                    player.addResource(tile.getResourceType(), player.getBuildings().get(i).getType().getAmountOfResourcesToAdd());
                    playerRepository.save(player);
                }
            }
        }

        return playerRepository.findAll();
    }

    public List<Player> buildSettlement(BuildDTO buildDto) throws Exception {
        List<Tile> board = tileRepository.findAll();
        if(doesPlayerNotExist(buildDto)) {
            throw new Exception("Player does not exist");
        }

        if(doesTileNotExist(buildDto)) {
            throw new Exception("Tile does not exist. (Choose between 1-19)");
        }

        if(doesPlayerNotHaveSettlementBeforeBuildingCity(board, buildDto)){
            throw new Exception("Build a settlement before building a city");
        }

        if(isLocationOccupied(board, buildDto)) {
            throw new Exception("Location is occupied. Choose another one");
        }

        if(doesPlayerNotHaveEnoughResources(buildDto)){
            throw new Exception("Not enough resources");
        }

        Player player = playerRepository.findById(buildDto.getPlayerID()).orElseThrow();
        List<Tile> tiles = List.of(board.get(buildDto.getTile1()), board.get(buildDto.getTile2()), board.get(buildDto.getTile3()));
        Building building = createSettlementOrCity(buildDto, tiles);
//        building.replaceSettlementByCity();
        player.addBuilding(building);
        subtractResources(buildDto, player);
        playerRepository.save(player);
        return playerRepository.findAll();
    }

    private Building createSettlementOrCity(BuildDTO buildDto, List<Tile> tiles) {
        Building building;
        if(buildDto.getType().equals(BuildingType.SETTLEMENT)){
            building = new Building(BuildingType.SETTLEMENT, tiles);
        } else {
            building = new Building(BuildingType.CITY, tiles);
        }
        return building;
    }

    private void subtractResources(BuildDTO buildDTO, Player player) {
        if(buildDTO.getType().equals(BuildingType.SETTLEMENT)){
            player.setBrick(player.getBrick()-1);
            player.setWool(player.getWool()-1);
            player.setGrain(player.getGrain()-1);
            player.setLumber(player.getLumber()-1);
        } else {
            player.setOre(player.getBrick()-3);
            player.setGrain(player.getGrain()-2);
        }
        playerRepository.save(player);
    }

    private boolean doesPlayerNotHaveSettlementBeforeBuildingCity(List<Tile> board, BuildDTO buildDto) {
        Player player = playerRepository.findById(buildDto.getPlayerID()).orElseThrow();
        if(buildDto.getType().equals(BuildingType.CITY)) {
            if(player.getBuildings().isEmpty()){
                return true;
            } else {
                boolean isSettlementOnLocation = player.getBuildings().stream()
                        .anyMatch(building ->
                                building.isOnTile(board.get(buildDto.getTile1())) &&
                                        building.isOnTile(board.get(buildDto.getTile2())) &&
                                        building.isOnTile(board.get(buildDto.getTile3())));
                return !isSettlementOnLocation;
            }
        }
        return false;
    }

    private boolean doesPlayerNotHaveEnoughResources(BuildDTO buildDTO) {
        Player player = playerRepository.findById(buildDTO.getPlayerID()).orElseThrow();
        if(buildDTO.getType().equals(BuildingType.SETTLEMENT)){
            return player.getGrain() < 1 || player.getLumber() < 1 || player.getWool() < 1 || player.getBrick() < 1;
        } else {
            return player.getBrick() < 3 || player.getOre() < 2;
        }
    }

    private boolean isLocationOccupied(List<Tile> board, BuildDTO buildDto) {
        return playerRepository.findAll().stream()
                .anyMatch(player -> player.getBuildings()
                        .stream().anyMatch(building ->
                                building.isOnTile(board.get(buildDto.getTile1())) &&
                                        building.isOnTile(board.get(buildDto.getTile2())) &&
                                        building.isOnTile(board.get(buildDto.getTile3()))));
    }

    private boolean doesTileNotExist(BuildDTO buildDto) {
        return buildDto.getTile1() <= -1 || buildDto.getTile1() >= 20 ||
                buildDto.getTile2() <= -1 || buildDto.getTile2() >= 20 ||
                buildDto.getTile3() <= -1 || buildDto.getTile3() >= 20;
    }

    private boolean doesPlayerNotExist(BuildDTO buildDto) {
        return buildDto.getPlayerID() <= 0 || buildDto.getPlayerID() >= 5;
    }

    public void placeRobber(Integer tileNumber) {
        List<Tile> tiles = tileRepository.findAll();
        boolean isRobberPlaced = tiles.stream().anyMatch(Tile::getHasRobber);
        if(isRobberPlaced){
            tiles.forEach(tile -> tile.setHasRobber(false));
        }
        Tile robbedTile = tiles.get(tileNumber);
        robbedTile.setHasRobber(true);
        tileRepository.save(robbedTile);
    }
}