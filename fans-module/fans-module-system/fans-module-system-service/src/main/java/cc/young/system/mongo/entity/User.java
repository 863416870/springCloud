package cc.young.system.mongo.entity;

import cc.young.system.mongo.entity.example.UserExample;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document("user")
public class User extends UserExample {

    @Id
    private String id;

    private String userName;

    private Integer sex;

    private Integer age;

}
