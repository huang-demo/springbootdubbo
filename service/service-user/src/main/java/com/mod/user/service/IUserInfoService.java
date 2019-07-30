package com.mod.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mod.common.entity.vo.TokenVO;
import com.mod.user.entity.dto.LoginUserDTO;
import com.mod.user.entity.dto.SysUserDTO;
import com.mod.user.entity.dto.UserInfoIdDTO;
import com.mod.user.entity.dto.UserInfoPageDTO;
import com.mod.user.entity.po.UserInfoPO;
import com.mod.user.entity.vo.UserInfoVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-22
 */
public interface IUserInfoService extends IService<UserInfoPO> {

    /**
     * getUserInfoById
     * @param dto
     * @return
     */
    UserInfoVO getUserInfoById(UserInfoIdDTO dto);

    /**
     * queryPage
     * @param param
     * @return
     */
    Page<UserInfoVO> queryPage(UserInfoPageDTO param);

    /**
     * createToken
     * @param userId
     * @return
     */
    TokenVO createToken(Long userId);

    /**
     *findByUserName
     * @param userName
     * @return
     */
    SysUserDTO findByUserName(String userName);

    /**
     * login
     * @param dto
     * @return
     */
    SysUserDTO login(LoginUserDTO dto);

    /**
     * logout
     * @param user
     */
    void logout(SysUserDTO user);
}
