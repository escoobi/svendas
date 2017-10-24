package br.com.gruporondomotos.svendas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.Context;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText mailMail, passPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mailMail = (EditText) findViewById(R.id.editTextUsuario);
		passPass = (EditText) findViewById(R.id.editTextSenha);
	}

	// *****************************Classe que passa os paramentros para a Classe
	// Login e realizar a autenticação
	public void autenticar(View view) {
		try {
			boolean conexao = verificaConexao();
			if (conexao == false) {
				Builder alert = new AlertDialog.Builder(MainActivity.this);
				alert.setTitle("Agenda Vendas - Aviso!");
				alert.setMessage("Ei mano! não tem conexão...");
				alert.setPositiveButton("OK", null);
				alert.show();
			} else {
				String username = mailMail.getText().toString();
				String password = passPass.getText().toString();
				String type = "login";
				Login login = new Login(this);
				login.execute(type, username, password);
			}
		} catch (Exception e) {
			Builder alert = new AlertDialog.Builder(MainActivity.this);
			alert.setTitle("Agenda Vendas - Aviso!");
			alert.setMessage("Ei mano! não tem conexão...");
			alert.setPositiveButton("OK", null);
			alert.show();
		}

	}

	/*
	 * Função para verificar existência de conexão com a internet
	 */
	public boolean verificaConexao() {
		boolean conectado;
		ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo().isAvailable()
				&& conectivtyManager.getActiveNetworkInfo().isConnected()) {
			conectado = true;
		} else {
			conectado = false;
		}
		return conectado;
	}

}
