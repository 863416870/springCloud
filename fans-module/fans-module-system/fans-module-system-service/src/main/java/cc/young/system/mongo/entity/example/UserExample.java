package cc.young.system.mongo.entity.example;

import cc.young.common.mongodb.entity.BaseEntity;
import cc.young.common.mongodb.entity.Opt;
import cc.young.system.mongo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;

public class UserExample extends BaseEntity<User, String> {
    @JsonIgnore
    @Transient
    private transient Opt idOpt = Opt.EQUAL;
    @JsonIgnore
    @Transient
    private transient Opt ageOpt = Opt.EQUAL;
    @JsonIgnore
    @Transient
    private transient Opt sexOpt = Opt.EQUAL;
    @JsonIgnore
    @Transient
    private transient Opt userNameOpt = Opt.LIKE;

    @JsonIgnore
    @Transient
    public transient static final String ID = "id";
    @JsonIgnore
    @Transient
    public transient static final String AGE = "age";
    @JsonIgnore
    @Transient
    public transient static final String SEX = "sex";
    @JsonIgnore
    @Transient
    public transient static final String USERNAME = "userName";


    public void andId(Opt opt, Object value) {
        super.addCondition(ID, opt, value);
    }

    public void andSex(Opt opt, Object value) {
        super.addCondition(SEX, opt, value);
    }

    public void andAge(Opt opt, Object value) {
        super.addCondition(AGE, opt, value);
    }

    public void andUserName(Opt opt, Object value) {
        super.addCondition(USERNAME, opt, value);
    }


}
