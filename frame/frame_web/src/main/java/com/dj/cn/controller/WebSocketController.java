package com.dj.cn.controller;

/**
 * @version V1.0
 * @Title: WebSocketController
 * @Package: commercial.web.controller
 * @author: Lenovo
 * @date: 2019/7/30 17:43
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */

import com.dj.cn.domain.entry.User;
import com.dj.cn.service.UserService;
import com.dj.cn.util.CollectionUtils;
import com.dj.cn.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * WebSocket服务器端推送消息示例Controller
 *
 * @author wallimn，http://wallimn.iteye.com
 *
 */
@RestController
@RequestMapping("/api")
public class WebSocketController {
    @Autowired
    private UserService userService;
    @RequestMapping("/messageAll")
    /**
     * 群发消息内容
     * @param message
     * @return
     */
    String sendAllMessage(@RequestParam(required=true) String message){
        try {
            WebSocketServer.BroadCastInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
    @RequestMapping("/messageOne")
    /**
     * 指定会话ID发消息
     * @param message 消息内容
     * @param id 连接会话ID
     * @return
     */
    String sendOneMessage(@RequestParam(required=true) String message, @RequestParam(required=true) String id){
        try {

            List<User> userList = userService.findUserById(id);
            //排序
            Collections.sort(userList, Comparator.comparing(User::getUsername));
            List<User> paging = CollectionUtils.paging(userList, 1, 1);
            userList.size();


            String serialize = JsonUtils.serialize(userList);
            WebSocketServer.SendMessage(id,message+serialize);
          /*  userList.forEach(user -> {
                if (user.getCreated().getTime() > System.currentTimeMillis()) {

                    try {
                        String serialize = JsonUtils.serialize(userList);
                        WebSocketServer.SendMessage(id,message+serialize);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
               }
            });
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "用户参数为"+id+ System.currentTimeMillis();
    }
}
