package com.huxley.html;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

public class EnviromentPath {

        public static String getPath(String fileName) {
                IWorkspace workspace = ResourcesPlugin.getWorkspace();
                IWorkspaceRoot root = workspace.getRoot();
                IProject projeto = root.getProject("thehuxley");
                
                String path = projeto.getFile(fileName).getLocation().toString();
                
                System.out.println(path);
                return path;
        }
        
}