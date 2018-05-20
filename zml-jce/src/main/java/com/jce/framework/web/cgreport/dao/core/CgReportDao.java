package com.jce.framework.web.cgreport.dao.core;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

@Repository("cgReportDao")
public interface CgReportDao{

	@Arguments("configId")
	List<Map<String,Object>> queryCgReportItems(String configId);
	
	@Arguments("id")
	Map queryCgReportMainConfig(String id);
}
