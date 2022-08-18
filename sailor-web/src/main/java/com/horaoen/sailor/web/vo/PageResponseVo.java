package com.horaoen.sailor.web.vo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author horaoen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseVo<T> extends UnifyResponseVo<List<T>> {
    private int total;

    private long page;

    private long count;
    public PageResponseVo(PageInfo<T> pageInfo, List<T> data) {
        super();
        this.setData(data);
        this.setTotal(pageInfo.getPages());
        this.setPage(pageInfo.getPageNum());
        this.setCount(pageInfo.getSize());
    }
}
