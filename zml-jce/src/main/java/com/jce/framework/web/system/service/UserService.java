package com.jce.framework.web.system.service;

import com.jce.framework.core.common.service.CommonService;

import com.jce.framework.web.system.pojo.base.TSUser;
/**
 * 
* @author  bobo
 *
 */
public interface UserService extends CommonService{

	public TSUser checkUserExits(TSUser user);
	public String getUserRole(TSUser user);
	public void pwdInit(TSUser user, String newPwd);
	/**
	 * 判断这个角色是不是还有用户使用
	 *@Author JueYue
	 *@date   2013-11-12
	 *@param id
	 *@return
	 */
	public int getUsersOfThisRole(String id);
}
