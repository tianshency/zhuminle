package com.zml.base.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 我的收藏商家
 *
 */
@Entity
@Table(name = "zml_user_collection_merchants", schema = "")
@SuppressWarnings("serial")
public class ZmlUserCollectionMerchantsEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**版本*/
	private java.lang.String version;
	/**用户ID*/
	@Excel(name="用户ID")
	private java.lang.String userId;
	/**标签*/
	@Excel(name="标签")
	private java.lang.String label;
	/**商家类型*/
	@Excel(name="商家类型")
	private java.lang.String collectionType;
	/**商家ID*/
	@Excel(name="商家ID")
	private java.lang.String merchantsId;
	/**商家名称*/
	@Excel(name="商家名称")
	private java.lang.String merchantsName;
	/**商家logo*/
	@Excel(name="商家logo")
	private java.lang.String merchantsLogo;
	/**负责人*/
	@Excel(name="负责人")
	private java.lang.String principal;
	/**所在地*/
	@Excel(name="所在地")
	private java.lang.String address;
	/**网址*/
	@Excel(name="网址")
	private java.lang.String url;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remarks;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String status;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本
	 */
	@Column(name ="VERSION",nullable=true,length=50)
	public java.lang.String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本
	 */
	public void setVersion(java.lang.String version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户ID
	 */
	@Column(name ="USER_ID",nullable=true,length=36)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户ID
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标签
	 */
	@Column(name ="LABEL",nullable=true,length=30)
	public java.lang.String getLabel(){
		return this.label;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标签
	 */
	public void setLabel(java.lang.String label){
		this.label = label;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商家类型
	 */
	@Column(name ="COLLECTION_TYPE",nullable=true,length=20)
	public java.lang.String getCollectionType(){
		return this.collectionType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家类型
	 */
	public void setCollectionType(java.lang.String collectionType){
		this.collectionType = collectionType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商家ID
	 */
	@Column(name ="MERCHANTS_ID",nullable=true,length=36)
	public java.lang.String getMerchantsId(){
		return this.merchantsId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家ID
	 */
	public void setMerchantsId(java.lang.String merchantsId){
		this.merchantsId = merchantsId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商家名称
	 */
	@Column(name ="MERCHANTS_NAME",nullable=true,length=200)
	public java.lang.String getMerchantsName(){
		return this.merchantsName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家名称
	 */
	public void setMerchantsName(java.lang.String merchantsName){
		this.merchantsName = merchantsName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商家logo
	 */
	@Column(name ="MERCHANTS_LOGO",nullable=true,length=200)
	public java.lang.String getMerchantsLogo(){
		return this.merchantsLogo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家logo
	 */
	public void setMerchantsLogo(java.lang.String merchantsLogo){
		this.merchantsLogo = merchantsLogo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  负责人
	 */
	@Column(name ="PRINCIPAL",nullable=true,length=20)
	public java.lang.String getPrincipal(){
		return this.principal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  负责人
	 */
	public void setPrincipal(java.lang.String principal){
		this.principal = principal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在地
	 */
	@Column(name ="ADDRESS",nullable=true,length=200)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所在地
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  网址
	 */
	@Column(name ="URL",nullable=true,length=200)
	public java.lang.String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  网址
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARKS",nullable=true,length=200)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=2)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
}
