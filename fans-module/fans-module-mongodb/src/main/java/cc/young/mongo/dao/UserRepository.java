package cc.young.mongo.dao;

import cc.young.common.mongodb.dao.BaseRepository;
import cc.young.mongo.entity.User;

public interface UserRepository extends BaseRepository<User,String>,UserRepositoryEnhance{

}
