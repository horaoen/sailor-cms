package com.horaoen.sailor.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horaoen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupVo {
    private Long id;
    private String name;

    /**
     * 分组信息：例如：搬砖的人
     */
    private String info;
}
