package llc.arma.checklisttag.db.dao;import androidx.room.Dao;import androidx.room.Delete;import androidx.room.Insert;import androidx.room.OnConflictStrategy;import androidx.room.Query;import llc.arma.checklisttag.db.entity.Reg;@Daopublic interface RegDao {    @Query("SELECT * FROM reg WHERE sid = :sid")    Reg getBySid(String sid);    @Insert(onConflict = OnConflictStrategy.REPLACE)    void insert(Reg reg);    @Delete    void delete(Reg reg);}