package com.mod.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
