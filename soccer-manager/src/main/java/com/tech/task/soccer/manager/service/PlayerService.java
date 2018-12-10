package com.tech.task.soccer.manager.service;

import com.tech.task.soccer.manager.model.Player;

import java.util.List;


public interface PlayerService {
    Player createNewPlayer(Player player);

    Player updatePlayer(Player player);

    Player findById(Long id);

    void deletePlayer(Long id);

    List<Player> getAllPLayers();

    List<Player> searchPlayers(String name, String position, long teamId);
}
