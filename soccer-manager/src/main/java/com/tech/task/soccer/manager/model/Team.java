package com.tech.task.soccer.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String name;
}
