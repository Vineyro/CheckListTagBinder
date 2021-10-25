package llc.arma.checklisttag.ui.add;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executors;

import llc.arma.checklisttag.App;
import llc.arma.checklisttag.Event;
import llc.arma.checklisttag.SharedHelper;
import llc.arma.checklisttag.data.Tag;

public class AddNfcObjectViewModel extends ViewModel {

    private final MutableLiveData<Event> closeEvent = new MutableLiveData<>();

    public LiveData<Event> getCloseEvent() {
        return closeEvent;
    }

    public MutableLiveData<String> newObjectName = new MutableLiveData<>("");
    public MutableLiveData<String> newObjectDescription = new MutableLiveData<>("");

    private String nfcId;

    public void start(String nfcId){
        this.nfcId = nfcId;
    }

    public void onAddClicked(){
        Executors.newSingleThreadExecutor().execute(() -> {
            if(newObjectName.getValue() != null && newObjectName.getValue().length() > 0) {
                App.getAppComponent().provideLocalRepo().insert(
                        Tag.createNew(
                                newObjectName.getValue(),
                                newObjectDescription.getValue() == null ? "" : newObjectDescription.getValue(),
                                SharedHelper.getUser().getOrganisation().getId(),
                                nfcId));
                closeEvent.postValue(new Event(new Bundle()));
            }
        });
    }

}
