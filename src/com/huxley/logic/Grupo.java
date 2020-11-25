package com.huxley.logic;

import java.util.ArrayList;

public class Grupo {
	private String nome;
	private ArrayList<String> professores,monitores = new ArrayList<String>();
	
	public Grupo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<String> getProfessores() {
		return professores;
	}
	public void setProfessores(ArrayList<String> professores) {
		this.professores = professores;
	}
	public ArrayList<String> getMonitores() {
		return monitores;
	}
	public void setMonitores(ArrayList<String> monitores) {
		this.monitores = monitores;
	}

}
