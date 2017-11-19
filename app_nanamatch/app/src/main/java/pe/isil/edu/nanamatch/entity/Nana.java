package pe.isil.edu.nanamatch.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by francosalcedo on 18/11/17.
 */

public class Nana implements Parcelable {
    private int id;
    private String email;
    private String name;
    private String last_name;
    private int info;
    private int img;

    public Nana(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    protected Nana(Parcel in) {
        id = in.readInt();
        email = in.readString();
        name = in.readString();
        last_name = in.readString();
        info = in.readInt();
        img = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(last_name);
        dest.writeInt(info);
        dest.writeInt(img);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Nana> CREATOR = new Parcelable.Creator<Nana>() {
        @Override
        public Nana createFromParcel(Parcel in) {
            return new Nana(in);
        }

        @Override
        public Nana[] newArray(int size) {
            return new Nana[size];
        }
    };
}