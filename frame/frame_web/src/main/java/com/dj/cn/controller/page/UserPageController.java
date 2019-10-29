package com.dj.cn.controller.page;

import com.alibaba.fastjson.JSONObject;
import com.dj.cn.domain.entry.User;
import com.dj.cn.util.JsonUtils;
import com.dj.cn.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/user/")
public class UserPageController {

    /**
     *  去登录
     * @param
     * @return
     */
    @GetMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }
    /**
     *  去登录
     * @param
     * @return
     */
    @GetMapping("toScoket")
    public String toScoket() {
        return "user/test_scoket";

    }
    /**
     *  去注册
     * @param
     * @return
     */
    @RequestMapping("toRegister")
    public String toRegister(String userName) {
        if (StringUtils.isNotEmpty(userName)) {

        }
        return "user/register";
    }


    @RequestMapping("toRegisterTest")
    public String toRegisterTest() {
        try {
            JsonUtils.serialize(new User());
            String a ="{\"result\":{\"rows\":[{\"Subject\":\"关于更新巡视整改台账的通知（2019-07-28）\",\"DraftDate\":\"2019-7-28\",\"Drafter\":\"技术支持\",\"DraftDeptOffice\":\"信息中心\\系统运行处\",\"NOs\":\"1|2|3|4|5\",\"OutstandingProblems\":\"突出问题1|突出问题1|突出问题2|突出问题2|突出问题2\",\"SpecificProblems\":\"具体问题1|具体问题2|具体问题1|具体问题2|具体问题3\",\"Measures\":\"&#65535;\n" +
                    "整改举措1| 整改举措1| 整改举措1| 整改举措2| 整改举措3\",\"EndDates\":\" 2019年2月| 2019年2月| 2019年2月| \n" +
                    "2019年2月| 2019年2月\",\"MainLeaders\":\" 王选文| 王选文| 王选文| 王选文| 王选文\",\"MainDepts\":\" 办公厅| \n" +
                    "办公厅| 办公厅| 办公厅| \n" +
                    "办公厅\",\"Progresss\":\"正按序推进|正按序推进|正按序推进|正按序推进|正按序推进\"},{\"Subject\":\"关于更新巡视整改台账的通知（2019-07-27）\",\"DraftDate\":\"2019-7-27\",\"Drafter\":\"技术支持\",\"DraftDeptOffice\":\"信息中心\\系统运行处\",\"NOs\":\"1|2|3|4|5|6\",\"OutstandingProblems\":\"\",\"SpecificProblems\":\"\",\"Measures\":\"\",\"EndDates\":\"\",\"MainLeaders\":\"\",\"MainDepts\":\"\",\"Progresss\":\"\"}],\"message\":&#65535;\n" +
                    "\"获取json成功\",\"success\":\"true\",\"total\":\"2\"}} ";
            //JSONObject.parseObject(a);
            Map<String,Object> toom = JSONObject.parseObject(a, Map.class);
            List list = JSONObject.parseObject(a, List.class);
            Set set = JSONObject.parseObject(a, Set.class);
            List list1 = JSONObject.parseObject(a, ArrayList.class);
            Objects objects = JSONObject.parseObject(a, Objects.class);//User
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "user/register_test";
    }
    /**
     *  去index
     * @param
     * @return
     */
    @GetMapping("toIndex")
    public String toIndex() {
        return "frameset/index";
    }
    /**
     * toTop
     * @return
     */
    @GetMapping("toTop")
    public String toTop() {
        return "frameset/top";
    }
    /**
     *toRight
     * @return
     */
    @GetMapping("toRight")
    public String toRight() {
        return "frameset/right";
    }
    /**
     * toLeft
     * @return
     */
    @GetMapping("toLeft")
    public String toLeft() {
        return "frameset/left";
    }

}
