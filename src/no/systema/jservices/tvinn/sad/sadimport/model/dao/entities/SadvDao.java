/**
 * 
 */
package no.systema.jservices.tvinn.sad.sadimport.model.dao.entities;

import java.lang.reflect.Field;
import java.util.*;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

/**
 * 
 * @author oscardelatorre
 * @date Nov 24, 2014
 * AS400 File: SADV ; Format: SADVR
 * 
 *
 */
@Data
public class SadvDao  implements IDao  {
	
	private String debugPrintlnAjax = "";
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	//Aux. attribute to validate tolltariff
	private boolean validTolltariff = true;
	public void setValidTolltariff(boolean value) {  this.validTolltariff = value; }
	public boolean isValidTolltariff() {return this.validTolltariff;}
	
	private boolean multipleChoiceAvgifter = false;
	public void setMultipleChoiceAvgifter(boolean value) {  this.multipleChoiceAvgifter = value; }
	public boolean isMultipleChoiceAvgifter() {return this.multipleChoiceAvgifter;}
	
	private boolean singleChoiceAvgifter = false;
	public void setSingleChoiceAvgifter(boolean value) {  this.singleChoiceAvgifter = value; }
	public boolean isSingleChoiceAvgifter() {return this.singleChoiceAvgifter;}
	
	//Aux. attribute to validate mangdenhet
	private String extraMangdEnhet = "";
	public void setExtraMangdEnhet(String value) {  this.extraMangdEnhet = value; }
	public String getExtraMangdEnhet() {return this.extraMangdEnhet;}
		
	
	
	private String svavd = "";
	private String svtdn = "";
	private String svli = "";
	private String svlk = "";
	private String svvnt = "";
	private Boolean svvntValid = true;
	private String svtn = "";
	private String svpre = "";
	private String svpreae = "";
	private String svas = "";
	private String svpva = "";
	private String svmfr = "";
	private String svvf = "";
	private String svvktb = "";
	private String svntm = "";
	private String svbelt = "";
	private String svbels = "";
	private String svvktn = "";
	private String svft = "";
	private String svnt = "";
	private String sveh = "";
	private String svvt = "";
	private String svcref = "";
	private String svtoa = "";
	private String svtob = "";
	private String svkdaae = "";
	private String svkdsae = "";
	private String svblae = "";
	private String svblgae = "";
	private String svblsae = "";
	private String sveh2ae = "";
	private String svln = "";
	
	private String svrefl = "";
	private String svexr01 = "";
	private String svexr02 = "";
	private String svexr03 = "";
	private String svexr04 = "";
	private String svexr05 = "";
	private String svexr06 = "";
	private String svexr07 = "";
	
	private String sverr = "";
	//These w(x)(n) group is not a database field but an agreed work variable array from/to the 
	//service program...
	//wa1-wa7 array of SVFT (database field)
	private String wa1 = "";
	private String wa2 = "";
	private String wa3 = "";
	private String wa4 = "";
	private String wa5 = "";
	private String wa6 = "";
	private String wa7 = "";
	//wb1-wb7 array of SVNT (database field)
	private String wb1 = "";
	private String wb2 = "";
	private String wb3 = "";
	private String wb4 = "";
	private String wb5 = "";
	private String wb6 = "";
	private String wb7 = "";
	
	//wc1-wc7 array of SVEH (database field)
	private String wc1 = "";
	private String wc2 = "";
	private String wc3 = "";
	private String wc4 = "";
	private String wc5 = "";
	private String wc6 = "";
	private String wc7 = "";
	
	//wd1-wd5 array of SVVT (database field)
	private String wd1 = "";
	private String wd2 = "";
	private String wd3 = "";
	private String wd4 = "";
	private String wd5 = "";
	
