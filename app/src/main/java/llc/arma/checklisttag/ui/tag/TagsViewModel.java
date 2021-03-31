package llc.arma.checklisttag.ui.tag;import android.os.Bundle;import org.jetbrains.annotations.NotNull;import java.util.concurrent.Executors;import androidx.lifecycle.LiveData;import androidx.lifecycle.MutableLiveData;import androidx.lifecycle.ViewModel;import androidx.paging.LivePagedListBuilder;import androidx.paging.PagedList;import llc.arma.checklisttag.App;import llc.arma.checklisttag.Event;import llc.arma.checklisttag.SharedHelper;import llc.arma.checklisttag.data.Tag;public class TagsViewModel extends ViewModel {    private final MutableLiveData<Event> showLoginEvent = new MutableLiveData<>();    private final MutableLiveData<Event> showWriteEvent = new MutableLiveData<>();    private final MutableLiveData<Event> showScanEvent = new MutableLiveData<>();    private final LiveData<PagedList<Tag>> pagedList;    public LiveData<PagedList<Tag>> getPagedList() {        return pagedList;    }    public LiveData<Event> getShowWriteEvent() {        return showWriteEvent;    }    public LiveData<Event> getShowLoginEvent() {        return showLoginEvent;    }    public LiveData<Event> getShowScanEvent() {        return showScanEvent;    }    public TagsViewModel(){        PagedList.Config config = new PagedList.Config.Builder()                .setEnablePlaceholders(false)                .setPageSize(10)                .build();        pagedList = new LivePagedListBuilder<>(App.getAppComponent().provideLocalRepo().getAllMarks(), config)                .setFetchExecutor(Executors.newSingleThreadExecutor())                .build();    }    public void onTagClicked(@NotNull Tag tag) {        Bundle bundle = new Bundle();        bundle.putString("sid", tag.getSid());        showScanEvent.postValue(new Event(bundle));    }    public void onLogoutClicked(){        showLoginEvent.postValue(new Event(new Bundle()));        SharedHelper.setToken(null);    }    public void onTagScanned(String sid, String nfcId){        App.getAppComponent().provideLocalRepo().bindTag(sid, nfcId);    }    public void onWriteClicked(){        showWriteEvent.postValue(new Event(new Bundle()));    }}