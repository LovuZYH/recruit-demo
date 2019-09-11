package com.zp.service;

import com.zp.model.LtHuiyuan;
import entity.PageResult;

public interface HuiyuanService {


    public PageResult findAll(LtHuiyuan ltHuiyuan , Integer pageSize, Integer pageNum);

    public void add(LtHuiyuan contentCategory);

    public void delete(Long[] ids);

    public LtHuiyuan findOne(Long id);

}
