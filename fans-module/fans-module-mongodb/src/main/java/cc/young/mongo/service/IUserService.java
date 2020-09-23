package cc.young.mongo.service;

import cc.young.common.mongodb.entity.PageResult;
import cc.young.common.mongodb.service.IBaseService;
import cc.young.mongo.entity.User;
import cc.young.mongo.vo.UserCountGroupBy;

import java.util.List;

public interface IUserService extends IBaseService<User,String> {

    List<User> list();
    List<User> listin();
    PageResult<User> page(User user);

    List<User> findListBySexType(String sexType);

    List<UserCountGroupBy> countUserGroupBySex(Integer sex);

}
