package cc.young.mongo.dao.impl;

import cc.young.mongo.dao.UserRepositoryEnhance;
import cc.young.mongo.entity.User;
import cc.young.mongo.vo.UserCountGroupBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryEnhance {

    @Autowired
    private MongoOperations mongoOperations;
    @Override
    public List<User> findListBySexType(String sexType) {
        Integer sex = "M".equals(sexType) ? 1 : 2;
        Criteria where = Criteria.where("sex").is(sex);
        Query query = Query.query(where);
        return mongoOperations.find(query, User.class);
    }

    @Override
    public List<User> findListByIds(Collection<String> ids) {
        Criteria where = Criteria.where("id").in(ids);
        Query query = Query.query(where);
//        query.fields().include("id");//查找指定字段
        return mongoOperations.find(query, User.class);
    }

    @Override
    public List<UserCountGroupBy> countUserGroupBySex(Integer sex) {
        Aggregation agg = Aggregation.newAggregation(
                // 第一步：挑选所需的字段，类似select *，*所代表的字段内容
                Aggregation.project("sex", "age"),
                // 第二步：sql where 语句筛选符合条件的记录
                //Aggregation.match(),
                // 第三步：分组条件，设置分组字段
                Aggregation.group("sex")
                        .count().as("size")// 增加COUNT为分组后输出的字段
                        .last("sex").as("sex"), // 增加publishDate为分组后输出的字段
                // 第四步：重新挑选字段
                Aggregation.project("sex",  "size")
        );
        AggregationResults<UserCountGroupBy> results = mongoOperations.aggregate(agg, "user", UserCountGroupBy.class);
        List<UserCountGroupBy> list = results.getMappedResults();

        return list;
    }
}
