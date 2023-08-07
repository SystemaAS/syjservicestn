package no.systema.jservices.tvinn.sad.digitoll.model.dao.entities;

import java.util.HashMap;
import java.util.Map; 
import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;

@Data
public class OscditDao implements IDao {
	
	private String etst = ""; // varchar(1) , 
	private String etuuid = ""; // varchar(36), 
	private String etmid = ""; // varchar(18), 
	private Integer etavd = 0; // numeric(4) default 0,
	private Integer etpro = 0; // numeric(8) default 0, 
	private Integer etdtr = 0; // numeric(8) default 0, etsg varchar(8),
	private String etsg = ""; // varchar(3)
	private String etnar = ""; // varchar(30), etrgr varchar(17), 
	private String etstr = ""; // varchar(3), etpsr varchar(24),
	private String etlkr = ""; // varchar(2) , etad1r varchar(30), 
	private String etpnr = ""; // varchar(9), etemr varchar(50),
	private String etemrt = ""; // varchar(2), etkmrk varchar(30) , 
	private String etktyp = ""; // varchar(2), etktm varchar(4) ,
	private String etklk = ""; // varchar(2), etktkd varchar(1), 
	private String etsjaf = ""; // varchar(50), etsjaemr varchar(50), 
	private String etsjaemrt = ""; // varchar(2), etnat varchar(30) , 
	private String etrgt = ""; // varchar(17), etpst varchar(24), 
	private String etlkt = ""; // varchar(2), etad1t varchar(30), 
	private String etpnt = ""; // varchar(9), etemt varchar(50), 
	private String etemtt = ""; // varchar(2),ettsd varchar(8), 
	private Integer etetad = 0; // numeric(8) default 0, 
	private Integer etetat = 0; // numeric(6) default 0, 
	private Integer etstad = 0; // numeric(8) default 0, 
	private Integer etstat = 0; // numeric(6) default 0, 
	private String etdkm = ""; // varchar(50), etdkmt varchar(4)
	

	
}



