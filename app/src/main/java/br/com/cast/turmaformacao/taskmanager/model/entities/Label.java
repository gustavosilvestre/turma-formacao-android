package br.com.cast.turmaformacao.taskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 17/09/2015.
 */
public class Label implements Parcelable {

    private Long id;
    private Color color;
    private String name;
    private String description;

    public Label() {
        super();
    }

    private Label(Parcel imp) {
        super();
        readFromParcel(imp);
    }

    public Long getId() {
        return id;
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

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return Color.getInstance(color.getHex()).toString();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Label label = (Label) o;

        if (id != null ? !id.equals(label.id) : label.id != null) return false;
        if (color != label.color) return false;
        if (name != null ? !name.equals(label.name) : label.name != null) return false;
        return !(description != null ? !description.equals(label.description) : label.description != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id == null ? -1 : id);
        dest.writeString(name == null ? "" : name);
        dest.writeString(description == null ? "" : description);
        dest.writeString(color == null ? "" : color.getHex());
    }


    public void readFromParcel(Parcel imp) {

        id = imp.readLong();
        id = id == -1 ? null : id;
        name = imp.readString();
        description = imp.readString();
        color = Color.getInstance(imp.readString());
    }

    public static final Parcelable.Creator<Label> CREATOR = new Parcelable.Creator<Label>() {


        @Override
        public Label createFromParcel(Parcel source) {
            return new Label(source);
        }

        @Override
        public Label[] newArray(int size) {
            return new Label[size];
        }
    };
}
