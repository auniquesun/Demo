package com.shy.server;

public class Student {
	String id,name,gender,birthday,school,major;
	
	public void setID(String s){
		id = s;
	}
	public void setName(String s){
		name = s;
	}
	public void setGender(String s){
		gender = s;
	}
	public void setBirthday(String s){
		birthday = s;
	}
	public void setSchool(String s){
		school = s;
	}
	public void setMajor(String s){
		major = s;
	}
	public String getID(){
		return id;
	}
	public String getName(){
		return name;	
	}
	public String getGender(){
		return gender;
	}
	public String getBirthday(){
		return birthday;
	}
	public String getSchool(){
		return school;
	}
	public String getMajor(){
		return major;
	}
}
