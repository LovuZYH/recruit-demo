package com.zp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zp.mapper.LtHuiyuanMapper;
import com.zp.model.LtHuiyuan;
import com.zp.model.LtHuiyuanExample;
import com.zp.service.HuiyuanService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class HuiyuanServiceImpl implements HuiyuanService {

    @Autowired
    private LtHuiyuanMapper ltHuiyuanMapper;

    @Override
    public PageResult findAll(LtHuiyuan ltHuiyuan, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        LtHuiyuanExample example = new LtHuiyuanExample();
        LtHuiyuanExample.Criteria criteria = example.createCriteria();
       if(ltHuiyuan!=null){
            if(ltHuiyuan.getUsername()!=null && ltHuiyuan.getUsername().length()>0){
                criteria.andUsernameLike("%"+ltHuiyuan.getUsername()+"%");
            }
        }
        Page<LtHuiyuan> tbContents = (Page<LtHuiyuan>) ltHuiyuanMapper.selectByExample(example);
        return new PageResult(tbContents.getTotal(),tbContents.getResult());
    }



    //新增or修改
    @Override
    public void add(LtHuiyuan ltHuiyuan) {
        if(ltHuiyuan.getId()==null){
            ltHuiyuanMapper.insert(ltHuiyuan);
        }else if(ltHuiyuan.getId()!=null){
            ltHuiyuanMapper.updateByPrimaryKey(ltHuiyuan);
        }
    }

    //回显
    @Override
    public LtHuiyuan findOne(Long id) {
        return ltHuiyuanMapper.selectByPrimaryKey(id);
    }


    //批删
    @Override
    public void delete(Long[] ids) {
        for (Long id: ids) {
            ltHuiyuanMapper.deleteByPrimaryKey(id);
        }
    }
}
