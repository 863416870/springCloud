package cc.young.common.mongodb.entity;

import org.springframework.data.mongodb.core.query.Criteria;

public enum  OptExample {

    EQUAL("=","EQUAL","等于") {
        @Override
        public Criteria run(String column,Object params) {
            return Criteria.where(column).is(params);
        }
    };

    private String key;
    private String value;
    private String description;

    OptExample(String key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public abstract Criteria run(String column,Object params);
}
