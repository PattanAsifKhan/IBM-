
package com.ibm.mobileappbuilder.grama20160930135917.ds;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class HELPDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("nAME") public String nAME;
    @SerializedName("eMAIL") public String eMAIL;
    @SerializedName("pHONENO") public String pHONENO;
    @SerializedName("id") public String id;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nAME);
        dest.writeString(eMAIL);
        dest.writeString(pHONENO);
        dest.writeString(id);
    }

    public static final Creator<HELPDSItem> CREATOR = new Creator<HELPDSItem>() {
        @Override
        public HELPDSItem createFromParcel(Parcel in) {
            HELPDSItem item = new HELPDSItem();

            item.nAME = in.readString();
            item.eMAIL = in.readString();
            item.pHONENO = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public HELPDSItem[] newArray(int size) {
            return new HELPDSItem[size];
        }
    };

}


