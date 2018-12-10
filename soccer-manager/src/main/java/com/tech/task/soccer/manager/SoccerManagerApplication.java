package com.tech.task.soccer.manager;

import com.tech.task.soccer.manager.model.Player;
import com.tech.task.soccer.manager.model.Team;
import com.tech.task.soccer.manager.repository.PlayerRepository;
import com.tech.task.soccer.manager.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import static com.tech.task.soccer.manager.model.Player.Position.CD;
import static com.tech.task.soccer.manager.model.Player.Position.GK;
import static com.tech.task.soccer.manager.model.Player.Position.LD;
import static com.tech.task.soccer.manager.model.Player.Position.LF;
import static com.tech.task.soccer.manager.model.Player.Position.RD;
import static com.tech.task.soccer.manager.model.Player.Position.RF;

@SpringBootApplication
public class SoccerManagerApplication {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void insertSomeData(){
        Team one = new Team();
        one.setName("Manchester City");
        one = teamRepository.save(one);

        Team two = new Team();
        two.setName("Barcelona");
        two= teamRepository.save(two);

        playerRepository.save(Player.builder().name("John").team(one).position(CD).build());
        playerRepository.save(Player.builder().name("Jack").team(one).position(LD).build());
        playerRepository.save(Player.builder().name("Sam").team(one).position(LF).build());
        playerRepository.save(Player.builder().name("Peter").team(one).position(CD).build());
        playerRepository.save(Player.builder().name("John").team(one).position(GK).build());
        playerRepository.save(Player.builder().name("John").team(one).position(RD).build());
        playerRepository.save(Player.builder().name("John").team(two).position(CD).build());
        playerRepository.save(Player.builder().name("Peter").team(two).position(CD).build());
        playerRepository.save(Player.builder().name("Jack").team(two).position(RF).build());
        playerRepository.save(Player.builder().name("Peter").team(two).position(CD).build());
        playerRepository.save(Player.builder().name("Sam").team(two).position(LF).build());
        playerRepository.save(Player.builder().name("John").team(two).position(RF).build());
        playerRepository.save(Player.builder().name("John").team(two).position(CD).build());




    }
    public static void main(String[] args) {
        SpringApplication.run(SoccerManagerApplication.class, args);
    }
}
