package cc.young.mongo.dao;

import cc.young.mongo.entity.User;
import cc.young.mongo.vo.UserCountGroupBy;

import java.util.Collection;
import java.util.List;

public interface UserRepositoryEnhance {
    /**
     * 根据性别类型获取列表
     * @param sexType
     * @return
     */
    List<User> findListBySexType(String sexType);

    List<User> findListByIds(Collection<String> ids);

    List<UserCountGroupBy>  countUserGroupBySex(Integer sex);
}
