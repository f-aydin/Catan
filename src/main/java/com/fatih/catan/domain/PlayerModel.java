package com.fatih.catan.domain;

import com.fatih.catan.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PlayerModel {
    private final PlayerRepository playerRepository;
    private final Tile[] tiles = new Tile[] {
            new Tile(1, 10, Resource.ORE),
            new Tile(2, 2, Resource.WOOL),
            new Tile(3, 9, Resource.LUMBER),
            new Tile(4, 12, Resource.GRAIN),
            new Tile(5, 6, Resource.BRICK),
            new Tile(6, 4, Resource.WOOL),
            new Tile(7, 10, Resource.BRICK),
            new Tile(8, 9, Resource.GRAIN),
            new Tile(9, 11, Resource.LUMBER),
            new Tile(10, 3, Resource.LUMBER),
            new Tile(11, 8, Resource.ORE),
            new Tile(12, 8, Resource.LUMBER),
            new Tile(13, 3, Resource.ORE),
            new Tile(14, 4, Resource.GRAIN),
            new Tile(15, 5, Resource.WOOL),
            new Tile(16, 5, Resource.BRICK),
            new Tile(17, 6, Resource.GRAIN),
            new Tile(18, 11, Resource.WOOL)
    };

    @Autowired
    public PlayerModel(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayer(){
        return playerRepository.findAll();
    }

    public List<Player> addResources(Integer dice) {
        List<Player> players = playerRepository.findAll();
        List<Tile> tiles = Arrays.stream(this.tiles).filter(tile -> tile.getToken() == dice).toList(); // list of tiles that correspond to dice roll

        for(Tile tile: tiles) {
            List<Player> playerThatReceivesResources = players.stream().filter(player -> player.hasBuilding(tile)).toList();
            for(Player player: playerThatReceivesResources) {
                player.addResource(tile.getResource());
            }
        }

//
//        if(areDiceRolledForWool(dice)){
//            for(Player player : players){
//                player.setWool(player.getWool() + 1);
//            }
//        }
//        if(areDiceRolledForOre(dice)){
//            for(Player player : players){
//                player.setOre(player.getOre() + 1);
//            }
//        }
//        if(areDiceRolledForLumber(dice)){
//            for(Player player : players){
//                player.setLumber(player.getLumber() + 1);
//            }
//        }
//        if(areDiceRolledForGrain(dice)){
//            for(Player player : players){
//                player.setGrain(player.getGrain() + 1);
//            }
//        }
//        if(areDiceRolledForBrick(dice)){
//            for(Player player : players){
//                player.setBrick(player.getBrick() + 1);
//            }
//        }
        return playerRepository.saveAll(players);
    }

    private boolean areDiceRolledForBrick(Integer dice) {
        return dice.equals(5) || dice.equals(6) || dice.equals(10);
    }

    private boolean areDiceRolledForGrain(Integer dice) {
        return dice.equals(4) || dice.equals(6) || dice.equals(9) || dice.equals(12);
    }

    private boolean areDiceRolledForLumber(Integer dice) {
        return dice.equals(3) || dice.equals(8) || dice.equals(9) || dice.equals(11);
    }

    private boolean areDiceRolledForOre(Integer dice) {
        return dice.equals(3) || dice.equals(8) || dice.equals(10);
    }

    private boolean areDiceRolledForWool(Integer dice) {
        return dice.equals(2) || dice.equals(4) || dice.equals(5) || dice.equals(11);
    }
}
