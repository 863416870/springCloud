package cc.young.mongo.service;

import cc.young.common.mongodb.entity.PageResult;
import cc.young.common.mongodb.service.IBaseService;
import cc.young.mongo.dto.UserDTO;
import cc.young.mongo.entity.User;
import cc.young.mongo.vo.UserCountGroupBy;

import java.util.List;

public interface IUserService extends IBaseService<User,String> {

    /**
     * 列表查询
     * @return
     */
    List<User> list();

    /**
     * 分行查询
     * @param user
     * @return
     */
    PageResult<User> page(User user);

    /**
     *  in 查询
     * @return
     */
    List<User> listInByIds(List<String> ids);

    /**
     *  详情
     * @return
     */
    User getById(String id);

    /**
     * 性别查询
     * @param sexType
     * @return
     */
    List<User> findListBySexType(String sexType);

    List<UserCountGroupBy> countUserGroupBySex(Integer sex);

}
