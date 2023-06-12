package com.promptpicture.backend.core.common.filter;

import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T, U> {

    public abstract Specification<T> getFilter(U request);

    protected <K> Specification<T> promptAttributeEqual(final String attributeName, final K value) {

        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.equal(root.get(attributeName), value);
        };
    }
}
