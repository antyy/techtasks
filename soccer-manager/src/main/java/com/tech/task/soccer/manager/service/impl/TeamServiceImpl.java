package com.tech.task.soccer.manager.service.impl;

import com.tech.task.soccer.manager.exception.TeamNotFoundException;
import com.tech.task.soccer.manager.model.Team;
import com.tech.task.soccer.manager.repository.TeamRepository;
import com.tech.task.soccer.manager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException("Team not found by id " + id));
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
