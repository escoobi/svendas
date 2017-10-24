package br.com.gruporondomotos.svendas;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListViewPessoa extends BaseAdapter{
	private LayoutInflater mInflater;
    private ArrayList<Pessoa> itens;

    
    public AdapterListViewPessoa(Context context, ArrayList<Pessoa> itens)
    {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }
    
	/**
     * Retorna a quantidade de itens
     *
     * @return
     */
    
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itens.size();
	}

	/**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
	
	@Override
	public Pessoa getItem(int posicao) {
		// TODO Auto-generated method stub
		return itens.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		// TODO Auto-generated method stub
		return posicao;
	}

	@Override
	public View getView(int posicao, View ver, ViewGroup verGrupo) {
		 //Pega o item de acordo com a posção.
        Pessoa item = itens.get(posicao);
        //infla o layout para podermos preencher os dados
        ver = mInflater.inflate(R.layout.lista_cliente, null);
 
        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) ver.findViewById(R.id.textCliente)).setText(item.getNome());
        ((TextView) ver.findViewById(R.id.textAgendamento)).setText(item.getAgendamento());
        
        
        
        return ver;
	}

}
