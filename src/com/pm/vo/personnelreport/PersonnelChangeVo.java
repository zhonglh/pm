package com.pm.vo.personnelreport;

import com.pm.util.log.EntityAnnotation;

import java.io.Serializable;

/**
 * 月人事报表
 * 各部门人员变动情况
 * @author Administrator
 */
public class PersonnelChangeVo implements Serializable {


    private String dept_id;
    @EntityAnnotation(item_name="部门名称",item_sort=10)
    private String dept_name;


    private String project_id;
    @EntityAnnotation(item_name="项目名称",item_sort=20)
    private String project_name;

    @EntityAnnotation(item_name="月初人数",item_sort=30)
    private int month_start_peoples;

    @EntityAnnotation(item_name="新入职人数",item_sort=40)
    private int month_join_peoples;

    @EntityAnnotation(item_name="当月换项目人数",item_sort=50)
    private int month_change_project_peoples;


    @EntityAnnotation(item_name="月末人数",item_sort=60)
    private int month_end_peoples;


    @EntityAnnotation(item_name="考勤总人数",item_sort=70)
    private int month_work_attendance_peoples;


    @EntityAnnotation(item_name="工资总人数",item_sort=80)
    private int month_salary_peoples;



    @EntityAnnotation(item_name="工资总金额",item_sort=90)
    private double month_salary_amount;



    @EntityAnnotation(item_name="月人均工资",item_sort=100)
    private double month_average_salary_amount;


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

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getMonth_start_peoples() {
        return month_start_peoples;
    }

    public void setMonth_start_peoples(int month_start_peoples) {
        this.month_start_peoples = month_start_peoples;
    }

    public int getMonth_join_peoples() {
        return month_join_peoples;
    }

    public void setMonth_join_peoples(int month_join_peoples) {
        this.month_join_peoples = month_join_peoples;
    }

    public int getMonth_change_project_peoples() {
        return month_change_project_peoples;
    }

    public void setMonth_change_project_peoples(int month_change_project_peoples) {
        this.month_change_project_peoples = month_change_project_peoples;
    }

    public int getMonth_end_peoples() {
        return month_end_peoples;
    }

    public void setMonth_end_peoples(int month_end_peoples) {
        this.month_end_peoples = month_end_peoples;
    }

    public int getMonth_work_attendance_peoples() {
        return month_work_attendance_peoples;
    }

    public void setMonth_work_attendance_peoples(int month_work_attendance_peoples) {
        this.month_work_attendance_peoples = month_work_attendance_peoples;
    }

    public int getMonth_salary_peoples() {
        return month_salary_peoples;
    }

    public void setMonth_salary_peoples(int month_salary_peoples) {
        this.month_salary_peoples = month_salary_peoples;
    }

    public double getMonth_salary_amount() {
        return month_salary_amount;
    }

    public void setMonth_salary_amount(double month_salary_amount) {
        this.month_salary_amount = month_salary_amount;
    }

    public double getMonth_average_salary_amount() {
        return month_average_salary_amount;
    }

    public void setMonth_average_salary_amount(double month_average_salary_amount) {
        this.month_average_salary_amount = month_average_salary_amount;
    }
}
