
package com.ibm.mobileappbuilder.grama20160930135917.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.grama20160930135917.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "HELPDSService" REST Service implementation
 */
public class HELPDSService extends RestService<HELPDSServiceRest>{

    public static HELPDSService getInstance(){
          return new HELPDSService();
    }

    private HELPDSService() {
        super(HELPDSServiceRest.class);

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

