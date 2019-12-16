package com.train.sbm.controller;

import com.train.sbm.entity.Game;
import com.train.sbm.service.GameService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "sb")
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/insert")
    public String insert(@RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "type", required = true) String type,
                         @RequestParam(value = "role_id", required = true) String role_id,
                         @RequestParam(value = "role_name", required = true) String role_name) {
        Game game = new Game();
        System.out.println("game==> " + game);

        game.setName(name);
        game.setType(type);
        game.setRole_id(role_id);
        game.setRole_name(role_name);

        int result = gameService.insert(game);
        if (result == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id,
                         @RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "type", required = true) String type,
                         @RequestParam(value = "role_id", required = true) String role_id,
                         @RequestParam(value = "role_name", required = true) String role_name) {
        Game game = new Game();


        game.setName(name);
        game.setId(id);
        game.setType(type);
        game.setRole_id(role_id);
        game.setRole_name(role_name);

        System.out.println("game==> " + game);
        int result = gameService.update(game);
        if (result == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable("id") Integer id) {
        return gameService.delete(id);
    }

    @GetMapping("/get/{id}")
    public Game getOne(@PathVariable("id") Integer id) {
        return gameService.getOne(id);
    }

    @GetMapping("/getlist")
    public List<Game> getList() {
        return gameService.getList(new Game());
    }

}
