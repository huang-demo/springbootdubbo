package com.mod.sys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mod.sys.dao.UserInfoDao;
import com.mod.sys.entity.po.UserInfoPO;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-22
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoPO> implements IUserInfoService {

}
