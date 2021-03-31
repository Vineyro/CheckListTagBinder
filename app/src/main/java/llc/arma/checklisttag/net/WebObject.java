package llc.arma.checklisttag.net;import java.util.ArrayList;import java.util.List;import java.util.Map;import llc.arma.checklisttag.db.model.SmartObject;public class WebObject {    public String id;    public String version;    public Attr attr;    public Map<String, Object> data;    public List<WebObject> childs;    static class Attr{        String sid;        String type;        String parent;        String tablet;        Integer login;        Integer sync;        Integer show;        Long ts;        Long lastupd;        String channel;        String name;        Long deadin;    }    public SmartObject toSmartObject()    {        SmartObject smartObject = new SmartObject();        smartObject.setSid(this.attr.sid);        smartObject.setType(this.attr.type);        smartObject.setParent(this.attr.parent == null ? "" : this.attr.parent);        smartObject.setTablet(this.attr.tablet);        smartObject.setLogin(this.attr.login == null ? 0 : this.attr.login);        smartObject.setSync(this.attr.sync);        smartObject.setShow(this.attr.show);        smartObject.setTs(this.attr.ts == null ? System.currentTimeMillis() : this.attr.ts);        smartObject.setLastupd(this.attr.lastupd == null ? 0 : this.attr.lastupd);        smartObject.setChannel(this.attr.channel);        smartObject.setName(this.attr.name);        smartObject.setDeadin(this.attr.deadin);        if(data != null)        {            smartObject.setData(new ArrayList<>());            data.forEach((k,v) -> {                if(!k.equals("tasks")) {                    if (v != null) {                        if (v instanceof String) {                            smartObject.setValue(k, v.toString());                        } else {                            try {                                smartObject.setValue(k, (long) Double.parseDouble(v.toString()));                            }catch (Exception e){                                smartObject.setValue(k, v.toString());                            }                        }                    /*if(v instanceof Integer || v instanceof Long){                        smartObject.setValue(k, Long.parseLong(v.toString()));                    } else{                        smartObject.setValue(k, v.toString());                    }*/                    }                }            });        }        return smartObject;    }}