/*
DROP TABLE SYSPEDF.OSCDIT 

CREATE TABLE SYSPEDF.OSCDIT (
  ETST VARCHAR(1) , ETUUID VARCHAR(36), 
  ETMID VARCHAR(18), ETAVD NUMERIC(4) DEFAULT 0,
  ETPRO NUMERIC(8) DEFAULT 0, 
  ETDTR NUMERIC(8) DEFAULT 0, ETSG VARCHAR(8),
  ETNAR VARCHAR(30), ETRGR VARCHAR(17), 
  ETSTR VARCHAR(3), ETPSR VARCHAR(24),
  ETLKR VARCHAR(2) , ETAD1R VARCHAR(30), 
  ETPNR VARCHAR(9), ETEMR VARCHAR(50),
  ETEMRT VARCHAR(2), ETKMRK VARCHAR(30) , 
  ETKTYP VARCHAR(2), ETKTM VARCHAR(4) ,
  ETKLK VARCHAR(2), ETKTKD VARCHAR(1), 
  ETSJAF VARCHAR(50), ETSJAEMR VARCHAR(50), 
  ETSJAEMRT VARCHAR(2), ETNAT VARCHAR(30) , 
  ETRGT VARCHAR(17), ETPST VARCHAR(24), 
  ETLKT VARCHAR(2), ETAD1T VARCHAR(30), 
  ETPNT VARCHAR(9), ETEMT VARCHAR(50), 
  ETEMTT VARCHAR(2),ETTSD VARCHAR(8), 
  ETETAD NUMERIC(8) DEFAULT 0, 
  ETETAT NUMERIC(6) DEFAULT 0, 
  ETSTAD NUMERIC(8) DEFAULT 0, 
  ETSTAT NUMERIC(6) DEFAULT 0, 
  ETDKM VARCHAR(50), ETDKMT VARCHAR(4)
  )





"documentIssueDate": "2022-04-20T07:49:52Z",
  "representative": {   
    "name": "Bring AS",                   -->ETNAR 30
    "identificationNumber": "951357482",  -->ETRGR 17
    "status": "2",                        -->ETSTR 3
    "address": {
      "city": "Oslo",                     -->ETPSR 24
      "country": "NO",                    -->ETLKR 2
      "subDivision": "string",
      "streetLine": "Hausemanns gate",    -->ETAD1R 30
      "postcode": "0530",                 -->ETPNR 9
      "streetAdditionalLine": "string",  
      "number": "52F",
      "poBox": "P.B. 0201"
    },
    "communication": [
      {
        "identifier": "en-epost@mail.no", -->ETEMR 50
        "type": "ME"                      -->ETEMRT 2
      }
    ]
  },
  "activeBorderTransportMeans": {
    "identificationNumber": "3535353535353535",  -->ETKMRK 30
    "typeOfIdentification": "40",                -->ETKTYP 2
    "typeOfMeansOfTransport": "4000",            -->ETKTM 4
    "conveyanceReferenceNumber": "stringstringstrin",
    "countryCode": "NO",                         -->ETKLK 2
    "modeOfTransportCode": "4",                  -->ETKTKD 1
    "operator": {
      "name": "Kari Nordmann",        -->ETSJAF 50
      "communication": [
        {
          "identifier": "en-epost@mail.no",   -->ETSJAEMR 50
          "type": "EM"                        -->ETSJAEMRT 2
        }
      ]
    }
  },
  "carrier": {
    "name": "SAS Norge",                    -->ETNAT 30
    "identificationNumber": "961510740",    -->ETRGT 17
    "address": {
      "city": "string",                     -->ETPST 24
      "country": "st",                      -->ETLKT 2
      "subDivision": "string",
      "postcode": "string",                 -->ETPNT 9
      "streetLine": "string",               -->ETAD1T 30
      "streetAdditionalLine": "string",
      "number": "string",
      "poBox": "string"
    },
    "communication": [
      {
        "identifier": "en-epost@mail.no",   -->ETEMT 50
        "type": "EM"                        -->ETEMTT 2
      }
    ]
  },
"customsOfficeOfFirstEntry": {
    "referenceNumber": "NO351001"           -->ETTSD 8
  },
  "estimatedDateAndTimeOfArrival": "2023-02-02T12:00:00Z",    -->ETETAD(8 SONET) + ETETAT (6 SONET)
  "scheduledDateAndTimeOfArrival": "2023-02-02T12:00:00Z",    -->ETSTAD(8 SONET) + ETSTAT (6 SONET)
  "consignmentMasterLevel": [
    {
      "transportDocumentMasterLevel": {
        "documentNumber": "string",         -->ETDKM  50
        "type": "N741"                      -->ETDKMT 4
      }
    }
  ]
}




insert into oscdit (
etavd, etpro, etnar,
etrgr, etstr, etpsr, etlkr, etad1r,  
etpnr, etemr, etemrt,
etkmrk, etktyp, etktm, etklk,
etktkd, etsjaf, etsjaemr, etsjaemrt,
etnat, etrgt, etpst, etlkt,
etad1t, etpnt, etemt, etemtt, ettsd,
etetad, etetat, etstad, etstat,
etdkm, etdkmt
  ) values (
1, 501941, 'VesternGeco AS',
'936809219', '2', 'OSLO', 'NO', 'Skippergt. 8-10',
'0152', '22335760', 'TE',
'AA123456', '10', '31', 'NO',
'4', 'OLA NORMANN', 'ola@normann.com', 'EM',
'CarrierGeco AS', '931234998', 'OSLO', 'NO',
'Dodensvei. 1-2', '0152', 'tarzan@dvei.com', 'EM', 'NO351001',
20231001, 1700, 20231002, 1600,
'TEST-FraktDocNr-20230511', 'N730'
)
*/