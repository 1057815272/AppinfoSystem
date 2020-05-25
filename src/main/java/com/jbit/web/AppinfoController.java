package com.jbit.web;

import com.jbit.pojo.DevUser;
import com.jbit.service.AppCategoryService;
import com.jbit.service.AppInfoService;
import com.jbit.service.DataDictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("dev/app")
public class AppinfoController {

    @Resource
    private AppInfoService appInfoService;

    @Resource
     private AppCategoryService appCategoryService;

    @Resource
    private DataDictionaryService dataDictionaryService;

    @RequestMapping("/list")
    public String list(HttpSession session, Model model,
                       @RequestParam(defaultValue = "1",value = "pageIndex") Integer pagenum,
                       String querySoftwareName,
                       Long queryStatus,
                       Long queryFlatformId,
                       Long queryCategoryLevel1,
                       Long queryCategoryLevel2,
                       Long queryCategoryLevel3
    ){
        DevUser devuser = (DevUser) session.getAttribute("devuser");
        model.addAttribute("pageInfo",appInfoService.queryAppInfo(pagenum,devuser.getId(),querySoftwareName,queryStatus,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3));
        //处理状态与所属平台
        model.addAttribute("statusList",dataDictionaryService.queryDataList("APP_STATUS"));
        model.addAttribute("flatFormList",dataDictionaryService.queryDataList("APP_FLATFORM"));
        //处理一级分类
        model.addAttribute("categoryLevel1List",appCategoryService.queryByPid(null));
        //处理二级分类
        if(queryCategoryLevel1 !=null){
            model.addAttribute("categoryLevel2List",appCategoryService.queryByPid(queryCategoryLevel1));
        }
        //处理三级分类
        if (queryCategoryLevel2!=null){
            model.addAttribute("categoryLevel3List",appCategoryService.queryByPid(queryCategoryLevel2));
        }
        model.addAttribute("querySoftwareName",querySoftwareName);
        model.addAttribute("queryStatus",queryStatus);
        model.addAttribute("queryFlatformId",queryFlatformId);
        model.addAttribute("queryCategoryLevel1",queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2",queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3",queryCategoryLevel3);
        return "developer/appinfolist";
    }
}
