/**
 * @ClassName RecruitServiceImpl.java
 * @author zyh
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019年09月09日 19:50:00
 */
package com.zp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zp.mapper.RecruitMapper;
import com.zp.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zyh
 * @version 1.0.0
 * @ClassName RecruitServiceImpl.java
 * @Description TODO
 * @createTime 2019年09月09日 19:50:00
 */
@Service(timeout = 60000)
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    private RecruitMapper recruitMapper;
}
