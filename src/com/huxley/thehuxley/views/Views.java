package com.huxley.thehuxley.views;

//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
//import java.util.List;

//import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
//import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.part.ViewPart;

//import com.huxley.html.HTMLParser;
import com.huxley.logic.EstruturaGrid;
import com.huxley.logic.Usuario;
import com.huxley.mock.Fachada;
//import com.huxley.model.MyModel;

public class Views extends ViewPart {

	public static final String ID = "thehuxley.views.Views";
	private Fachada fachada = Fachada.getInstance();
	private EstruturaGrid gridView;
	//private HTMLParser view = new HTMLParser();
	//private PrintWriter out;
	private GridLayout layout;
	private Label name, rank, score, tryRating, link;

	@Override
	public void createPartControl(Composite parent) {
		criaLabels(parent);
		fachada.setParent(parent);
		fachada.setUsuarioLogado(false);
		configuracoesGrid();
		eventosMouse();
	}

	private void configuracoesGrid() {
		gridView = new EstruturaGrid(fachada.getParent());
		if (!fachada.getUsuarioLogado()) {
			gridView.gridNaoLogado();
		} else {
			gridView.gridLogado();
		}
		gridView.atualizaGrid();
		gridView.criaGridData();

	}

	private void eventosMouse() {
		cliqueDoMouse();
		duploCliqueMouse();
	}

	private void duploCliqueMouse() {
		gridView.getGridArvoreView().addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
/*				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
							.showView("com.huxley.tests.Description");
				} catch (PartInitException e) {
					e.printStackTrace();
				}*/

/*				IStructuredSelection selection = (IStructuredSelection) gridView.getGridArvoreView().getSelection();
				if (selection.isEmpty())
					return;

				List<?> list = selection.toList();
				MyModel model = (MyModel) list.get(0);

				String[] estados = { "terminado", "terminado", "terminado", "terminado", "terminado", "terminado",
						"terminado" };
				String[] linguagens = { "java", "python", "C", "C++", "R", "PHP", "Javascript" };

				String html = view.generate(model.getDescription().toString(), model.getTitle().toString(), model
						.getTitle().toString(), model.getTopic().toString(), "ultima submissao", model.getNd()
						.toString(), "tempo de execucao recorde pessoal", "tempo de execucao recorde",
						"formato de entrada", "formato de saida", linguagens, estados);

				writeTextFile(ResourcesPlugin.getWorkspace().getRoot().getProject("thehuxley").getLocation().toString()
						+ "/tempFiles/showWebHTML" + model.getTitle() + ".html", html);

				IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
				try {

					IWebBrowser browser = browserSupport.createBrowser(model.getTitle());
					URL url = new URL(model.getLink());
					browser.openURL(url);

				} catch (PartInitException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}*/
			}
		});
	}

/*	private void writeTextFile(String filename, String string) {
		try {
			out = new PrintWriter(filename);
			out.println(string);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}*/

	private void cliqueDoMouse() {
		gridView.getGridArvoreView().getGrid().addMouseMoveListener(new MouseMoveListener() {

			@Override
			public void mouseMove(MouseEvent e) {

				if (fachada.getSessao() == 1 && fachada.getUsuarioLogado()) {
					atualizaLabels();
					gridView.gridLogado(); // define as colunas
					gridView.atualizaGrid();// insere os dados nas
											// colunas
					fachada.setSessao();
				}
				if (fachada.getAtualizarDadosView()) {
					gridView.atualizaGrid();
					fachada.setSessao();
					fachada.setAtualizarDadosView(false);
				}
			}
		});
		gridView.getGridArvoreView().getGrid().addMouseListener(new MouseAdapter() {

			public void mouseDown(MouseEvent e) {
				Point pt = new Point(e.x, e.y);
				int columnIndex = gridView.getGridArvoreView().getGrid()
						.indexOf(gridView.getGridArvoreView().getGrid().getItem(pt));
				if (columnIndex == 0 && !fachada.getUsuarioLogado()) {
					fachada.telaLoginESenha();
				}
			}

		});
	}

	public void setFocus() {
		gridView.getGridArvoreView().getControl().setFocus();

	}

	public void criaLabels(Composite parent) {

		layout = new GridLayout(5, true);
		parent.setLayout(layout);
		name = new Label(parent, SWT.NONE);
		name.setText("Usuário:                                ");
		rank = new Label(parent, SWT.NONE);
		rank.setText("#TopCoder:                      ");
		score = new Label(parent, SWT.NONE);
		score.setText("Pontuação:                      ");
		tryRating = new Label(parent, SWT.NONE);
		tryRating.setText("Tentativas/Acertos:  " + "            /             ");
		link = new Label(parent, SWT.RIGHT);
		link.setText("Acessar dashboard na web");
		org.eclipse.swt.graphics.Color azul = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
		link.setForeground(azul);
		linkEventos();
	}

	private void linkEventos() {
		link.addMouseMoveListener(new MouseMoveListener() {
			@Override
			public void mouseMove(MouseEvent e) {
				Cursor cursor = Display.getDefault().getSystemCursor(SWT.CURSOR_HAND);
				link.setCursor(cursor);
			}
		});
		link.addMouseListener(new MouseListener() {

			@Override
			public void mouseDown(MouseEvent e) {
				IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
				try {

					IWebBrowser browser = browserSupport.createBrowser("The Huxley");
					URL url = new URL("http://www.thehuxley.com");

					browser.openURL(url);

				} catch (PartInitException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}

			@Override
			public void mouseUp(MouseEvent e) {
			}
		});
	}

	@Override
	public void dispose() {
		fachada.setSessao(0);
		fachada.setUsuarioLogado(false);
		fachada.setLoginExecutando(false);
		fachada.setDados(new ArrayList<ArrayList<String>>());
		super.dispose();
	}

	private void atualizaLabels() {
		Usuario usuario = fachada.getUsuario();
		name.setText("Usuário: " + usuario.getNome());
		rank.setText("#TopCoder: " + usuario.getTopcoder());
		score.setText("Pontuação: " + usuario.getPontuacao());
		tryRating.setText("Tentativas/Acertos: " + usuario.getTentativas() + "/" + usuario.getAcertos());
		link.setText("Acessar dashboard na web");
	}

}