package com.fatih.catan.domain;

import com.fatih.catan.dto.BuildDTO;
import com.fatih.catan.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

    public List<Player> addResources(Integer dice) {
        Board board = new Board();
        List<Player> players = playerRepository.findAll();
        List<Tile> tiles = Arrays.stream(board.getTiles()).filter(tile -> tile.getToken() == dice).toList();
        List<Player> playerThatReceivesResources;
        for(Tile tile: tiles) {
            playerThatReceivesResources =  players.stream().filter(player -> player.hasBuilding(tile)).toList();
            for(Player player: playerThatReceivesResources) {
                player.addResource(tile.getResourceType());
                playerRepository.save(player);
            }
        }

        return playerRepository.findAll();
    }

    public List<Player> buildSettlement(BuildDTO buildDto) throws Exception {
        Board board = new Board();
        if(doesPlayerNotExist(buildDto)){
            throw new Exception("Player does not exist");
        }

        if(doesTileNotExist(buildDto)){
            throw new Exception("Tile does not exist. (Choose between 1-20");
        }

        if(isLocationOccupied(board, buildDto)){
            throw new Exception("Location is occupied. Choose another one");
        }

        Player player = playerRepository.findById(buildDto.getPlayerID()).orElseThrow();

        List<Tile> tiles =List.of(board.getTile(buildDto.getTile1()), board.getTile(buildDto.getTile2()), board.getTile(buildDto.getTile3()));
        Building building = new Building(tiles);
        player.addBuilding(building);
        playerRepository.save(player);
        return playerRepository.findAll();
    }

    private boolean isLocationOccupied(Board board, BuildDTO buildDto){
        for(Player player : playerRepository.findAll()){
            if(player.hasBuilding(board.getTile(buildDto.getTile1())) && player.hasBuilding(board.getTile(buildDto.getTile2())) &&
            player.hasBuilding(board.getTile(buildDto.getTile3()))){
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
}
