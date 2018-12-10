package com.tech.task.soccer.manager.controller;

import com.tech.task.soccer.manager.model.Team;
import com.tech.task.soccer.manager.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/{id}")
    public Team getById(@PathVariable Long id) {
        log.info("GET team by id {}", id);
        return teamService.findById(id);
    }

    @GetMapping("/all")
    public List<Team> getAllTeams() {
        return teamService.getAll();
    }

    @PostMapping
    public Team createNewTeam(@RequestBody @Valid Team team) {
        log.info("Create team request. Input team {}", team);
        return teamService.createTeam(team);
    }

    @PatchMapping
    public Team updateTeam(@RequestBody @Valid Team team){
        log.info("Update team request. Input team {}", team);
        return teamService.updateTeam(team);
    }

    @DeleteMapping
    public void deleteTeam(@RequestBody Long teamId){
        log.info("Delete team request. Team id {}", teamId);
        teamService.deleteTeam(teamId);
    }
}