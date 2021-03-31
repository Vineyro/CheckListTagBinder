package llc.arma.checklisttag.db.model;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Relation;
import llc.arma.checklisttag.db.entity.Reg;
import llc.arma.checklisttag.db.entity.Value;

public class SmartObject {

    public static final String NFC_MARK_NEW = "nfcMarkNew";
    public static final String NFC_MARK_LINKED = "nfcMarkLinked";
    public static final String NFC_MARK = "nfcMark";

    public static final String TAG_ID = "nfcId";

    private Integer id;

    private String sid;

    private String parent;

    private String type;

    private String name;

    private String channel;

    private String tablet;

    private long ts;

    private int login;

    public int sync;

    private int show;

    private long lastupd;

    private long deadin;

    @Relation(parentColumn = "sid", entityColumn = "sid")
    private List<Value> data = new ArrayList<>();

    @Relation(parentColumn = "sid", entityColumn = "sid")
    private List<Reg> list = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTablet() {
        return tablet;
    }

    public void setTablet(String tablet) {
        this.tablet = tablet;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public long getLastupd() {
        return lastupd;
    }

    public void setLastupd(long lastupd) {
        this.lastupd = lastupd;
    }

    public long getDeadin() {
        return deadin;
    }

    public void setDeadin(long deadin) {
        this.deadin = deadin;
    }

    public List<Value> getData() {
        return data;
    }

    public void setData(List<Value> data) {
        this.data = data;
    }

    public List<Reg> getList() {
        return list;
    }

    public void setList(List<Reg> list) {
        this.list = list;
    }

    public void setValue(String name, String val){
        Value value;

        if(getValue(name) != null){
            value = getValue(name);
            value.strvalue = val;
        }else {
            value = new Value();
            value.ts = System.currentTimeMillis();
            value.sid = sid;
            value.strvalue = val;
            value.name = name;
            value.type = 0;
        }
        data.removeIf(item -> item.name.equals(name));
        data.add(value);
    }

    public void setValue(String name, long val){
        Value value;

        if(getValue(name) != null){
            value = getValue(name);
            value.numvalue = val;
        }else{
            value = new Value();
            value.ts = System.currentTimeMillis();
            value.sid = sid;
            value.numvalue = val;
            value.name = name;
            value.type = 1;
        }
        data.removeIf(item -> item.name.equals(name));
        data.add(value);
    }

    public Value getValue(String name){
        return data.stream().filter(value -> value.name.equals(name)).findAny().orElse(null);
    }

}
