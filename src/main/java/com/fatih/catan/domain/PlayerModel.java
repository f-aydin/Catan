package com.fatih.catan.domain;

import com.fatih.catan.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
}
