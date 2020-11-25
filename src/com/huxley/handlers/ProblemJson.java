package com.huxley.handlers;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.huxley.logic.Problem;

public class ProblemJson {
	private Problem problem;
	private ArrayList<Problem> problems;
	
	public ProblemJson() {
		problem = new Problem();
		problems = new ArrayList<Problem>();
	}
	

	public ArrayList<Problem> getProblems(String json) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = null;
		JsonArray jarray = null;
		
		try {
			JsonElement jelement = parser.parse(json);
			jarray = jelement.getAsJsonArray();
			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

		for(int i=0;i<jarray.size();i++) {
			jsonObject = jarray.get(i).getAsJsonObject();

			problem = new Problem();
	        problem.setName(jsonObject.getAsJsonPrimitive("name").getAsString());
	        problem.setDescription(jsonObject.getAsJsonPrimitive("description").getAsString());
	        problem.setTopic("TODO");
	        problem.setNd(jsonObject.getAsJsonPrimitive("nd").getAsInt());
	        
	        problems.add(problem);
		}
        return problems;
	}

}
