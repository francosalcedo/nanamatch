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
    private String info;
    private String img;
    private String distritName;
    private int distrit;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDistrit() {
        if (distrit == 1){return "Miraflores";}else if(distrit == 2){
            return "Surquillo";
        }else{
            return "Cercado de Lima";
        }
    }

    public int getDistritInt() {
        return distrit;
    }

    public void setDistrit(int distrit) {
        this.distrit = distrit;
    }

    public void setDistritName(String distritName){
        this.distritName = distritName;
    }

    public String getDistritName(){
        return distritName;
    }

    protected Nana(Parcel in) {
        id = in.readInt();
        email = in.readString();
        name = in.readString();
        last_name = in.readString();
        info = in.readString();
        img = in.readString();
        distritName = in.readString();
        distrit = in.readInt();
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
        dest.writeString(info);
        dest.writeString(img);
        dest.writeString(distritName);
        dest.writeInt(distrit);
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