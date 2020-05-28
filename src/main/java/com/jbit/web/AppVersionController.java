package com.jbit.web;

import com.jbit.pojo.AppVersion;
import com.jbit.service.AppInfoService;
import com.jbit.service.AppVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class AppVersionController {

    @Resource
    private AppVersionService appVersionService;

    @Resource
    private AppInfoService appInfoService;

    //反填版本信息
    @GetMapping("/appversionadd")
    public String queryByid(Model model,Long id){
        model.addAttribute("appVersionList",appVersionService.queryByAppid(id));
        return "developer/appversionadd";
    }
}
