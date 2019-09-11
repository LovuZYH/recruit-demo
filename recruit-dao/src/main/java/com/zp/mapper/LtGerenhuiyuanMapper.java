package com.zp.mapper;


import java.util.List;

import com.zp.model.LtGerenhuiyuan;
import com.zp.model.LtGerenhuiyuanExample;
import org.apache.ibatis.annotations.Param;

public interface LtGerenhuiyuanMapper {
    int countByExample(LtGerenhuiyuanExample example);

    int deleteByExample(LtGerenhuiyuanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LtGerenhuiyuan record);

    int insertSelective(LtGerenhuiyuan record);

    List<LtGerenhuiyuan> selectByExample(LtGerenhuiyuanExample example);

    LtGerenhuiyuan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LtGerenhuiyuan record, @Param("example") LtGerenhuiyuanExample example);

    int updateByExample(@Param("record") LtGerenhuiyuan record, @Param("example") LtGerenhuiyuanExample example);

    int updateByPrimaryKeySelective(LtGerenhuiyuan record);

    int updateByPrimaryKey(LtGerenhuiyuan record);
}