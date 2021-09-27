package llc.arma.checklisttag.db.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import llc.arma.checklisttag.db.entity.Object;
import llc.arma.checklisttag.db.entity.ObjectEntity;
import llc.arma.checklisttag.db.entity.Value;

public class Smart {

    private final ObjectEntity object;

    public ObjectEntity getObject() {
        return object;
    }

    public void setParent(String parent) {
        object.setParent(parent);
        setLastupd(System.currentTimeMillis());
    }

    public void setChannel(String channel) {
        object.setChannel(channel);
        setLastupd(System.currentTimeMillis());
    }

    public void setTablet(String tablet) {
        object.setTablet(tablet);
        setLastupd(System.currentTimeMillis());
    }

    public void setTs(long ts) {
        object.setTs(ts);
    }

    public void setLogin(Long login) {
        object.setLogin(login);
        setLastupd(System.currentTimeMillis());
    }

    public void setSync(int sync) {
        object.setSync(sync);
    }

    public void isReportData(boolean isReportData){
        object.setReportData(isReportData);
    }

    public void setShow(int show) {
        object.setShow(show);
        setLastupd(System.currentTimeMillis());
    }

    public void setLastupd(long lastupd) {
        object.setLastupd(lastupd);
        setSync(2);
    }

    public void setDeadin(long deadin) {
        object.setDeadin(deadin);
        setLastupd(System.currentTimeMillis());
    }

    public Smart(@NotNull ObjectEntity dataObject){
        this.object = dataObject;
    }

    public String getType() {
        return object.getType();
    }

    public void setType(String type) {
        object.setType(type);
        setLastupd(System.currentTimeMillis());
    }

    public String getName() {
        return object.getName();
    }

    public void setName(String name) {
        object.setName(name);
        setLastupd(System.currentTimeMillis());
    }

    public String getSid() {
        return object.getSid();
    }

    public void setSid(String sid) {
        object.setSid(sid);
        setLastupd(System.currentTimeMillis());
    }

    public Integer getId() {
        return object.getId();
    }

    public String getParent() {
        return object.getParent();
    }

    public String getChannel() {
        return object.getChannel();
    }

    public String getTablet() {
        return object.getTablet();
    }

    public long getTs() {
        return object.getTs();
    }

    public Long getLogin() {
        return object.getLogin();
    }

    public int getSync() {
        return object.getSync();
    }

    public int getShow() {
        return object.getShow();
    }

    public long getLastupd() {
        return object.getLastupd();
    }

    public long getDeadin() {
        return object.getDeadin();
    }

    public List<Value> getData() {
        return object.getData();
    }

    public Value getValue(String name){
        return object.getData().stream().filter(value ->
                value.getName().equals(name)).findAny().orElse(null);
    }

    public void setNewSid(String newSid){
        setSid(newSid);
        for (Value value : getData()) {
            value.setSid(newSid);
        }
    }

    public void setData(List<Value> data){
        object.setData(data);
    }

    public void setValue(String name, String val){
        Value value;

        if(getValue(name) != null){
            value = getValue(name);
            value.setStrvalue(val);
        }else {
            value = new Value();
            value.setTs(System.currentTimeMillis());
            value.setSid(object.getSid());
            value.setStrvalue(val);
            value.setName(name);
            value.setType(0);
        }
        object.getData().removeIf(item -> item.getName().equals(name));
        object.getData().add(value);
    }

    public void setValue(String name, long val){
        Value value;

        if(getValue(name) != null){
            value = getValue(name);
            value.setNumvalue(val);
        }else{
            value = new Value();
            value.setTs(System.currentTimeMillis());
            value.setSid(object.getSid());
            value.setNumvalue(val);
            value.setName(name);
            value.setType(1);
        }
        object.getData().removeIf(item -> item.getName().equals(name));
        object.getData().add(value);
    }

    public Smart copy(){

        Gson gson = new Gson();

        return gson.fromJson(gson.toJson(this), new TypeToken<Smart>(){}.getType());

    }

}