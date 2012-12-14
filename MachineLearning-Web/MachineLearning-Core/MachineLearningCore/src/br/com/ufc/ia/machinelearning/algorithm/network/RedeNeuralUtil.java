package br.com.ufc.ia.machinelearning.algorithm.network;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class RedeNeuralUtil{
	
	public static RedeNeural txtToJava(String url) throws Exception{
		RedeNeural rede = new RedeNeural();
		FileInputStream fi = new FileInputStream(url);
		byte b[] = new byte[fi.available()];
		fi.read(b);
		String text = new String(b);
		String linhas[] = text.split("\n");
		
		ArrayList<Neuronio> neuronios = new ArrayList<Neuronio>();
		for(int i=0;i<linhas.length;i++){
			Neuronio neuronio = new Neuronio();
			String aux[] = linhas[i].split(",");
			long val[] = new long[10];
			for(int j=0;j<10;j++){
				val[j] =  Long.parseLong(aux[j]);
				neuronio.setValor(j,val[j]);
			}
			neuronios.add(neuronio);
		}
		rede.setConjunto(neuronios);
		fi.close();
		return rede;
	}
	
	public static void javaToTxt(RedeNeural rede, String url) throws Exception{
		StringBuilder sb = new StringBuilder();
		for(Neuronio neuronio:rede.getConjunto()){
			for(int i=0;i<10;i++){
				if(i!=9){
					sb.append(neuronio.getValor(i) +",");
				}
				else{
					sb.append(neuronio.getValor(i));
				}
			}
			sb.append("\n");
		}
		sb.delete(sb.length()-1, sb.length());
		
		FileOutputStream fo = new FileOutputStream(url);
		fo.write(sb.toString().getBytes());
		fo.close();
	}
}
