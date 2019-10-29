package com.dj.cn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.cn.domain.entry.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService extends IService<User> {
    /**
     * findUserById
     * @param id
     */
    List<User> findUserById(String id);

    /**
     * exportExcleUser
     * @param request
     * @param responses
     * @param i
     */
    void exportExcleUser(HttpServletRequest request, HttpServletResponse responses, int i);
}
