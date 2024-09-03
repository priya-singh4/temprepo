package api_utilities.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class APIModel {

    int testCaseIdNew = 1;
    String testCaseName = "";
    String module = "";
    String interfaceName = "";
    String fileFormat = "";
    String apiType = "";
    String serviceURL = "";
    String soapAction = "";
    String methodType = "";
    String dbQuery="";
    String dbExpectedValue="";
	boolean certificateRequired;
    String certificateFileName = "";
    String certificatePassword = "";
    String requestFilePath = "";
    String responseFilePath = "";
    String testDataFilePath = "";
    String requestData="";
    String responseData = "";
    boolean needMockNew;
    String mockedInterfaceNameNew;
    int startIndexforIterationNew;
    int endIndexforIteration;
    ArrayList<Integer> iterationsCollection = new ArrayList<>();
    int iterationCountNew;
    int timeout;
    ResponseValidationModel responseValidationModel= new ResponseValidationModel();
    StoreResponseModel storeResponseModel= new StoreResponseModel();
    MockData mockData = new MockData();

    HashMap<String, String> headerData = new HashMap<>();

    public void setTestCaseId(String testCaseId)
    {
        testCaseIdNew = Integer.parseInt(testCaseId);
    }


    public void setTestCaseName(String testCaseName)
    {
        testCaseName = testCaseName;
    }

    public void setModule(String module)
    {
        module = module;
    }

    public void setInterfaceName(String interfaceName)
    {
        interfaceName = interfaceName;
    }

    public void setFileFormat(String fileFormat)
    {
        fileFormat = fileFormat;
    }

    public void setInterfaceType(String interfaceType)
    {
        apiType = interfaceType;
    }

    public void setServiceUrl(String url)
    {
        serviceURL = url;
    }

    public void setSOAPAction(String sOAPAction)
    {
        soapAction = sOAPAction;
    }


    public void setMethodType(String methodType)
    {
        methodType = methodType;
    }

    public String getDBQuery() {
		return dbQuery;
	}


	public void setDBQuery(String dBQuery) {
		dbQuery = dBQuery;
	}
	
	public String getDBExpectedValue() {
		return dbExpectedValue;
	}


	public void setDBExpectedValue(String dBExpectedValue) {
		dbExpectedValue = dBExpectedValue;
	}
	
    public void setCertificatePath(String certificationRequired,String  certificatePath, String certificatePassword)
    {
        if (certificationRequired.equalsIgnoreCase("YES"))
        {
            certificateRequired = true;
            certificateFileName = certificatePath;
            certificatePassword = certificatePassword;
        }
        else
        {
            certificateRequired = false;
            certificateFileName = "";
            certificatePassword = "";
        }
    }


    public void setRequestData(String requestData)
    {
        requestData = requestData;
    }

    public void setResponseData(String responseData)
    {
        responseData = responseData;
    }

    public void setRequestPath(String requestPath)
    {
        requestFilePath = requestPath;
    }

    public void setResponsePath(String responsePath)
    {
        responseFilePath = responsePath;
    }

    public void setTestDataFileName(String testDataFileName)
    {
        testDataFilePath = testDataFileName;
    }

    public void setMockDetails(String needMock, String mockedInterfaceName)
    {
        if (needMock.toUpperCase().trim() == "YES")
        {
            needMockNew = true;
            mockedInterfaceNameNew = mockedInterfaceName;

        }
        else
        {
            needMockNew = false;
            mockedInterfaceNameNew = "";
        }
    }

    public void setStartIndexAndEndforIteration(String startIndexforIteration, String iterationCount)
    {
       iterationCountNew = 1;
        startIndexforIterationNew = 1;
        try
        {
            iterationCountNew = Integer.parseInt(iterationCount);

        }

        catch (Exception e)
        {

        }
        try
        {
            startIndexforIterationNew = Integer.parseInt(startIndexforIteration);

        }

        catch (Exception e)
        {

        }

        if (iterationCountNew == 1)
        {
            endIndexforIteration = startIndexforIterationNew;

            iterationsCollection.add(endIndexforIteration);
        }
        else
        {
            endIndexforIteration = startIndexforIterationNew + (iterationCountNew - 1);

            for (int ii = 0; ii < iterationCountNew; ii++)
            {
                iterationsCollection.add(startIndexforIterationNew + ii);
            }
        }

    }


    public void setTestDataPath(String testDataPath)
    {
        testDataFilePath = testDataPath;
    }


    public void setHeaderData(Map<String, String> headerData)
    {
        headerData = headerData;
    }




    public void setMockData(MockData lclmockData)
    {
        mockData = lclmockData;
    }

    public void setTimeout(int timeout)
    {
        timeout = timeout;
    }


    public int getTestCaseId()
    {
        return testCaseIdNew;
    }


    public String getTestCaseName()
    {
        return testCaseName;
    }

    public String getModule()
    {
        return module;
    }

    public String getInterfaceName()
    {
        return interfaceName;
    }

    public String getFileFormat()
    {
        return fileFormat;
    }

    public String getInterfaceType()
    {
        return apiType;
    }

    public String getServiceUrl()
    {
        return serviceURL;
    }

    public String getSOAPAction()
    {
        return soapAction;
    }

    public String getMethodType()
    {
        return methodType;
    }

    public boolean isCertificateRequired()
    {
        return certificateRequired;
    }


    public String getCertificatePath()
    {
        return certificateFileName;
    }


    public String getCertificatePassword()
    {
        return certificatePassword;
    }

    public String gettRequestPath()
    {
        return requestFilePath;
    }
    public String gettRequestData()
    {
        return requestData;
    }

    public String getResponsePath()
    {
        return responseFilePath;
    }
    public String getResponseData()
    {
        return responseData;
    }


    public String getTestDataPath()
    {
        return testDataFilePath;
    }

    public boolean isMockRequired()
    {
        return needMockNew;
    }


    public String getMockedInterfaceName()
    {
        return mockedInterfaceNameNew;
    }

    public int getStartIndexForIteration()
    {
        return startIndexforIterationNew;
    }

    public int getEndIndexforIteration()
    {
        return endIndexforIteration;
    }

    public int getIterationCount()
    {
        return iterationCountNew;
    }

    public int getIterationFromCollection(int iterationCount) throws Exception
    {
        try
        {

            return iterationsCollection.get(iterationCount);

        }
        catch (Exception e)
        {
            throw new Exception("Incorrect Iteration Count provided");
        }
    }
    public MockData getMockData()
    {
        return mockData;
    }


    public Map<String, String> getHeaderData()
    {
        return headerData;
    }

    public int getTimeout()
    {
        return timeout;
    }

    public ResponseValidationModel getResponseValidationModel()
    {
        return responseValidationModel;
    }

    public void setResponseValidationModel(ResponseValidationModel responseValidationModel)
    {
    	this.responseValidationModel=responseValidationModel;
    }

    
    public StoreResponseModel getStoreResponseModel()
    {
        return storeResponseModel;
    }

    public void setStoreResponseModel(StoreResponseModel storeResponseModel)
    {
    	this.storeResponseModel=storeResponseModel;
    }


    public void setInterfaceDetails(String module, String interfaceName, String fileFormat, String interfaceType,String serviceURL,String soapAction,String methodType, String certificateRequired, String certificateFileName, String certificatePassword, String requestData, String responseData, String needMock, String mockedInterfaceName, MockData mockData, Map<String,String> headerData, int timeout,ResponseValidationModel responseValidationModel ,StoreResponseModel storeResponseModel, String dbQuery, String dbExpectedValue)
	{

        setModule(module);
		setInterfaceName(interfaceName);
        setFileFormat(fileFormat);
        setInterfaceType(interfaceType);
        setServiceUrl(serviceURL);
        setSOAPAction(soapAction);
        setMethodType(methodType);
        setCertificatePath(certificateRequired, certificateFileName,certificatePassword);
		setRequestData(requestData);
		setResponseData(responseData);
        setMockDetails(needMock, mockedInterfaceName);
        setHeaderData(headerData);
        setMockData(mockData);
        setTimeout(timeout);
        setResponseValidationModel(responseValidationModel);
        setStoreResponseModel(storeResponseModel);
        setDBQuery(dbQuery);
        setDBExpectedValue(dbExpectedValue);

    }
	
	
}
