package com.zml.base.loan.entity;
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
 * @Description: 黑名单表
 * @author onlineGenerator
 * @date 2017-03-20 08:31:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_black_list", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanBlackListEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.Integer version;
	/**用户*/
    @Excel(name="用户")
	private java.lang.String userId;
	/**类型*/
    @Excel(name="类型")
	private java.lang.String type;
	/**来源*/
    @Excel(name="来源")
	private java.lang.String source;
	/**数据*/
    @Excel(name="数据")
	private java.lang.String data;
	/**备注*/
    @Excel(name="备注")
	private java.lang.String remarks;
	/**审批标识*/
    @Excel(name="审批标识")
	private java.lang.String approvalFlag;
	/**审批人*/
    @Excel(name="审批人")
	private java.lang.String approvalUserId;
	/**审批时间*/
    @Excel(name="审批时间",format = "yyyy-MM-dd")
	private java.util.Date approvalDate;
	/**审批意见*/
    @Excel(name="审批意见")
	private java.lang.String approvalOpinion;
	
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  版本
	 */
	
	@Column(name ="VERSION",nullable=true,length=11)
	public java.lang.Integer getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  版本
	 */
	public void setVersion(java.lang.Integer version){
		this.version = version;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户
	 */
	
	@Column(name ="USER_ID",nullable=true,length=36)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */
	
	@Column(name ="TYPE",nullable=true,length=2)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  来源
	 */
	
	@Column(name ="SOURCE",nullable=true,length=2)
	public java.lang.String getSource(){
		return this.source;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  来源
	 */
	public void setSource(java.lang.String source){
		this.source = source;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数据
	 */
	
	@Column(name ="DATA",nullable=true,length=100)
	public java.lang.String getData(){
		return this.data;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数据
	 */
	public void setData(java.lang.String data){
		this.data = data;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	
	@Column(name ="REMARKS",nullable=true,length=500)
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
	 *@return: java.lang.String  审批标识
	 */
	
	@Column(name ="APPROVAL_FLAG",nullable=true,length=1)
	public java.lang.String getApprovalFlag(){
		return this.approvalFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批标识
	 */
	public void setApprovalFlag(java.lang.String approvalFlag){
		this.approvalFlag = approvalFlag;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批人
	 */
	
	@Column(name ="APPROVAL_USER_ID",nullable=true,length=36)
	public java.lang.String getApprovalUserId(){
		return this.approvalUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批人
	 */
	public void setApprovalUserId(java.lang.String approvalUserId){
		this.approvalUserId = approvalUserId;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  审批时间
	 */
	
	@Column(name ="APPROVAL_DATE",nullable=true,length=32)
	public java.util.Date getApprovalDate(){
		return this.approvalDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审批时间
	 */
	public void setApprovalDate(java.util.Date approvalDate){
		this.approvalDate = approvalDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批意见
	 */
	
	@Column(name ="APPROVAL_OPINION",nullable=true,length=500)
	public java.lang.String getApprovalOpinion(){
		return this.approvalOpinion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批意见
	 */
	public void setApprovalOpinion(java.lang.String approvalOpinion){
		this.approvalOpinion = approvalOpinion;
	}
	
}
