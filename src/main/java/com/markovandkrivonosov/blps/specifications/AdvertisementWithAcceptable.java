package com.markovandkrivonosov.blps.specifications;

import com.markovandkrivonosov.blps.module.Advertisement;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class AdvertisementWithAcceptable implements Specification<Advertisement> {

    private Boolean acceptable;

    public AdvertisementWithAcceptable(Boolean acceptable) {
        this.acceptable = acceptable;
    }

    public Predicate toPredicate(Root<Advertisement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (acceptable == null)
            return cb.isNull(root.get("acceptable"));
        else
            return cb.equal(root.get("acceptable"), this.acceptable);
    }

}
