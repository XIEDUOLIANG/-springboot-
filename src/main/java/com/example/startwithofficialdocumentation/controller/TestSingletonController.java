package com.example.startwithofficialdocumentation.controller;

import com.example.startwithofficialdocumentation.StartwithofficialdocumentationApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XieDuoLiang
 * @date 2020/11/18 下午7:16
 */
@RestController
@RequestMapping("/single")
public class TestSingletonController {

    static Integer countNum = 0;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public synchronized String test(@RequestParam("num") Integer num) {
        countNum += num;
        System.out.println(countNum);
        return String.valueOf(countNum);
    }

    @RequestMapping(value = "/testexception",method = RequestMethod.POST)
    public int testex(@RequestParam("num") Integer num) {
        int a = 1;
        int b = 0;
        return a/b;
    }
}
