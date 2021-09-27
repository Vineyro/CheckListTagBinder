package llc.arma.checklisttag.repo;import org.jetbrains.annotations.NotNull;import java.util.List;import java.util.concurrent.Executors;import java.util.stream.Collectors;import androidx.lifecycle.LiveData;import androidx.lifecycle.Transformations;import androidx.paging.DataSource;import llc.arma.checklisttag.data.Tag;import llc.arma.checklisttag.db.AppDatabase;import llc.arma.checklisttag.db.entity.Object;import llc.arma.checklisttag.db.model.Smart;public class LocalRepo {    private final AppDatabase appDatabase;    public LocalRepo(AppDatabase appDatabase){        this.appDatabase = appDatabase;    }    public List<Tag> getNotSynced(){        return appDatabase.getObjectDao().getNotSynced().stream().map(Tag::new).collect(Collectors.toList());    }    public LiveData<List<Tag>> getNotSyncedLiveData(){        return Transformations.map(appDatabase.getObjectDao().getNotSyncedLiveData(), input ->                input.stream().map(Tag::new).collect(Collectors.toList()));    }    public boolean isNfcTagExist(String nfcId){        return appDatabase.getObjectDao().getAllByType(Object.NFC_MARK_LINKED,                Object.NFC_MARK_NEW, Object.NFC_MARK).stream()                .map(Tag::new)                .anyMatch(item -> item.getNfcId() != null && item.getNfcId().equals(nfcId));    }    public DataSource.Factory<Integer, Tag> getAllNfcMarks(){        return appDatabase.getObjectDao().getAll(Object.NFC_MARK_LINKED,                Object.NFC_MARK_NEW, Object.NFC_MARK).map(Tag::new);    }    public DataSource.Factory<Integer, Tag> getAllBleMarks(){        return appDatabase.getObjectDao().getAll(Object.BLE_MARK_LINKED,                Object.BLE_MARK_NEW, Object.BLE_MARK).map(Tag::new);    }    public void deleteSyncedMarks(){        appDatabase.getObjectDao().deleteSynced();    }    public void bindTag(String sid, String tagId){        Executors.newSingleThreadExecutor().execute(() -> {            Tag tag = new Tag(appDatabase.getObjectDao().getBySid(sid));            tag.bindTag(tagId);            insert(tag);        });    }    public void insert(@NotNull Smart smartObject) {        appDatabase.getObjectDao().insert(smartObject.getObject());    }    public void insert(Object object){        appDatabase.getObjectDao().insert(object);    }}