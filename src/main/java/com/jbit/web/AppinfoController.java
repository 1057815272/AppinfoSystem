package com.jbit.web;

import com.jbit.pojo.DevUser;
import com.jbit.service.AppInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("dev/app")
public class AppinfoController {

    @Resource
    private AppInfoService appInfoService;

    @RequestMapping("/list")
    public String list(HttpSession session, Model model){
        DevUser devuser = (DevUser) session.getAttribute("devuser");
        model.addAttribute("appInfoList",appInfoService.queryAppInfo(devuser.getId()));
        return "developer/appinfolist";
    }
}
