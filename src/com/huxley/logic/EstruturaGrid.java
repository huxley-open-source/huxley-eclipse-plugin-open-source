package com.huxley.logic;

import org.eclipse.nebula.jface.gridviewer.GridTreeViewer;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.huxley.mock.Fachada;
import com.huxley.model.MyContentProvider;
import com.huxley.model.MyLabelProvider;
import com.huxley.model.MyModel;

public class EstruturaGrid {
	private GridTreeViewer gridArvoreView;
	private GridColumn column;
	private Fachada fachada = Fachada.getInstance();

	public EstruturaGrid(Composite parent) {
		setGridArvoreView(new GridTreeViewer(parent, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.BORDER));
	}

	public void gridNaoLogado() {
		atualizaGridColumn(10000, " ");

	}

	public void gridLogado() {
		if (getColumn() != null) {
			getColumn().dispose();
		}
		atualizaGridColumn(300, "Título");
		getColumn().setTree(true);
		atualizaGridColumn(400, "Descrição");
		atualizaGridColumn(300, "Tópico");
		atualizaGridColumn(90, "N/D");
	}

	public void atualizaGridColumn(int width, String text) {
		setColumn(new GridColumn(getGridArvoreView().getGrid(), SWT.NONE));
		getColumn().setWidth(width);
		getColumn().setText(text);
	}

	public void atualizaGrid() {
		getGridArvoreView().getGrid().setHeaderVisible(true);
		getGridArvoreView().getGrid().setLinesVisible(true);
		getGridArvoreView().setColumnProperties(new String[] { "col1", "col2", "col3", "col4" });
		getGridArvoreView().setLabelProvider(new MyLabelProvider());
		getGridArvoreView().setContentProvider(new MyContentProvider());

		insereDadosUsuario();

	}

	public void insereDadosUsuario() {
		if (fachada.getUsuarioLogado()) {
			gridArvoreView.setInput(fachada.leDados(fachada.getUsuario()));
		} else {
			gridSemUsuario("Click here to login");
		}
		getGridArvoreView().getGrid().setTreeLinesVisible(false);
	}

	public void gridSemUsuario(String entrada) {
		MyModel root = new MyModel(null, "", "", "", "", "");
		MyModel login = new MyModel(root, entrada, "", "", "", "");
		root.addChild(login);
		getGridArvoreView().setInput(root);
	}

	public void criaGridData() {
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 5;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridArvoreView.getControl().setLayoutData(gridData);
	}

	public GridTreeViewer getGridArvoreView() {
		return gridArvoreView;
	}

	public void setGridArvoreView(GridTreeViewer gridArvoreView) {
		this.gridArvoreView = gridArvoreView;
	}

	public GridColumn getColumn() {
		return column;
	}

	public void setColumn(GridColumn column) {
		this.column = column;
	}

	public void dispose() {
		gridArvoreView = null;
		column = null;
	}
}