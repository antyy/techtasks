package com.tech.task.soccer.manager.repository.specifiation;

import com.tech.task.soccer.manager.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class PlayerSpecification implements Specification<Player> {
    private final String attributeName;
    private final Object attributeValue;

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.<Object>get(attributeName), attributeValue);
    }
}
