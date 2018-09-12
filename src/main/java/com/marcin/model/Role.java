package com.marcin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Model tablicy role w bazie danych z adnotacjami Hibernate

@Entity // oznacza, ze klasa ma role tablicy
@Table(name = "role") // nazwa tablicy jak w bazie danych
public class Role {
	
	@Id // primary key dla tabeli
    @GeneratedValue(strategy = GenerationType.AUTO) // specyfikacja jak glowny klucz jest generowany
	@Column(name="role_id") // nazwa kolumny
	private int id;
	
	@Column(name="role") 
	private String role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
