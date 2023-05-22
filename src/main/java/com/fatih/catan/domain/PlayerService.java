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

    public List<Player> getPlayer(){
        return playerRepository.findAll();
    }

    public List<Player> addResources(Integer dice) {
        Board board = new Board();

//        List<Player> players = playerRepository.findAll();

        Player player1 = new Player(1);
        Player player2 = new Player(2);

        Building building1 = new Building(1, Arrays.asList(new Tile(1, 10, Resource.ORE), new Tile(2, 2, Resource.WOOL), new Tile(5,6, Resource.BRICK)));
        Building building2 = new Building(2, Arrays.asList(new Tile(8, 9, Resource.GRAIN), new Tile(9, 11, Resource.LUMBER), new Tile(13,8, Resource.LUMBER)));

        player1.addBuilding(building1);
        player2.addBuilding(building2);

        List<Player> players = Arrays.asList(player1, player2);

        List<Tile> tiles = Arrays.stream(board.getTiles()).filter(tile -> tile.getToken() == dice).toList();
        List<Player> playerThatReceivesResources;

        for(Tile tile: tiles) {
            playerThatReceivesResources =  players.stream().filter(player -> player.hasBuilding(tile)).toList();
            for(Player player: playerThatReceivesResources) {
                player.addResource(tile.getResource());
                System.out.println(player.getPlayerID());
                System.out.println(player.getLumber());
                System.out.println(player.getGrain());
                System.out.println(player.getBrick());
                System.out.println(player.getOre());
                System.out.println(player.getWool());
//                playerRepository.save(player);
            }
        }

        return playerRepository.findAll();

//        return playerRepository.saveAll(playerThatReceivesResources);
    }
}
