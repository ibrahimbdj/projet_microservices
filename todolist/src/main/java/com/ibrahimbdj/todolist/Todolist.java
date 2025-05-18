package com.ibrahimbdj.todolist;

import java.util.ArrayList;
import java.util.List;

public class Todolist {
	private String title;
	private int id;
	private String pwd;
	private List<String> taches;
	private static int next_id = 0;
	
	public Todolist(String title) {
		this.title = title;
		this.id = next_id;
		System.out.println(id);
		next_id++;
		this.taches = new ArrayList<String> ();
	}
	
	public Todolist() {
		this.taches = new ArrayList<String>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public List<String> getTaches() {
		return taches;
	}
	
	public void setTaches(List<String> taches) {
		this.taches = taches;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
