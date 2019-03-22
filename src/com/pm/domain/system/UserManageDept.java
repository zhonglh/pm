package com.pm.domain.system;


import com.pm.domain.business.IdEntity;

/**
 * 用户管理的部门设置
 */
public class UserManageDept extends IdEntity {

    private String user_id;
    private String dept_id;

    ////////////////////////////

    private String dept_name;
    private String dept_no;
    private String description;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }


    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
