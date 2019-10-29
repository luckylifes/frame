package com.dj.cn.controller;

import com.dj.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Export the excle order report
     * @param responses responses
     * @param request request
     */
    @RequestMapping("/exportUserList")
    public void exportUserList(HttpServletRequest request, HttpServletResponse responses) {
        try {
            userService.exportExcleUser(request, responses, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
