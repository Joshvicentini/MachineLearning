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

import org.primefaces.event.CellEditEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.ufc.ia.machinelearning.enumeration.MethodEnum;
import br.com.ufc.ia.machinelearning.model.FileData;
import br.com.ufc.ia.machinelearning.spi.Algorithm;
import br.com.ufc.ia.machinelearning.spi.Point;
import br.com.ufc.ia.machinelearning.util.Mensagem;
import br.com.ufc.ia.machinelearning.util.Util;

@ManagedBean(name="mainBean") @ViewScoped
public class MainBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String INICIAL = "inicial";
	
	private int selectedMethod = 0;
	private int selectedParameter = 1;
	private int selectedChartType = 1;
	
	private UploadedFile file;
	private List<FileData> listDataModel;
	private FileData newData = new FileData(0L);
	private Long newDataRowKey = 0L;
	private String nameColumn1;
	private String nameColumn2;
	private String fileSeparator = ",";
	
	private boolean showChart = false;
	private boolean hasHeader = true;
	
	private Algorithm<List<Point>> algorithm;
	private List<Point> listResultPoints;

	private CartesianChartModel chartModel;
	
	public String prepareToShow(){
		showChart = false;
		hasHeader = true;
		fileSeparator = ",";
		return INICIAL;		
	}
	
	@SuppressWarnings("unchecked")
	public void execute(){
		chartModel = new CartesianChartModel();
		String methodClassName = MethodEnum.values()[selectedMethod].getClassName();
		try {
			algorithm =  (Algorithm<List<Point>>) Class.forName("br.com.ufc.ia.machinelearning.algorithm."+methodClassName).newInstance();
			List<Point> listPoints = new ArrayList<Point>();
			for(FileData fileData:listDataModel){
				listPoints.add(new Point(fileData.getValue1(), fileData.getValue2()));
			}
			algorithm.execute(listPoints, null);
			listResultPoints = algorithm.getResult(); 
			
			ChartSeries chartSerie = new ChartSeries();
			for(Point point : listResultPoints){
				chartSerie.set(point.x, point.y);
			}
			chartModel.addSeries(chartSerie);
			showChart = true;
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addData(){
		if(newData != null){
			newData.setId(newDataRowKey);
			if(listDataModel == null){
				listDataModel = new ArrayList<FileData>();
			}
			listDataModel.add(newData);
			newDataRowKey++;
			newData = new FileData(newDataRowKey);
		}
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
				
				if(hasHeader){
					line = item.readLine();
					String nameColumns[] = line.split(fileSeparator,-1);
					if(nameColumns.length == numberOfColumns){
						nameColumn1 = String.valueOf(nameColumns[0]);
						nameColumn2 = String.valueOf(nameColumns[1]);
					}
				}
				Long i = 0L;
				while((line = item.readLine()) != null){
					String values[] = line.split(fileSeparator,-1);
					if(values.length == numberOfColumns){
						FileData fileData = new FileData(i);
						try{
							fileData.setValue1(Double.valueOf(values[0]));
							fileData.setValue2(Double.valueOf(values[1]));
							listDataModel.add(fileData);
						}
						catch(NumberFormatException e){
							Mensagem.sendErrorMessage("Invalid file data on line #"+(i+1)+"!", null);
							listDataModel = null;
							return;
						}
						i++;
					}
				}
				if(listDataModel != null){
					newDataRowKey = Long.valueOf(listDataModel.size());
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void onCellEdit(CellEditEvent event){
		Object oldValue = event.getOldValue();  
        Object newValue = event.getNewValue();  
          
        if(newValue != null && !newValue.equals(oldValue)) {  
        	System.out.println("valor da celular modificado de "+ oldValue +" para "+ newValue + "!");
        }
	}
	
	public String getFileNameTruncated(){
		if(file != null){
			return Util.truncate(file.getFileName(), 20);
		}
		return null;
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

	public boolean isShowChart() {
		return showChart;
	}

	public void setShowChart(boolean showChart) {
		this.showChart = showChart;
	}

	public Algorithm<List<Point>> getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm<List<Point>> algorithm) {
		this.algorithm = algorithm;
	}

	public List<Point> getListResultPoints() {
		return listResultPoints;
	}

	public void setListResultPoints(List<Point> listResultPoints) {
		this.listResultPoints = listResultPoints;
	}

	public CartesianChartModel getChartModel() {
		return chartModel;
	}

	public void setChartModel(CartesianChartModel chartModel) {
		this.chartModel = chartModel;
	}

	public boolean isHasHeader() {
		return hasHeader;
	}

	public void setHasHeader(boolean hasHeader) {
		this.hasHeader = hasHeader;
	}

	public String getFileSeparator() {
		return fileSeparator;
	}

	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	public int getSelectedChartType() {
		return selectedChartType;
	}

	public void setSelectedChartType(int selectedChartType) {
		this.selectedChartType = selectedChartType;
	}

	public FileData getNewData() {
		return newData;
	}

	public void setNewData(FileData newData) {
		this.newData = newData;
	}

	public Long getNewDataRowKey() {
		return newDataRowKey;
	}

	public void setNewDataRowKey(Long newDataRowKey) {
		this.newDataRowKey = newDataRowKey;
	}
	 
}
