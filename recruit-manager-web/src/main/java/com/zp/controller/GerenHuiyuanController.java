package com.zp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zp.model.LtGerenhuiyuan;
import com.zp.model.LtHuiyuan;
import com.zp.service.GerenHuiyuanService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gerenhuiyuan")
public class GerenHuiyuanController {

    @Reference
    private GerenHuiyuanService gerenHuiyuanService;

    //查询
    @RequestMapping("showAll")
    public PageResult findAll(LtGerenhuiyuan ltGerenhuiyuan, @RequestParam(defaultValue = "1") Integer page, Integer pageSize){
        return gerenHuiyuanService.findAll(ltGerenhuiyuan,pageSize,page);
    }

    //新增
    @RequestMapping("add")
    public Result add(LtGerenhuiyuan ltGerenhuiyuan) {
        try {
            gerenHuiyuanService.add(ltGerenhuiyuan);
            return new Result(true, "新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, "新增失败！");
    }


    //回显
    @RequestMapping("findById")
    public LtGerenhuiyuan findById(Long id){
        return gerenHuiyuanService.findOne(id);
    }

    //修改
    @RequestMapping("update")
    public Result update(LtGerenhuiyuan ltGerenhuiyuan){
        try {
            gerenHuiyuanService.add(ltGerenhuiyuan);
            return new Result(false,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false,"修改失败");
    }

    //批删
    @RequestMapping("delete")
    public Result delete(Long[] ids) {
        try {
            gerenHuiyuanService.delete(ids);
            return new Result(true, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"删除失败！");
    }


}
