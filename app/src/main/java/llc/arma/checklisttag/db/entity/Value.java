package llc.arma.checklisttag.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;


@Entity(primaryKeys = {"sid", "name"})
public class Value {

    /**
     * Sid Object'a к которому принадлежит реестр
     */
    @NonNull
    public String sid = "";

    /**
     * Имя значения
     */
    @NonNull
    public String name = "";

    /**
     * Тип значения
     * 0 - numvalue
     * 1 - strvalue
     */
    public int type;

    /**
     * Целочисленное значение
     */
    public long numvalue;

    /**
     * Строковое значение
     */
    public String strvalue;

    /**
     * Время создания
     */
    public long ts;

}