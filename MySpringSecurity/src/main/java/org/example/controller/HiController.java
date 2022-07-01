package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-01 01:22
 */
@RestController
public class HiController {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String hi(){
        return "hi spring security";
    }
    @RequestMapping(value = "/success",method = RequestMethod.POST)
    public String successP(){
        return "Congrats! Successfully logged in";
    }
    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String success(){
        return "Congrats! ";
    }
}
