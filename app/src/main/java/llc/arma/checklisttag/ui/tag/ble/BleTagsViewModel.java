package llc.arma.checklisttag.ui.tag.ble;import android.os.Bundle;import org.jetbrains.annotations.NotNull;import java.util.concurrent.Executors;import androidx.lifecycle.LiveData;import androidx.lifecycle.MediatorLiveData;import androidx.lifecycle.MutableLiveData;import androidx.lifecycle.ViewModel;import androidx.paging.LivePagedListBuilder;import androidx.paging.PagedList;import llc.arma.checklisttag.App;import llc.arma.checklisttag.Event;import llc.arma.checklisttag.SharedHelper;import llc.arma.checklisttag.data.Tag;import llc.arma.checklisttag.repo.RemoteRepo;public class BleTagsViewModel extends ViewModel {    private final MediatorLiveData<Boolean> loadResult = new MediatorLiveData<>();    private final MutableLiveData<Event> showLoginEvent = new MutableLiveData<>();    private final MutableLiveData<Event> showWriteEvent = new MutableLiveData<>();    private final MutableLiveData<Event> showScanBleEvent = new MutableLiveData<>();    private final LiveData<PagedList<Tag>> pagedList;    public LiveData<Event> getShowScanBleEvent() {        return showScanBleEvent;    }    public LiveData<PagedList<Tag>> getPagedList() {        return pagedList;    }    public LiveData<Event> getShowWriteEvent() {        return showWriteEvent;    }    public LiveData<Event> getShowLoginEvent() {        return showLoginEvent;    }    public LiveData<Boolean> getLoadResult() {        return loadResult;    }    public BleTagsViewModel(){        PagedList.Config config = new PagedList.Config.Builder()                .setEnablePlaceholders(false)                .setPageSize(10)                .build();        pagedList = new LivePagedListBuilder<>(App.getAppComponent().provideLocalRepo().getAllBleMarks(), config)                .setFetchExecutor(Executors.newSingleThreadExecutor())                .build();    }    public void onTagClicked(@NotNull Tag tag) {        Bundle bundle = new Bundle();        bundle.putString("sid", tag.getSid());        if (tag.isBle()) showScanBleEvent.postValue(new Event(bundle));    }    public void onLogoutClicked(){        showLoginEvent.postValue(new Event(new Bundle()));        SharedHelper.setToken(null);    }    public void loadTags() {        loadResult.addSource(new RemoteRepo().loadTag(), aBoolean -> {            if (!aBoolean) onLogoutClicked();        });        loadResult.addSource(new RemoteRepo().loadBle(), aBoolean -> {            if (!aBoolean) onLogoutClicked();        });    }}