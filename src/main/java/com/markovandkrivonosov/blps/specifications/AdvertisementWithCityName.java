package com.markovandkrivonosov.blps.specifications;

import com.markovandkrivonosov.blps.module.Advertisement;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class AdvertisementWithCityName implements Specification<Advertisement> {

    private String city;

    public AdvertisementWithCityName(String city) {
        this.city = city;
    }

    public Predicate toPredicate(Root<Advertisement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (city == null) {
            return cb.isTrue(cb.literal(true)); // always true = no filtering
        }

        return cb.equal(root.get("city").get("name"), this.city);
    }

}
