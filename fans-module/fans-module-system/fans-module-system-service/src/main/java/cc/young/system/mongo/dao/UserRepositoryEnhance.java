package cc.young.system.mongo.dao;

import cc.young.system.mongo.entity.User;
import cc.young.system.mongo.vo.UserCountGroupBy;

import java.util.List;

public interface UserRepositoryEnhance {
    /**
     * 根据性别类型获取列表
     * @param sexType
     * @return
     */
    List<User> findListBySexType(String sexType);

    List<UserCountGroupBy>  countUserGroupBySex(Integer sex);
}
