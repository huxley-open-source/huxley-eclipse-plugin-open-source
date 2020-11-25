package com.huxley.html;

public class HTMLParser {

	private static final HTMLTemplate TEMPLATE = HTMLTemplate.THEHUXLEYTEMPLATEHTML;

	public HTMLParser() {
		
	}

	public String generate(String descricao, String title, String problema,
			String topico, String ultimaSubmissao, String nivelDinamico,
			String tempoDeExecucaoRecordePessoal,
			String tempoDeExecucaoRecorde, String formatoDeEntrada,
			String formatoDeSaida, String[] linguagens, String[] estados) {
		
		String html = TEMPLATE.getHTML();
		
		html = replaceTitle(html, title);
		html = replaceDescricao(html, descricao);
		html = replaceProblema(html, problema);
		html = replaceTopico(html, topico);
		html = replaceUltimaSubmissao(html, ultimaSubmissao);
		html = replaceNivelDinamico(html, nivelDinamico);
		html = replaceTempoDeExecucaoRecordePessoal(html, tempoDeExecucaoRecordePessoal);
		html = replaceTempoDeExecucaoRecorde(html, tempoDeExecucaoRecorde);
		html = replaceFormatoDeEntrada(html, formatoDeEntrada);
		html = replaceFormatoDeSaida(html, formatoDeSaida);
		html = replaceLinguagem1(html, linguagens[0]);
		html = replaceLinguagem2(html, linguagens[1]);
		html = replaceLinguagem3(html, linguagens[2]);
		html = replaceLinguagem4(html, linguagens[3]);
		html = replaceLinguagem5(html, linguagens[4]);
		html = replaceLinguagem6(html, linguagens[5]);
		html = replaceLinguagem7(html, linguagens[6]);
		html = replaceEstado1(html, estados[0]);
		html = replaceEstado2(html, estados[1]);
		html = replaceEstado3(html, estados[2]);
		html = replaceEstado4(html, estados[3]);
		html = replaceEstado5(html, estados[4]);
		html = replaceEstado6(html, estados[5]);
		html = replaceEstado7(html, estados[6]);

		return html;
	}

	private String replaceTitle(String template, String novo) {
		return template.replace("#_title_", novo);
	}

	private String replaceDescricao(String template, String novo) {
		return template.replace("#_descricao_", novo);
	}

	private String replaceProblema(String template, String novo) {
		return template.replace("#_problema_", novo);
	}

	private String replaceTopico(String template, String novo) {
		return template.replace("#_topico_", novo);
	}

	private String replaceUltimaSubmissao(String template, String novo) {
		return template.replace("#_ultimasubmissao_", novo);
	}

	private String replaceNivelDinamico(String template, String novo) {
		return template.replace("#_niveldinamico_", novo);
	}

	private String replaceTempoDeExecucaoRecordePessoal(String template,
			String novo) {
		return template.replace("#_tempodeexecucaorecordepessoal_", novo);
	}

	private String replaceTempoDeExecucaoRecorde(String template, String novo) {
		return template.replace("#_tempodeexecucaorecorde_", novo);
	}

	private String replaceFormatoDeEntrada(String template, String novo) {
		return template.replace("#_formatodeentrada_", novo);
	}

	private String replaceFormatoDeSaida(String template, String novo) {
		return template.replace("#_formatodesaida_", novo);
	}

	private String replaceLinguagem1(String template, String novo) {
		return template.replace("#Linguagem1", novo);
	}

	private String replaceLinguagem2(String template, String novo) {
		return template.replace("#Linguagem2", novo);
	}

	private String replaceLinguagem3(String template, String novo) {
		return template.replace("#Linguagem3", novo);
	}

	private String replaceLinguagem4(String template, String novo) {
		return template.replace("#Linguagem4", novo);
	}

	private String replaceLinguagem5(String template, String novo) {
		return template.replace("#Linguagem5", novo);
	}

	private String replaceLinguagem6(String template, String novo) {
		return template.replace("#Linguagem6", novo);
	}

	private String replaceLinguagem7(String template, String novo) {
		return template.replace("#Linguagem7", novo);
	}

	private String replaceEstado1(String template, String novo) {
		return template.replace("#Estado1", novo);
	}

	private String replaceEstado2(String template, String novo) {
		return template.replace("#Estado2", novo);
	}

	private String replaceEstado3(String template, String novo) {
		return template.replace("#Estado3", novo);
	}

	private String replaceEstado4(String template, String novo) {
		return template.replace("#Estado4", novo);
	}

	private String replaceEstado5(String template, String novo) {
		return template.replace("#Estado5", novo);
	}

	private String replaceEstado6(String template, String novo) {
		return template.replace("#Estado6", novo);
	}

	private String replaceEstado7(String template, String novo) {
		return template.replace("#Estado7", novo);
	}
}