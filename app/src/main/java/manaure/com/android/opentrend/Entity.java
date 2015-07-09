package manaure.com.android.opentrend;


/**
 * Created by manaure on 09/07/15.
 */
public class Entity {
    private String thumbnail;
    private String picture;
    private  String description;

    public Entity() {
    }

    public Entity(String description, String thumbnail, String picture) {
        this.description = description;
        this.thumbnail = thumbnail;
        this.picture = picture;
    }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

