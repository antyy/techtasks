package com.tech.task.soccer.manager.repository;

import com.tech.task.soccer.manager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
