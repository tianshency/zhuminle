package com.zml.biz.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.jce.framework.core.beanvalidator.BeanValidators;
import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil;
import com.jce.framework.core.util.ExceptionUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.cgform.entity.upload.CgUploadEntity;
import com.jce.framework.web.cgform.service.config.CgFormFieldServiceI;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserReleaseDocEntity;
import com.zml.service.ZmlMyReleaseDocServiceI;
/**   
 * @Title: Controller  
 * @Description: 发布文档
 * @author onlineGenerator
 * @date 2017-01-17 09:53:32
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlMyReleaseDocController")
public class ZmlMyReleaseDocController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlMyReleaseDocController.class);

	@Autowired
	private ZmlMyReleaseDocServiceI zmlMyReleaseDocService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	


	/**
	 * 发布文档列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/release_doc/zmlMyReleaseDocList");
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
	public void datagrid(ZmlUserReleaseDocEntity zmlMyReleaseDoc,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserReleaseDocEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlMyReleaseDoc, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlMyReleaseDocService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除发布文档
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlUserReleaseDocEntity zmlMyReleaseDoc, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlMyReleaseDoc = systemService.getEntity(ZmlUserReleaseDocEntity.class, zmlMyReleaseDoc.getId());
		message = "发布文档删除成功";
		try{
			zmlMyReleaseDocService.delete(zmlMyReleaseDoc);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "发布文档删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除发布文档
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布文档删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlUserReleaseDocEntity zmlMyReleaseDoc = systemService.getEntity(ZmlUserReleaseDocEntity.class, 
				id
				);
				zmlMyReleaseDocService.delete(zmlMyReleaseDoc);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "发布文档删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加发布文档
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlUserReleaseDocEntity zmlMyReleaseDoc, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布文档添加成功";
		try{
			zmlMyReleaseDocService.save(zmlMyReleaseDoc);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "发布文档添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(zmlMyReleaseDoc);
		return j;
	}
	
	/**
	 * 更新发布文档
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlUserReleaseDocEntity zmlMyReleaseDoc, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布文档更新成功";
		ZmlUserReleaseDocEntity t = zmlMyReleaseDocService.get(ZmlUserReleaseDocEntity.class, zmlMyReleaseDoc.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlMyReleaseDoc, t);
			zmlMyReleaseDocService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "发布文档更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 发布文档新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlUserReleaseDocEntity zmlMyReleaseDoc, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlMyReleaseDoc.getId())) {
			zmlMyReleaseDoc = zmlMyReleaseDocService.getEntity(ZmlUserReleaseDocEntity.class, zmlMyReleaseDoc.getId());
			req.setAttribute("zmlMyReleaseDocPage", zmlMyReleaseDoc);
		}
		return new ModelAndView("com/zml/release_doc/zmlMyReleaseDoc-add");
	}
	/**
	 * 发布文档编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlUserReleaseDocEntity zmlMyReleaseDoc, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlMyReleaseDoc.getId())) {
			zmlMyReleaseDoc = zmlMyReleaseDocService.getEntity(ZmlUserReleaseDocEntity.class, zmlMyReleaseDoc.getId());
			req.setAttribute("zmlMyReleaseDocPage", zmlMyReleaseDoc);
		}
		return new ModelAndView("com/zml/release_doc/zmlMyReleaseDoc-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlMyReleaseDocController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlUserReleaseDocEntity zmlMyReleaseDoc,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserReleaseDocEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlMyReleaseDoc, request.getParameterMap());
		List<ZmlUserReleaseDocEntity> zmlMyReleaseDocs = this.zmlMyReleaseDocService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"发布文档");
		modelMap.put(NormalExcelConstants.CLASS,ZmlUserReleaseDocEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("发布文档列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlMyReleaseDocs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlUserReleaseDocEntity zmlMyReleaseDoc,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"发布文档");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlUserReleaseDocEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("发布文档列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<ZmlUserReleaseDocEntity> listZmlMyReleaseDocEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlUserReleaseDocEntity.class,params);
				for (ZmlUserReleaseDocEntity zmlMyReleaseDoc : listZmlMyReleaseDocEntitys) {
					zmlMyReleaseDocService.save(zmlMyReleaseDoc);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	/**
	 * 获取文件附件信息
	 * 
	 * @param id zmlMyReleaseDoc主键id
	 */
	@RequestMapping(params = "getFiles")
	@ResponseBody
	public AjaxJson getFiles(String id){
		List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
		List<Map<String,Object>> files = new ArrayList<Map<String,Object>>(0);
		for(CgUploadEntity b:uploadBeans){
			String title = b.getAttachmenttitle();//附件名
			String fileKey = b.getId();//附件主键
			String path = b.getRealpath();//附件路径
			String field = b.getCgformField();//表单中作为附件控件的字段
			Map<String, Object> file = new HashMap<String, Object>();
			file.put("title", title);
			file.put("fileKey", fileKey);
			file.put("path", path);
			file.put("field", field==null?"":field);
			files.add(file);
		}
		AjaxJson j = new AjaxJson();
		j.setObj(files);
		return j;
	}
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlUserReleaseDocEntity> list() {
		List<ZmlUserReleaseDocEntity> listZmlMyReleaseDocs=zmlMyReleaseDocService.getList(ZmlUserReleaseDocEntity.class);
		return listZmlMyReleaseDocs;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlUserReleaseDocEntity task = zmlMyReleaseDocService.get(ZmlUserReleaseDocEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlUserReleaseDocEntity zmlMyReleaseDoc, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserReleaseDocEntity>> failures = validator.validate(zmlMyReleaseDoc);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlMyReleaseDocService.save(zmlMyReleaseDoc);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlMyReleaseDoc.getId();
		URI uri = uriBuilder.path("/rest/zmlMyReleaseDocController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlUserReleaseDocEntity zmlMyReleaseDoc) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserReleaseDocEntity>> failures = validator.validate(zmlMyReleaseDoc);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlMyReleaseDocService.saveOrUpdate(zmlMyReleaseDoc);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		zmlMyReleaseDocService.deleteEntityById(ZmlUserReleaseDocEntity.class, id);
	}
}
