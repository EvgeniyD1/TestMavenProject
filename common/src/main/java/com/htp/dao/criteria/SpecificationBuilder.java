package com.htp.dao.criteria;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SpecificationBuilder<T> {

    private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<>();
    }

    public SpecificationBuilder<T> with(String orPredicate, String key, String operation, String value) {
        params.add(new SearchCriteria(orPredicate, key, operation, value));
        return this;
    }

    public Specification<T> build() {
        if (params.isEmpty()) {
            return null;
        }

        List<Specification<T>> specs = params.stream()
                .map(SearchSpecification<T>::new)
                .collect(Collectors.toList());

        Specification<T> result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).getOrPredicate()!=null && params.get(i).getOrPredicate().equals("'")
                    ? Objects.requireNonNull(Specification.where(result))
                    .or(specs.get(i))
                    : Objects.requireNonNull(Specification.where(result))
                    .and(specs.get(i));
        }
        return result;
    }
}
