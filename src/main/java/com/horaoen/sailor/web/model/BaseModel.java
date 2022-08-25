package com.horaoen.sailor.web.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author horaoen
 */
@Data
public class BaseModel {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;
}
