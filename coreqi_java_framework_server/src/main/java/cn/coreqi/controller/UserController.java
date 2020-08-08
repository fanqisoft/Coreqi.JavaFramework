package cn.coreqi.controller;

import cn.coreqi.core.ProjectException;
import cn.coreqi.core.ResWrapper;
import cn.coreqi.core.TUserModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Api(tags = "暴露系统用户数据控制器")
public class UserController {

//    @Autowired
//    private UserService userService;
//
//    /**
//     * 获取所有项
//     *
//     * @param pageable
//     * @return
//     */
//    @GetMapping(path = "/fetchList")
//    @ApiOperation(value = "查询系统中的所有用户")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageable",value = "分页数据", required = true),
//    })
//    public ResWrapper fetchList(
//            @PageableDefault(
//                    size = 10,
//                    page = 1,
//                    sort = {"createTime"},
//                    direction = Sort.Direction.DESC
//            ) Pageable pageable) throws ProjectException {
//
//        Page<TUserModel> page = new Page<>(1,10);
//
//        IPage<TUserModel> data = userService.selectUserPage(page);
//        return ResWrapper.Success(data);
//    }
}
