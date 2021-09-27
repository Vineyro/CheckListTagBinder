package llc.arma.checklisttag.data;

import java.util.stream.Collectors;

import javax.inject.Inject;

import llc.arma.checklisttag.db.entity.ObjectEntity;
import llc.arma.checklisttag.db.entity.Value;
import llc.arma.checklisttag.net.WebObject;

public class ObjectSourceToWebMapper extends Mapper<ObjectEntity, WebObject> {

    @Inject
    public ObjectSourceToWebMapper() {
    }

    @Override
    public WebObject map(ObjectEntity input) {

        WebObject.Attr attr = new WebObject.Attr(input.getSid(), input.getType().toString(), input.getParent(),
                input.getTablet(), input.getLogin(), input.getSync(), input.getShow(), input.getTs(),
                input.getLastupd(), input.getChannel(), input.getName(), input.getDeadin());

        return new WebObject(attr, input.getData()
                .stream()
                .collect(Collectors.toMap(Value::getName, item -> {
                    if (item.getType() == 0) {

                        return item.getStrvalue();

                    } else {

                        return item.getNumvalue();

                    }
                }))
        );
    }

}
