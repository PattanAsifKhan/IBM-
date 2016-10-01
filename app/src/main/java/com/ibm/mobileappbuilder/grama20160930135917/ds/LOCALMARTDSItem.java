
package com.ibm.mobileappbuilder.grama20160930135917.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class LOCALMARTDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("contactName") public String contactName;
    @SerializedName("email") public String email;
    @SerializedName("city") public String city;
    @SerializedName("phone") public String phone;
    @SerializedName("picture") public String picture;
    @SerializedName("pricePERKG") public Long pricePERKG;
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
        dest.writeString(contactName);
        dest.writeString(email);
        dest.writeString(city);
        dest.writeString(phone);
        dest.writeString(picture);
        dest.writeValue(pricePERKG);
        dest.writeString(id);
    }

    public static final Creator<LOCALMARTDSItem> CREATOR = new Creator<LOCALMARTDSItem>() {
        @Override
        public LOCALMARTDSItem createFromParcel(Parcel in) {
            LOCALMARTDSItem item = new LOCALMARTDSItem();

            item.contactName = in.readString();
            item.email = in.readString();
            item.city = in.readString();
            item.phone = in.readString();
            item.picture = in.readString();
            item.pricePERKG = (Long) in.readValue(null);
            item.id = in.readString();
            return item;
        }

        @Override
        public LOCALMARTDSItem[] newArray(int size) {
            return new LOCALMARTDSItem[size];
        }
    };

}


