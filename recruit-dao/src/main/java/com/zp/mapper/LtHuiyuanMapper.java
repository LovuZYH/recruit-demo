package com.zp.mapper;


import java.util.List;

import com.zp.model.LtHuiyuan;
import com.zp.model.LtHuiyuanExample;
import org.apache.ibatis.annotations.Param;

public interface LtHuiyuanMapper {
    int countByExample(LtHuiyuanExample example);

    int deleteByExample(LtHuiyuanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LtHuiyuan record);

    int insertSelective(LtHuiyuan record);

    List<LtHuiyuan> selectByExample(LtHuiyuanExample example);

    LtHuiyuan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LtHuiyuan record, @Param("example") LtHuiyuanExample example);

    int updateByExample(@Param("record") LtHuiyuan record, @Param("example") LtHuiyuanExample example);

    int updateByPrimaryKeySelective(LtHuiyuan record);

    int updateByPrimaryKey(LtHuiyuan record);
}