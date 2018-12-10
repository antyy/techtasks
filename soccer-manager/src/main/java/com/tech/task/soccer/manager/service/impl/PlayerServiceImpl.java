package com.tech.task.soccer.manager.service.impl;

import com.tech.task.soccer.manager.exception.PlayerNotFoundException;
import com.tech.task.soccer.manager.model.Player;
import com.tech.task.soccer.manager.repository.PlayerRepository;
import com.tech.task.soccer.manager.repository.specifiation.PlayerSpecification;
import com.tech.task.soccer.manager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;

    @Override
    public Player createNewPlayer(Player player) {
        return repository.save(player);
    }

    @Override
    public Player updatePlayer(Player player) {
        return repository.save(player);
    }

    @Override
    public Player findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player not found by id " + id));
    }

    @Override
    public void deletePlayer(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Player> getAllPLayers() {
        return repository.findAll();
    }

    @Override
    public List<Player> searchPlayers(String name, String position, Long teamId) {

        Specification specification = Specification.where( new PlayerSpecification("name", name));
        if (position != null ){
            specification = specification.or(new PlayerSpecification("position", position));
        }
        if (teamId != null){
            specification = specification.or(new PlayerSpecification("team", teamId));
        }

        return repository.findAll(specification);
    }
}
