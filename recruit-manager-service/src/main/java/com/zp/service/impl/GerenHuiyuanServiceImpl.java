package com.zp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zp.mapper.LtGerenhuiyuanMapper;
import com.zp.model.LtGerenhuiyuan;

import com.zp.model.LtGerenhuiyuanExample;

import com.zp.service.GerenHuiyuanService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GerenHuiyuanServiceImpl implements GerenHuiyuanService {

    @Autowired
    private LtGerenhuiyuanMapper ltGerenhuiyuanMapper;

    @Override
    public PageResult findAll(LtGerenhuiyuan ltGerenhuiyuan, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        LtGerenhuiyuanExample example = new LtGerenhuiyuanExample();
        LtGerenhuiyuanExample.Criteria criteria = example.createCriteria();
        if(ltGerenhuiyuan!=null){
            if(ltGerenhuiyuan.getUsername()!=null && ltGerenhuiyuan.getUsername().length()>0){
                criteria.andUsernameLike("%"+ltGerenhuiyuan.getUsername()+"%");
            }
        }
        Page<LtGerenhuiyuan> tbContents = (Page<LtGerenhuiyuan>) ltGerenhuiyuanMapper.selectByExample(example);
        return new PageResult(tbContents.getTotal(),tbContents.getResult());
    }



    //新增or修改
    @Override
    public void add(LtGerenhuiyuan ltGerenhuiyuan) {
        if(ltGerenhuiyuan.getId()==null){
            ltGerenhuiyuanMapper.insert(ltGerenhuiyuan);
        }else if(ltGerenhuiyuan.getId()!=null){
            ltGerenhuiyuanMapper.updateByPrimaryKey(ltGerenhuiyuan);
        }
    }

    //回显
    @Override
    public LtGerenhuiyuan findOne(Long id) {
        return ltGerenhuiyuanMapper.selectByPrimaryKey(id);
    }


    //批删
    @Override
    public void delete(Long[] ids) {
        for (Long id: ids) {
            ltGerenhuiyuanMapper.deleteByPrimaryKey(id);
        }
    }
}
