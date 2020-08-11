package cn.coreqi.controller;

import io.swagger.annotations.Api;
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
