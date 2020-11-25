package com.huxley.model;

import java.util.ArrayList;

public class MyModel {
		public MyModel parent;

		public ArrayList<MyModel> childs = new ArrayList<MyModel>();

		public int counter = 0;
		
		private String title;
		private String description;
		private String topic;
		private String nd;
		private String link;

		public MyModel( MyModel parent, String title, String description, String topic, String nd, String link) {
			this.parent = parent;
			this.title = title;
			this.description = description;
			this.topic = topic;
			this.nd = nd;
			this.link = link;
		}

		public MyModel getParent() {
			return parent;
		}

		public ArrayList<MyModel> getChilds() {
			return childs;
		}

		public int getCounter() {
			return counter;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		public String getTopic() {
			return topic;
		}

		public String getNd() {
			return nd;
		}

		public void addChild(MyModel child){
			this.childs.add(child);
			title = title.split(" ")[0]+" ("+childs.size()+")";
		}
		
		
		public String toString() {
			String rv = "Item ";
			if (parent != null) {
				rv = parent.toString() + ".";
			}

			rv += counter;

			return rv;
		}

		public String toString(int columnIndex) {
			switch(columnIndex){
				case 0:
					return this.title;
				case 1:
					return this.description;
				case 2:
					return this.topic;
				case 3:
					return this.nd;
				default:
					return "";
			}

		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}
		
	}