package no.systema.jservices.tvinn.sad.z.maintenance.sadimport.jsonwriter;

import java.util.*;


import org.apache.logging.log4j.*;

import no.systema.jservices.tvinn.sad.z.maintenance.sadimport.model.dao.entities.*;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
import no.systema.main.util.JsonSpecialCharactersManager;
import no.systema.main.util.constants.JsonConstants;

/**
 * 
 * @author oscardelatorre
 * JSON outputter
 * May 13, 2016
 */
public class JsonTvinnMaintImportResponseWriterGyldigeKoder extends JsonResponseWriter {
	private static JsonSpecialCharactersManager jsonFixMgr = new JsonSpecialCharactersManager();
	private static Logger logger = LogManager.getLogger(JsonTvinnMaintImportResponseWriterGyldigeKoder.class.getName());
	//go to parent
	
}
