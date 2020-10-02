package cc.young.mongo.service.impl;

import cc.yound.common.core.exception.NotFoundException;
import cc.young.common.mongodb.entity.Opt;
import cc.young.common.mongodb.entity.PageResult;
import cc.young.common.mongodb.service.impl.BaseServiceImpl;
import cc.young.mongo.dao.UserRepository;
import cc.young.mongo.entity.User;
import cc.young.mongo.entity.example.UserExample;
import cc.young.mongo.service.IUserService;
import cc.young.mongo.vo.UserCountGroupBy;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> listInByIds(List<String> ids) {
        User user = new User();
        user.andId(Opt.IN,ids);
        return userRepository.findList(user);
    }


    @Override
    public User getById(String id) {
        return this.selectById(id);
    }

    @Override
    public PageResult<User> page(User user) {
        //1.对象将id赋值为null
        String id = user.getId();
        //1.1 排序
        user.addOrderBy("sex", Opt.DESC);
        user.addOrderBy("age",Opt.DESC);
        user.setId(null);
        //2.包含字段
        List<String> includeFields = new ArrayList<>();
//        includeFields.add("id");
//        includeFields.add("userName");
        //3.排除字段
        List<String> excludeFields= new ArrayList<>();
//        excludeFields.add("userName");
        //4.id不为空 以附加条件查询
        if (StrUtil.isNotEmpty(id)){
            user.andId( Opt.EQUAL,id);
        }
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
