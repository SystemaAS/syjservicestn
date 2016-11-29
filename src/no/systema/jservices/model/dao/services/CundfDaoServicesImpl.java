package no.systema.jservices.model.dao.services;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.jservices.model.dao.mapper.CundfMapper;
import no.systema.main.util.DbErrorMessageManager;


public class CundfDaoServicesImpl implements CundfDaoServices {
	private static Logger logger = Logger.getLogger(CundfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();

	
	/**
	 * 
	 * @return
	 */
	public List<CundfDao> getList(){
		//String sql = "select knavn, adr1, adr2, postnr, adr3 from cundf";
		StringBuffer sqlBuffer = new StringBuffer(this.getSELECT_FROM_CLAUSE());
		return this.jdbcTemplate.query( sqlBuffer.toString(), new CundfMapper());
	}

	
	@Override
	public List getListForQualityValidation() {
/*		Selektion:
		AKTKOD   1A    Aktivitetskod        = 'A'
		FIRMA    2A    Firmakode            = FIFIRM från filen: FIRM
		SYRG    14A    Organisasjonsnummer  <> blankt och <> '000000000'
	    SYLAND 2A Landkod = blank eller 'NO'
*/				
		StringBuffer sqlBuffer = new StringBuffer(this.getSELECT_FROM_CLAUSE());
		sqlBuffer.append(" and aktkod = 'A'");
		sqlBuffer.append(" and (syland = 'NO' or NULLIF(syland, '') IS NULL) ");
		sqlBuffer.append(" and NULLIF(syrg, '') IS NOT NULL ");
		sqlBuffer.append(" and syrg <> '000000000'");

		return this.jdbcTemplate.query( sqlBuffer.toString() ,new CundfMapper());
	}                                    
	
	@Override
	public List findById(String id, StringBuffer errorStackTrace){
		List<CundfDao> retval = new ArrayList<CundfDao>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" and kundnr = ? ");
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id }, new CundfMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}	
	
	
	
	private String getSELECT_FROM_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select kundnr, knavn, adr1, adr2, postnr, adr3, firma, syrg, syland, ");
		//all columns...map to  CundfMapper as needed
		sql.append(" dkund, kpers, sonavn, valkod, spraak, bankg, postg, fmot, betbet, ");
		sql.append(" betmat, sfakt, kgrens, tfaxnr, syregn, sykont, sylikv, syopdt, syminu, ");
		sql.append(" syutlp, sypoge, systat, syselg, syiat1, syiat2, sycoty, syfr01, syfr02, ");
		sql.append(" syfr03, syfr04, syfr05, syfr06, sysalu, syepos, aknrku, vatkku, xxbre, ");
		sql.append(" xxlen, xxinm3, xxinlm, rnraku, golk, kundgr, pnpbku, adr21, eori ");
		sql.append(" FROM cundfny a, firm b ");
		sql.append(" WHERE a.firma = b.fifirm ");
		
		return sql.toString();
	}	
	
	
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}

}
