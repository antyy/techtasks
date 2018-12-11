package com.tech.task.soccer.manager.configuration;

import static com.tech.task.soccer.manager.model.Player.Position.CD;
import static com.tech.task.soccer.manager.model.Player.Position.GK;
import static com.tech.task.soccer.manager.model.Player.Position.LD;
import static com.tech.task.soccer.manager.model.Player.Position.LF;
import static com.tech.task.soccer.manager.model.Player.Position.RD;
import static com.tech.task.soccer.manager.model.Player.Position.RF;

import com.tech.task.soccer.manager.model.Player;
import com.tech.task.soccer.manager.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class DemoDataIitialization {

    private final PlayerRepository playerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void insertSomeData() {
        Long one = 1L;
        Long two = 2L;

        playerRepository.save(Player.builder().name("John")
                .teamId(one)
                .birthday(ZonedDateTime.of(LocalDate.of(1990, 2, 1), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(12)
                .position(CD).build());
        playerRepository.save(Player.builder().name("Jack")
                .teamId(one)
                .position(LD)
                .birthday(ZonedDateTime.of(LocalDate.of(1988, 7, 16), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(3).build());
        playerRepository.save(Player.builder().name("Sam")
                .teamId(one)
                .position(LF).build());
        playerRepository.save(Player.builder().name("Peter")
                .teamId(one)
                .position(CD)
                .birthday(ZonedDateTime.of(LocalDate.of(1988, 1, 1), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(13).build());
        playerRepository.save(Player.builder().name("John")
                .teamId(one)
                .position(GK)
                .birthday(ZonedDateTime.of(LocalDate.of(1985, 7, 16), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(17).build());
        playerRepository.save(Player.builder().name("John")
                .teamId(one)
                .position(RD)
                .birthday(ZonedDateTime.of(LocalDate.of(1986, 7, 16), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(21).build());
        playerRepository.save(Player.builder().name("John")
                .teamId(two)
                .position(CD)
                .birthday(ZonedDateTime.of(LocalDate.of(1980, 7, 16), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(8).build());
        playerRepository.save(Player.builder().name("Peter")
                .teamId(two)
                .position(CD)
                .birthday(ZonedDateTime.of(LocalDate.of(1995, 7, 16), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(5).build());
        playerRepository.save(Player.builder().name("Jack")
                .teamId(two)
                .position(RF)
                .birthday(ZonedDateTime.of(LocalDate.of(1998, 7, 16), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(16).build());
        playerRepository.save(Player.builder().name("Peter")
                .teamId(two)
                .position(CD)
                .birthday(ZonedDateTime.of(LocalDate.of(1988, 7, 16), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(1).build());
        playerRepository.save(Player.builder().name("Sam")
                .teamId(two)
                .position(LF)
                .birthday(ZonedDateTime.of(LocalDate.of(1988, 7, 16), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(9).build());
        playerRepository.save(Player.builder().name("John")
                .teamId(two)
                .position(RF)
                .birthday(ZonedDateTime.of(LocalDate.of(1988, 7, 16), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(7).build());
        playerRepository.save(Player.builder().name("John")
                .teamId(two)
                .position(CD)
                .birthday(ZonedDateTime.of(LocalDate.of(1988, 7, 16), LocalTime.now(), ZoneId.of("UTC")).toInstant())
                .playerNumber(4).build());
    }
}
