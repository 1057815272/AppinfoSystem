package com.jbit.service;

import com.jbit.mapper.DevUserMapper;
import com.jbit.pojo.DevUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional( propagation = Propagation.SUPPORTS)
public class DevUserService {

    @Resource
    private DevUserMapper devUserMapper;

    public DevUser queryLogin(String devcode,String devpassword){
        DevUser devUser = new DevUser();
        devUser.setDevcode(devcode);
        devUser.setDevpassword(devpassword);
        return devUserMapper.selectOne(devUser);
    }
}
