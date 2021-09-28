package llc.arma.checklisttag.ui.tag.nfc;import android.os.Bundle;import androidx.lifecycle.LiveData;import androidx.lifecycle.MediatorLiveData;import androidx.lifecycle.MutableLiveData;import androidx.lifecycle.ViewModel;import androidx.paging.LivePagedListBuilder;import androidx.paging.PagedList;import org.jetbrains.annotations.NotNull;import java.util.concurrent.Executors;import llc.arma.checklisttag.App;import llc.arma.checklisttag.Event;import llc.arma.checklisttag.SharedHelper;import llc.arma.checklisttag.data.Tag;import llc.arma.checklisttag.repo.RemoteRepo;public class NfcTagsViewModel extends ViewModel {    private final MediatorLiveData<Boolean> loadResult = new MediatorLiveData<>();    private final MutableLiveData<Event> showLoginEvent = new MutableLiveData<>();    private final MutableLiveData<Event> showScanEvent = new MutableLiveData<>();    private final LiveData<PagedList<Tag>> pagedList;    public LiveData<PagedList<Tag>> getPagedList() {        return pagedList;    }    public LiveData<Event> getShowLoginEvent() {        return showLoginEvent;    }    public LiveData<Event> getShowScanEvent() {        return showScanEvent;    }    public LiveData<Boolean> getLoadResult() {        return loadResult;    }    public NfcTagsViewModel(){        PagedList.Config config = new PagedList.Config.Builder()                .setEnablePlaceholders(false)                .setPageSize(10)                .build();        pagedList = new LivePagedListBuilder<>(App.getAppComponent().provideLocalRepo().getAllNfcMarks(), config)                .setFetchExecutor(Executors.newSingleThreadExecutor())                .build();    }    public void onTagClicked(@NotNull Tag tag) {        Bundle bundle = new Bundle();        bundle.putString("sid", tag.getSid());        if(tag.isNfc()) showScanEvent.postValue(new Event(bundle));    }    public void onLogoutClicked(){        showLoginEvent.postValue(new Event(new Bundle()));        SharedHelper.setToken(null);    }    public void loadTags() {        loadResult.addSource(new RemoteRepo().loadTag(), aBoolean -> {            if (!aBoolean) onLogoutClicked();        });        loadResult.addSource(new RemoteRepo().loadBle(), aBoolean -> {            if (!aBoolean) onLogoutClicked();        });    }}