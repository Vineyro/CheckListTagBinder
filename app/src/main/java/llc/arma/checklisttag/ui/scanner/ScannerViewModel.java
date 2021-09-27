package llc.arma.checklisttag.ui.scanner;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import llc.arma.checklisttag.Event;
import llc.arma.checklisttag.SharedHelper;

public class ScannerViewModel extends ViewModel {

    private final MutableLiveData<Event> closeEvent = new MutableLiveData<>();

    public LiveData<Event> getCloseEvent() {
        return closeEvent;
    }

    public void onCodeScanned(String text){

        String[] splitAddress = text.split(":", 2);

        if(splitAddress.length == 2) {

            if (splitAddress[0].equals("arma")) {

                SharedHelper.saveServerAddress(splitAddress[1]);

                closeEvent.postValue(new Event(new Bundle()));

            }

        }

    }

}
