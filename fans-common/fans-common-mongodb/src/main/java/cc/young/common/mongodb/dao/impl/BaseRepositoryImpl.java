package cc.young.common.mongodb.dao.impl;

import cc.young.common.mongodb.dao.BaseRepository;
import cc.young.common.mongodb.entity.*;
import cc.young.common.mongodb.util.CriteriaUtil;
import cc.young.common.mongodb.util.FieldCacheUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements BaseRepository<T, ID> {


    private MongoOperations mongoOperations;
    private MongoEntityInformation<T, ID> entityInformation;

    public BaseRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
        entityInformation = metadata;
    }


    @Override
    public MongoEntityInformation<T, ID> getCurEntityInformation() {
        return entityInformation;
    }


    @Override
    public PageResult<T> findPage(T t) {

        Pageable pageable = getPage(t);
        Query query = new Query();

        // 查询条件
        setQueryParams(query, t, entityInformation.getJavaType());
        long count = mongoOperations.count(query, entityInformation.getCollectionName());
        query.with(pageable);
        List<T> list = mongoOperations.find(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
        PageImpl<T> page = new PageImpl<>(list, pageable, count);
        return new PageResult<>(page.getTotalElements(), page.getTotalPages(), page.getContent());
    }

    @Override
    public List<T> findList(T t) {

        Query query = new Query();
        // 查询条件
        setQueryParams(query, t, entityInformation.getJavaType());

        //分页设置
        if(Objects.nonNull(t.getPageSize())) {
            query.with(PageRequest.of(0,t.getPageSize()));
        }

        List<T> list = mongoOperations.find(query, entityInformation.getJavaType(), entityInformation.getCollectionName());

        return list;
    }

    /**
     * 获取分页设置
     * @param t
     * @return
     */
    private Pageable getPage(T t) {
        Integer page = 0;
        Integer pageSize = 10;
        if (Objects.isNull(t)) {
            return PageRequest.of(page, pageSize);
        }
        if (Objects.nonNull(t.getPage()) && t.getPage() > 0) {
            page = t.getPage() - 1;
        }
        if (Objects.nonNull(t.getPageSize()) && t.getPageSize() > 0) {
            pageSize = t.getPageSize();
        }
        return PageRequest.of(page, pageSize);
    }

    private void setQueryParams(Query query, T t, Class<T> entityClass) {
        setWhereParams(query, t, entityClass);
        setOrderByParams(query, t);
    }

    private void setWhereParams(Query query, T t, Class<T> entityClass) {
        List<Criteria> addCriteriaList = new ArrayList<>();
        List<Criteria> orCriteriaList = new ArrayList<>();

        Map<String, Field> fieldMap = FieldCacheUtil.getFieldMap(entityClass);
        //设置对象查询
        fieldMap.forEach((fieldName, field) -> {
            Object fieldValue = ReflectUtil.getFieldValue(t, field);
            if (Objects.nonNull(fieldValue)) {
                Class<?> type = field.getType();
                if (type.equals(String.class)) {
                    addCriteriaList.add(CriteriaUtil.like(fieldName, (String) fieldValue));
                } else {
                    addCriteriaList.add(CriteriaUtil.eq(fieldName, fieldValue));
                }
            }
        });

        //附加条件查询
        List<ConditionGrp> conditionGrpList = t.getConditionGrpList();
        if (CollectionUtil.isNotEmpty(conditionGrpList)) {

            for (ConditionGrp cdnGrp : conditionGrpList) {
                //连接
                List<Condition> conditionList = cdnGrp.getConditionList();
                if (CollectionUtil.isNotEmpty(conditionList)) {
                    Criteria[] cdnCriterias = conditionList.stream().map(condition ->
                            condition.getOpt().buildCriteria(condition.getColumnName(), condition.getValue())).toArray(Criteria[]::new);
                    Criteria cdnGrpCriteria = new Criteria();
                    //子查询连接
                    if (cdnGrp.getSubConnector() == null || Opt.AND.equals(cdnGrp.getSubConnector())) {
                        cdnGrpCriteria.andOperator(cdnCriterias);
                    } else if (Opt.OR.equals(cdnGrp.getSubConnector())) {
                        cdnGrpCriteria.orOperator(cdnCriterias);
                    }

                    // 连接到主查询中
                    if (Opt.AND.equals(cdnGrp.getConnector())) {
                        addCriteriaList.add(cdnGrpCriteria);
                    } else if (Opt.OR.equals(cdnGrp.getConnector())) {
                        orCriteriaList.add(cdnGrpCriteria);
                    }

                }
            }
        }
        Criteria criteria = new Criteria();
        if (CollectionUtil.isNotEmpty(addCriteriaList)) {
            criteria.andOperator(addCriteriaList.toArray(new Criteria[]{}));
        }
        if (CollectionUtil.isNotEmpty(orCriteriaList)) {
            criteria.andOperator(orCriteriaList.toArray(new Criteria[]{}));
        }
        query.addCriteria(criteria);
    }

    private void setOrderByParams(Query query, T t) {

        List<OrderBy> orderByList = t.getOrderByList();

        if (CollectionUtil.isNotEmpty(orderByList)) {
            List<Sort.Order> list = new ArrayList<>();
            for (OrderBy orderBy : orderByList) {
                list.add(new Sort.Order(orderBy.getMode().equals(Opt.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy.getColumnName()));
            }
            query.with(Sort.by(list));
        }
    }


    private void setPageParams(Query query, T t) {

        // 从那条记录开始
        query.skip((t.getPage() - 1) * t.getPage());
        // 取多少条记录
        query.limit(t.getPageSize());
    }

}
