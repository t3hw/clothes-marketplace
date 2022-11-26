package com.ravid.clothes_marketplace.app.db.repo.fragmentrepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ravid.clothes_marketplace.app.db.model.Garment;

public class DynamicGarmentRepositoryImpl implements DynamicGarmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Garment> findGarmentsBySearchParams(Optional<String> publisherNamePattern
                                                   ,Optional<String> publisherId
                                                   ,Optional<String> optType
                                                   ,Optional<String> optSize
                                                   ,Optional<Float> optMinPrice 
                                                   ,Optional<Float> optMaxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Garment> query = cb.createQuery(Garment.class);
        Root<Garment> garment = query.from(Garment.class);

        
        List<Predicate> predicates = new ArrayList<>();
        
        // Build predicate list for the dynamic query
        publisherNamePattern.ifPresent(value -> {
            Path<String> path = garment.get("publisher").get("fullName");
            predicates.add(cb.like(cb.lower(path), "%"+value.toLowerCase()+"%"));
        });
        publisherId.ifPresent(value -> {
            Path<String> path = garment.get("publisher").get("idNumber");
            predicates.add(cb.equal(path, value));
        });
        optType.ifPresent(value -> {
            Path<String> path = garment.get("garmentType");
            predicates.add(cb.equal(path, value));
        });
        optSize.ifPresent(value -> {
            Path<String> path = garment.get("size");
            predicates.add(cb.equal(path, value));
        });
        optMinPrice.ifPresent(value -> {
            Path<Float> path = garment.get("price");
            predicates.add(cb.gt(path, value));
        });
        optMaxPrice.ifPresent(value -> {
            Path<Float> path = garment.get("price");
            predicates.add(cb.lt(path, value));
        });

        query.select(garment)
            .where(cb.and(predicates.toArray(new Predicate[predicates.size()])))
            .orderBy(cb.asc(garment.get("publisher").get("idNumber")));

        return entityManager.createQuery(query)
            .getResultList();
    }
}
