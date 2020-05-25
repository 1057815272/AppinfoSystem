package com.jbit.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jbit.mapper.AppInfoMapper;
import com.jbit.pojo.AppInfo;
import com.jbit.pojo.AppVersion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppInfoService {
    @Resource
    private AppInfoMapper appInfoMapper;

    @Resource
    private DataDictionaryService dataDictionaryService;

    @Resource
    private AppCategoryService appCategoryService;

    @Resource
    private AppVersionService appVersionService;
    /*
    App列表查询 每一个dev登陆后只查看属于自己的appinfo
    * */
    public PageInfo queryAppInfo(Integer pagenum,Long devId){
        //实现分页
        PageHelper.startPage(pagenum,5);
        AppInfo appInfo = new AppInfo();
        appInfo.setDevid(devId);
        List<AppInfo> appInfos = appInfoMapper.select(appInfo);
        bindData(appInfos);
        //处理分页
        return new PageInfo<>(appInfos);
    }

    public void bindData(List<AppInfo> appInfos){
      /*  for (AppInfo app:appInfos){
            //所属平台
            app.setFlatformname(dataDictionaryService.queryData("APP_FLATFORM",app.getFlatformid()).getValuename());
            //分类
            app.setCategorylevel1name(appCategoryService.queryById(app.getCategorylevel1()).getCategoryname());
            app.setCategorylevel2name(appCategoryService.queryById(app.getCategorylevel1()).getCategoryname());
            app.setCategorylevel3name(appCategoryService.queryById(app.getCategorylevel1()).getCategoryname());
            //状态
            app.setStatusname(dataDictionaryService.queryData("APP_STATUS",app.getStatus()).getValuename());
            //版本号
            AppVersion appVersion = appVersionService.queryById(app.getVersionid());
            if (appVersion !=null){
                app.setVersionno(appVersion.getVersionno());
            }
        }*/
        appInfos.forEach((app)->{

            app.setFlatformname(dataDictionaryService.queryData("APP_FLATFORM",app.getFlatformid()).getValuename());

            app.setCategorylevel1name(appCategoryService.queryById(app.getCategorylevel1()).getCategoryname());
            app.setCategorylevel2name(appCategoryService.queryById(app.getCategorylevel2()).getCategoryname());
            app.setCategorylevel3name(appCategoryService.queryById(app.getCategorylevel3()).getCategoryname());

            app.setStatusname(dataDictionaryService.queryData("APP_STATUS",app.getStatus()).getValuename());

            AppVersion appVersion = appVersionService.queryById(app.getVersionid());
            if (appVersion !=null){
                app.setVersionno(appVersion.getVersionno());
            }
        });
    }
}
