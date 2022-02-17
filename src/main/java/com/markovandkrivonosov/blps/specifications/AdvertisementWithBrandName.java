package com.markovandkrivonosov.blps.specifications;

import com.markovandkrivonosov.blps.module.Advertisement;
import com.markovandkrivonosov.blps.module.Brand;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;


public class AdvertisementWithBrandName implements Specification<Advertisement> {

    private String brand;

    public AdvertisementWithBrandName(String brand) {
        this.brand = brand;
    }

    public Predicate toPredicate(Root<Advertisement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (brand == null) {
            return cb.isTrue(cb.literal(true)); // always true = no filtering
        }

        return cb.equal(root.get("brand").get("name"), this.brand);
    }

}
