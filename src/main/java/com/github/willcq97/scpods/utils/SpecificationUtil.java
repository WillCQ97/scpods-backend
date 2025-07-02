package com.github.willcq97.scpods.utils;

import java.time.LocalDate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public class SpecificationUtil {

    private SpecificationUtil() {}

    public static Predicate addBetweenDates( Path<?> rootOrJoin, CriteriaBuilder cb, Predicate predicate, String attr, LocalDate start, LocalDate end ) {
        if( start != null ) {
            predicate = cb.and( predicate, cb.greaterThanOrEqualTo( rootOrJoin.<LocalDate>get( attr ), start ) );
        }

        if( end != null ) {
            predicate = cb.and( predicate, cb.lessThanOrEqualTo( rootOrJoin.<LocalDate>get( attr ), end ) );
        }
        return predicate;
    }

    public static Predicate addLike( Path<?> rootOrJoin, CriteriaBuilder cb, Predicate predicate, String attr, String compareValue ) {
        if( compareValue != null && !compareValue.isBlank() ) {
            var cleanedString = compareValue.trim().toLowerCase();
            return cb.and( predicate, cb.like( cb.lower( rootOrJoin.<String>get( attr ) ), cleanedString ) );
        }
        return predicate;
    }

    public static Predicate addLikeIgnoreCase( Path<?> rootOrJoin, CriteriaBuilder cb, Predicate predicate, String attr, String compareValue ) {
        if( compareValue != null && !compareValue.isBlank() ) {
            var cleanedString = compareValue.trim().toLowerCase();
            return cb.and( predicate, cb.like( cb.lower( rootOrJoin.<String>get( attr ) ), "%" + cleanedString + "%" ) );
        }
        return predicate;
    }

}
