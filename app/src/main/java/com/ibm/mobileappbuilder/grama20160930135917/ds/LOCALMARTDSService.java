
package com.ibm.mobileappbuilder.grama20160930135917.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.grama20160930135917.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "LOCALMARTDSService" REST Service implementation
 */
public class LOCALMARTDSService extends RestService<LOCALMARTDSServiceRest>{

    public static LOCALMARTDSService getInstance(){
          return new LOCALMARTDSService();
    }

    private LOCALMARTDSService() {
        super(LOCALMARTDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://ibm-pods.buildup.io";
    }

    @Override
    protected String getApiKey() {
        return "OynpEOpK";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://ibm-pods.buildup.io/app/57ee6ffb11cb3e03009e921f",
                path,
                "apikey=OynpEOpK");
    }

}

