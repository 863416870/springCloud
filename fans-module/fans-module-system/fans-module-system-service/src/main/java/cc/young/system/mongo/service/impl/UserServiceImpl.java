package cc.young.system.mongo.service.impl;

import cc.young.common.mongodb.entity.Opt;
import cc.young.common.mongodb.entity.PageResult;
import cc.young.common.mongodb.service.impl.BaseServiceImpl;
import cc.young.system.mongo.dao.UserRepository;
import cc.young.system.mongo.entity.User;
import cc.young.system.mongo.service.IUserService;
import cc.young.system.mongo.vo.UserCountGroupBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,String> implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> list() {
        User user = new User();
//        user.setUserName("小明");
//        Example<User> sExample = Example.of(user);
        List<User> all = userRepository.findAll();
        return all;
    }

    @Override
    public PageResult<User> page(User user) {
        user.addOrderBy("sex", Opt.DESC);
        user.addOrderBy("age",Opt.DESC);
        return userRepository.findPage(user);
    }

    @Override
    public List<User> findListBySexType(String sexType) {
        return userRepository.findListBySexType(sexType);
    }

    @Override
    public List<UserCountGroupBy> countUserGroupBySex(Integer sex) {
        return userRepository.countUserGroupBySex(sex);
    }
}
