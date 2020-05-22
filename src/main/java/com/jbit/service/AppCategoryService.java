package com.jbit.service;

import com.jbit.mapper.AppCategoryMapper;
import com.jbit.pojo.AppCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AppCategoryService {
    @Resource
    private AppCategoryMapper appCategoryMapper;

    public AppCategory queryById(Long id){
        return  appCategoryMapper.selectByPrimaryKey(id);
    }
}
