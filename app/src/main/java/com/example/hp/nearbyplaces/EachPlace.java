package com.example.hp.nearbyplaces;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

class EachPlace {

     String name,vicinity;
    float rating;
     Geometry geometry;
     Hourseopen opening_hours;
     String icon,place_id;
     ArrayList<photoobject>photos;



    public EachPlace() {
    }

//    protected EachPlace(Parcel in) {
//        name = in.readString();
//        vicinity = in.readString();
//        rating = in.readDouble();
//        icon = in.readString();
//        place_id = in.readString();
//    }

//    public static final Creator<EachPlace> CREATOR = new Creator<EachPlace>() {
//        @Override
//        public EachPlace createFromParcel(Parcel in) {
//            return new EachPlace(in);
//        }
//
//        @Override
//        public EachPlace[] newArray(int size) {
//            return new EachPlace[size];
//        }
//    };

    public String getPlace_id() {
        return place_id;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public float getRating() {
        return rating;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public Hourseopen getOpening_hours() {
        return opening_hours;
    }

    public void setPhotos(ArrayList<photoobject> photos) {
        this.photos = photos;
    }

    public ArrayList<photoobject> getPhotos() {
        return photos;
    }




//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(name);
//        parcel.writeString(vicinity);
//        parcel.writeDouble(rating);
//        parcel.writeString(icon);
//        parcel.writeString(place_id);
//    }
}
