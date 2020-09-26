package cc.young.mongo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCountGroupBy implements Serializable {

    private Long size;

    private Integer sex;
}
