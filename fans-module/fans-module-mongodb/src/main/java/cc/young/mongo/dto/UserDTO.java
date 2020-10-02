package cc.young.mongo.dto;

import cc.young.mongo.entity.example.UserExample;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document("user")
public class UserDTO extends UserExample {

    private String userName;

    private Integer sex;

    private Integer age;

}
