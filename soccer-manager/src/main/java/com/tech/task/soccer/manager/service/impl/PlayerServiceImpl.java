package com.tech.task.soccer.manager.service.impl;

import static com.tech.task.soccer.manager.repository.specifiation.PlayerSpecifications.nameEmptyOrEqualTo;
import static com.tech.task.soccer.manager.repository.specifiation.PlayerSpecifications.positionEmptyOrEqualTo;
import static com.tech.task.soccer.manager.repository.specifiation.PlayerSpecifications.teamEmptyOrEqualTo;

import com.tech.task.soccer.manager.exception.PlayerNotFoundException;
import com.tech.task.soccer.manager.model.Player;
import com.tech.task.soccer.manager.repository.PlayerRepository;
import com.tech.task.soccer.manager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public List<Player> getAllPLayers(String name, Player.Position position, Long teamId) {
        return repository.findAll(Specification.where(nameEmptyOrEqualTo(name))
                .and(positionEmptyOrEqualTo(position))
                .and(teamEmptyOrEqualTo(teamId)));
    }
}
