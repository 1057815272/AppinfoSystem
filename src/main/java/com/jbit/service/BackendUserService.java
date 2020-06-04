package com.jbit.service;

import com.jbit.mapper.BackendUserMapper;
import com.jbit.pojo.BackendUser;
import com.jbit.utils.AppUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Service
public class BackendUserService {

    @Resource
    private BackendUserMapper backendUserMapper;

    @Resource
    private DataDictionaryService dataDictionaryService;

    public BackendUser queryLogin(String usercode,String userpassword){
        BackendUser backendUser = new BackendUser();
        backendUser.setUsercode(usercode);
        backendUser.setUserpassword(AppUtils.encoderByMd5(userpassword));
        BackendUser backuser = backendUserMapper.selectOne(backendUser);
        //处理角色名称
        backuser.setUsertypename(dataDictionaryService.queryData("USER_TYPE",backuser.getUsertype()).getValuename());
        return backuser;
    }



}
