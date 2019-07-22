package com.mod.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mod.user.entity.dto.UserInfoPageDTO;
import com.mod.user.entity.po.UserInfoPO;
import com.mod.user.entity.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-22
 */
public interface UserInfoDao extends BaseMapper<UserInfoPO> {


    /**
     *getUserInfoById
     * @param userId
     * @return
     */
    UserInfoVO getUserInfoById(@Param("userId") Long userId);

    /**
     * queryPage
     * @param page
     * @param param
     * @return
     */
    List<UserInfoVO> queryPage(Page<UserInfoVO> page,UserInfoPageDTO param);
}
