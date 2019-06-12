package lesson5.rumpilstilstkin.ru.databaseex;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Users {
    private String login;

    @PrimaryKey(autoGenerate = true)
    private long _id;

    private String userId;

    private String avatarUrl;

    Users(long _id, String login, @NonNull String userId, String avatarUrl){
        this._id = _id;
        this.login = login;
        this.userId = userId;
        this.avatarUrl = avatarUrl;
    }

    public long get_id() {
        return _id;
    }

    public String getLogin() {
        return login;
    }

    public String getUserId() {
        return userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
