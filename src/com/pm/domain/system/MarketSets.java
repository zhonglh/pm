package com.pm.domain.system;

import com.pm.domain.business.IdEntity;
import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class MarketSets  extends IdEntity implements java.io.Serializable{
	

	@EntityAnnotation(item_name="平台收费比例", item_sort=1)
	private double platform_ratio;
	


	@EntityAnnotation(item_name="影响级数", item_sort=2)
	private int affect_level_number;

	@EntityAnnotation(item_name="收费比例", item_sort=3)
	private double affect_ratio;	

	private String affect_strategy;	

	@EntityAnnotation(item_name="递减系数", item_sort=5)
	private double affect_decline_factor;	

	/////////////////////////////////
	
	
	@EntityAnnotation(item_name="收费策略", item_sort=4)
	private String affect_strategy_name;	
	
	
	public int getAffect_level_number() {
		return affect_level_number;
	}
	public void setAffect_level_number(int affect_level_number) {
		this.affect_level_number = affect_level_number;
	}
	public double getAffect_ratio() {
		return affect_ratio;
	}
	public void setAffect_ratio(double affect_ratio) {
		this.affect_ratio = affect_ratio;
	}
	public String getAffect_strategy() {
		return affect_strategy;
	}
	public void setAffect_strategy(String affect_strategy) {
		this.affect_strategy = affect_strategy;
	}
	public double getAffect_decline_factor() {
		return affect_decline_factor;
	}
	public void setAffect_decline_factor(double affect_decline_factor) {
		this.affect_decline_factor = affect_decline_factor;
	}
	public double getPlatform_ratio() {
		return platform_ratio;
	}
	public void setPlatform_ratio(double platform_ratio) {
		this.platform_ratio = platform_ratio;
	}
	public String getAffect_strategy_name() {
		return affect_strategy_name;
	}
	public void setAffect_strategy_name(String affect_strategy_name) {
		this.affect_strategy_name = affect_strategy_name;
	}
	
	@Override
	public String toString() {
		return "";
	}
	
	
	
	
	
}
