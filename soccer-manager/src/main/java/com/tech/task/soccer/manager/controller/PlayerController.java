package com.tech.task.soccer.manager.controller;

import com.tech.task.soccer.manager.model.Player;
import com.tech.task.soccer.manager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        log.info("Get player request. Id {} ", id);
        return playerService.findById(id);
    }

    @GetMapping("/all")
    public List<Player> getAll(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Player.Position position,
                               @RequestParam(required = false) Long teamId) {
        log.info("Get all players request. Name = {}, position = {}, teamId = {}" ,name,position,teamId);
        return playerService.getAllPLayers(name, position, teamId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Player createPlayer(@RequestBody @Valid Player player) {
        log.info("Create new  player request. Player {} ", player);
        return playerService.createNewPlayer(player);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody Long id) {
        log.info("Delete player request. Id {} ", id);
        playerService.deletePlayer(id);
    }

    @PatchMapping
    public Player updatePlayer(@RequestBody @Valid Player player) {
        log.info("Update  player request. Player {} ", player);
        return playerService.updatePlayer(player);
    }

}
