package br.com.gruporondomotos.svendas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TelaAgenda extends Activity {

	private ArrayList<Pessoa> itens;
	public String textResult, nomeCliente, agendamento;
	String textSource;
	Context context;
	private ListView listView;
	private AdapterListViewPessoa adapterListView;

	
	NotificationManager manager;
	Notification myNotication;

	Pessoa chamaTudo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_agenda);
		refreshLista();
		chamaNotificacao();
	}

	@SuppressWarnings("deprecation")
	public void chamaNotificacao() {

		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Intent intent = new Intent("com.rj.notitfications.SECACTIVITY");
		PendingIntent pendingIntent = PendingIntent.getActivity(TelaAgenda.this, 1, intent, 0);
		Notification.Builder builder = new Notification.Builder(TelaAgenda.this);
		builder.setAutoCancel(false);
		builder.setTicker("Novo Contato Disponível");
		builder.setContentTitle("Contato disponivel");
		builder.setContentText("Nome do contato aqui");
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentIntent(pendingIntent);
		builder.setOngoing(true);
		builder.setNumber(100);
		builder.build();
		myNotication = builder.getNotification();
		manager.notify(11, myNotication);
	}

	public void refreshLista() {

		new MyTask().execute();
		listView = (ListView) findViewById(R.id.listViewCliente);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				itens.get(position);
				nomeCliente = itens.get(position).getNome();
				agendamento = itens.get(position).getAgendamento();
				manager.cancel(11);// aqui é para cancelar a notificação enviada carai
				if (nomeCliente.equals(null)) {

				} else {
					// Aqui chama uma segunda activity caso precise
				}
			}
		});
		  

	}

	protected class MyTask extends AsyncTask<Void, Void, String> {

		public String doInBackground(Void... params) {

			try {

				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(Pessoa.recebeurl.openStream()));
				String stringBuffer;
				String stringText = "";
				while ((stringBuffer = bufferReader.readLine()) != null) {
					stringText += stringBuffer;
				}

				bufferReader.close();
				textResult = stringText;
				return textResult;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				textResult = e.toString();

			} catch (IOException e) {
				e.printStackTrace();
				textResult = e.toString();
			}

			return null;
		}

		public void createListView() {

			try {
				Pessoa estObj = new Pessoa();
				JSONArray jsonArrayLoco = new JSONArray(textResult);
				int x = jsonArrayLoco.length();
				for (int y = 0; y <= x; y++) {

					JSONObject jObject = jsonArrayLoco.getJSONObject(y);
					estObj.setNome(jObject.getString("cliente"));
					estObj.setAgendamento(jObject.getString("proximo_atendimento"));

					chamaTudo = new Pessoa(jObject.getString("cliente"), jObject.getString("proximo_atendimento"));
					itens.add(chamaTudo);

				}

			} catch (JSONException e) {
				Log.e("log_tag", "Erro realizando o parse dos dados " + e.toString());
			} finally {

			}

		}

		public void onPostExecute(String textResult) {
			super.onPostExecute(textResult);

			itens = new ArrayList<Pessoa>();

			adapterListView = new AdapterListViewPessoa(TelaAgenda.this, itens);

			listView.setAdapter(adapterListView);

			createListView();
		}

		protected void onPreExecute() {
			// Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " +
			// Thread.currentThread().getName());
			// load = ProgressDialog.show(TelaFornecedor.this, "Por favor Aguarde ...",
			// "Obtendo Estoque ...");

		}

	}

}
