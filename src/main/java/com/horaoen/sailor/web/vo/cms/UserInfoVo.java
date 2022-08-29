package com.horaoen.sailor.web.vo.cms;

import cn.hutool.core.bean.BeanUtil;
import com.horaoen.sailor.web.model.cms.UserDo;
import com.horaoen.sailor.web.vo.scc.OrgVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author horaoen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private Long id;

    /**
     * 用户名，唯一
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    private String createUser;
    
    private String org;
    
    private String className;   

    private Date createTime;

    private String roleName;
    public UserInfoVo(UserDo user, GroupVo groupVo, OrgVo orgVo) {
        BeanUtil.copyProperties(user, this);
        setRoleName(groupVo);
        setOrgAndClass(orgVo);
    }
    
    private void setRoleName(GroupVo groupVo) {
        this.roleName = groupVo.getName();
    }
    
    private void setOrgAndClass(OrgVo orgVo) {
        String ancestors = orgVo.getAncestors();
        int i = ancestors.lastIndexOf("-");
        String org;
        if(i != -1) {
            org = ancestors.substring(ancestors.lastIndexOf("-"));
        } else {
            org = ancestors;
        }
        
        switch (orgVo.getAncestors().split("-").length) {
            case 1:
                this.org = org;
                this.className = "全部学校";
                break;
            case 2:
                this.org = org;
                this.className = "全部年级";
                break;
            case 3:
                this.org = org;
                this.className = "全部班级";
                break;
            default:
                this.org = org;
                this.className = orgVo.getOrgName();
        }
    }
}
