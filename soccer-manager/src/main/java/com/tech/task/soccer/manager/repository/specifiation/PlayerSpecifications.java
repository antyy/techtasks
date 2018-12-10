package com.tech.task.soccer.manager.repository.specifiation;

import com.tech.task.soccer.manager.model.Player;
import com.tech.task.soccer.manager.model.Team;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class PlayerSpecifications {
    private PlayerSpecifications() {
    }

    public static Specification<Player> nameEmptyOrEqualTo(String name) {
        return (root, query, criteriaBuilder) -> Optional.ofNullable(name)
                .map(nameValue -> criteriaBuilder.equal(root.get("name"), nameValue))
                .orElse(criteriaBuilder.conjunction());
    }

    public static Specification<Player> positionEmptyOrEqualTo(Player.Position position) {
        return (root, query, criteriaBuilder) -> Optional.ofNullable(position)
                .map(positionValue -> criteriaBuilder.equal(root.get("position"), positionValue))
                .orElse(criteriaBuilder.conjunction());
    }

    public static Specification<Player> teamEmptyOrEqualTo(Team team) {
        return (root, query, criteriaBuilder) -> Optional.ofNullable(team)
                .map(teamValue -> criteriaBuilder.equal(root.get("team"), teamValue))
                .orElse(criteriaBuilder.conjunction());
    }
}
