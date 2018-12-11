package com.tech.task.soccer.manager.repository.specifiation;

import com.tech.task.soccer.manager.model.Player;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class PlayerSpecifications {
    private PlayerSpecifications() {
    }

    public static Specification<Player> nameIsEmptyOrEqualTo(String name) {
        return (root, query, criteriaBuilder) -> Optional.ofNullable(name)
                .map(nameValue -> criteriaBuilder.equal(root.get("name"), nameValue))
                .orElse(criteriaBuilder.conjunction());
    }

    public static Specification<Player> positionIsEmptyOrEqualTo(Player.Position position) {
        return (root, query, criteriaBuilder) -> Optional.ofNullable(position)
                .map(positionValue -> criteriaBuilder.equal(root.get("position"), positionValue))
                .orElse(criteriaBuilder.conjunction());
    }

    public static Specification<Player> teamIdIsEmptyOrEqualTo(Long teamId) {
        return (root, query, criteriaBuilder) -> Optional.ofNullable(teamId)
                .map(teamValue -> criteriaBuilder.equal(root.get("teamId"), teamValue))
                .orElse(criteriaBuilder.conjunction());
    }
}
