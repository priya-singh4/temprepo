package uiperformanceutilities.constants;

import java.util.ArrayList;
import java.util.HashMap;

import uiperformanceutilities.model.UIPerformanceModel;



public class UIPerformanceConstants {

	public static HashMap<String,ArrayList<UIPerformanceModel>> uiPerfData= new HashMap<>();

	public static final long EXPECTEDRESPONSETIMEINMILLISECOND=1000;
	
	public void addUIPerfData(String sourceScreen,String destinationScreen,UIPerformanceModel uiPerfModel)
	{
		ArrayList<UIPerformanceModel> uiPerfModels= new ArrayList<>();
		uiPerfModels.add(uiPerfModel);
		uiPerfData.put(sourceScreen +"_"+ destinationScreen, uiPerfModels);
	}
	
	public void addUpdatePerfData(String sourceScreen,UIPerformanceModel uiPerfModel)
	{
		
		if(uiPerfData.containsKey(sourceScreen))
		{
			ArrayList<UIPerformanceModel> uiPerfModels=uiPerfData.get(sourceScreen );
			uiPerfModels.add(uiPerfModel);
			uiPerfData.put(sourceScreen , uiPerfModels);
		}
		else
		{
			ArrayList<UIPerformanceModel> uiPerfModels= new ArrayList<>();
			uiPerfModels.add(uiPerfModel);
			uiPerfData.put(sourceScreen , uiPerfModels);	
		}
	}
	
	public ArrayList<UIPerformanceModel> getPerfData(String sourceScreen,String destinationScreen)
	{
		if(uiPerfData.containsKey(sourceScreen +"_"+ destinationScreen))
		{
			return uiPerfData.get(sourceScreen +"_"+ destinationScreen);
		}
		else
		{
			return null;	
		}
	}
}