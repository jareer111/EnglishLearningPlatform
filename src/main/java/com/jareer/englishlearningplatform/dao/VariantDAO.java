package com.jareer.englishlearningplatform.dao;
import com.jareer.englishlearningplatform.domains.Variants;
import jakarta.persistence.EntityManager;

import java.util.List;


public class VariantDAO extends BaseDAO<Variants, Integer> {


    public List<Variants> findAllByQuestionId( Integer id ) {
        List<Variants> variantsList;
        try (EntityManager em = emf.createEntityManager()) {
            variantsList = em.createNativeQuery("select * from variants where questions_id = :id", Variants.class).setParameter("id", id).getResultList();
        }
        return variantsList;
    }


    public void deleteVariantsByQuestionId(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createNativeQuery("delete from variants where questions_id = :id").setParameter("id", id).executeUpdate();
            em.getTransaction().commit();
        }
    }
}
