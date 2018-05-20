package com.zml.loan_service.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.loan.entity.ZmlLoanOverdueDetailEntity;
import com.zml.loan_service.ZmlLoanOverdueDetailServiceI;

@Service("zmlLoanOverdueDetailService")
@Transactional
public class ZmlLoanOverdueDetailServiceImpl extends CommonServiceImpl implements ZmlLoanOverdueDetailServiceI {

	
 	public void delete(ZmlLoanOverdueDetailEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlLoanOverdueDetailEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlLoanOverdueDetailEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlLoanOverdueDetailEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlLoanOverdueDetailEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlLoanOverdueDetailEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlLoanOverdueDetailEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("version", t.getVersion());
		map.put("contract_id", t.getContractId());
		map.put("user_id", t.getUserId());
		map.put("repay_plan_detail_id", t.getRepayPlanDetailId());
		map.put("over_start_date", t.getOverStartDate());
		map.put("principal", t.getPrincipal());
		map.put("interest", t.getInterest());
		map.put("impose_interest", t.getImposeInterest());
		map.put("penalty", t.getPenalty());
		map.put("period", t.getPeriod());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanOverdueDetailEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{contract_id}",String.valueOf(t.getContractId()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{repay_plan_detail_id}",String.valueOf(t.getRepayPlanDetailId()));
 		sql  = sql.replace("#{over_start_date}",String.valueOf(t.getOverStartDate()));
 		sql  = sql.replace("#{principal}",String.valueOf(t.getPrincipal()));
 		sql  = sql.replace("#{interest}",String.valueOf(t.getInterest()));
 		sql  = sql.replace("#{impose_interest}",String.valueOf(t.getImposeInterest()));
 		sql  = sql.replace("#{penalty}",String.valueOf(t.getPenalty()));
 		sql  = sql.replace("#{period}",String.valueOf(t.getPeriod()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}