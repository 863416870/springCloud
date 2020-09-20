package cc.young.common.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;


@Data
public class PageVo {
    @Transient
    private transient Integer page;
    @Transient
    private transient Integer pageSize;
}
