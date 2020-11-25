package com.huxley.model;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class MyContentProvider implements ITreeContentProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(
	 * java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return ((MyModel) inputElement).childs.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		String path = null;
		
		try{
			path = ResourcesPlugin.getWorkspace().getRoot().getProject("thehuxley").getLocation().toString() + "/tempFiles";
			
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.toString().contains("showWebHTML")
						&& file.toString().endsWith(".html")) {
					file.delete();
				}
			}
		}catch(NullPointerException e){ }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse
	 * .jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang
	 * .Object)
	 */
	public Object[] getChildren(Object parentElement) {
		return getElements(parentElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang
	 * .Object)
	 */
	public Object getParent(Object element) {
		if (element == null) {
			return null;
		}

		return ((MyModel) element).parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang
	 * .Object)
	 */
	public boolean hasChildren(Object element) {
		return ((MyModel) element).childs.size() > 0;
	}

}