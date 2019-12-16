package com.train;

/**
 * 	女朋友类
 * @author junki
 * @date 2019年9月1日
 */
public class Girlfriend {
	
	private String name;
	private Character sex;
	private Integer age;
	
	public Girlfriend() {
	}

	public Girlfriend(String name, Character sex, Integer age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Girlfriend [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
}
