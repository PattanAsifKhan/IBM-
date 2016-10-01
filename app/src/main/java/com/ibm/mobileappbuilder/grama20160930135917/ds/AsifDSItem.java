
package com.ibm.mobileappbuilder.grama20160930135917.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class AsifDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("text1") public String text1;
    @SerializedName("text2") public Long text2;
    @SerializedName("picture") public String picture;
    @SerializedName("text3") public Long text3;
    @SerializedName("pENDING") public Long pENDING;
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
        dest.writeString(text1);
        dest.writeValue(text2);
        dest.writeString(picture);
        dest.writeValue(text3);
        dest.writeValue(pENDING);
        dest.writeString(id);
    }

    public static final Creator<AsifDSItem> CREATOR = new Creator<AsifDSItem>() {
        @Override
        public AsifDSItem createFromParcel(Parcel in) {
            AsifDSItem item = new AsifDSItem();

            item.text1 = in.readString();
            item.text2 = (Long) in.readValue(null);
            item.picture = in.readString();
            item.text3 = (Long) in.readValue(null);
            item.pENDING = (Long) in.readValue(null);
            item.id = in.readString();
            return item;
        }

        @Override
        public AsifDSItem[] newArray(int size) {
            return new AsifDSItem[size];
        }
    };

}


