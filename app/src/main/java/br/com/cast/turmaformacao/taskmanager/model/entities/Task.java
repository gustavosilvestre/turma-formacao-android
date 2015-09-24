package br.com.cast.turmaformacao.taskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrador on 15/09/2015.
 */
public class Task implements Parcelable {

    @JsonIgnore
    private Long id;

    @JsonProperty("_id")
    private Long web_id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonIgnore
    private Label label;

    public Task() {
        super();
    }

    public Task(Parcel imp) {
        super();
        readFromParcel(imp);
    }

    public Long getId() {
        return id;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getWeb_id() {
        return web_id;
    }

    public void setWeb_id(Long web_id) {
        this.web_id = web_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id == null ? -1 : id);
        dest.writeLong(web_id == null ? -1 : web_id);
        dest.writeString(name == null ? "" : name);
        dest.writeString(description == null ? "" : description);
        dest.writeParcelable(label, flags);
    }

    public void readFromParcel(Parcel imp) {

        id = imp.readLong();
        id = id == -1 ? null : id;
        web_id = imp.readLong();
        web_id = web_id == -1 ? null : id;

        name = imp.readString();
        description = imp.readString();
        label = imp.readParcelable(Label.class.getClassLoader());
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }

    };


}
