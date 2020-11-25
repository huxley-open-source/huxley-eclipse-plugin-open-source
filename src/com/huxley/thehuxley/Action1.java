package com.huxley.thehuxley;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import com.huxley.mock.Fachada;

public class Action1 extends ActionDelegate implements IObjectActionDelegate{
	private Fachada fachada = Fachada.getInstance();
	private IWorkbenchWindow activeWindow;

	public void run(IAction action) {
		fachada.setAction(action);
		if(fachada.getUsuarioLogado() ){
			ITreeSelection ss = null;
			activeWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

			ISelection selection = activeWindow.getSelectionService()
					.getSelection();

			if (selection instanceof ITreeSelection) {
				ss = (ITreeSelection) selection;
			}

			ICompilationUnit cUnit = (ICompilationUnit) ss.getFirstElement();
			IFile file = null;
			try {
				file = (IFile) cUnit.getCorrespondingResource();
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
			ArrayList<ArrayList<String>> dados= fachada.getDados();
			String [] saida = new String[dados.size()];
			for (int i = 0; i < dados.size(); i++) {
				saida[i] = dados.get(i).get(0);
			}
			String [] assuntos = saida;
			ElementListSelectionDialog dialog = 
					new ElementListSelectionDialog(activeWindow.getShell(), new LabelProvider());
			dialog.setElements(assuntos);
			dialog.setTitle("Select the problem.");
			if (dialog.open() == Window.OK) {
				for (int i = 0; i < dados.size(); i++) {
					if(dados.get(i).get(0).equals( dialog.getFirstResult().toString())){
						dados.remove(i);
						fachada.setDados(dados);
						fachada.setAtualizarDadosView(true);
						break;
					}
				}
				MessageBox box = new MessageBox(activeWindow.getShell());
				box.setMessage("Exercício: "+ dialog.getFirstResult().toString()+ "\nClasse: "+ cUnit.getElementName() + " enviada com sucesso!");
				fachada.enviarArquivo(cUnit,dialog.getFirstResult().toString());
				box.open();
			}

		}
	}


	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}

}