package cc.young.system.mongo.service;

import cc.young.common.mongodb.entity.PageResult;
import cc.young.common.mongodb.service.IBaseService;
import cc.young.system.mongo.entity.User;
import cc.young.system.mongo.vo.UserCountGroupBy;

import java.util.List;

public interface IUserService extends IBaseService<User,String> {

    List<User> list();

    PageResult<User> page(User user);

    List<User> findListBySexType(String sexType);

    List<UserCountGroupBy> countUserGroupBySex(Integer sex);

}
