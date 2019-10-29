package com.dj.cn.domain.entry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import devutility.external.poi.models.FieldColumnMap;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Objects;

@Data
@TableName("user")
@Accessors(chain = true)
public class User implements Cloneable{

	private Integer id ;
	private  String username;
	private  Integer password;
	private String userType;
	private Integer managerId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date created;

	@TableField(exist = false)
	private String birth;
	@TableField(exist = false)
	private  Integer sex;
	@TableField(exist = false)
	private String idCard;
	@TableField(exist = false)
	private String email;
	@TableField(exist = false)
	private  String phone;

	public static FieldColumnMap<User> getFieldColumnMap() {
		FieldColumnMap<User> fieldColumnMap = new FieldColumnMap(User.class);
		fieldColumnMap.put(0, "id");
		fieldColumnMap.put(1, "username");
		fieldColumnMap.put(2, "password");
		fieldColumnMap.put(3, "userType");
		fieldColumnMap.put(4, "managerId");
		fieldColumnMap.put(5, "created");
		return fieldColumnMap;
	}


	public User(Integer id, String username, Integer password, String userType, Integer managerId, Date created, String birth, Integer sex, String idCard, String email, String phone) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.managerId = managerId;
		this.created = created;
		this.birth = birth;
		this.sex = sex;
		this.idCard = idCard;
		this.email = email;
		this.phone = phone;
	}

	public User() {
	}


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password=" + password +
				", userType='" + userType + '\'' +
				", managerId=" + managerId +
				", created=" + created +
				", birth='" + birth + '\'' +
				", sex=" + sex +
				", idCard='" + idCard + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()){
			return false;
		}
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(username, user.username) &&
				Objects.equals(password, user.password) &&
				Objects.equals(userType, user.userType) &&
				Objects.equals(managerId, user.managerId) &&
				Objects.equals(created, user.created) &&
				Objects.equals(birth, user.birth) &&
				Objects.equals(sex, user.sex) &&
				Objects.equals(idCard, user.idCard) &&
				Objects.equals(email, user.email) &&
				Objects.equals(phone, user.phone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, userType, managerId, created, birth, sex, idCard, email, phone);
	}
}
