package cc.young.common.mongodb.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ConditionGrp {
    private Opt connector;
    private Opt subConnector;
    private List<Condition> conditionList = new ArrayList();
}
