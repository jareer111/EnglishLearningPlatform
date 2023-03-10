package com.jareer.englishlearningplatform.dao;

import com.jareer.englishlearningplatform.domains.Users;
import com.jareer.englishlearningplatform.enums.Roles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO<Users, Long> {
    public List<Users> getPage(int page, int size) {
        List<Users> users = new ArrayList<>();
        TypedQuery<Users> query;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            query = em.createQuery("from Users ", Users.class);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            em.getTransaction().commit();
            users = query.getResultList();
        }
        return users;
    }

    public boolean changeRole(Integer id, String path) {
        int i;
        try (EntityManager em = emf.createEntityManager()) {
            Roles role = null;
            switch (path) {
                case "admin" -> role = Roles.ADMIN;
                case "teacher" -> role = Roles.TEACHER;
                case "user" -> role = Roles.USER;
            }

            em.getTransaction().begin();
            i = em.createQuery("update Users set role = :role where id = :id")
                    .setParameter("role", role)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        }
        return i > 0;
    }

    public List<Users> findAll() {
        List<Users> users = new ArrayList<>();
        Query query;
        try (EntityManager em = emf.createEntityManager()) {
            query = em.createQuery("select u from Users u", Users.class);
            users = query.getResultList();
        }
        return users;
    }

    public Users findByUsername(String username) {
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery("select u from Users u where u.username = :username", Users.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
//    public void updateLastTestID(Integer userId, int i) {
//        em.getTransaction().begin();
//        Users user = findById(Long.valueOf(userId));
//        user.setLastTestID(i);
//        update(user);
//        em.getTransaction().commit();
//    }

//    public void updateScore(Integer userId, int i) {
//        em.getTransaction().begin();
//        Users users = findById(Long.valueOf(userId));
//        users.setScore(i);
//        update(users);
//        em.getTransaction().commit();
//    }

    public void updateLastTestID(Integer userId, int i) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Users user = findById(Long.valueOf(userId));
            user.setLastTestID(i);
            update(user);
            em.getTransaction().commit();
        }
    }

    public void updateScore(Integer userId, int i) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Users users = findById(Long.valueOf(userId));
            users.setScore(i);
            update(users);
            em.getTransaction().commit();
        }
    }

}
