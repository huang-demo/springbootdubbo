package com.mod.admin.web.sys;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mod.common.core.Result;
import com.mod.common.web.BaseController;
import com.mod.sys.entity.dto.DictDTO;
import com.mod.sys.entity.dto.DictQueryDTO;
import com.mod.sys.entity.vo.DictVO;
import com.mod.sys.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
@RestController
@RequestMapping("/sys/dict")
@Api(tags = "系统模块-字典")
public class DictController extends BaseController {

    @Reference
    private IDictService dictService;

    @PostMapping("/saveOrUpdate")
    @ApiOperation("保存或者更新")
    public Result saveOrUpdate(@RequestBody DictDTO dto){
        dictService.insertOrUpdate(dto);
        return Result.success();
    }
    @PostMapping("/queryPage")
    @ApiOperation(value = "列表查询",response = DictVO.class)
    public Result queryPage(@RequestBody DictQueryDTO dto){
        Page<DictVO> page = dictService.queryPage(dto);
        return success(page);
    }



}
