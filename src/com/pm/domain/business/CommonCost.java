package com.pm.domain.business;

import com.pm.util.log.EntityAnnotation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 公共费用
 * @author Administrator
 */
public class CommonCost extends IdEntity implements  Serializable {






    private int use_month;

    @EntityAnnotation(item_name="支付日期",item_sort=7 , length = 10)
    private Date pay_date;


    @EntityAnnotation(item_name="金额"  ,item_sort=3,length=12)
    private double amount ;


    //报销人员
    private String staff_id;

    //费用类别
    private String pay_item_id;


    @EntityAnnotation(item_name="备注" ,item_sort=2,length=300)
    private String description;


    private int import_order;

    private Timestamp build_datetime;
    private String build_username;
    private String build_userid;
    private String verify_username;
    private String verify_userid;
    private Timestamp verify_datetime;

    //////////////////////////////////////

    @EntityAnnotation(item_name="报销人工号", item_sort=5)
    private String staff_no;
    @EntityAnnotation(item_name="报销人姓名", item_sort=6,length=60)
    private String staff_name;

    @EntityAnnotation(item_name="费用类别", item_sort=4,length=50)
    private String pay_item_name;

    //仅用于导入导出,月份
    @EntityAnnotation(item_name="月份", item_sort=1,length=10)
    private String str_month;



    private String errorInfo = "";

    //是否申请 取消核单状态 1:已申请取消核单  其他:正常
    private String need_approve;


    //////////查询条件////



    //核单标记， 用于查询
    //1: 已核单，  2:未核单
    private String verify_flag;


    private int use_month1;
    private int use_month2;
    private Timestamp pay_date1;
    private Timestamp pay_date2;


    @Override
    public String toString() {
        return pay_item_name+"("+amount+")" ;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getPay_item_id() {
        return pay_item_id;
    }

    public void setPay_item_id(String pay_item_id) {
        this.pay_item_id = pay_item_id;
    }

    public int getUse_month() {
        return use_month;
    }

    public void setUse_month(int use_month) {
        this.use_month = use_month;
    }

    public Date getPay_date() {
        return pay_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImport_order() {
        return import_order;
    }

    public void setImport_order(int import_order) {
        this.import_order = import_order;
    }

    public Timestamp getBuild_datetime() {
        return build_datetime;
    }

    public void setBuild_datetime(Timestamp build_datetime) {
        this.build_datetime = build_datetime;
    }

    public String getBuild_username() {
        return build_username;
    }

    public void setBuild_username(String build_username) {
        this.build_username = build_username;
    }

    public String getBuild_userid() {
        return build_userid;
    }

    public void setBuild_userid(String build_userid) {
        this.build_userid = build_userid;
    }

    public String getVerify_username() {
        return verify_username;
    }

    public void setVerify_username(String verify_username) {
        this.verify_username = verify_username;
    }

    public String getVerify_userid() {
        return verify_userid;
    }

    public void setVerify_userid(String verify_userid) {
        this.verify_userid = verify_userid;
    }

    public Timestamp getVerify_datetime() {
        return verify_datetime;
    }

    public void setVerify_datetime(Timestamp verify_datetime) {
        this.verify_datetime = verify_datetime;
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

    public String getPay_item_name() {
        return pay_item_name;
    }

    public void setPay_item_name(String pay_item_name) {
        this.pay_item_name = pay_item_name;
    }

    public String getStr_month() {
        return str_month;
    }

    public void setStr_month(String str_month) {
        this.str_month = str_month;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getNeed_approve() {
        return need_approve;
    }

    public void setNeed_approve(String need_approve) {
        this.need_approve = need_approve;
    }


    public String getVerify_flag() {
        return verify_flag;
    }

    public void setVerify_flag(String verify_flag) {
        this.verify_flag = verify_flag;
    }

    public int getUse_month1() {
        return use_month1;
    }

    public void setUse_month1(int use_month1) {
        this.use_month1 = use_month1;
    }

    public int getUse_month2() {
        return use_month2;
    }

    public void setUse_month2(int use_month2) {
        this.use_month2 = use_month2;
    }

    public Timestamp getPay_date1() {
        return pay_date1;
    }

    public void setPay_date1(Timestamp pay_date1) {
        this.pay_date1 = pay_date1;
    }

    public Timestamp getPay_date2() {
        return pay_date2;
    }

    public void setPay_date2(Timestamp pay_date2) {
        this.pay_date2 = pay_date2;
    }
}
