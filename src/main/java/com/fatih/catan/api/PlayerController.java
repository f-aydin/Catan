package com.fatih.catan.api;

import com.fatih.catan.domain.PlayerService;
import com.fatih.catan.domain.Player;
import com.fatih.catan.dto.BuildDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api")

public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/greet")
    public String hello(){
        return "hello";
    }

    @GetMapping("/playerResources")
    public List<Player> getPlayers(){
        return playerService.getPlayers();
    }

    @PostMapping("/addOneByDice")
    public List<Player> addResources(@RequestBody Integer dice){
        return playerService.addResources(dice);
    }

    @PostMapping("/buildSettlement")
    public List<Player> buildSettlement(@RequestBody BuildDTO buildDTO) throws Exception {
        return playerService.buildSettlement(buildDTO);
    }
}
