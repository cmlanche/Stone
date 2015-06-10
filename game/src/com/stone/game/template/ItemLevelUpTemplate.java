package com.stone.game.template;
import com.stone.core.annotation.TemplateClass;
/**
 * The template of ItemLevelUpTemplate( 物品升级模版 );<br>
 *
 * Auto generated by tools, do not modified;
 *
 * @author crazyjohn
 *
 */
 @TemplateClass(templateFile = "xml/ItemLevelUpTemplate.xml")
public class ItemLevelUpTemplate {
    /**  等级s */
	protected int level;
    /** 消耗货币s */
	protected int costCoin;
    /** descs */
	protected String desc;
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	public int getCostCoin() {
		return costCoin;
	}
	
	public void setCostCoin(int costCoin) {
		this.costCoin = costCoin;
	}
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

}