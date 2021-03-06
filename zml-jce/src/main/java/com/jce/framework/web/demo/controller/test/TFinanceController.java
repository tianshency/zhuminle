package com.jce.framework.web.demo.controller.test;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.stream.FileCacheImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jce.framework.web.demo.entity.test.TFinanceEntity;
import com.jce.framework.web.demo.entity.test.TFinanceFilesEntity;
import com.jce.framework.web.demo.service.test.TFinanceServiceI;
import com.jce.framework.web.system.pojo.base.TSDocument;
import com.jce.framework.web.system.service.SystemService;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.common.UploadFile;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.core.util.oConvertUtils;
import com.jce.framework.tag.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller
 * @Description: 资金管理
 * @author 张代浩
 * @date 2013-06-27 00:10:59
 * @version V1.0   
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/tFinanceController")
public class TFinanceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TFinanceController.class);

	@Autowired
	private TFinanceServiceI tFinanceService;
	@Autowired
	private SystemService systemService;


	/**
	 * 资金管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tFinance")
	public ModelAndView tFinance(HttpServletRequest request) {
		return new ModelAndView("sys/demo/test/tFinanceList");
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
	public void datagrid(TFinanceEntity tFinance,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TFinanceEntity.class, dataGrid);
		//查询条件组装器
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tFinance, request.getParameterMap());
		this.tFinanceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	
	/**
	 * 保存文件
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, TFinanceFilesEntity financeFile) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));// 文件ID
		
		String financeId = oConvertUtils.getString(request.getParameter("financeId"));//资金ID
		
		if (StringUtil.isNotEmpty(fileKey)) {
			financeFile.setId(fileKey);
			financeFile = systemService.getEntity(TFinanceFilesEntity.class, fileKey);

		}
		TFinanceEntity finance = systemService.getEntity(TFinanceEntity.class, financeId);
		financeFile.setFinance(finance);
		UploadFile uploadFile = new UploadFile(request, financeFile);
		uploadFile.setCusPath("files");
		uploadFile.setSwfpath("swfpath");
		uploadFile.setByteField(null);//不存二进制内容
		financeFile = systemService.uploadFile(uploadFile);
		attributes.put("fileKey", financeFile.getId());
		attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + financeFile.getId());
		attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + financeFile.getId());
		j.setMsg("文件添加成功");
		j.setAttributes(attributes);

		return j;
	}
	
	/**
	 * 删除资金管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TFinanceEntity tFinance, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tFinance = systemService.getEntity(TFinanceEntity.class, tFinance.getId());
		message = "删除成功";
		tFinanceService.deleteFinance(tFinance);
		systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加资金管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TFinanceEntity tFinance, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tFinance.getId())) {
			message = "更新成功";
			TFinanceEntity t = tFinanceService.get(TFinanceEntity.class, tFinance.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tFinance, t);
				tFinanceService.saveOrUpdate(t);
				systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			tFinanceService.save(tFinance);
		
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setObj(tFinance);
		return j;
	}

	
	/**
	 * 删除文档
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delFile")
	@ResponseBody
	public AjaxJson delFile( HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String id  = request.getParameter("id");
		TFinanceFilesEntity file = systemService.getEntity(TFinanceFilesEntity.class, id);
		message = "" + file.getAttachmenttitle() + "被删除成功";
		tFinanceService.deleteFile(file);
		systemService.addLog("", "", message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setSuccess(true);
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 资金管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TFinanceEntity tFinance, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tFinance.getId())) {
			tFinance = tFinanceService.getEntity(TFinanceEntity.class, tFinance.getId());
			req.setAttribute("tFinancePage", tFinance);
		}
		return new ModelAndView("sys/demo/test/tFinance");
	}
}
