package com.mod.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mod.common.constant.ExceptionCode;
import com.mod.common.entity.vo.TokenVO;
import com.mod.common.exception.GlobalException;
import com.mod.common.exception.ValidateParamException;
import com.mod.common.utils.JwtUtils;
import com.mod.common.utils.MD5Util;
import com.mod.user.dao.UserInfoDao;
import com.mod.user.entity.dto.LoginUserDTO;
import com.mod.user.entity.dto.SysUserDTO;
import com.mod.user.entity.dto.UserInfoIdDTO;
import com.mod.user.entity.dto.UserInfoPageDTO;
import com.mod.user.entity.po.LoginLogPO;
import com.mod.user.entity.po.UserInfoPO;
import com.mod.user.entity.vo.UserInfoVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-22
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoPO> implements IUserInfoService {

    @Value("${shrio.login.secret:#EwFgh}")
    private String secret;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private ILoginLogService loginLogService;

    @Override
    public UserInfoVO getUserInfoById(UserInfoIdDTO dto) {

        return userInfoDao.getUserInfoById(dto.getUserId());
    }

    @Override
    public Page<UserInfoVO> queryPage(UserInfoPageDTO param) {
        Page<UserInfoVO> page = new Page<>(param.getPageIndex(), param.getPageSize());
        page.setRecords(userInfoDao.queryPage(page, param));
        return page;
    }

    @Override
    public TokenVO createToken(Long userId) {
        UserInfoPO userInfoPO = this.getById(userId);
        if (userInfoPO == null) {
            throw new GlobalException(ExceptionCode.USER_NOT_EXIST);
        }
        String sign = JwtUtils.sign(userInfoPO.getOpenId(), userInfoPO.getUserName(), userInfoPO.getPassword());
        TokenVO token = new TokenVO();
        token.setToken(sign);
        return token;
    }

    @Override
    public SysUserDTO findByUserName(String userName) {
        SysUserDTO user = userInfoDao.findByUserName(userName);
        if (user == null) {
            throw new ValidateParamException(ExceptionCode.USER_NOT_EXIST);
        }
        return user;
    }

    @Override
    public SysUserDTO login(LoginUserDTO dto) {
        SysUserDTO user = findByUserName(dto.getUserName());
        String encode = MD5Util.encode(dto.getPassword());
        boolean pass = encode.equals(user.getPassword());
        Integer state = pass ? LoginLogPO.LoginState.SUCCESS.getCode() : LoginLogPO.LoginState.ERR.getCode();
        //登录日志
        saveLoginLog(user, LoginLogPO.LoginType.LOGIN.getCode(), state);
        if (!pass) {
            throw new ValidateParamException("密码错误！");
        }
        return user;
    }

    @Override
    public void logout(SysUserDTO user) {
        //退出日志
        saveLoginLog(user, LoginLogPO.LoginType.LOGOUT.getCode(), LoginLogPO.LoginState.SUCCESS.getCode());
    }

    private void saveLoginLog(SysUserDTO userDTO, Integer type, Integer state) {

        LoginLogPO loginLog = new LoginLogPO();
        loginLog.setState(state);
        loginLog.setType(type);
        loginLog.setUserId(userDTO.getUserId());
        loginLog.setUserName(userDTO.getUserName());
        loginLogService.save(loginLog);
    }

}
