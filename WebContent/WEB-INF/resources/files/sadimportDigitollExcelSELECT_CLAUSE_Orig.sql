select distinct a.siavd,a.sisg,a.sist,a.sitdn,a.sidt,a.sidtg,a.sitrid,a.sitle,a.sitll,a.sinas,a.sinak,a.sivkb,a.sign,b.etetad,
 varchar_format(to_date(char(a.sidt),'YYYYMMDD'),'DDMMYY') sidtno,
 varchar_format(to_date(char(b.etetad),'YYYYMMDD'),'DDMMYY') etetadno,
 b.etlnrt, b.etpro, b.etkmrk, c.emvkb
 from sadh as a
 full outer join sadmotf AS b
 on a.sitrid = b.etkmrk
 #and a.sisg = b.etsg 
 full outer join sadmomf AS c
 on b.etlnrt = c.emlnrt
 and a.sivkb = c.emvkb
