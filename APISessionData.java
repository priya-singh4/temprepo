package api_utilities.TestSettings;

import java.util.HashMap;

import api_utilities.models.EnvModel;

public class APISessionData {

	public static HashMap<String,String> sessionKeys= new HashMap<>();
	public static HashMap<String,String> apiSessionKeys= new HashMap<>();
	public static EnvModel envModel= new EnvModel();
	public static String dbLoggingTable;
	
	public static void setSessionData(String testCase, String module, String browser,String iteration,String key, String value)
	{
		sessionKeys.put(testCase+ "_"+ module + "_" +browser+ "_"+iteration+ "_"+ key,value);
	}
	

	public static void syncSessionData(HashMap<String,String> sessionData)
	{

		if(sessionData.isEmpty()==false)
		{
			for(String key:sessionData.keySet())
			{

				sessionKeys.put( key,sessionData.get(key));
			}
		}
	}
	
	public static void setSessionDataCollection(String testCase, String module, String browser,String iteration,HashMap<String,String> sessionData)
	{
		if(sessionData.isEmpty()==false)
		{
			for(String key:sessionData.keySet())
			{

				sessionKeys.put(testCase+ "_"+ module + "_" +browser+ "_"+iteration+ "_"+ key,sessionData.get(key));
				apiSessionKeys.put(testCase+ "_"+ module + "_" +browser+ "_"+iteration+ "_"+ key,sessionData.get(key));
			}
		}

	}
	
	
	public static String getSessionData(String testCase, String module, String browser,String iteration,String key)
	{
		return sessionKeys.get(testCase+ "_"+ module + "_" +browser+ "_"+iteration+ "_"+ key);		
	}

	
	
	public static String getAPISessionData(String testCase, String module, String browser,String iteration,String key)
	{
		return apiSessionKeys.get(testCase+ "_"+ module + "_" +browser+ "_"+iteration+ "_"+ key);		
	}


	public static HashMap<String, String> getAPISessionDataCollection()
	{
		return apiSessionKeys;		
	}

	public static String replaceSessionData(String testCase, String module, String browser,String iteration,String value)
	{
		
		for(String s : sessionKeys.keySet())
		{
			s= s.replace(testCase+ "_"+ module + "_" +browser+ "_"+iteration+ "_", "");
			if(value.contains(s))
			{
				value=value.replace("##"+s + "##", sessionKeys.get(testCase+ "_"+ module + "_" +browser+ "_"+iteration+ "_"+s));
			}
		}
		return value;		
	}
	
	public static HashMap<String,String> replaceSessionDataCollection(String testCase, String module, String browser,String iteration,HashMap<String,String> collectionData)
	{
		
		for(String colKey : collectionData.keySet())
		{
			String colvalue=collectionData.get(colKey);
			
			for(String sessionKey : sessionKeys.keySet())
			{
				String s=sessionKey.replace(testCase+ "_"+ module + "_" +browser+ "_"+iteration+ "_", "");
				s="##"+s +"##";
				if(colvalue.contains(s))
				{
					colvalue=colvalue.replaceAll(s, sessionKeys.get(sessionKey));
					collectionData.put(colKey, colvalue);
				}
				
			}
			
		}
		return collectionData;		
	}
	
	
	public static boolean sessionDataContainsKey(String testCase, String module, String browser,String iteration,String key)
	{
		if( sessionKeys.containsKey(testCase+ "_"+ module + "_" +browser+ "_"+iteration+ "_"+ key))
		{
			return true;
		}
		else
		{
			return false;			
		}
	}


	public static char[] getSessionStartTime() {
	
		return null;
	}
	

}
