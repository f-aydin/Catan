package com.fatih.catan.domain;

import com.fatih.catan.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerModel {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerModel(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayer(){
        return playerRepository.findAll();
    }

    public List<Player> addResources(Integer dice) {
        List<Player> players = playerRepository.findAll();
        if(areDiceRolledForWool(dice)){
            for(Player player : players){
                player.setWool(player.getWool() + 1);
            }
        }
        if(areDiceRolledForOre(dice)){
            for(Player player : players){
                player.setOre(player.getOre() + 1);
            }
        }
        if(areDiceRolledForLumber(dice)){
            for(Player player : players){
                player.setLumber(player.getLumber() + 1);
            }
        }
        if(areDiceRolledForGrain(dice)){
            for(Player player : players){
                player.setGrain(player.getGrain() + 1);
            }
        }
        if(areDiceRolledForBrick(dice)){
            for(Player player : players){
                player.setBrick(player.getBrick() + 1);
            }
        }
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
