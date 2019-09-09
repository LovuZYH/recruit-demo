/**
 * @ClassName RecruitController.java
 * @author zyh
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019年09月09日 19:51:00
 */
package com.zp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zp.service.RecruitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyh
 * @version 1.0.0
 * @ClassName RecruitController.java
 * @Description TODO
 * @createTime 2019年09月09日 19:51:00
 */
@RestController
@RequestMapping("recruit")
public class RecruitController {

    @Reference(timeout = 60000)
    private RecruitService recruitService;


}
