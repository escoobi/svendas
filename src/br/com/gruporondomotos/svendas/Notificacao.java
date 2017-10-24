package br.com.gruporondomotos.svendas;

import android.app.Activity;

import android.os.Bundle;
import android.widget.TextView;



public class Notificacao extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  
        TextView txt = new TextView(this);
         
        txt.setText("Activity after click on notification");
        setContentView(txt);
		
		
	}
	
	
}
