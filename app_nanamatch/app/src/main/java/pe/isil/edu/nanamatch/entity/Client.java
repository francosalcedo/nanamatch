package pe.isil.edu.nanamatch.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by francosalcedo on 18/11/17.
 */

public class Client implements Parcelable {
    private int id;
    private String email;
    private String name;
    private String last_name;
    private int gender;
    private String address;
    private int phone_number;
    private int id_distric;


    public Client(){

    }


    public Client(Parcel in) {
        id = in.readInt();
        email = in.readString();
        name = in.readString();
        last_name = in.readString();
        gender = in.readInt();
        address = in.readString();
        phone_number = in.readInt();
        id_distric = in.readInt();
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
        dest.writeInt(gender);
        dest.writeString(address);
        dest.writeInt(phone_number);
        dest.writeInt(id_distric);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getId_distric() {
        if (id_distric == 1){
            return "Miraflores";
        }else if(id_distric == 2){
            return "Surquillo";
        }else{
            return "Cercado de Lima";
        }
    }

    public void setId_distric(int id_distric) {
        this.id_distric = id_distric;
    }

}