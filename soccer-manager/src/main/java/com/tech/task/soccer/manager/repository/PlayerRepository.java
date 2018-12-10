package com.tech.task.soccer.manager.repository;

import com.tech.task.soccer.manager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player> {

    List<Player> findAllByNameOrTeamIdOrPosition(String name, Long teamId, Player.Position position);
}
