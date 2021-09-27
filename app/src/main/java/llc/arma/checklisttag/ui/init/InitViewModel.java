package llc.arma.checklisttag.ui.init;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import llc.arma.checklisttag.Event;
import llc.arma.checklisttag.SharedHelper;

public class InitViewModel extends ViewModel {

    private final MutableLiveData<Event> showScannerEvent = new MutableLiveData<>();
    private final MutableLiveData<Event> showLoginEvent = new MutableLiveData<>();
    private final MutableLiveData<Event> showMainEvent = new MutableLiveData<>();

    public LiveData<Event> getShowScannerEvent() {
        return showScannerEvent;
    }

    public LiveData<Event> getShowLoginEvent() {
        return showLoginEvent;
    }

    public LiveData<Event> getShowMainEvent() {
        return showMainEvent;
    }

    public void start(){

        if(SharedHelper.getServerAddress() != null){
            if(SharedHelper.isAuth()){
                showMainEvent.postValue(new Event(new Bundle()));
            }else {
                showLoginEvent.postValue(new Event(new Bundle()));
            }
        }else {
            showScannerEvent.postValue(new Event(new Bundle()));
        }

    }

}
