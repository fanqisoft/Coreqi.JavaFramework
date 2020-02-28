package cn.coreqi.controller;

import cn.coreqi.core.ProjectException;
import cn.coreqi.core.ResWrapper;
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

    /**
     * 获取所有项
     *
     * @param pageable
     * @return
     */
//    @GetMapping(path = "/fetchList")
//    public ResWrapper fetchList(
//            @RequestParam(required = false) String contract_name,
//            @RequestParam(required = false) String contract_no,
//            @RequestParam(required = false) String trades_sort,
//            @RequestParam(required = false) String first_party_work,
//            @RequestParam(required = false) String project_date,
//            @PageableDefault(
//                    size = 10,
//                    page = 1,
//                    sort = {"stockinTime"},
//                    direction = Sort.Direction.DESC
//            ) Pageable pageable) throws ProjectException {
//
//        Page<TContractData> page = new Page<>(1,10);
//
//        IPage<TContractData> data = contractManageService.selectContractPage(page,contract_name,contract_no,
//                trades_sort,first_party_work,project_date);
//        return ResWrapper.Success(data);
//    }
}
