package br.com.ufc.ia.machinelearning.util;

import java.util.Map;

import javax.faces.context.FacesContext;

public class SessionUtil {

	public static Map<String,Object> getSessionMap(){
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
}
