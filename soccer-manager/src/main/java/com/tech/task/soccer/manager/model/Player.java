package com.tech.task.soccer.manager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Instant birthday;

    @ManyToOne
    private Team team;

    @Column
    private Integer playerNumber;

    @Column
    @NotNull
    private String name;

    @Column
    @Convert(attributeName = "id")
    private Position position;

    @Getter
    @RequiredArgsConstructor
    public enum Position {
        LF(0),
        RF(1),
        CF(2),
        RD(3),
        LD(4),
        CD(5),
        GK(6);

        private final int id;
    }
}
