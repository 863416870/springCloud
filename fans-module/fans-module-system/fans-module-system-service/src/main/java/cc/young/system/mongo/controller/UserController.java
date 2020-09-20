package cc.young.system.mongo.controller;

import cc.yound.common.core.util.R;
import cc.young.common.mongodb.entity.PageResult;
import cc.young.system.mongo.entity.User;
import cc.young.system.mongo.service.IUserService;
import cc.young.system.mongo.vo.UserCountGroupBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/save")
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("/list")
    public R<List<User>> list() {

        return R.ok(userService.list());
    }

    @GetMapping("/listin")
    public R<List<User>> listin() {
        return R.ok(userService.listin());
    }

    @PostMapping("/page")
    public PageResult<User> page(@RequestBody User user) {
        return userService.page(user);
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
