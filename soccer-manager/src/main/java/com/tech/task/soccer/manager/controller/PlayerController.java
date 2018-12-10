package com.tech.task.soccer.manager.controller;

import com.tech.task.soccer.manager.model.Player;
import com.tech.task.soccer.manager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        return playerService.findById(id);
    }

    @GetMapping("/all")
    public List<Player> getAll() {
        return playerService.getAllPLayers();
    }

    @GetMapping("/search")
    public List<Player> search(@RequestParam(required = true) String name,
                               @RequestParam(required = false) String position,
                               @RequestParam(required = false) Long teamID) {
        return playerService.searchPlayers(name,position,teamID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Player createPlayer(@RequestBody  @Valid  Player player) {
        return playerService.createNewPlayer(player);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody Long id) {
        playerService.deletePlayer(id);
    }

    @PatchMapping
    public Player updatePlayer(@RequestBody  @Valid Player player) {
        return playerService.updatePlayer(player);
    }

}
