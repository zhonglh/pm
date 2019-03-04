package com.pm.vo.personnelreport;

import com.pm.util.log.EntityAnnotation;

import java.io.Serializable;

/**
 * 五险一金缴纳情况
 * @author Administrator
 */
public class PersonnelInsuranceFundVo implements Serializable {

    @EntityAnnotation(item_name="缴纳单位", item_sort=10)
    private String securty_unit;

    @EntityAnnotation(item_name="社保缴纳人数", item_sort=20)
    private int securty_peoples;


    @EntityAnnotation(item_name="社保个人合计", item_sort=30)
    private double insurance_bypersonal_sum;


    @EntityAnnotation(item_name="社保公司合计", item_sort=40)
    private double insurance_bycompany_sum;


    @EntityAnnotation(item_name="社保缴费合计", item_sort=50)
    private double insurance_all_sum;



    @EntityAnnotation(item_name="公积金缴纳人数", item_sort=60)
    private int fund_peoples;

    @EntityAnnotation(item_name="公积金个人合计", item_sort=70)
    private double fund_bypersonal_sum;


    @EntityAnnotation(item_name="公积金公司合计", item_sort=80)
    private double fund_bypcompany_sum;

    @EntityAnnotation(item_name="公积金合计", item_sort=90)
    private double fund_all_sum;


    public String getSecurty_unit() {
        return securty_unit;
    }

    public void setSecurty_unit(String securty_unit) {
        this.securty_unit = securty_unit;
    }

    public int getSecurty_peoples() {
        return securty_peoples;
    }

    public void setSecurty_peoples(int securty_peoples) {
        this.securty_peoples = securty_peoples;
    }

    public double getInsurance_bypersonal_sum() {
        return insurance_bypersonal_sum;
    }

    public void setInsurance_bypersonal_sum(double insurance_bypersonal_sum) {
        this.insurance_bypersonal_sum = insurance_bypersonal_sum;
    }

    public double getInsurance_bycompany_sum() {
        return insurance_bycompany_sum;
    }

    public void setInsurance_bycompany_sum(double insurance_bycompany_sum) {
        this.insurance_bycompany_sum = insurance_bycompany_sum;
    }

    public double getInsurance_all_sum() {
        return insurance_all_sum;
    }

    public void setInsurance_all_sum(double insurance_all_sum) {
        this.insurance_all_sum = insurance_all_sum;
    }

    public int getFund_peoples() {
        return fund_peoples;
    }

    public void setFund_peoples(int fund_peoples) {
        this.fund_peoples = fund_peoples;
    }

    public double getFund_bypersonal_sum() {
        return fund_bypersonal_sum;
    }

    public void setFund_bypersonal_sum(double fund_bypersonal_sum) {
        this.fund_bypersonal_sum = fund_bypersonal_sum;
    }

    public double getFund_bypcompany_sum() {
        return fund_bypcompany_sum;
    }

    public void setFund_bypcompany_sum(double fund_bypcompany_sum) {
        this.fund_bypcompany_sum = fund_bypcompany_sum;
    }

    public double getFund_all_sum() {
        return fund_all_sum;
    }

    public void setFund_all_sum(double fund_all_sum) {
        this.fund_all_sum = fund_all_sum;
    }
}
