package cc.young.mongo.controller;

import cc.yound.common.core.util.R;
import cc.young.common.mongodb.entity.PageResult;
import cc.young.mongo.entity.User;
import cc.young.mongo.service.IUserService;
import cc.young.mongo.vo.UserCountGroupBy;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "mongo测试")
public class UserController {

    @Autowired
    private IUserService userService;



    @GetMapping("/list")
    public R<List<User>> list() {
        return R.ok(userService.list());
    }

    @PostMapping("/page")
    public PageResult<User> page(@RequestBody User user) {
        return userService.page(user);
    }

    @GetMapping("/listInByIds")
    public R<List<User>> listInByIds(@RequestParam(value = "ids") List<String> ids) {
//        List<String> ids = new ArrayList<>();
//        ids.add("5f76b6e6efc1ab66dd498936");
//        ids.add("5f76b6efefc1ab66dd498937");
//        return R.ok(userService.listInByIds(ids));
        return R.ok(userService.selectBatchIds(ids));
    }

    @GetMapping("/detail")
    public R<User> detail(@RequestParam(value = "id") String id) {
        return R.ok(userService.selectById(id));
    }

    @PostMapping("/create")
    public R save(@RequestBody User user) {
        userService.save(user);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateById(User user) {
        userService.updateById(user);
        return R.ok();
    }

    @PostMapping("/del")
    public R del(@RequestParam(value = "id") String id) {
        userService.deleteById(id);
        return R.ok();
    }

    @PostMapping("/delbyIds")
    public R delbyIds(@RequestParam(value = "ids") List<String> ids) {
        userService.deleteBatchIds(ids);
        return R.ok();
    }

    @GetMapping("/list/sextype")
    public List<User> findListBySexType(String sexType) {
        return userService.findListBySexType(sexType);
    }

    @GetMapping("/count/groupby/sex")
    public List<UserCountGroupBy> countUserGroupBy(Integer sex) {
        return userService.countUserGroupBySex(sex);
    }
}
