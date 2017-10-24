package br.com.gruporondomotos.svendas;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import android.util.Log;



public class Login extends AsyncTask<String, Void, String> {

	URL url;
	
	
	Context context;

	AlertDialog alertDialog;

	Login(Context ctx) {
		context = ctx;

	}

	@Override
	protected String doInBackground(String... params) {
		String type = params[0];

		String login_url = "http://192.168.2.11:8080/svendas/agenda.php";

		if (type.equals("login")) {
			try {

				String user_name = params[1];
				String password = params[2];
				String post_data = "?user" + "=" + user_name + "&" + "pass" + "=" + password;
				url = new URL(login_url + post_data);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
				String result = "";
				String line = "";

				while ((line = bufferedReader.readLine()) != null) {
					result += line;
				}

				bufferedReader.close();
				return result;

			} catch (MalformedURLException e) {
				e.printStackTrace();
				

			} catch (IOException e) {
				e.printStackTrace();
				alertDialog.setMessage("Erro conectando no servidor!");
				alertDialog.show();
			}
		}

		return null;
	}

	@Override
	protected void onPreExecute() {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle("Status do login");
	}
	
		
	

	
	
	@Override
	protected void onPostExecute(String result) {

		try {
			

			Pessoa pessoaObj = new Pessoa();
			JSONArray jsonArrayLoco = new JSONArray(result);
			JSONObject jObject = jsonArrayLoco.getJSONObject(0);
			pessoaObj.setNome(jObject.getString("cliente"));
			pessoaObj.setAgendamento(jObject.getString("proximo_atendimento"));
			Pessoa.recebeurl = url;
			Intent a = new Intent(context, TelaAgenda.class);
			context.startActivity(a);
		
	
		} catch (JSONException e) {
			Log.e("log_tag", "Erro realizando o parse dos dados " + e.toString());
			alertDialog.setMessage("Usuário/Senha inválido!");
			alertDialog.show();

		}
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}

}