	//we1-we10 array of SVCREF (database field)
	private String we1 = "";
	public void setWe1(String value) {  this.we1 = value; }
	public String getWe1() { return this.we1; }
	private String we2 = "";
	public void setWe2(String value) {  this.we2 = value; }
	public String getWe2() { return this.we2; }
	private String we3 = "";
	public void setWe3(String value) {  this.we3 = value; }
	public String getWe3() { return this.we3; }
	private String we4 = "";
	public void setWe4(String value) {  this.we4 = value; }
	public String getWe4() { return this.we4; }
	private String we5 = "";
	public void setWe5(String value) {  this.we5 = value; }
	public String getWe5() { return this.we5; }
	private String we6 = "";
	public void setWe6(String value) {  this.we6 = value; }
	public String getWe6() { return this.we6; }
	private String we7 = "";
	public void setWe7(String value) {  this.we7 = value; }
	public String getWe7() { return this.we7; }
	private String we8 = "";
	public void setWe8(String value) {  this.we8 = value; }
	public String getWe8() { return this.we8; }
	private String we9 = "";
	public void setWe9(String value) {  this.we9 = value; }
	public String getWe9() { return this.we9; }
	private String we10 = "";
	public void setWe10(String value) {  this.we10 = value; }
	public String getWe10() { return this.we10; }
	
	//wf1-wf10 array of SVTOA (database field)
	private String wf1 = "";
	public void setWf1(String value) {  this.wf1 = value; }
	public String getWf1() { return this.wf1; }
	private String wf2 = "";
	public void setWf2(String value) {  this.wf2 = value; }
	public String getWf2() { return this.wf2; }
	private String wf3 = "";
	public void setWf3(String value) {  this.wf3 = value; }
	public String getWf3() { return this.wf3; }
	private String wf4 = "";
	public void setWf4(String value) {  this.wf4 = value; }
	public String getWf4() { return this.wf4; }
	private String wf5 = "";
	public void setWf5(String value) {  this.wf5 = value; }
	public String getWf5() { return this.wf5; }
	private String wf6 = "";
	public void setWf6(String value) {  this.wf6 = value; }
	public String getWf6() { return this.wf6; }
	private String wf7 = "";
	public void setWf7(String value) {  this.wf7 = value; }
	public String getWf7() { return this.wf7; }
	private String wf8 = "";
	public void setWf8(String value) {  this.wf8 = value; }
	public String getWf8() { return this.wf8; }
	private String wf9 = "";
	public void setWf9(String value) {  this.wf9 = value; }
	public String getWf9() { return this.wf9; }
	private String wf10 = "";
	public void setWf10(String value) {  this.wf10 = value; }
	public String getWf10() { return this.wf10; }
	
	//wg1-wg8 array of SVKDAAE (database field)
	private String wg1 = "";
	public void setWg1(String value) {  this.wg1 = value; }
	public String getWg1() { return this.wg1; }
	private String wg2 = "";
	public void setWg2(String value) {  this.wg2 = value; }
	public String getWg2() { return this.wg2; }
	private String wg3 = "";
	public void setWg3(String value) {  this.wg3 = value; }
	public String getWg3() { return this.wg3; }
	private String wg4 = "";
	public void setWg4(String value) {  this.wg4 = value; }
	public String getWg4() { return this.wg4; }
	private String wg5 = "";
	public void setWg5(String value) {  this.wg5 = value; }
	public String getWg5() { return this.wg5; }
	private String wg6 = "";
	public void setWg6(String value) {  this.wg6 = value; }
	public String getWg6() { return this.wg6; }
	private String wg7 = "";
	public void setWg7(String value) {  this.wg7 = value; }
	public String getWg7() { return this.wg7; }
	private String wg8 = "";
	public void setWg8(String value) {  this.wg8 = value; }
	public String getWg8() { return this.wg8; }
	
	//wh1-wh8 array of SVKDSAE (database field)
	private String wh1 = "";
	public void setWh1(String value) {  this.wh1 = value; }
	public String getWh1() { return this.wh1; }
	private String wh2 = "";
	public void setWh2(String value) {  this.wh2 = value; }
	public String getWh2() { return this.wh2; }
	private String wh3 = "";
	public void setWh3(String value) {  this.wh3 = value; }
	public String getWh3() { return this.wh3; }
	private String wh4 = "";
	public void setWh4(String value) {  this.wh4 = value; }
	public String getWh4() { return this.wh4; }
	private String wh5 = "";
	public void setWh5(String value) {  this.wh5 = value; }
	public String getWh5() { return this.wh5; }
	private String wh6 = "";
	public void setWh6(String value) {  this.wh6 = value; }
	public String getWh6() { return this.wh6; }
	private String wh7 = "";
	public void setWh7(String value) {  this.wh7 = value; }
	public String getWh7() { return this.wh7; }
	private String wh8 = "";
	public void setWh8(String value) {  this.wh8 = value; }
	public String getWh8() { return this.wh8; }
		
