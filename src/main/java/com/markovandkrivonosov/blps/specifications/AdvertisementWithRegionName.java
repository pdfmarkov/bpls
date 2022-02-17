package com.markovandkrivonosov.blps.specifications;

import com.markovandkrivonosov.blps.module.Advertisement;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class AdvertisementWithRegionName implements Specification<Advertisement> {

    private String region;

    public AdvertisementWithRegionName(String region) {
        this.region = region;
    }

    public Predicate toPredicate(Root<Advertisement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (region == null) {
            return cb.isTrue(cb.literal(true)); // always true = no filtering
        }

        return cb.equal(root.get("region").get("name"), this.region);
    }

}
