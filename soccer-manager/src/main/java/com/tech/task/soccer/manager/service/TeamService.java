package com.tech.task.soccer.manager.service;

import com.tech.task.soccer.manager.model.Team;

import java.util.List;

public interface TeamService {
    Team findById(Long id);

    List<Team> getAll();

    Team createTeam(Team team);

    Team updateTeam(Team team);

    void deleteTeam(Long id);
}
