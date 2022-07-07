package org.example.service;

import org.example.dao.ClientMapper;
import org.example.dao.CosMapper;
import org.example.model.Client;
import org.example.model.Cos;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-05 11:12
 */
@Service
public class ClientService implements UserDetailsService {
    @Resource
    ClientMapper clientMapper;
    @Resource
    CosMapper cosMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientMapper.loadUserByUsername(username);
        System.out.println("查询到的 client = " + client);
        if(client == null){
            throw new UsernameNotFoundException("当前用户名不存在: "+username);
        }
        System.out.println("其具有的角色权限如下");
        List<Cos> coses = cosMapper.loadCosesByUid(client.getId());
        client.setCoses(coses);
        coses.stream().forEach(System.out::println);
        return client;
    }
}
