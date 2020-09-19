package cc.young.common.mongodb.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageResult<T> {
    private Long total;
    private Integer totalPage;
    private List<T> data;

    public PageResult() {
    }

    public PageResult(Long total, Integer totalPage, List<T> data) {
        this.total = total;
        this.totalPage = totalPage;
        this.data = data;
    }



}
