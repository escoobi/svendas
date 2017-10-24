package br.com.gruporondomotos.svendas;

import java.net.URL;

public class Pessoa {

private String nome;
private String agendamento;

public static URL recebeurl;



public String getNome() {
return nome;
}

public void setNome(String nome) {
this.nome = nome;
}

public String getAgendamento() {
return agendamento;
}

public void setAgendamento(String agendamento) {
this.agendamento = agendamento;
}


public Pessoa(String nomeCliente, String agendamento)
{
    this.nome = nomeCliente;
    this.agendamento = agendamento;
 
    
}

public Pessoa() {
	// TODO Auto-generated constructor stub
}


}