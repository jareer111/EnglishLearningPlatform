package com.jareer.englishlearningplatform.dao;

import com.jareer.englishlearningplatform.domains.Story;
import com.jareer.englishlearningplatform.domains.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class StoryDAO extends BaseDAO<Story, Integer> {

    public List<Story> getPage(int page, int size) {
        List<Story> stories = new ArrayList<>();
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Story> query = em.createQuery("from Story ", Story.class);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            stories = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stories;
    }

    @Override
    public boolean delete(Story story) {
        delete(story.getId());
        return true;
    }


    public Story getStoryWithOption(String userLevel, Integer userId) {

        Story story = null;
        try (EntityManager em = emf.createEntityManager()) {
            story = (Story) em.createNativeQuery("select *\n" +
                            "from story\n" +
                            "where level = :level\n" +
                            "  and id not in (select story_id from user_story where user_id = :userId and is_saved = true);", Story.class)
                    .setParameter("level", userLevel)
                    .setParameter("userId", userId).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return story;
    }


    public List<Story> findAllStories() {
        List<Story> stories = null;
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Story> query = em.createQuery("from Story s where s.deleted=false order by createdAt desc", Story.class);
            stories = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stories;
    }

    public boolean delete(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createNativeQuery("update story set deleted = true where id = :id;").setParameter("id", id);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public Story getStoryWithOption(String userLevel, Long userId) {
        Story story = null;
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createNativeQuery("select * from story where level = :level and id not in (select story_id from user_story where user_id = :userId and is_saved = true) limit 1;", Story.class)
                    .setParameter("level", userLevel)
                    .setParameter("userId", userId);
            if (query.getResultList().isEmpty()) {
                return null;
            }
            story = (Story) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return story;
    }

    public List<Story> getStoriesById(List<Integer> storyIds) {
        List<Story> stories = null;
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Story> query = em.createQuery("from Story s where s.deleted=false and s.id in :storyIds", Story.class);
            query.setParameter("storyIds", storyIds);
            stories = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stories;
    }

    public Story getStoryById(long storyId) {
        Story story = null;
        try (EntityManager em = emf.createEntityManager()) {
            story = em.find(Story.class, storyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return story;
    }

    public static StoryDAO getInstance() {
        return new StoryDAO();
    }

    public List<Story> getStoryListByUserLevel(Long id) {
        List<Story> stories = null;
        try (EntityManager em = emf.createEntityManager()) {
            Users user = new UserDAO().findById(id);
            if (user == null) {
                return new ArrayList<>();
            }

            stories = em.createNativeQuery(
                            "select * from story s where s.level = :level and s.id in (select story_id from user_story where user_id = :userId and is_saved = true);", Story.class)
                    .setParameter("level", user.getLevel().name())
                    .setParameter("userId", id).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stories;
    }

}
