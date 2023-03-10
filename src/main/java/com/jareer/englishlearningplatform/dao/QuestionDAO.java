package com.jareer.englishlearningplatform.dao;

import com.jareer.englishlearningplatform.domains.Questions;
import jakarta.persistence.EntityManager;

import java.util.List;

public class QuestionDAO extends BaseDAO<Questions, Integer> {




    public List<Questions> findAllByGrammarId( Integer id ) {
        List<Questions> questionsList=null;
        try (EntityManager em = emf.createEntityManager()) {
            questionsList = em.createNativeQuery("select * from questions where grammar_id = :id", Questions.class).setParameter("id", id).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return questionsList;
    }

}
