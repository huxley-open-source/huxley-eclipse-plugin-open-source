package com.huxley.model;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class MyLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		FontRegistry registry = new FontRegistry();

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
			
		}

		public String getColumnText(Object element, int columnIndex) {
			return ((MyModel)element).toString(columnIndex);
		}
	}