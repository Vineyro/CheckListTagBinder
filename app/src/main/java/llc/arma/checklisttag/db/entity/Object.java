package llc.arma.checklisttag.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Object {

    public Integer id;

    @NonNull
    @PrimaryKey()
    public String sid = "";
    
    public String parent;

    public String type;

    public String name;

    public String tablet;

    public String channel;

    public int login;

    public int sync;

    public int show;

    public long lastupd;

    public long deadin;

    public long ts;

}
