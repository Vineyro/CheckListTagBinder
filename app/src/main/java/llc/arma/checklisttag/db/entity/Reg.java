package llc.arma.checklisttag.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"text", "sid"})
public class Reg {

    /**
     * Sid Object'a к которому принадлежит реестр
     */
    @NonNull
    public String sid = "";

    /**
     * Позиуия при сортировке
     */
    public int pos;

    /**
     * Имя реестра
     */
    @NonNull
    public String text = "";

    /**
     * Дополнительное значение
     * В случе, если реест - ответ на задачу, определяет статус выполненной задачи
     * В случае, если реестр добавлен пользователем, как ветка,
     * используется как флаг для опредления кем был создан реестр
     * 0 - Создан системой
     * 1 - Создан пользователем и его необходимо удалить во время начала следующего отчета
     */
    public int weight;

}
