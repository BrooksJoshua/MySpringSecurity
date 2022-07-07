package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.Client;
import org.springframework.stereotype.Repository;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-05 11:11
 */
@Repository
public interface ClientMapper {
    public Client loadUserByUsername(@Param("username") String username);
}
