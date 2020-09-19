package cc.young.common.mongodb.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 查询语句生成器
 *
 * @author linxb
 */
@UtilityClass
public class CriteriaUtil {

    /**
     * 等于
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria eq(String column, Object params) {
        Criteria criteria = Criteria.where(column).is(params);
        return criteria;
    }

    /**
     * 不等于
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria ne(String column, Object params) {
        Criteria criteria = Criteria.where(column).ne(params);
        return criteria;
    }

    /**
     * 小于
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria lt(String column, Object params) {
        Criteria criteria = Criteria.where(column).lt(params);
        return criteria;
    }

    /**
     * 小于或等于
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria lte(String column, Object params) {
        Criteria criteria = Criteria.where(column).lte(params);
        return criteria;
    }

    /**
     * 大于
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria gt(String column, Object params) {
        Criteria criteria = Criteria.where(column).gt(params);
        return criteria;
    }

    /**
     * 大于或等于
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria gte(String column, Object params) {
        Criteria criteria = Criteria.where(column).gte(params);
        return criteria;
    }

    /**
     * 包含
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria contain(String column, Object params) {
        Criteria criteria = Criteria.where(column).all(params);
        return criteria;
    }



    /**
     * 相似于
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria like(String column, String params) {
        Pattern pattern = Pattern.compile("^.*" + params + ".*$", Pattern.CASE_INSENSITIVE);
        return Criteria.where(column).regex(pattern);
    }

    /**
     * 在其中
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria in(String column, Collection<?> params) {
        return Criteria.where(column).in(params);
    }

    /**
     * 在其中
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria in(String column, Object[] params) {
        return in(column, Arrays.asList(params));
    }

    /**
     * 不在其中
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria nin(String column, Collection<?> params) {
        Criteria criteria = Criteria.where(column).nin(params);
        return criteria;
    }

    /**
     * 不在其中
     *
     * @param column 字段
     * @param params 参数
     * @return Criteria
     */
    public Criteria nin(String column, Object[] params) {
        return nin(column, Arrays.asList(params));
    }

    /**
     * 为空
     *
     * @param column 字段
     * @return Criteria
     */
    public Criteria isNull(String column) {
        Criteria criteria = Criteria.where(column).is(null);
        return criteria;
    }

    /**
     * 不为空
     *
     * @param column 字段
     * @return Criteria
     */
    public Criteria isNotNull(String column) {
        Criteria criteria = Criteria.where(column).ne(null);
        return criteria;
    }

}
