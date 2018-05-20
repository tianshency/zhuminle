package com.jce.framework.web.demo.controller.test;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jce.framework.web.demo.entity.test.JeecgJdbcEntity;
import com.jce.framework.web.demo.service.test.JeecgJdbcServiceI;
import com.jce.framework.web.system.pojo.base.TSDepart;
import com.jce.framework.web.system.service.SystemService;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller
 * @Description: 页面不用自定义标签
 *@author  张代浩
 */
//@Scope("prototype")
@Controller
@RequestMapping("/jeecgEasyUIController")
public class JeecgEasyUIController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JeecgEasyUIController.class);

	@Autowired
	private JeecgJdbcServiceI jeecgJdbcService;
	@Autowired
	private SystemService systemService;

	/**
	 * 页面不用自定义标签 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgEasyUI")
	public ModelAndView jeecgEasyUI(HttpServletRequest request) {
        String departsReplace = "";
        List<TSDepart> departList = systemService.getList(TSDepart.class);
        for (TSDepart depart : departList) {
            if (departsReplace.length() > 0) {
                departsReplace += ",";
            }
            departsReplace += depart.getDepartname() + "_" + depart.getId();
        }
        request.setAttribute("departsReplace", departsReplace);
		return new ModelAndView("sys/demo/notag/jeecgEasyUIList");
	
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(JeecgJdbcEntity jeecgJdbc,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		// 方式1, 用底层自带的方式往对象中设值  -------------------
		/*
		this.jeecgJdbcService.getDatagrid1(jeecgJdbc, dataGrid);
		TagUtil.datagrid(response, dataGrid);
		// end of 方式1 ========================================= */ 
		/*
		this.jeecgJdbcService.getDatagrid2(jeecgJdbc, dataGrid);
		TagUtil.datagrid(response, dataGrid);
		// end of 方式2 ========================================= */ 
		//*
		JSONObject jObject = this.jeecgJdbcService.getDatagrid3(jeecgJdbc, dataGrid);
		responseDatagrid(response, jObject);
		// end of 方式3 ========================================= */
	}

	/**
	 * 删除通过JDBC访问数据库
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(JeecgJdbcEntity jeecgJdbc, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		
		String sql = "delete from jeecg_demo where id='" + jeecgJdbc.getId() + "'";
		jeecgJdbcService.executeSql(sql);

		message = "删除成功";
		systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加通过JDBC访问数据库
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(JeecgJdbcEntity jeecgJdbc, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgJdbc.getId())) {
			message = "更新成功";
			JeecgJdbcEntity t = jeecgJdbcService.get(JeecgJdbcEntity.class, jeecgJdbc.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(jeecgJdbc, t);
				jeecgJdbcService.saveOrUpdate(t);
				systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			jeecgJdbcService.save(jeecgJdbc);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 通过JDBC访问数据库列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(JeecgJdbcEntity jeecgJdbc, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgJdbc.getId())) {
			jeecgJdbc = jeecgJdbcService.getEntity(JeecgJdbcEntity.class, jeecgJdbc.getId());
			req.setAttribute("jeecgJdbcPage", jeecgJdbc);
		}
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		return new ModelAndView("sys/demo/notag/jeecgEasyUI");
	}
	// 以下各函数可以提成共用部件 (Add by Quainty)
	public void responseDatagrid(HttpServletResponse response, JSONObject jObject) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter pw = null;
		try {
			pw=response.getWriter();
			pw.write(jObject.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				pw.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}	
}
