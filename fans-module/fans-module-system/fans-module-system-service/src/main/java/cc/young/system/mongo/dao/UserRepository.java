package cc.young.system.mongo.dao;

import cc.young.common.mongodb.dao.BaseRepository;
import cc.young.system.mongo.entity.User;

public interface UserRepository extends BaseRepository<User,String>,UserRepositoryEnhance{

}
