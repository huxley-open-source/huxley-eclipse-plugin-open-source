package com.huxley.html;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

public enum HTMLTemplate {
	THEHUXLEYTEMPLATEHTML("theHuxleyTemplateHTML");

	private String templateName;

	private HTMLTemplate(String templateName) {
		this.templateName = templateName;
	}

	public String getHTML() {
		return getTemplate(getPath());
	}

	private String getTemplate(String path) {
		try {
			return FileUtils.readFileToString(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "file not found";
	}

	private String getPath() {
		Path path = FileSystems.getDefault().getPath(templateName	+ ".html");
		return path.toString();
	}
}

