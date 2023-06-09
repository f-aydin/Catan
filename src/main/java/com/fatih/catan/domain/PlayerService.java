package com.fatih.catan.domain;

import com.fatih.catan.dto.BuildDTO;
import com.fatih.catan.dto.DevDTO;
import com.fatih.catan.repository.PlayerRepository;
import com.fatih.catan.repository.TileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TileRepository tileRepository;
    private final Deck deck = new Deck();

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
        if(buildDto.getType().equals(BuildingType.CITY)){
            replaceSettlementByCity(buildDto, board);
        } else {
            player.addBuilding(building);
        }
        subtractResourcesForBuilding(buildDto, player);
        playerRepository.save(player);
        return playerRepository.findAll();
    }

    private void replaceSettlementByCity(BuildDTO buildDTO, List<Tile> board) {
        Player player = playerRepository.findById(buildDTO.getPlayerID()).orElseThrow();
        List<Building> playerBuildings = player.getBuildings();
        List<Tile> tilesToBeBuildOn = List.of(board.get(buildDTO.getTile1()), board.get(buildDTO.getTile2()), board.get(buildDTO.getTile3()));
        for(Building building : playerBuildings){
            if(building.isOnTile(tilesToBeBuildOn.get(0)) && building.isOnTile(tilesToBeBuildOn.get(1)) && building.isOnTile(tilesToBeBuildOn.get(2)) && building.getType().equals(BuildingType.SETTLEMENT)){
                building.setBuildingTypeToCity();
            }
        }
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

    private void subtractResourcesForBuilding(BuildDTO buildDTO, Player player) {
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
        List<Building> playerBuildings = player.getBuildings();
        List<Building> buildingOnTile = playerBuildings.stream().filter(building ->
                building.isOnTile(board.get(buildDto.getTile1())) &&
                building.isOnTile(board.get(buildDto.getTile2())) &&
                building.isOnTile(board.get(buildDto.getTile3()))).toList();
        return buildingOnTile.isEmpty() && buildDto.getType().equals(BuildingType.CITY);
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
        List<Player> players = playerRepository.findAll();
        List<Building> allPlayerBuildings = new ArrayList<>();
        for(Player player : players) {
            allPlayerBuildings.addAll(player.getBuildings());
        }
        List<Tile> tilesToBuildOn = List.of(board.get(buildDto.getTile1()), board.get(buildDto.getTile2()), board.get(buildDto.getTile3()));
        for(Building building : allPlayerBuildings) {
            if(building.isOnTile(tilesToBuildOn.get(0)) &&
                    building.isOnTile(tilesToBuildOn.get(1)) &&
                    building.isOnTile(tilesToBuildOn.get(2)) &&
                    buildDto.getType().equals(BuildingType.SETTLEMENT)){
                return true;
            }
        }
        return false;
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
        stealRandomResourceFromRandomPlayer(robbedTile);
    }

    private void stealRandomResourceFromRandomPlayer(Tile robbedTile) {
        Random random = new Random();
        List<Player> players = playerRepository.findAll();
        List<Player> playersOnRobbedTile = players.stream().filter(player -> player.hasBuilding(robbedTile)).toList();
        if(playersOnRobbedTile.size() > 0){
            Player playerToStealFrom = playersOnRobbedTile.get(random.nextInt(playersOnRobbedTile.size()));
            Player playerStealing = playerRepository.findByHasTurn(true);
            Resource resourceToSteal = Resource.randomResource();
            playerToStealFrom.addResource(resourceToSteal, -1);
            playerStealing.addResource(resourceToSteal, 1);
            playerRepository.saveAll(List.of(playerStealing, playerToStealFrom));
        }
    }

    public int switchTurn(){
        List<Player> players = playerRepository.findAll();
        Player playerThatHasTurn = players.stream().filter(Player::isHasTurn).toList().get(0);
        int playerIDOfPlayerThatHasTurn = playerThatHasTurn.getPlayerID();
        playerThatHasTurn.setHasTurn(false);
        playerRepository.save(playerThatHasTurn);
        if(playerIDOfPlayerThatHasTurn != 4){
            Player nextPlayer = playerRepository.findById(playerIDOfPlayerThatHasTurn + 1).orElseThrow();
            nextPlayer.setHasTurn(true);
            playerRepository.save(nextPlayer);
        } else {
            Player firstPlayer =playerRepository.findById(1).orElseThrow();
            firstPlayer.setHasTurn(true);
            playerRepository.save(firstPlayer);
        }
        return players.stream().filter(Player::isHasTurn).findAny().orElseThrow().getPlayerID();
    }

    public DevelopmentCard buyDevCard(Integer playerID) throws Exception {
        Player player = playerRepository.findById(playerID).orElseThrow();
        DevelopmentCard drawnCard = deck.drawCard();
        player.addCard(drawnCard);
        subtractResourcesForDevCard(player);
        switch(drawnCard){
            case KNIGHT -> placeRobber(0);
            case VICTORYPOINT -> {
                player.addVictoryPoint();
                playerRepository.save(player);
            }
        }
        return drawnCard;
    }

    private void subtractResourcesForDevCard(Player player) throws Exception {
        if(player.getGrain() > 0 && player.getOre() > 0 && player.getWool() > 0){
            player.setOre(player.getOre() - 1);
            player.setGrain(player.getGrain() - 1);
            player.setWool(player.getWool() - 1);
            playerRepository.save(player);
        } else {
            throw new Exception("Not enough resources");
        }
    }

    public void yearOfPlenty(DevDTO devDTO) {
        Player player = playerRepository.findById(devDTO.getPlayerID()).orElseThrow();
        player.addResource(devDTO.getType(), 2);
        playerRepository.save(player);
    }
}