package llc.arma.checklisttag.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Arrays;

import llc.arma.checklisttag.db.Converters;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@TypeConverters({Converters.class})
public class Object {

    public static final String NFC_MARK_NEW = "nfcMarkNew";
    public static final String NFC_MARK_LINKED = "nfcMarkLinked";
    public static final String NFC_MARK = "nfcMark";

    public static final String BLE_MARK_NEW = "bleNew";
    public static final String BLE_MARK_LINKED = "bleLinked";
    public static final String BLE_MARK = "ble";

    public static final String TAG_ID = "nfcId";

    private Integer id;

    @NonNull
    @PrimaryKey()
    private String sid = "";

    private String parent;

    private String type;

    private String name;

    private String tablet;

    private String channel;

    private Long login;

    private int sync;

    private int show;

    private long lastupd;

    private long deadin;

    private long ts;

    private boolean isReportData;


}
