package com.example.hw_4;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    String name;
    int id;
    int price;
    int image;
    boolean box;

    public int i;

    Product(String _name, int _id, int _price, int _image, boolean _box, int _i){
        name = _name;
        id = _id;
        price = _price;
        image = _image;
        box = _box;
        i = _i;
    }

    protected Product(Parcel in) {
        name = in.readString();
        id = in.readInt();
        price = in.readInt();
        image = in.readInt();
        box = in.readByte() != 0;
        i = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeInt(price);
        dest.writeInt(image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(box);
        }
        dest.writeInt(i);
    }
}
