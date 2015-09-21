package br.com.cast.turmaformacao.taskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 21/09/2015.
 */
public class User implements Parcelable {

    private Long id;
    private String login;
    private String password;
    private String name;
    private String email;

    public User() {
        super();
    }

    public User(String login, String password,String name,String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.login == null ? "" : this.login);
        dest.writeString(this.password == null ? "" : this.password);
        dest.writeString(this.name == null ? "" : this.name);
        dest.writeString(this.email == null ? "" : this.email);
    }

    protected User(Parcel in) {
        this.id = in.readLong();
        this.id = this.id == -1 ? null : this.id;
        this.login = in.readString();
        this.password = in.readString();
        this.name = in.readString();
        this.email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
