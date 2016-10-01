
package com.ibm.mobileappbuilder.grama20160930135917.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class FARMERSDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("nAME") public String nAME;
    @SerializedName("iD") public Long iD;
    @SerializedName("picture") public String picture;
    @SerializedName("pHONENO") public String pHONENO;
    @SerializedName("id") public String id;
    @SerializedName("pictureUri") public transient Uri pictureUri;

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
        dest.writeValue(iD);
        dest.writeString(picture);
        dest.writeString(pHONENO);
        dest.writeString(id);
    }

    public static final Creator<FARMERSDSItem> CREATOR = new Creator<FARMERSDSItem>() {
        @Override
        public FARMERSDSItem createFromParcel(Parcel in) {
            FARMERSDSItem item = new FARMERSDSItem();

            item.nAME = in.readString();
            item.iD = (Long) in.readValue(null);
            item.picture = in.readString();
            item.pHONENO = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public FARMERSDSItem[] newArray(int size) {
            return new FARMERSDSItem[size];
        }
    };

}


