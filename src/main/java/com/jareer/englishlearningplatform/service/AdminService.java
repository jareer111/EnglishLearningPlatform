package com.jareer.englishlearningplatform.service;

import com.jareer.englishlearningplatform.dao.UserDAO;
import com.jareer.englishlearningplatform.domains.Users;

import java.util.List;

public class AdminService {


    private static final AdminService service = ThreadLocal.withInitial(AdminService::new).get();


    public static AdminService getInstance() {
        return service;
    }

    public  List<Users> usersList(Integer userId,Integer page, Integer size) {
        // todo check page and size
        List<Users> users = new UserDAO().getPage(userId,page, size);
        return users;
    }


    public boolean changeRole(Integer id, String role) {
        //todo check role is valid
        boolean result = new UserDAO().changeRole(id, role);
        return result;
    }

    public boolean changeDeleted(Integer userId, boolean deleted) {

        //todo check userId  is valid
        boolean result = new UserDAO().changeDeleted(Long.valueOf(userId), deleted);
        return result;
    }
}