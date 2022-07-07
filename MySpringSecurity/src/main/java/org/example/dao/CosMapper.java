package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.Client;
import org.example.model.Cos;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-05 11:17
 */
@Repository
public interface CosMapper {
    public List<Cos> loadCosesByUid(@Param("clientId") Long cid);
}
