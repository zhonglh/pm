package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.pm.util.constant.BusinessUtil;
import com.pm.util.log.EntityAnnotation;

/**
 * 其他人员表
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class OtherStaff implements Serializable {
	
	
	private String staff_id;
	

	@EntityAnnotation(item_name="工号" , item_sort=1 , length=20 )
	private String staff_no;
	
	@EntityAnnotation(item_name="姓名" ,item_sort=2 , length=50 )
	private String staff_name;
	
	//部门ID
	private String dept_id;
	

	
	@EntityAnnotation(item_name="职位类型")
	private String position_type ;
	

	@EntityAnnotation(item_name="兼职类型1")
	private String position_type1 ;
	
	@EntityAnnotation(item_name="兼职类型2")
	private String position_type2 ;
	
	@EntityAnnotation(item_name="兼职类型3")
	private String position_type3 ;



	@EntityAnnotation(item_name="岗位名称", item_sort=5,length=36)
	private String job_title;



	@EntityAnnotation(item_name="性别", item_sort=7)
	private String sex;


	@EntityAnnotation(item_name="生日", item_sort=8)
	private Date birthday;

	//民族
	private String nationality;

	//户籍性质
	private String census_property ;

	@EntityAnnotation(item_name="身份证号", item_sort=11,length=18)
	private String identity_card_number;


	@EntityAnnotation(item_name="联系电话", item_sort=12,length=30)
	private String tel;


	@EntityAnnotation(item_name="紧急联系人电话", item_sort=13,length=20)
	private String pressing_tel ;


	@EntityAnnotation(item_name="QQ号码", item_sort=14,length=20)
	private String qq ;

	@EntityAnnotation(item_name="合同开始时间", item_sort=15,length=10)
	private Date contract_start_date ;

	@EntityAnnotation(item_name="合同结束时间", item_sort=16,length=10)
	private Date contract_end_date   ;

	@EntityAnnotation(item_name="签约次数", item_sort=17,length=6)
	private int contract_number;

	@EntityAnnotation(item_name="毕业学校", item_sort=18,length=120)
	private String graduate_school;

	//学历
	private String educational ;

	//专业
	private String specialty ;


	@EntityAnnotation(item_name="毕业时间", item_sort=21,length=10)
	private Date graduation_date;

	@EntityAnnotation(item_name="工作年限", item_sort=22,length=7)
	private double working_life ;




	@EntityAnnotation(item_name="户口所在地", item_sort=23,length=200)
	private String registered_residence;

	@EntityAnnotation(item_name="通讯地址", item_sort=24,length=200)
	private String postal_address    ;


	@EntityAnnotation(item_name="入职时间", item_sort=25,length=10)
	private Timestamp join_datetime;


	@EntityAnnotation(item_name="离职时间", item_sort=26)
	private Timestamp leave_job_datetime;




	@EntityAnnotation(item_name="转正日期", item_sort=30,length=10)
	private Date confirmation_date   ;

	@EntityAnnotation(item_name="试用期工资", item_sort=31,length=10)
	private double tryout_salary     ;

	//试用期工资
	private double tryout_basic_salary;
	private double tryout_post_salary;
	private double tryout_performance_allowances;


	@EntityAnnotation(item_name="正式工资", item_sort=32,length=10)
	private double official_salary;


	@EntityAnnotation(item_name="基本工资", item_sort=33)
	private double basic_salary;

	@EntityAnnotation(item_name="岗位工资", item_sort=34)
	private double post_salary;

	@EntityAnnotation(item_name="绩效津贴", item_sort=35)
	private double performance_allowances;


	@EntityAnnotation(item_name="电脑补助", item_sort=36,length=10)
	private double computer_allowance;

	@EntityAnnotation(item_name="餐补(每天)", item_sort=37,length=10)
	private double meal_allowance;

	@EntityAnnotation(item_name="奖金(税前)", item_sort=38,length=10)
	private double project_allowance;


	@EntityAnnotation(item_name="出差补助", item_sort=39,length=10)
	private double travel_allowance;


	//保险档次ID
	private String insurance_grade_id;



	@EntityAnnotation(item_name="社保缴纳单位", item_sort=41,length=200)
	private String securty_unit;

	@EntityAnnotation(item_name="个人缴纳养老保险", item_sort=42)
	private double endowment_insurance_bypersonal;

	@EntityAnnotation(item_name="个人缴纳医疗保险", item_sort=43)
	private double medical_insurance_bypersonal;

	@EntityAnnotation(item_name="个人缴纳失业保险", item_sort=44)
	private double losejob_insurance_bypersonal;

	@EntityAnnotation(item_name="个人缴纳公积金", item_sort=45)
	private double reservefund_bypersonal;

	@EntityAnnotation(item_name="缴纳个人所得税", item_sort=46)
	private double incometax_bypersonal;

	@EntityAnnotation(item_name="公司缴纳养老保险", item_sort=47)
	private double endowment_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳医疗保险", item_sort=48)
	private double medical_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳失业保险", item_sort=49)
	private double losejob_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳生育保险", item_sort=50)
	private double maternity_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳工伤保险", item_sort=51)
	private double jobharm_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳公积金", item_sort=52)
	private double reservefund_bypcompany;



	@EntityAnnotation(item_name="额外", item_sort=53,length=10)
	private double extra;




	@EntityAnnotation(item_name="邮箱地址", item_sort=55,length=60)
	private String email;



	@EntityAnnotation(item_name="开户行", item_sort=56,length=200)
	private String open_account;

	@EntityAnnotation(item_name="银行卡号", item_sort=57,length=50)
	private String bank_card_number;


	//上级ID
	private String lead_id;

	@EntityAnnotation(item_name="额外支出", item_sort=60,length=13)
	private double extra_expend;


	@EntityAnnotation(item_name="是否允许发送信息", item_sort=61,length=1)
	private String can_send_info = BusinessUtil.STAFF_CAN_SEND_MESSAGE;


	//合同种类
	private String contract_type;

	//合同归属
	private String contract_attach;

	//社保说明
	private String social_security;


	//参保城市
	private String insured_city;

	//工作地点
	private String working_address;

	//证书
	private String certificate;




	@EntityAnnotation(item_name="子女教育", item_sort=69,length=8)
	private double children_education;


	@EntityAnnotation(item_name="住房贷款利息", item_sort=70,length=8)
	private double housing_loans;

	@EntityAnnotation(item_name="住房租金", item_sort=71,length=8)
	private double housing_rent;


	@EntityAnnotation(item_name="赡养老人", item_sort=72,length=8)
	private double support_elderly;

	@EntityAnnotation(item_name="继续教育", item_sort=73,length=8)
	private double continuing_education;

	//工作状态
	private String work_status;


	@EntityAnnotation(item_name="备注" , item_sort=74,length=300)
	private String description;


	private int import_order;

	//版本号
	private int version_no;
	
	private Timestamp build_datetime;
	private String build_userid;
	private String build_username;
	private String delete_flag;
	private Timestamp delete_datetime;
	private String delete_userid;
	private String delete_username;
	
	
	///////////////////////////
	////////////扩展使用///////
	//////////////////////////
	
	
	private String errorInfo="" ;
	@EntityAnnotation(item_name="职位类型" ,item_sort=4 , length=20 )
	private String position_type_temp ;
	

	@EntityAnnotation(item_name="部门名称" ,item_sort=3 , length=50 )
	private String dept_name;


	@EntityAnnotation(item_name="民族", item_sort=9,length=100)
	private String nationality_name;

	@EntityAnnotation(item_name="户籍性质", item_sort=10,length=100)
	private String census_property_name ;


	@EntityAnnotation(item_name="学历", item_sort=19,length=100)
	private String educational_name ;


	@EntityAnnotation(item_name="专业", item_sort=20,length=100)
	private String specialty_name ;


	@EntityAnnotation(item_name="社保种类", item_sort=40,length=50)
	private String insurance_radix;


	@EntityAnnotation(item_name="合同种类", item_sort=63,length=50)
	private String contract_type_name;

	@EntityAnnotation(item_name="合同归属", item_sort=64,length=50)
	private String contract_attach_name;

	@EntityAnnotation(item_name="社保说明", item_sort=65,length=50)
	private String social_security_name;


	@EntityAnnotation(item_name="参保城市", item_sort=66,length=20)
	private String insured_city_name;

	@EntityAnnotation(item_name="工作地点", item_sort=67,length=200)
	private String working_address_name;

	@EntityAnnotation(item_name="证书", item_sort=68,length=20)
	private String certificate_name;






	//转正月份
	private String confirmation_month;
	//合同到期月份
	private String contract_end_month;
	//社保基数
	private double base_cardinal;


	//查询时间段
	private Timestamp date1;
	private Timestamp date2;

	private Timestamp join_datetime1;
	private Timestamp join_datetime2;
	private Timestamp leave_job_datetime1;
	private Timestamp leave_job_datetime2;

	//合同到期时间段
	private Timestamp contrct_expiration_date1;
	private Timestamp contrct_expiration_date2;

	//试用期时间段
	private Timestamp tryoutDate1;
	private Timestamp tryoutDate2;


	/**
	 * 查询起始月
	 */
	private int month1;

	/**
	 * 查询结束月
	 */
	private int month2;

	//用于排序
	private String orderby;


	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getPosition_type() {
		return position_type;
	}
	public void setPosition_type(String position_type) {
		this.position_type = position_type;
	}
	public String getPosition_type1() {
		return position_type1;
	}
	public void setPosition_type1(String position_type1) {
		this.position_type1 = position_type1;
	}
	public String getPosition_type2() {
		return position_type2;
	}
	public void setPosition_type2(String position_type2) {
		this.position_type2 = position_type2;
	}
	public String getPosition_type3() {
		return position_type3;
	}
	public void setPosition_type3(String position_type3) {
		this.position_type3 = position_type3;
	}
	public Timestamp getBuild_datetime() {
		return build_datetime;
	}
	public void setBuild_datetime(Timestamp build_datetime) {
		this.build_datetime = build_datetime;
	}
	public String getBuild_userid() {
		return build_userid;
	}
	public void setBuild_userid(String build_userid) {
		this.build_userid = build_userid;
	}
	public String getBuild_username() {
		return build_username;
	}
	public void setBuild_username(String build_username) {
		this.build_username = build_username;
	}
	public String getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}
	public Timestamp getDelete_datetime() {
		return delete_datetime;
	}
	public void setDelete_datetime(Timestamp delete_datetime) {
		this.delete_datetime = delete_datetime;
	}
	public String getDelete_userid() {
		return delete_userid;
	}
	public void setDelete_userid(String delete_userid) {
		this.delete_userid = delete_userid;
	}
	public String getDelete_username() {
		return delete_username;
	}
	public void setDelete_username(String delete_username) {
		this.delete_username = delete_username;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getPosition_type_temp() {
		return position_type_temp;
	}
	public void setPosition_type_temp(String position_type_temp) {
		this.position_type_temp = position_type_temp;
	}
	public String getStaff_no() {
		return staff_no;
	}
	public void setStaff_no(String staff_no) {
		this.staff_no = staff_no;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCensus_property() {
		return census_property;
	}

	public void setCensus_property(String census_property) {
		this.census_property = census_property;
	}

	public String getIdentity_card_number() {
		return identity_card_number;
	}

	public void setIdentity_card_number(String identity_card_number) {
		this.identity_card_number = identity_card_number;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPressing_tel() {
		return pressing_tel;
	}

	public void setPressing_tel(String pressing_tel) {
		this.pressing_tel = pressing_tel;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getContract_start_date() {
		return contract_start_date;
	}

	public void setContract_start_date(Date contract_start_date) {
		this.contract_start_date = contract_start_date;
	}

	public Date getContract_end_date() {
		return contract_end_date;
	}

	public void setContract_end_date(Date contract_end_date) {
		this.contract_end_date = contract_end_date;
	}

	public int getContract_number() {
		return contract_number;
	}

	public void setContract_number(int contract_number) {
		this.contract_number = contract_number;
	}

	public String getGraduate_school() {
		return graduate_school;
	}

	public void setGraduate_school(String graduate_school) {
		this.graduate_school = graduate_school;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public Date getGraduation_date() {
		return graduation_date;
	}

	public void setGraduation_date(Date graduation_date) {
		this.graduation_date = graduation_date;
	}

	public double getWorking_life() {
		return working_life;
	}

	public void setWorking_life(double working_life) {
		this.working_life = working_life;
	}

	public String getRegistered_residence() {
		return registered_residence;
	}

	public void setRegistered_residence(String registered_residence) {
		this.registered_residence = registered_residence;
	}

	public String getPostal_address() {
		return postal_address;
	}

	public void setPostal_address(String postal_address) {
		this.postal_address = postal_address;
	}

	public Timestamp getJoin_datetime() {
		return join_datetime;
	}

	public void setJoin_datetime(Timestamp join_datetime) {
		this.join_datetime = join_datetime;
	}

	public Timestamp getLeave_job_datetime() {
		return leave_job_datetime;
	}

	public void setLeave_job_datetime(Timestamp leave_job_datetime) {
		this.leave_job_datetime = leave_job_datetime;
	}


	public Date getConfirmation_date() {
		return confirmation_date;
	}

	public void setConfirmation_date(Date confirmation_date) {
		this.confirmation_date = confirmation_date;
	}

	public double getTryout_salary() {
		return tryout_salary;
	}

	public void setTryout_salary(double tryout_salary) {
		this.tryout_salary = tryout_salary;
	}

	public double getTryout_basic_salary() {
		return tryout_basic_salary;
	}

	public void setTryout_basic_salary(double tryout_basic_salary) {
		this.tryout_basic_salary = tryout_basic_salary;
	}

	public double getTryout_post_salary() {
		return tryout_post_salary;
	}

	public void setTryout_post_salary(double tryout_post_salary) {
		this.tryout_post_salary = tryout_post_salary;
	}

	public double getTryout_performance_allowances() {
		return tryout_performance_allowances;
	}

	public void setTryout_performance_allowances(double tryout_performance_allowances) {
		this.tryout_performance_allowances = tryout_performance_allowances;
	}

	public double getOfficial_salary() {
		return official_salary;
	}

	public void setOfficial_salary(double official_salary) {
		this.official_salary = official_salary;
	}

	public double getBasic_salary() {
		return basic_salary;
	}

	public void setBasic_salary(double basic_salary) {
		this.basic_salary = basic_salary;
	}

	public double getPost_salary() {
		return post_salary;
	}

	public void setPost_salary(double post_salary) {
		this.post_salary = post_salary;
	}

	public double getPerformance_allowances() {
		return performance_allowances;
	}

	public void setPerformance_allowances(double performance_allowances) {
		this.performance_allowances = performance_allowances;
	}

	public double getComputer_allowance() {
		return computer_allowance;
	}

	public void setComputer_allowance(double computer_allowance) {
		this.computer_allowance = computer_allowance;
	}

	public double getMeal_allowance() {
		return meal_allowance;
	}

	public void setMeal_allowance(double meal_allowance) {
		this.meal_allowance = meal_allowance;
	}

	public double getProject_allowance() {
		return project_allowance;
	}

	public void setProject_allowance(double project_allowance) {
		this.project_allowance = project_allowance;
	}

	public double getTravel_allowance() {
		return travel_allowance;
	}

	public void setTravel_allowance(double travel_allowance) {
		this.travel_allowance = travel_allowance;
	}

	public String getInsurance_grade_id() {
		return insurance_grade_id;
	}

	public void setInsurance_grade_id(String insurance_grade_id) {
		this.insurance_grade_id = insurance_grade_id;
	}

	public String getSecurty_unit() {
		return securty_unit;
	}

	public void setSecurty_unit(String securty_unit) {
		this.securty_unit = securty_unit;
	}

	public double getEndowment_insurance_bypersonal() {
		return endowment_insurance_bypersonal;
	}

	public void setEndowment_insurance_bypersonal(double endowment_insurance_bypersonal) {
		this.endowment_insurance_bypersonal = endowment_insurance_bypersonal;
	}

	public double getMedical_insurance_bypersonal() {
		return medical_insurance_bypersonal;
	}

	public void setMedical_insurance_bypersonal(double medical_insurance_bypersonal) {
		this.medical_insurance_bypersonal = medical_insurance_bypersonal;
	}

	public double getLosejob_insurance_bypersonal() {
		return losejob_insurance_bypersonal;
	}

	public void setLosejob_insurance_bypersonal(double losejob_insurance_bypersonal) {
		this.losejob_insurance_bypersonal = losejob_insurance_bypersonal;
	}

	public double getReservefund_bypersonal() {
		return reservefund_bypersonal;
	}

	public void setReservefund_bypersonal(double reservefund_bypersonal) {
		this.reservefund_bypersonal = reservefund_bypersonal;
	}

	public double getIncometax_bypersonal() {
		return incometax_bypersonal;
	}

	public void setIncometax_bypersonal(double incometax_bypersonal) {
		this.incometax_bypersonal = incometax_bypersonal;
	}

	public double getEndowment_insurance_bycompany() {
		return endowment_insurance_bycompany;
	}

	public void setEndowment_insurance_bycompany(double endowment_insurance_bycompany) {
		this.endowment_insurance_bycompany = endowment_insurance_bycompany;
	}

	public double getMedical_insurance_bycompany() {
		return medical_insurance_bycompany;
	}

	public void setMedical_insurance_bycompany(double medical_insurance_bycompany) {
		this.medical_insurance_bycompany = medical_insurance_bycompany;
	}

	public double getLosejob_insurance_bycompany() {
		return losejob_insurance_bycompany;
	}

	public void setLosejob_insurance_bycompany(double losejob_insurance_bycompany) {
		this.losejob_insurance_bycompany = losejob_insurance_bycompany;
	}

	public double getMaternity_insurance_bycompany() {
		return maternity_insurance_bycompany;
	}

	public void setMaternity_insurance_bycompany(double maternity_insurance_bycompany) {
		this.maternity_insurance_bycompany = maternity_insurance_bycompany;
	}

	public double getJobharm_insurance_bycompany() {
		return jobharm_insurance_bycompany;
	}

	public void setJobharm_insurance_bycompany(double jobharm_insurance_bycompany) {
		this.jobharm_insurance_bycompany = jobharm_insurance_bycompany;
	}

	public double getReservefund_bypcompany() {
		return reservefund_bypcompany;
	}

	public void setReservefund_bypcompany(double reservefund_bypcompany) {
		this.reservefund_bypcompany = reservefund_bypcompany;
	}

	public double getExtra() {
		return extra;
	}

	public void setExtra(double extra) {
		this.extra = extra;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOpen_account() {
		return open_account;
	}

	public void setOpen_account(String open_account) {
		this.open_account = open_account;
	}

	public String getBank_card_number() {
		return bank_card_number;
	}

	public void setBank_card_number(String bank_card_number) {
		this.bank_card_number = bank_card_number;
	}

	public String getLead_id() {
		return lead_id;
	}

	public void setLead_id(String lead_id) {
		this.lead_id = lead_id;
	}

	public double getExtra_expend() {
		return extra_expend;
	}

	public void setExtra_expend(double extra_expend) {
		this.extra_expend = extra_expend;
	}

	public String getCan_send_info() {
		return can_send_info;
	}

	public void setCan_send_info(String can_send_info) {
		this.can_send_info = can_send_info;
	}

	public String getContract_type() {
		return contract_type;
	}

	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}

	public String getContract_attach() {
		return contract_attach;
	}

	public void setContract_attach(String contract_attach) {
		this.contract_attach = contract_attach;
	}

	public String getSocial_security() {
		return social_security;
	}

	public void setSocial_security(String social_security) {
		this.social_security = social_security;
	}

	public String getInsured_city() {
		return insured_city;
	}

	public void setInsured_city(String insured_city) {
		this.insured_city = insured_city;
	}

	public String getWorking_address() {
		return working_address;
	}

	public void setWorking_address(String working_address) {
		this.working_address = working_address;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public double getChildren_education() {
		return children_education;
	}

	public void setChildren_education(double children_education) {
		this.children_education = children_education;
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

	public double getContinuing_education() {
		return continuing_education;
	}

	public void setContinuing_education(double continuing_education) {
		this.continuing_education = continuing_education;
	}

	public String getWork_status() {
		return work_status;
	}

	public void setWork_status(String work_status) {
		this.work_status = work_status;
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

	public String getNationality_name() {
		return nationality_name;
	}

	public void setNationality_name(String nationality_name) {
		this.nationality_name = nationality_name;
	}

	public String getCensus_property_name() {
		return census_property_name;
	}

	public void setCensus_property_name(String census_property_name) {
		this.census_property_name = census_property_name;
	}

	public String getEducational_name() {
		return educational_name;
	}

	public void setEducational_name(String educational_name) {
		this.educational_name = educational_name;
	}

	public String getSpecialty_name() {
		return specialty_name;
	}

	public void setSpecialty_name(String specialty_name) {
		this.specialty_name = specialty_name;
	}

	public String getInsurance_radix() {
		return insurance_radix;
	}

	public void setInsurance_radix(String insurance_radix) {
		this.insurance_radix = insurance_radix;
	}

	public String getContract_type_name() {
		return contract_type_name;
	}

	public void setContract_type_name(String contract_type_name) {
		this.contract_type_name = contract_type_name;
	}

	public String getContract_attach_name() {
		return contract_attach_name;
	}

	public void setContract_attach_name(String contract_attach_name) {
		this.contract_attach_name = contract_attach_name;
	}

	public String getSocial_security_name() {
		return social_security_name;
	}

	public void setSocial_security_name(String social_security_name) {
		this.social_security_name = social_security_name;
	}

	public String getInsured_city_name() {
		return insured_city_name;
	}

	public void setInsured_city_name(String insured_city_name) {
		this.insured_city_name = insured_city_name;
	}

	public String getWorking_address_name() {
		return working_address_name;
	}

	public void setWorking_address_name(String working_address_name) {
		this.working_address_name = working_address_name;
	}

	public String getCertificate_name() {
		return certificate_name;
	}

	public void setCertificate_name(String certificate_name) {
		this.certificate_name = certificate_name;
	}

	public String getConfirmation_month() {
		return confirmation_month;
	}

	public void setConfirmation_month(String confirmation_month) {
		this.confirmation_month = confirmation_month;
	}

	public String getContract_end_month() {
		return contract_end_month;
	}

	public void setContract_end_month(String contract_end_month) {
		this.contract_end_month = contract_end_month;
	}

	public double getBase_cardinal() {
		return base_cardinal;
	}

	public void setBase_cardinal(double base_cardinal) {
		this.base_cardinal = base_cardinal;
	}

	public Timestamp getDate1() {
		return date1;
	}

	public void setDate1(Timestamp date1) {
		this.date1 = date1;
	}

	public Timestamp getDate2() {
		return date2;
	}

	public void setDate2(Timestamp date2) {
		this.date2 = date2;
	}

	public Timestamp getJoin_datetime1() {
		return join_datetime1;
	}

	public void setJoin_datetime1(Timestamp join_datetime1) {
		this.join_datetime1 = join_datetime1;
	}

	public Timestamp getJoin_datetime2() {
		return join_datetime2;
	}

	public void setJoin_datetime2(Timestamp join_datetime2) {
		this.join_datetime2 = join_datetime2;
	}

	public Timestamp getLeave_job_datetime1() {
		return leave_job_datetime1;
	}

	public void setLeave_job_datetime1(Timestamp leave_job_datetime1) {
		this.leave_job_datetime1 = leave_job_datetime1;
	}

	public Timestamp getLeave_job_datetime2() {
		return leave_job_datetime2;
	}

	public void setLeave_job_datetime2(Timestamp leave_job_datetime2) {
		this.leave_job_datetime2 = leave_job_datetime2;
	}

	public Timestamp getContrct_expiration_date1() {
		return contrct_expiration_date1;
	}

	public void setContrct_expiration_date1(Timestamp contrct_expiration_date1) {
		this.contrct_expiration_date1 = contrct_expiration_date1;
	}

	public Timestamp getContrct_expiration_date2() {
		return contrct_expiration_date2;
	}

	public void setContrct_expiration_date2(Timestamp contrct_expiration_date2) {
		this.contrct_expiration_date2 = contrct_expiration_date2;
	}

	public Timestamp getTryoutDate1() {
		return tryoutDate1;
	}

	public void setTryoutDate1(Timestamp tryoutDate1) {
		this.tryoutDate1 = tryoutDate1;
	}

	public Timestamp getTryoutDate2() {
		return tryoutDate2;
	}

	public void setTryoutDate2(Timestamp tryoutDate2) {
		this.tryoutDate2 = tryoutDate2;
	}

	public int getMonth1() {
		return month1;
	}

	public void setMonth1(int month1) {
		this.month1 = month1;
	}

	public int getMonth2() {
		return month2;
	}

	public void setMonth2(int month2) {
		this.month2 = month2;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public int getVersion_no() {
		return version_no;
	}

	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}
}
