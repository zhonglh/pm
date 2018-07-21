package com.pm.util.constant;

public enum EnumPersonnelMonthlyType {
	
	JoinStaff("A","personnel.monthly.A","入职"),
	LeaveStaff("B","personnel.monthly.B","离职"),
	
	Official("1","personnel.monthly.1","转正"),
	AddSalary("2","personnel.monthly.2","加薪"),
	DecrSalary("3","personnel.monthly.3","减薪"),
	AddInsurance("4","personnel.monthly.4","社保增员"),
	DecrInsurance("5","personnel.monthly.5","社保减员"),
	AddReserveFund("6","personnel.monthly.6","公积金增员"),
	DecrReserveFund("7","personnel.monthly.7","公积金减员"),
	Bonus("8","personnel.monthly.8","奖金"),	
	SalarySupply("9","personnel.monthly.9","工资补充"),
	;
	

	private String id;
	private String i18ncode;
	private String name;

	
	
	private EnumPersonnelMonthlyType(String id, String i18ncode,String name) {
		this.id = id;
		this.i18ncode = i18ncode;
		this.name = name;
	}
	
	public static EnumPersonnelMonthlyType getEnumByName(String name){
		for(EnumPersonnelMonthlyType type : EnumPersonnelMonthlyType.values()){
			if(type.getName().equals(name)) return type;
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getI18ncode() {
		return i18ncode;
	}

	public void setI18ncode(String i18ncode) {
		this.i18ncode = i18ncode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

}
