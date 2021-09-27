package llc.arma.checklisttag.db.entity;import androidx.room.Embedded;import androidx.room.Relation;import java.util.ArrayList;import java.util.List;public class ObjectEntity {    @Embedded    private Object object;    @Relation(parentColumn = "sid", entityColumn = "sid")    private List<Value> data = new ArrayList<>();    public ObjectEntity() {        object = new Object();    }    public Object getObject() {        return object;    }    public void setObject(Object object) {        this.object = object;    }    public Integer getId() {        return object.getId();    }    public void setId(Integer id) {        this.object.setId(id);    }    public String getSid() {        return object.getSid();    }    public void setSid(String sid) {        this.object.setSid(sid);    }    public String getParent() {        return object.getParent();    }    public void setParent(String parent) {        this.object.setParent(parent);    }    public String getType() {        return object.getType();    }    public void setType(String type) {        this.object.setType(type);    }    public String getName() {        return object.getName();    }    public void setName(String name) {        this.object.setName(name);    }    public String getChannel() {        return object.getChannel();    }    public void setChannel(String channel) {        this.object.setChannel(channel);    }    public String getTablet() {        return object.getTablet();    }    public void setTablet(String tablet) {        this.object.setTablet(tablet);    }    public long getTs() {        return object.getTs();    }    public void setTs(long ts) {        this.object.setTs(ts);    }    public Long getLogin() {        return object.getLogin();    }    public void setLogin(Long login) {        this.object.setLogin(login);    }    public int getSync() {        return object.getSync();    }    public void setSync(int sync) {        this.object.setSync(sync);        if(sync == 2)            setReportData(true);    }    public boolean isReportData() {        return object.isReportData();    }    public int getShow() {        return object.getShow();    }    public void setShow(int show) {        this.object.setShow(show);    }    public long getLastupd() {        return object.getLastupd();    }    public void setLastupd(long lastupd) {        this.object.setLastupd(lastupd);    }    public long getDeadin() {        return object.getDeadin();    }    public void setDeadin(long deadin) {        this.object.setDeadin(deadin);    }    public List<Value> getData() {        return data;    }    public void setData(List<Value> data) {        this.data = data;    }    public void setReportData(boolean reportData) {        object.setReportData(reportData);    }    public Object toObject(){        return object;    }}