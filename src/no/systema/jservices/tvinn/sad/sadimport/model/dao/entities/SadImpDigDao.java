/**
 * 
 */
package no.systema.jservices.tvinn.sad.sadimport.model.dao.entities;

import no.systema.jservices.model.dao.entities.IDao;
import no.systema.jservices.tvinn.sad.digitoll.model.dao.entities.SadmoafDao;

import java.lang.reflect.Field;
import java.util.*;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Feb, 2024
 *
 */
@Data
public class SadImpDigDao implements IDao {
	
	private Integer siavd = 0;
	private String sisg = "";
	private String sist = "";
	private Integer sitdn = 0;
	private Integer sidt = 0;
	private Integer sidt_to = 0; //only for date purposes in the select
	private String sidtno = ""; //only for presentation purposes format NO (Norway)
	private String sidtg = "";
	private String sitrid = ""; //bilregnr
	private String sitle = ""; //Exped.enhet
	private String sitll = ""; //Løpenr
	private String h_xref = ""; //Ext.ref
	private String sinas = ""; //Avs
	private String sinak = ""; //Mott
	private String sivkb = ""; //Brutto vekt
	private String sign = ""; //Godsnr
	
	//Digitoll fields
	private Integer etlnrt = 0; 
	private Integer etpro = 0;
	private String etkmrk = "";
	private String emdkm = ""; 
	private String ehrgm = "";
	private Integer ehpro = 0;
	private Integer ehtdn = 0;
	private String ehdkh = "";
	private String ehvkb = "";
	private String ehvt = "";
	
	
	
	/*
	private String avd = "";
	private String opd = "";
	private String refnr = "";
	//Godsnr = sign
	private String sign = "";
	private String sg = "";
	private String datum = "";
	private String status = "";
	private String avsNavn = "";
	private String motNavn = "";
	private String aart = "";
	private String sitll = "";
	private String sitle = "";
	private String sivkb = "";
	private String simi = "";
	private String h_xref = "";
	private String o2_simf = "";
	private String o2_sist = "";
	private String o2_sidt = "";
	private String o2_sitll = "";
	private String epjn = "";
	private String detaout= "";
	*/


}
