package cc.young.common.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class BaseEntity<T, ID extends Serializable> extends PageVo {

    private static final long serialVersionUID = 5227521689231872715L;
    @Transient
    @JsonIgnore
    private transient List<ConditionGrp> conditionGrpList = new ArrayList();
    @Transient
    @JsonIgnore
    private transient List<OrderBy> orderByList = new ArrayList();
    @Transient
    @JsonIgnore
    public transient T updateParam;

    public void and(Opt subConnector, Condition... conditions) {
        if (conditions != null) {
            ConditionGrp cdtGrp = new ConditionGrp();
            cdtGrp.setConnector(Opt.AND);
            cdtGrp.setSubConnector(subConnector);
            List<Condition> cdnList = Arrays.asList(conditions);
            cdtGrp.getConditionList().addAll(cdnList);
            this.conditionGrpList.add(cdtGrp);
        }
    }

    public void or(Opt subConnector, Condition... conditions) {
        if (conditions != null) {
            ConditionGrp cdtGrp = new ConditionGrp();
            cdtGrp.setConnector(Opt.OR);
            cdtGrp.setSubConnector(subConnector);
            List<Condition> cdnList = Arrays.asList(conditions);
            cdtGrp.getConditionList().addAll(cdnList);
            this.conditionGrpList.add(cdtGrp);
        }
    }

    public List<ConditionGrp> getConditionGrpList() {
        return conditionGrpList;
    }

    public List<OrderBy> getOrderByList() {
        return orderByList;
    }

    public T getUpdateParam() {
        return updateParam;
    }

    public void addOrderBy(String columnName, Opt mode) {
        OrderBy orderBy = new OrderBy(columnName, mode);
        orderByList.add(orderBy);
    }


    protected void addCondition(String columnName, Opt opt, Object value) {
        if (Opt.IN.equals(opt) || Opt.NOT_IN.equals(opt)) {
            if (!(value instanceof Collection)) {
                throw new RuntimeException("in或not in条件参数必须为集合");
            }

            Collection<?> collection = (Collection)value;
            if (CollectionUtils.isEmpty(collection)) {
                opt = Opt.EQUAL;
                columnName = "-1";
                value = 1;
            }
        }

        if (value != null) {
            ConditionGrp cdtGrp = new ConditionGrp();
            cdtGrp.setConnector(Opt.AND);
            Condition cdt = new Condition(columnName, opt, value);
            cdtGrp.getConditionList().add(cdt);
            this.conditionGrpList.add(cdtGrp);
        }
    }
}
