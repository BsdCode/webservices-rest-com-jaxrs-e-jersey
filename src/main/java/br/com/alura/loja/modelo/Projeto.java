package br.com.alura.loja.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Projeto {
	
	long id;
	String nome;
	int anoDeInicio;
	
	public Projeto(){
		
	}
	
	public Projeto(long id, String nome , int anoDeInicio) {
		super();
		this.nome = nome;
		this.id = id;
		this.anoDeInicio = anoDeInicio;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getAnoDeInicio() {
		return anoDeInicio;
	}
	
	public void setAnoDeInicio(int anoDeInicio) {
		this.anoDeInicio = anoDeInicio;
	}

	public String toXML() {
		return new XStream().toXML(this);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
	
	

}
