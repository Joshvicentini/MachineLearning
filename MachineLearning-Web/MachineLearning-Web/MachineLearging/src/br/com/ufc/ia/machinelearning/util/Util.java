package br.com.ufc.ia.machinelearning.util;


public class Util
{
  public static String toUpperPrimeiraLetra(String palavra)
  {
    if ((palavra != null) && (palavra.length() > 1)) {
      return palavra.substring(0, 1).toUpperCase() + palavra.substring(1, palavra.length());
    }
    return palavra;
  }
  
  public static String getDoubleFormated(Double num){
	  String str = num.toString();
	  if(str.substring(str.indexOf(".")).equals(".0")){
		  return str.substring(0, str.indexOf("."));
	  }
	  return str;
  }

  public static String truncate(String input,int max){
	  if(input != null && input.length() > max){
		  return input.substring(0, max-3) + "...";
	  }
	  return input;
  }
  
  public static String normilizeId(String input){
	  input = input.trim();
	  input = input.replace(" ", "");
	  input = input.substring(0,1).toLowerCase() + input.substring(1);
	  return input;
  }
}
