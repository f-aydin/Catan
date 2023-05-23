package com.fatih.catan.domain;

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
                playerRepository.saveAll(playerThatReceivesResources);
            }
        }

        return playerRepository.findAll();
    }

    public List<Player> buildSettlement(Integer playerID, Integer tile1, Integer tile2, Integer tile3){
        Board board = new Board();
        Player player = playerRepository.findById(playerID).orElseThrow();
        List<Tile> tiles = List.of(board.getSpecificTile(tile1), board.getSpecificTile(tile2), board.getSpecificTile(tile3));
        Building building = new Building(tiles);
        player.addBuilding(building);
        return playerRepository.saveAll(playerRepository.findAll());
    }
}
