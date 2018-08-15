package adel.co.asyst.learnlistview.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    String name;
    String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    protected Person(Parcel in) {
        name = in.readString();
        address = in.readString();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
    }
}
