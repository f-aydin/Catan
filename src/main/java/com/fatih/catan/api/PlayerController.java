package com.fatih.catan.api;

import com.fatih.catan.domain.PlayerModel;
import com.fatih.catan.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api")

public class PlayerController {
    private final PlayerModel playerModel;

    @Autowired
    public PlayerController(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    @GetMapping
    public List<Player> getPerson(){
        return playerModel.getPerson();
    }

//    @PostMapping
//    public void add(@RequestBody PlayerModel.NewPersonRequest person){
//        playerModel.savePerson(person);
//    }

}
