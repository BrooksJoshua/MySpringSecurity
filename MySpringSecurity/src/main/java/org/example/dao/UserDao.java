package org.example.dao;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-02 23:03
 */
public interface UserDao extends JpaRepository<User,Long> {
    User findUserByUsername(String name);
}
