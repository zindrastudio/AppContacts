package com.example.zivdrag.contactapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactPeople implements Parcelable {

    private String mName, mPhone, mPhotoURI;

    public ContactPeople(){
   // ne treba space, zasto si stavio prazan konstruktor? :)
    }

    public ContactPeople(String name, String phone, String photoURI){

        this.mPhone = phone;
        this.mName = name;
        this.mPhotoURI = photoURI;

    }

    protected ContactPeople(Parcel in) {
        mName = in.readString();
        mPhone = in.readString();
        mPhotoURI = in.readString();
    }

    public static final Creator<ContactPeople> CREATOR = new Creator<ContactPeople>() {
        @Override
        public ContactPeople createFromParcel(Parcel in) {
            return new ContactPeople(in);
        }

        @Override
        public ContactPeople[] newArray(int size) {
            return new ContactPeople[size];
        }
    };

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getmPhotoURI() {
        return mPhotoURI;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public void setmPhotoURI(String mPhotoURI) {
        this.mPhotoURI = mPhotoURI;
    }

    @Override
    public boolean equals(Object object) {

        if(object instanceof ContactPeople){
            ContactPeople temp = (ContactPeople) object;
            if(((ContactPeople) object).getName().equals(temp.getName())){

                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mPhone);
        dest.writeString(mPhotoURI);

    }

    public static final Parcelable.Creator<ContactPeople> CREATE = new Parcelable.Creator<ContactPeople>() {

        @Override
        public ContactPeople createFromParcel(Parcel source) {
            return new ContactPeople(source);
        }

        @Override
        public ContactPeople[] newArray(int size) {
            return new ContactPeople[size];
        }
    };
}

