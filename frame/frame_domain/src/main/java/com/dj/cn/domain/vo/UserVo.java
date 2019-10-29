package com.dj.cn.domain.vo;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserVo {

	private Integer id ;
	private  String username;
	private  String password;
	private String birth;
	private  Integer sex;
	private String idCard;
	private String email;
	private  String phone;


}
