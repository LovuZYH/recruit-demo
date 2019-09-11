package com.zp.service;

import com.zp.model.LtGerenhuiyuan;
import com.zp.model.LtHuiyuan;
import entity.PageResult;

public interface GerenHuiyuanService {

    public PageResult findAll(LtGerenhuiyuan ltGerenhuiyuan , Integer pageSize, Integer pageNum);

    public void add(LtGerenhuiyuan contentCategory);

    public void delete(Long[] ids);

    public LtGerenhuiyuan findOne(Long id);

}
