package com.pm.vo;


import com.pm.domain.business.StaffCost;
import com.pm.util.log.EntityAnnotation;

import java.io.Serializable;

/**
 * 人员成本的专项附加扣除
 * @author Administrator
 */
public class StaffAdditionalDeduction implements Serializable {

    private String staff_id ;

    @EntityAnnotation(item_name="员工工号", item_sort=1,length=30)
    private String staff_no;

    @EntityAnnotation(item_name="员工名称", item_sort=2,length=60)
    private String staff_name;



    @EntityAnnotation(item_name="子女教育", item_sort=3,length=10)
    private double children_education;


    @EntityAnnotation(item_name="继续教育", item_sort=4,length=10)
    private double continuing_education;

    @EntityAnnotation(item_name="住房贷款利息", item_sort=5,length=10)
    private double housing_loans;

    //住房租金
    @EntityAnnotation(item_name="住房租金", item_sort=6,length=10)
    private double housing_rent;

    //赡养老人
    @EntityAnnotation(item_name="赡养老人", item_sort=7,length=10)
    private double support_elderly;


    ///////////////////

    private StaffCost staffCost;

    private String errorInfo;


    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_no() {
        return staff_no;
    }

    public void setStaff_no(String staff_no) {
        this.staff_no = staff_no;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public double getChildren_education() {
        return children_education;
    }

    public void setChildren_education(double children_education) {
        this.children_education = children_education;
    }

    public double getContinuing_education() {
        return continuing_education;
    }

    public void setContinuing_education(double continuing_education) {
        this.continuing_education = continuing_education;
    }

    public double getHousing_loans() {
        return housing_loans;
    }

    public void setHousing_loans(double housing_loans) {
        this.housing_loans = housing_loans;
    }

    public double getHousing_rent() {
        return housing_rent;
    }

    public void setHousing_rent(double housing_rent) {
        this.housing_rent = housing_rent;
    }

    public double getSupport_elderly() {
        return support_elderly;
    }

    public void setSupport_elderly(double support_elderly) {
        this.support_elderly = support_elderly;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public StaffCost getStaffCost() {
        return staffCost;
    }

    public void setStaffCost(StaffCost staffCost) {
        this.staffCost = staffCost;
    }
}
