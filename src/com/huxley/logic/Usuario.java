package com.huxley.logic;

import org.eclipse.swt.widgets.Composite;

import com.huxley.model.MyModel;

/**
 * 
 * @author Luiz
 *
 */
public class Usuario {

	private String nome,login,senha;
	private double topcoder,pontuacao,tentativas,acertos =0;
	private MyModel root,problems, surveys;
	private Composite parent;
	private int sessao = 0;

	public Usuario(String login, String senha,String nome, double topcoder,double pontuacao,double tentativas,double acertos,Composite parent){
		root = new MyModel( null, "", "", "", "", "");
		problems = new MyModel( root, "Problemas ", "", "", "", "");
		surveys = new MyModel( root, "Questionários ", "", "", "",
				"");
		this.login = login;
		this.senha = senha;
		this.nome=nome;
		this.topcoder=topcoder;
		this.pontuacao =pontuacao;
		this.tentativas =tentativas;
		this.acertos=acertos;
		this.parent = parent;
		getRoot().counter = 0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getTopcoder() {
		return topcoder;
	}

	public void setTopcoder(double topcoder) {
		this.topcoder = topcoder;
	}

	public double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public double getTentativas() {
		return tentativas;
	}

	public void setTentativas(double tentativas) {
		this.tentativas = tentativas;
	}

	public double getAcertos() {
		return acertos;
	}

	public void setAcertos(double acertos) {
		this.acertos = acertos;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Composite getParent() {
		return parent;
	}

	public void setParent(Composite parent) {
		this.parent = parent;
	}

	public MyModel getProblems() {
		return problems;
	}

	public void setProblems(MyModel problems) {
		this.problems = problems;
	}

	public MyModel getSurveys() {
		return surveys;
	}

	public void setSurveys(MyModel surveys) {
		this.surveys = surveys;
	}

	public MyModel getRoot() {
		return root;
	}

	public void setRoot(MyModel root) {
		this.root = root;
	}

	public int getSessao() {
		return sessao;
	}

	public void incrementaSessao() {
		this.sessao++;
	}

}
