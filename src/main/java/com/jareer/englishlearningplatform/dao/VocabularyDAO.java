package com.jareer.englishlearningplatform.dao;

import com.jareer.englishlearningplatform.domains.Story;
import com.jareer.englishlearningplatform.domains.Vocabulary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VocabularyDAO extends BaseDAO<Vocabulary, Integer> {


    public List<Vocabulary> getVocabulariesByStoryId(Story storyId) {
        List<Vocabulary> vocabularies = null;
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Vocabulary> query = em.createQuery("from Vocabulary v where v.story = :storyId", Vocabulary.class);
            query.setParameter("storyId", storyId);
            vocabularies = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vocabularies;
    }

    public Integer getVocabularyCountWithOption(Integer userId, Integer storyId) {
        int size = 0;
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Vocabulary> query = em.createQuery("from Vocabulary v where v.deleted=false and v.createdBy = :userId and v.story.id = :storyId", Vocabulary.class);
            query.setParameter("userId", userId);
            query.setParameter("storyId", storyId);
            size = query.getResultList().size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }


    public boolean hasWord(String eWord, String uWord) {
        int size = 0;
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Vocabulary> query = em.createQuery("from Vocabulary v where v.word=:eWord and v.meaning=:uWord", Vocabulary.class);
            query.setParameter("eWord", eWord);
            query.setParameter("uWord", uWord);
            commit();
            size = query.getResultList().size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size > 0;
    }
}
