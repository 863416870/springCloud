package cc.young.common.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageCounter {
    private Integer curPage = 1;
    private Integer perPage = 20;
}
