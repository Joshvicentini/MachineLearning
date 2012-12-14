package br.com.ufc.ia.machinelearning.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.ufc.ia.machinelearning.enumeration.MethodEnum;


@ManagedBean(name="autocompleteBean") @SessionScoped
public class AutocompleteBean {
	
	public List<SelectItem> getListMethods(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		for(MethodEnum method : MethodEnum.values()){
			list.add(new SelectItem(method.ordinal(), method.toString()));
		}
		return list;
	}

}
