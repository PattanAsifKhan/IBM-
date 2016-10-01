
package com.ibm.mobileappbuilder.grama20160930135917.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.grama20160930135917.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "FARMERSIDDSService" REST Service implementation
 */
public class FARMERSIDDSService extends RestService<FARMERSIDDSServiceRest>{

    public static FARMERSIDDSService getInstance(){
          return new FARMERSIDDSService();
    }

    private FARMERSIDDSService() {
        super(FARMERSIDDSServiceRest.class);

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

