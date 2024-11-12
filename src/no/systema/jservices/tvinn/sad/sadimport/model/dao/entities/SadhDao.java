/**
 * 
 */
package no.systema.jservices.tvinn.sad.sadimport.model.dao.entities;

import no.systema.jservices.model.dao.entities.IDao;

import java.util.*;

import lombok.Data;

import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Nov, 2014
 *
 */
@Data
public class SadhDao implements IDao{
	
	private String o2_sist = "";
	private String o2_sidt = "";
	private String o2_simf = "";
	
	//These variables have been replaced by true variables implemented in AS400 (antk, antvp, sumbv)
	private Integer sumOfAntalKolliInItemLines = 0;
	public void setSumOfAntalKolliInItemLines(Integer value) {  this.sumOfAntalKolliInItemLines = value; }
	public Integer getSumOfAntalKolliInItemLines() {return this.sumOfAntalKolliInItemLines;}
	
	private Integer sumOfAntalItemLines = 0;
	public void setSumOfAntalItemLines(Integer value) {  this.sumOfAntalItemLines = value; }
	public Integer getSumOfAntalItemLines() {return this.sumOfAntalItemLines;}
	
	
	private Double sumTotalAmountItemLines = 0.00D;
	public void setSumTotalAmountItemLines(Double value) {  this.sumTotalAmountItemLines = value; }
	public Double getSumTotalAmountItemLines() {return this.sumTotalAmountItemLines;}
	
	
	private Double sumTotalBruttoViktItemLines = 0.00D;
	public void setSumTotalBruttoViktItemLines(Double value) {  this.sumTotalBruttoViktItemLines = value; }
	public Double getSumTotalBruttoViktItemLines() {return this.sumTotalBruttoViktItemLines;}
	
	
	private boolean finansOpplysningarExist = false;
	public void setFinansOpplysningarExist(boolean value) { this.finansOpplysningarExist = value; }
	public boolean getFinansOpplysningarExist (){ return this.finansOpplysningarExist; }

	
	//Used when different currencies exist. The main currency must be = SEK
	private String finansOpplysningarTotValidCurrency = "";
	private String finansOpplysningarTotSum = "";
	private String finansOpplysningarTotKurs = "";
	
	//New Sum-variables (antk, antvp, sumbv) coming now from the back-end
	private String antk = "";
	private String antvp = "";
	private String sumbv = "";
	
	private String sist = "";
	private String siavd = "";
	private String sitdn = "";
	private String sidt = "";
	
	private String siur = "";
	private String sidty = "";
	private String own_sidp = "";
	private String sidp = "";
	private String sidp2 = "";
	private String sikns = "";
	private String sinas = "";
	private String siads1 = "";
	private String siads2 = "";
	private String siads3 = "";
	private String sintk = "";
	private String siski = "";
	private String sikddk = "";
	private String sivkb = "";
	private String sikdc = "";
	private String sisg = "";
	private String siknk = "";
	private String sirg = "";
	private String simva = "";
	private String sinak = "";
	private String siadk1 = "";
	private String siadk2 = "";
	private String siadk3 = "";
	private String sival1 = "";
	private String sibel1 = "";
	private String sival2 = "";
	private String sibel2 = "";
	private String siftg2 = "";
	private String silka = "";
	private String sitlf = "";
	private String sinad = "";
	private String silv = "";
	private String silvt = "";
	private String sitrid = "";
	private String silkt = "";
	private String sival3 = "";
	private String sibel3 = "";
	
	private String sivku = "";
	private String sitst = "";
	private String sitrt = "";
	private String sitrm = "";
	private String sifif = "";
	private String sifid = "";
	private String sibelt = "";
	private String si07 = "";
	private String sikta = "";
	private String siktb = "";
	private String opdknum = "";
	private String sign = "";
	private String sift1 = "";
	private String sift2 = "";
	private String sift3 = "";
	private String sift4 = "";
	private String sidst = "";
	private String sibel4 = "";
	private String sidtg = "";
	private String deta = "";
	private String sitll = "";
	private String sitle = "";
	private String sils = "";
	private String sikdls = "";
	private String sikdh = "";
	private String sikdtr = "";
	private String sias = "";
	private String sibel5 = "";
	private String sibel6 = "";
	private String sibel7 = "";
	private String sibel8 = "";
	private String sibel9 = "";
	private String sibela = "";
	private String silv2 = "";
	private String sipos = "";
	private String sitarf = "";
	private String sivalb = "";
	private String sibelb = "";
	private String simid = "";
	private String simidk = "";
	private String simf = "";
	private String simp = "";
	private String sidto = "";
	private String simi = "";
	private String sibeld = "";
	private String sipkl = "";
	private String sikdv = "";
	private String si0035 = "";
	private String si27 = "";
	private String siopd = "";
	private String sifrbn = "";
	private String siws = "";
	private String siktc = "";
	private String sivalr = "";
	private String sibelr = "";
	private String sibels = "";
	private String sirab = "";
	private String insivf = "";
	private String insibvnv = "";
	private String h_xref = "";
	private String s3039ex1 = "";
	private String s3039ex2 = "";
	private String s3039ex3 = "";
	
	//OMBEREGNING variables
	private String om_sitype = ""; 
	
	private String om_sift01 = ""; 
	private String om_sift02 = ""; 
	private String om_sift03 = ""; 
	private String om_sift04 = ""; 
	private String om_sift05 = ""; 
	private String om_sift11 = ""; 
	private String om_sift12 = ""; 
	private String om_sift13 = ""; 
	private String om_sift14 = ""; 
	private String om_sift15 = ""; 
	private String om_sibltt = ""; 
	private String om_sity01 = ""; 
	private String om_sity02 = ""; 
	private String om_sity03 = ""; 
	private String om_sity04 = ""; 
	private String om_sity05 = ""; 
	private String om_sibl01 = ""; 
	private String om_sibl02 = ""; 
	private String om_sibl03 = ""; 
	private String om_sibl04 = ""; 
	private String om_sibl05 = ""; 
	
	
	/**
	 * Used for java reflection in other classes
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
