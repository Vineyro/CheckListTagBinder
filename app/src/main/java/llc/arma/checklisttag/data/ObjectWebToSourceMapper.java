package llc.arma.checklisttag.data;

import java.util.ArrayList;

import llc.arma.checklisttag.db.entity.Object;
import llc.arma.checklisttag.db.entity.ObjectEntity;
import llc.arma.checklisttag.db.model.Smart;
import llc.arma.checklisttag.net.WebObject;

public class ObjectWebToSourceMapper extends Mapper<WebObject, ObjectEntity> {

    @Override
    public ObjectEntity map(WebObject input) {
        ObjectEntity dataObject = new ObjectEntity();
        dataObject.setSid(input.getAttr().getSid());
        dataObject.setType(input.getAttr().getType());
        dataObject.setParent(input.getAttr().getParent() == null ? "" : input.getAttr().getParent());
        dataObject.setTablet(input.getAttr().getTablet());
        dataObject.setLogin(input.getAttr().getLogin() == null ? 0 : input.getAttr().getLogin());
        dataObject.setSync(input.getAttr().getSync() == null ? 0 : input.getAttr().getSync());
        dataObject.setShow(input.getAttr().getShow());
        dataObject.setTs(input.getAttr().getTs() == null ? System.currentTimeMillis() : input.getAttr().getTs());
        dataObject.setLastupd(input.getAttr().getLastupd() == null ? 0 : input.getAttr().getLastupd());
        dataObject.setChannel(input.getAttr().getChannel());
        dataObject.setName(input.getAttr().getName());
        dataObject.setDeadin(input.getAttr().getDeadin() == null ? 0 : input.getAttr().getDeadin());

        Smart smart = new Smart(dataObject);

        if(input.getData() != null)
        {
            smart.setData(new ArrayList<>());
            input.getData().forEach((k,v) -> {
                if(!k.equals("tasks")) {
                    if (v != null) {
                        if (v instanceof String) {
                            smart.setValue(k, v.toString());
                        } else {
                            try {
                                smart.setValue(k, (long) Double.parseDouble(v.toString()));
                            }catch (Exception e){
                                smart.setValue(k, v.toString());
                            }
                        }
                    }
                }
            });

        }

        return smart.getObject();
    }

}
