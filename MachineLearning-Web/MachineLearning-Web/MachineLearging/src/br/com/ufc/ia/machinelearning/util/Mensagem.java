package br.com.ufc.ia.machinelearning.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {
	
	public static void sendMessage(String msg){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}
	
	public static void sendErrorMessage(String msg,String detail){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, detail));
	}
	
	public static void sendInfoMessage(String msg,String detail){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detail));
	}
	
	public static void sendWarnMessage(String msg,String detail){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, detail));
	}
	
	public static void sendFatalMessage(String msg,String detail){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, detail));
	}
	
	public static String getMsg(String key){
		String text = null;
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle("SystemMessages", context.getViewRoot().getLocale(), getCurrentClassLoader(null));
		try{
			text = bundle.getString(key);
		} catch(MissingResourceException e){
			text = "--- Chave " + key + " não encontrada! ---";
		}
		return text;
	}
	
	protected static ClassLoader getCurrentClassLoader(Object defaultObject){
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if(loader == null){
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}
}
