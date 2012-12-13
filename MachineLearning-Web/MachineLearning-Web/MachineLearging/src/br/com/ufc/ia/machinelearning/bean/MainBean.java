package br.com.ufc.ia.machinelearning.bean;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import br.com.ufc.ia.machinelearning.model.FileData;

@ManagedBean(name="mainBean") @ViewScoped
public class MainBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String INICIAL = "inicial";
	
	private int selectedMethod = 1;
	private int selectedParameter = 1;
	private int selectedGraphic = 1;
	
	private UploadedFile file;
	private List<FileData> listDataModel;
	private String nameColumn1;
	private String nameColumn2;

	public String prepareToShow(){
		return INICIAL;
	}
	
	public void execute(){
		
	}
	
	public void plot(){
		
	}
	
	public void loadFile(){
		if(file != null){
			try{
				byte[] uploadBytes = (byte[]) file.getContents();
				ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);  
				InputStreamReader inputStreamReader = new InputStreamReader(byteInputStream);
				
				BufferedReader item = new BufferedReader(inputStreamReader); 
				String line;

				listDataModel = new ArrayList<FileData>();
				int numberOfColumns = 2;
				line = item.readLine();
				String nameColumns[] = line.split(";",-1);
				if(nameColumns.length == numberOfColumns){
					nameColumn1 = String.valueOf(nameColumns[0]);
					nameColumn2 = String.valueOf(nameColumns[1]);
				}
				Long i = 0L;
				while((line = item.readLine()) != null){
					String values[] = line.split(";",-1);
					if(values.length == numberOfColumns){
						FileData fileData = new FileData(i);
						fileData.setNameValue1(nameColumn1);
						fileData.setNameValue2(nameColumn2);
						fileData.setValue1(Double.valueOf(values[0]));
						fileData.setValue2(Double.valueOf(values[1]));
						listDataModel.add(fileData);
						i++;
					}
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void rowEditListener(RowEditEvent event){
		FileData fileData = (FileData) event.getObject();
	}
	
	
	public void setSelectedMethod(int selectedMethod){
		this.selectedMethod = selectedMethod;
	}
	
	public int getSelectedMethod(){
		return selectedMethod;
	}

	public int getSelectedParameter() {
		return selectedParameter;
	}

	public void setSelectedParameter(int selectedParameter) {
		this.selectedParameter = selectedParameter;
	}

	public int getSelectedGraphic() {
		return selectedGraphic;
	}

	public void setSelectedGraphic(int selectedGraphic) {
		this.selectedGraphic = selectedGraphic;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<FileData> getListDataModel() {
		return listDataModel;
	}

	public void setListDataModel(List<FileData> listDataModel) {
		this.listDataModel = listDataModel;
	}

	public String getNameColumn1() {
		return nameColumn1;
	}

	public void setNameColumn1(String nameColumn1) {
		this.nameColumn1 = nameColumn1;
	}

	public String getNameColumn2() {
		return nameColumn2;
	}

	public void setNameColumn2(String nameColumn2) {
		this.nameColumn2 = nameColumn2;
	}
	 
}
