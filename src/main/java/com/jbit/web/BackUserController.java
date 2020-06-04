package com.jbit.web;

import com.jbit.pojo.BackendUser;
import com.jbit.service.AppInfoService;
import com.jbit.service.AppVersionService;
import com.jbit.service.BackendUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("backend")
public class BackUserController {

    @Resource
    private BackendUserService backendUserService;

    @Resource
    private AppVersionService appVersionService;

    /*后台登陆*/
    @PostMapping("login")
    public String login(HttpSession session,String usercode,String userpassword){
        BackendUser backendUser = backendUserService.queryLogin(usercode, userpassword);
        if (backendUser!=null){
            session.setAttribute("backuser",backendUser);
            return "redirect:/jsp/backend/main.jsp";
        }
        return "backendlogin";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "backendlogin";
    }


}