	//wi1-wi8 array of SVBLAE (database field)
	private String wi1 = "";
	public void setWi1(String value) {  this.wi1 = value; }
	public String getWi1() { return this.wi1; }
	private String wi2 = "";
	public void setWi2(String value) {  this.wi2 = value; }
	public String getWi2() { return this.wi2; }
	private String wi3 = "";
	public void setWi3(String value) {  this.wi3 = value; }
	public String getWi3() { return this.wi3; }
	private String wi4 = "";
	public void setWi4(String value) {  this.wi4 = value; }
	public String getWi4() { return this.wi4; }
	private String wi5 = "";
	public void setWi5(String value) {  this.wi5 = value; }
	public String getWi5() { return this.wi5; }
	private String wi6 = "";
	public void setWi6(String value) {  this.wi6 = value; }
	public String getWi6() { return this.wi6; }
	private String wi7 = "";
	public void setWi7(String value) {  this.wi7 = value; }
	public String getWi7() { return this.wi7; }
	private String wi8 = "";
	public void setWi8(String value) {  this.wi8 = value; }
	public String getWi8() { return this.wi8; }
	
	//wj1-wj8 array of SVBLGAE (database field)
	private String wj1 = "";
	public void setWj1(String value) {  this.wj1 = value; }
	public String getWj1() { return this.wj1; }
	private String wj2 = "";
	public void setWj2(String value) {  this.wj2 = value; }
	public String getWj2() { return this.wj2; }
	private String wj3 = "";
	public void setWj3(String value) {  this.wj3 = value; }
	public String getWj3() { return this.wj3; }
	private String wj4 = "";
	public void setWj4(String value) {  this.wj4 = value; }
	public String getWj4() { return this.wj4; }
	private String wj5 = "";
	public void setWj5(String value) {  this.wj5 = value; }
	public String getWj5() { return this.wj5; }
	private String wj6 = "";
	public void setWj6(String value) {  this.wj6 = value; }
	public String getWj6() { return this.wj6; }
	private String wj7 = "";
	public void setWj7(String value) {  this.wj7 = value; }
	public String getWj7() { return this.wj7; }
	private String wj8 = "";
	public void setWj8(String value) {  this.wj8 = value; }
	public String getWj8() { return this.wj8; }
	
	//wk1-wk8 array of SVBLSAE (database field)
	private String wk1 = "";
	public void setWk1(String value) {  this.wk1 = value; }
	public String getWk1() { return this.wk1; }
	private String wk2 = "";
	public void setWk2(String value) {  this.wk2 = value; }
	public String getWk2() { return this.wk2; }
	private String wk3 = "";
	public void setWk3(String value) {  this.wk3 = value; }
	public String getWk3() { return this.wk3; }
	private String wk4 = "";
	public void setWk4(String value) {  this.wk4 = value; }
	public String getWk4() { return this.wk4; }
	private String wk5 = "";
	public void setWk5(String value) {  this.wk5 = value; }
	public String getWk5() { return this.wk5; }
	private String wk6 = "";
	public void setWk6(String value) {  this.wk6 = value; }
	public String getWk6() { return this.wk6; }
	private String wk7 = "";
	public void setWk7(String value) {  this.wk7 = value; }
	public String getWk7() { return this.wk7; }
	private String wk8 = "";
	public void setWk8(String value) {  this.wk8 = value; }
	public String getWk8() { return this.wk8; }
		
	private String svcnr = "";
	
	private String own_svdp = "";
	
	private String svdp = "";
	private String svdp2 = "";
	
	//From header in order to send to validator (place holder)
	//private String ekspedType = "";
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

}
