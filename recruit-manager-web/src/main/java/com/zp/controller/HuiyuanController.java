package com.zp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zp.model.LtHuiyuan;
import com.zp.service.HuiyuanService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("huiyuan")
public class HuiyuanController {

    @Reference
    private HuiyuanService huiyuanService;



    //查询
    @RequestMapping("showAll")
    public PageResult findAll(LtHuiyuan ltHuiyuan, @RequestParam(defaultValue = "1") Integer page, Integer pageSize){
        return huiyuanService.findAll(ltHuiyuan,pageSize,page);
    }

    //新增
    @RequestMapping("add")
    public Result add(LtHuiyuan ltHuiyuan) {
        try {
            huiyuanService.add(ltHuiyuan);
            return new Result(true, "新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, "新增失败！");
    }


    //回显
    @RequestMapping("findById")
    public LtHuiyuan findById(Long id){
        return huiyuanService.findOne(id);
    }

    //修改
    @RequestMapping("update")
    public Result update(LtHuiyuan ltHuiyuan){
        try {
            huiyuanService.add(ltHuiyuan);
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
            huiyuanService.delete(ids);
            return new Result(true, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"删除失败！");
    }




}
