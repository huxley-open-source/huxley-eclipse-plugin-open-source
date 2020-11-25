package com.huxley.mock;

import java.util.ArrayList;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Composite;

import com.huxley.handlers.RequestHandler;
import com.huxley.logic.LoginESenha;
import com.huxley.logic.Problem;
import com.huxley.logic.Questionnaire;
import com.huxley.logic.Usuario;
import com.huxley.model.MyModel;

public class Fachada {

	private static Fachada fachada = null;
	private Usuario usuario;
	private int ahSessao = 0;
	private ArrayList<ArrayList<String>> dados = new ArrayList<ArrayList<String>>();
	private IAction action;
	private Composite parent;
	private boolean atualizarDadosView;
	private boolean loginExecutando = false;
	private boolean usuarioEstaLogado = false;

	private Fachada() {
		usuario = new Usuario("", "", "", 0, 0, 0, 0, parent);

	}

	public static Fachada getInstance() {
		if (fachada == null) {
			fachada = new Fachada();

		}
		return fachada;
	}

	public Usuario getUsuario(String login, String senha) {
		if(login.length()==0 || senha.length()==0){
			return null;
		}
		RequestHandler requestHandler = new RequestHandler(login, senha, "user");
		usuario = requestHandler.getUsuario();
		retrieveProblems();
		retrieveQuestionnaires();

		return (usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void retrieveProblems() {
		RequestHandler requestHandler = new RequestHandler(usuario.getLogin(),usuario.getSenha(), "problems");
		ArrayList<String> problems = new ArrayList<String>();
		ArrayList<Problem> problemAux = requestHandler.getProblems();

		for (int i = 0; i < problemAux.size(); i++) {
			problems.add(problemAux.get(i).getName());
			problems.add(problemAux.get(i).getDescription());
			problems.add(problemAux.get(i).getTopic());
			problems.add(String.valueOf(problemAux.get(i).getNd()));
			problems.add("problem");

			dados.add(problems);
			problems = new ArrayList<String>();
		}
	}

	public void retrieveQuestionnaires() {
		RequestHandler requestHandler = new RequestHandler(usuario.getLogin(),usuario.getSenha(),"questionnaires");

		ArrayList<String> questionnaires = new ArrayList<String>();
		ArrayList<Questionnaire> questionnaireAux = requestHandler.getQuestionnaires();

		for (int i = 0; i < questionnaireAux.size(); i++) {
			questionnaires.add(questionnaireAux.get(i).getTitle());
			questionnaires.add(questionnaireAux.get(i).getDescription());
			questionnaires.add(questionnaireAux.get(i).getTopic());
			questionnaires.add("");
			questionnaires.add("");

			dados.add(questionnaires);
			questionnaires = new ArrayList<String>();
		}
	}

	public ArrayList<ArrayList<String>> getDados() {
		return (dados);
	}

	public void setDados(ArrayList<ArrayList<String>> dadosAtualizados) {
		dados = dadosAtualizados;
	}

	public MyModel leDados(Usuario usuario) {
		if (usuarioEstaLogado) {
			MyModel root = new MyModel(null, "", "", "", "", "");
			usuario.setRoot(root);
			usuario.setProblems(new MyModel(root, "Problemas ", "", "", "", ""));
			usuario.setSurveys(new MyModel(root, "Questionários ", "", "", "", ""));

			MyModel problems = usuario.getProblems();
			MyModel surveys = usuario.getSurveys();

			dados = getDados();

			for (int i = 0; i < dados.size(); i++) {
				if (dados.get(i).get(4).equals("problem")) {// eh problema
					MyModel problema = new MyModel(problems, dados.get(i).get(0).toString(), dados.get(i).get(1).toString(),
							dados.get(i).get(2).toString(), dados.get(i).get(3).toString(), dados.get(i).get(4).toString());
					//problema.setLink("file:/"+ EnviromentPath.getPath("tempFiles/showWebHTML" + problema.getTitle() + ".html"));
					problems.addChild(problema);

				} else { // eh questionario
					MyModel survey = new MyModel(surveys, dados.get(i).get(0).toString(), dados.get(i).get(1).toString(),
							dados.get(i).get(2).toString(), dados.get(i).get(3).toString(), dados.get(i).get(4).toString());
					//survey.setLink("file:/"	+ EnviromentPath.getPath("tempFiles/showWebHTML" + survey.getTitle() + ".html"));
					surveys.addChild(survey);
				}
			}

			usuario.getRoot().addChild(problems);
			usuario.getRoot().addChild(surveys);

			usuario.setProblems(problems);
			usuario.setSurveys(surveys);
			usuario.incrementaSessao();
		}
		return (usuario.getRoot());

	}

	public boolean getUsuarioLogado() {
		return usuarioEstaLogado;
	}

	public void setUsuarioLogado(boolean usuarioEstaLogado) {
		this.usuarioEstaLogado = usuarioEstaLogado;
	}

	public void setParent(Composite parent) {
		usuario.setParent(parent);
		this.parent = parent;

	}

	public Composite getParent() {
		return (this.parent);
		
	}

	public void telaLoginESenha() {
		if (!loginExecutando) {
			new LoginESenha();
		}
	}

	public int getSessao() {
		return ahSessao;
	}

	public void setSessao() {
		ahSessao++;
	}

	public void setSessao(int i) {
		ahSessao = i;

	}

	public void setAction(IAction action) {
		this.action = action;

	}

	public IAction getAction() {
		return (action);

	}

	public void setAtualizarDadosView(boolean b) {
		atualizarDadosView = b;

	}

	public boolean getAtualizarDadosView() {
		return (atualizarDadosView);

	}

	public void setLoginExecutando(boolean exibeLogin) {
		loginExecutando = exibeLogin;

	}

	public void enviarArquivo(ICompilationUnit classe, String exercicio) {
		// TODO enviar para o site essa classe "classe" referente ao exercicio
		// "exercicio"

	}

}
