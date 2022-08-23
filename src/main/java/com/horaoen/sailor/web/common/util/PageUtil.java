package com.horaoen.sailor.web.common.util;

import com.github.pagehelper.PageInfo;
import com.horaoen.sailor.web.vo.PageResponseVo;

import java.util.List;

/**
 * @author horaoen
 */
public class PageUtil {
    public static <T> PageResponseVo<T> build(List<T> data) {
        PageInfo<T> pageInfo = new PageInfo<>(data);
        return new PageResponseVo<>(pageInfo, data);
    }
}
