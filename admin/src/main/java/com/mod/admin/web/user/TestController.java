package com.mod.admin.web.user;

import com.mod.admin.rabbitmq.test.provider.TestProvider;
import com.mod.common.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/8/8 11:09
 */
@RestController
@RequestMapping("test")
public class TestController{
    @Autowired
    private TestProvider testProvider;

    @GetMapping("/sendMsg")
    public Result sendMsg(){
        testProvider.test();
        return Result.success();
    }
}
