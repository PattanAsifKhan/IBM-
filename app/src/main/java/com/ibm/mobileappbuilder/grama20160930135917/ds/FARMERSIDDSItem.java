
package com.ibm.mobileappbuilder.grama20160930135917.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class FARMERSIDDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("picture") public String picture;
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
        dest.writeString(picture);
        dest.writeString(id);
    }

    public static final Creator<FARMERSIDDSItem> CREATOR = new Creator<FARMERSIDDSItem>() {
        @Override
        public FARMERSIDDSItem createFromParcel(Parcel in) {
            FARMERSIDDSItem item = new FARMERSIDDSItem();

            item.picture = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public FARMERSIDDSItem[] newArray(int size) {
            return new FARMERSIDDSItem[size];
        }
    };

}


