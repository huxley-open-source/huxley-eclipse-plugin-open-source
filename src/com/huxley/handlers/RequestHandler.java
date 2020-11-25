package com.huxley.handlers;

import java.util.ArrayList;

import com.huxley.logic.Problem;
import com.huxley.logic.Questionnaire;
import com.huxley.logic.Usuario;

public class RequestHandler {
	private ResourceHandler resourceHandler;
	private String username, password, resouce;
	
	public RequestHandler(String username, String password, String resource) {
		this.resourceHandler = new ResourceHandler();
		
		setUsername(username);
		setPassword(password);
		setResouce(resource);
		
		resourceHandler.setUsernamePassword(getUsername(), getPassword());
		resourceHandler.setResource(getResouce());
	}

	public Usuario getUsuario() {
		UsuarioJson usuario = new UsuarioJson(getUsername(), getPassword());
		
		try{
			return usuario.geraUsuario(resourceHandler.getMessage());
		}catch(Exception e){ }
		
		return null;
	}
	
	public ArrayList<Problem> getProblems(){
		ProblemJson problem = new ProblemJson();
		
		try{
			return problem.getProblems(resourceHandler.getMessage());
		}catch(Exception e){}
		
		return null;
	}
	
	public ArrayList<Questionnaire> getQuestionnaires(){
		QuestionnaireJson questionnaire = new QuestionnaireJson();
		
		try{
			return questionnaire.getQuestionnaires(resourceHandler.getMessage());
		}catch(Exception e){ }
		
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResouce() {
		return resouce;
	}

	public void setResouce(String resouce) {
		this.resouce = resouce;
	}
}
