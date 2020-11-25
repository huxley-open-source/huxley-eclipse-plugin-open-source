/**
 * @author Paulo Barbosa
 */
package com.huxley.handlers;

import org.eclipse.swt.widgets.Composite;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.huxley.logic.Usuario;

public class UsuarioJson {
	private Composite parent;
	private Usuario usuario = new Usuario("","","",0,0,0,0,parent);
	
	public UsuarioJson(String login, String senha){
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
	}
	
	public Usuario geraUsuario(String json){
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = null;
		
		try {
			jsonObject = (JsonObject) parser.parse(json);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

        usuario.setNome(jsonObject.getAsJsonPrimitive("name").getAsString());
        usuario.setPontuacao(jsonObject.getAsJsonPrimitive("top_coder_position").getAsInt());
        usuario.setTopcoder(jsonObject.getAsJsonPrimitive("top_coder_score").getAsDouble());
        
        return usuario;
        
	}
	
}