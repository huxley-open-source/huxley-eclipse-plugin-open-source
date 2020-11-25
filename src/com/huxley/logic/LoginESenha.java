package com.huxley.logic;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.huxley.mock.Fachada;

public class LoginESenha {

	private Fachada fachada = Fachada.getInstance();
	protected Shell shlEnterMarmosetUsernamepassword;
	private Text text;
	private Text text_1;
	private Label label;
	
	public LoginESenha() {
		open();
		fachada.setLoginExecutando(true);
	}
	
	public static String getPath(String fileName) {

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject projeto = root.getProject("thehuxley");
		String path = projeto.getFile(fileName).getLocation().toString();
		return path;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlEnterMarmosetUsernamepassword.open();
		shlEnterMarmosetUsernamepassword.layout();
		while (!shlEnterMarmosetUsernamepassword.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlEnterMarmosetUsernamepassword = new Shell();
		shlEnterMarmosetUsernamepassword.setBackgroundMode(SWT.INHERIT_FORCE);
		shlEnterMarmosetUsernamepassword.setModified(true);
		shlEnterMarmosetUsernamepassword.setSize(371, 251);
		shlEnterMarmosetUsernamepassword.setText("Enter marmoset username/password");
		
		Label lblUsername = new Label(shlEnterMarmosetUsernamepassword, SWT.NONE);
		lblUsername.setBounds(10, 76, 70, 17);
		lblUsername.setText("Username");
		
		text = new Text(shlEnterMarmosetUsernamepassword, SWT.BORDER);
		text.setBounds(100, 76, 238, 27);
		
		Label lblPassword = new Label(shlEnterMarmosetUsernamepassword, SWT.NONE);
		lblPassword.setBounds(10, 110, 70, 17);
		lblPassword.setText("Password");
		
		text_1 = new Text(shlEnterMarmosetUsernamepassword, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(100, 110, 238, 27);
		
		Button btnOk = new Button(shlEnterMarmosetUsernamepassword, SWT.NONE);
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				checkLogin(e);
			}
		});
		
		btnOk.setBounds(137, 174, 91, 29);
		btnOk.setText("Ok");
		
		Button btnCancelar = new Button(shlEnterMarmosetUsernamepassword, SWT.NONE);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				fachada.setLoginExecutando(false);
				shlEnterMarmosetUsernamepassword.setVisible(false);
			}
		});
		btnCancelar.setBounds(247, 174, 91, 29);
		btnCancelar.setText("Cancel");
		
		Label lblPlease = new Label(shlEnterMarmosetUsernamepassword, SWT.NONE);
		lblPlease.setBounds(10, 46, 300, 17);
		lblPlease.setText("Please enter your username and password.");
		
		label = new Label(shlEnterMarmosetUsernamepassword, SWT.NONE);
		label.setBounds(10, 151, 367, 17);

	}

	protected void checkLogin(MouseEvent e) {
		String login = text.getText();
		String password = text_1.getText();
		Usuario usuario = fachada.getUsuario(login, password);
		if(usuario != null){
			fachada.setUsuarioLogado(true);
			shlEnterMarmosetUsernamepassword.setVisible(false);
			fachada.setSessao();
		}else{
			label.setText("Username or password incorrect.");
			label.setVisible(true);
		}
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginESenha window = new LoginESenha();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
