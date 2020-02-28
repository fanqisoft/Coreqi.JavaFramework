package cn.coreqi.controller;

import cn.coreqi.core.ProjectException;
import cn.coreqi.core.ResWrapper;
import cn.coreqi.entity.TUser;
import cn.coreqi.services.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有项
     *
     * @param pageable
     * @return
     */
    @GetMapping(path = "/fetchList")
    public ResWrapper fetchList(
            @PageableDefault(
                    size = 10,
                    page = 1,
                    sort = {"createTime"},
                    direction = Sort.Direction.DESC
            ) Pageable pageable) throws ProjectException {

        Page<TUser> page = new Page<>(1,10);

        IPage<TUser> data = userService.selectUserPage(page);
        return ResWrapper.Success(data);
    }
}
