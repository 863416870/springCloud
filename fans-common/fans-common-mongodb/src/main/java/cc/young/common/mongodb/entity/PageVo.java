package cc.young.common.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * @Author linxubin
 * @Date 2020/4/12
 * @Description
 */
@Data
public class PageVo {
    @Transient
    private transient Integer page;
    @Transient
    private transient Integer pageSize;
}
