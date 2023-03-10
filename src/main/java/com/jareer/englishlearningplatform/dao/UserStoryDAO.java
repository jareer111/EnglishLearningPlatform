
package com.jareer.englishlearningplatform.dao;

import com.jareer.englishlearningplatform.domains.User_Story;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

public class UserStoryDAO extends BaseDAO<User_Story, Integer> {


    public boolean isSaved( Integer storyId, Integer userId ) {
        try (EntityManager em = emf.createEntityManager()) {
            User_Story user_story = (User_Story) em.createNativeQuery("select * from user_story where story_id = :story_id and user_id = :user_id;", User_Story.class)
                    .setParameter("user_id", storyId)
                    .setParameter("story_id", storyId)
                    .getSingleResult();
            if (Objects.isNull(user_story)) return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }


    public List<Integer> getStoriesByUserId(Integer userId) {
        List<Integer> storyIds=null;
        try (EntityManager em = emf.createEntityManager()) {
            storyIds = em.createNativeQuery("select story_id from user_story where user_id = :userId", Integer.class)
                    .setParameter("userId", userId)
                    .getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return storyIds;
    }
}
