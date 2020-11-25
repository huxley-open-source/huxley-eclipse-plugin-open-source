package com.huxley.handlers;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.huxley.logic.Questionnaire;

public class QuestionnaireJson {
	private Questionnaire questionnaire;
	private ArrayList<Questionnaire> questionnaires;
	
	public QuestionnaireJson() {
		questionnaire = new Questionnaire();
		questionnaires = new ArrayList<Questionnaire>();
	}
	
	public ArrayList<Questionnaire> getQuestionnaires(String json) {
		
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

			questionnaire = new Questionnaire();
	        questionnaire.setTitle(jsonObject.getAsJsonPrimitive("title").getAsString());
	        questionnaire.setDescription(jsonObject.getAsJsonPrimitive("description").getAsString());
	        questionnaire.setTopic("TODO");
	        
	        questionnaires.add(questionnaire);
		}
        return questionnaires;
	}
}
