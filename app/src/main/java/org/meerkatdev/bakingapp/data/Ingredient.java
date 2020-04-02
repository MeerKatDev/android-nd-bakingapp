package org.meerkatdev.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {

    public String ingredient;
    public String measure;
    public String quantity;

    public Ingredient(String name, String measure, String quantity) {
        this.ingredient = name;
        this.measure = measure;
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ingredient);
        dest.writeString(this.measure);
        dest.writeString(this.quantity);
    }

    protected Ingredient(Parcel in) {
        this.ingredient = in.readString();
        this.measure = in.readString();
        this.quantity = in.readString();
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
