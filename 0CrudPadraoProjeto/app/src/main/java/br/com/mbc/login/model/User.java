package br.com.mbc.login.model;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int id;
    private String email;
    private String passwd;

    public User() { }
    public User(String email, String passwd) { this.email = email; this.passwd = passwd; }
    public User(int id, String email, String passwd){this.id = id; this.email = email; this.passwd = passwd; }

    protected User(Parcel in) {
        id = in.readInt();
        email = in.readString();
        passwd = in.readString();
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPasswd() {
        return passwd;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override public User createFromParcel(Parcel in) { return new User(in); }
        @Override public User[] newArray(int size) { return new User[size]; }
    };

    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(email);
        parcel.writeString(passwd);
    }

    @Override public String toString() { return "id: " + id + "  " + "email: " + email; }
}
