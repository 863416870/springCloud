package cc.young.system.mongo.service.impl;

import cc.young.common.mongodb.entity.Opt;
import cc.young.common.mongodb.entity.PageResult;
import cc.young.common.mongodb.service.impl.BaseServiceImpl;
import cc.young.system.mongo.dao.UserRepository;
import cc.young.system.mongo.entity.User;
import cc.young.system.mongo.entity.example.UserExample;
import cc.young.system.mongo.service.IUserService;
import cc.young.system.mongo.vo.UserCountGroupBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,String> implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> list() {
        User user = new User();
//        user.andId(Opt.EQUAL,"5f670c7496fe3936cba1afca");
//        user.setId("5f670c7496fe3936cba1afca");
//        Example<User> sExample = Example.of(user);
//        UserExample userExample = new UserExample();
//        user.andId(Opt.AND,"5f670c7496fe3936cba1afca");
        List<User> all = userRepository.findList(user);
        return all;
    }

    @Override
    public List<User> listin() {

        Collection<String> ids = new ArrayList<>();
        ids.add("5f670c7496fe3936cba1afca");
        ids.add("5f670c8c96fe3936cba1afcb");
//        List<User> all = userRepository.findListByIds(ids);
        User user = new User();
        user.andId(Opt.IN,ids);
        List<User> list = userRepository.findList(user);
        return list;
    }

    @Override
    public PageResult<User> page(User user) {
        user.addOrderBy("sex", Opt.DESC);
        user.addOrderBy("age",Opt.DESC);
        List<String> includeFields = new ArrayList<>();
//        includeFields.add("id");
//        includeFields.add("userName");
        List<String> excludeFields= new ArrayList<>();
        excludeFields.add("userName");
        return userRepository.findPage(user,includeFields,excludeFields);
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
