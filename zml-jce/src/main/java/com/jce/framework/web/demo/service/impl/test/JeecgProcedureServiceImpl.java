package com.jce.framework.web.demo.service.impl.test;

import java.util.List;
import java.util.Map;

import com.jce.framework.web.demo.dao.test.JeecgProcedureDao;
import com.jce.framework.web.demo.entity.test.JeecgDemo;
import com.jce.framework.web.demo.service.test.JeecgProcedureServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jeecgProcedureServiceImpl")
@Transactional
public class JeecgProcedureServiceImpl extends JdbcDaoSupport implements JeecgProcedureServiceI{

	@Autowired
	private JeecgProcedureDao jeecgProcedureDao;
	@Override
	public List queryDataByProcedure(String tableName, String fields, String whereSql) {
		//return jeecgProcedureDao.queryDataByProcedure(tableName, fields, whereSql);
		String sql = "call formDataList('"+tableName+"','"+fields+"','"+whereSql+"')";
		return this.getJdbcTemplate().queryForList(sql);
	}

}