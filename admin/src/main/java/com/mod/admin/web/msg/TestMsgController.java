package com.mod.admin.web.msg;

import com.mod.admin.rabbitmq.test.provider.DeadLetterProvider;
import com.mod.admin.rabbitmq.test.provider.TopicProvider;
import com.mod.common.core.Result;
import com.mod.common.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/8/9 9:48
 */
@RestController
@RequestMapping("/msg")
@Api(tags = "消息模块-测试")
public class TestMsgController extends BaseController{
    @Autowired
    private DeadLetterProvider deadLetterProvider;

    @Autowired
    private TopicProvider lazyProvider;

    @PostMapping("/sendDeadLetterMsg")
    public Result sendDeadLetterMsg(String msg){
        deadLetterProvider.send(msg);
        return success();
    }
    @PostMapping("/sendLazyMsg")
    public Result sendLazyMsg(String msg){
        lazyProvider.send(msg);
        return success();
    }
}
