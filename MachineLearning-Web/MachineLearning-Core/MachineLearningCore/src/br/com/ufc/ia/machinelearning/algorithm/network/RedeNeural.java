package br.com.ufc.ia.machinelearning.algorithm.network;

import java.util.ArrayList;

public class RedeNeural {

	private ArrayList<Neuronio>  conjunto;

	private double tempo=1;

	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public void setConjunto(ArrayList<Neuronio> conjunto) {
		this.conjunto = conjunto;
	}

	public ArrayList<Neuronio> getConjunto() {
		return conjunto;
	}

	public void insert(Neuronio neuronio){
		conjunto.add(neuronio);
	}

	public Neuronio Vencedor(Neuronio X) {
		Neuronio min = new Neuronio();
		Neuronio dif = new Neuronio();
		Neuronio n = new Neuronio();
		Neuronio win = new Neuronio();

		for (int i = 0; i < 10; i++) {
			min.setValor(i, Integer.MAX_VALUE);
		}


		for (int i = 0; i < conjunto.size(); ++i) {
			n=conjunto.get(i);
			dif = n.Diferenca(X);
			if (min.Norma() > dif.Norma())
			{
				min = dif;
				win = n;
			}

		}

		if (getTempo()>0.5)
			setTempo(getTempo()-0.1);
		else
			if (getTempo()>0.1)
				setTempo(getTempo()-0.01);		

		return win;
	}

	public void Treinar(ArrayList<Neuronio> conjunto_treino, int m) {

		Neuronio win = new Neuronio();
		Neuronio minus = new Neuronio();
//		int id; 

		win = null;

		for (int j = 0; j < m; j++) {

			for (int i = 0; i < conjunto_treino.size(); ++i) {

				win = this.Vencedor(conjunto_treino.get(i));

//				id = this.conjunto.indexOf(win);
				
				minus=conjunto_treino.get(i).Diferenca(win);

				win.setValor(win.Soma(minus));

//				this.conjunto.set(id, win);

			}

		}

	}


}

