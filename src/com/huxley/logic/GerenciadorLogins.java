package com.huxley.logic;

import java.util.ArrayList;

public class GerenciadorLogins {

	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private static GerenciadorLogins gerenciador = null;

	private GerenciadorLogins(){
	}

	public static GerenciadorLogins getInstance(){
		if(gerenciador == null){
			gerenciador = new GerenciadorLogins();
		}return gerenciador;
	}
	public boolean validaLoginESenha(String login,String senha){
		for (int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getLogin().equals(login) && usuarios.get(i).getSenha().equals(senha)){
				return true;
			}
		}
		return false;
	}

	public Usuario getUsuario(String login,String senha){
		for (int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getLogin().equals(login) && usuarios.get(i).getSenha().equals(senha)){
				return usuarios.get(i);
			}
		}
		return null;
	}

	public boolean cadastraLoginESenha(Usuario usuario){
		if(usuarios.contains(usuario)){
			return false;
		}
		Usuario usuarioNovo = new Usuario(usuario.getLogin(),usuario.getSenha(),
				usuario.getNome(),usuario.getTopcoder(),usuario.getPontuacao(),
				usuario.getTentativas(),usuario.getAcertos(),usuario.getParent());

		usuarios.add(usuarioNovo);
		return true;
	}
}
