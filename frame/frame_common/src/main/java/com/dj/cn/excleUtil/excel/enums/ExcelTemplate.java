package com.dj.cn.excleUtil.excel.enums;

/**
 * ExcelTemplateEnums
 * 
 * @author: Lenovo
 * @date: 2018-11-14 10:49:30
 * @Copyright: 2018 www.lenovo.com Inc. All rights reserved.
 */
public enum ExcelTemplate {

	/**
	 * Unknow
	 */
	UNKNOW(1, "UNKNOW", "Unknow"),
	
	/**
	 * ContactUs
	 */
	CONTACT(2, "ContactUs", "ContactUs.xlsx"),
	
	/**
	 * Products
	 */
	PRODUCT(3, "Products", "Products.xlsx"),
	
	/**
	 * ProductSerials
	 */
	PRODUCT_SERIALS(4, "ProductSerials", "ProductSerials.xlsx"),
	
	/**
	 * Reports
	 */
	REPORT(5, "Reports", "Reports.xlsx"),

	/**
	 * Services
	 */
	SERVICE(6, "Services", "Services.xlsx"),

	/**
	 * Users
	 */
	USER(7, "Users", "Users.xlsx"),

	/**
	 * BatchUpload
	 */
	BATCH_UPLOAD_RESULT(8, "BatchUpload", "BatchUploadResult.xlsx"),

	/**
	 * Warranties
	 */
	WARRANTIES(9, "Warranties", "Warranties.xlsx"),

	/**
	 * WarrantySerials
	 */
	WARRANTIES_SERIALS(10, "WarrantySerials", "WarrantySerials.xlsx"),

	/**
	 * PartsExport
	 */
	PARTS(11, "PartsExport", "PartsExport.xlsx"),

	/**
	 * UsAgeDetailExport
	 */
	USERAGE_DETAIL(12, "UsAgeDetailExport", "UsAgeDetailExport.xlsx"),

	/**
	 * Health
	 */
	HEALTH(13, "Health", "Health.xlsx"),
	
	/**
	 * MemberErrorList
	 */
	BATCH_UPLOAD_MEMBER_ERROR_LIST(14, "MemberErrorList", "batchUploadMemberErrorList.xlsx"),
	
	/**
	 * NoParentCatalog
	 */
	NO_PARENT_CATALOG(15, "NoParentCatalog", "NoParentCatalog.xlsx"),
	
	/**
	 * 
	 */
	BATCH_UPLOAD_NONGLOBAL(16, "BatchUploadNonGlobalError", "BatchUploadNonGlobalError.xlsx"),
	/**
	 *
	 */
	USER_ID(18, "User", "User.xlsx");

	private int code;
	private String value;
	private String description;

	public static ExcelTemplate parse(String value) {
		ExcelTemplate[] array = ExcelTemplate.values();
		for (int i = 0; i < array.length; i++) {
			if (array[i].getValue().equals(value)) {
				return array[i];
			}
		}
		return ExcelTemplate.UNKNOW;
	}
	
	private ExcelTemplate(int code, String value, String description) {
		this.code = code;
		this.value = value;
		this.description = description;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
