package com.jareer.englishlearningplatform.listeners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebListener
public class ListenerApp implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public ListenerApp() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        try ( EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("english-learning-platform");
//              EntityManager entityManager = entityManagerFactory.createEntityManager();
//        ){
//            sce.getServletContext().setAttribute("entityManager", entityManager);
//        }

        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
