package com.horaoen.sailor.web.common.enumeration;

/**
 * @author horaoen  
 */
public enum GroupLevelEnum {
    /**
     * 超级管理员
     */
    ROOT("root"),
    /**
     * 游客
     */
    GUEST("guest"),
    /**
     * 普通用户
     */
    USER("user");
    
    
    private String value;
    
    GroupLevelEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}
