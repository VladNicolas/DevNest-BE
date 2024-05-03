package com.nicoals.DevNestBE.service;

import com.nicoals.DevNestBE.model.User;

public interface UserService {

    User findUserByJwt(String jwt) throws Exception;
    User findUserByEmail (String email) throws Exception;
    User findUserById (Long userId) throws Exception;
    User updateUsersProjectSize(User user, int number);
}