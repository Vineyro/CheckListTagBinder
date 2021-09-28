package llc.arma.checklisttag.ui.login;import android.os.Bundle;import androidx.lifecycle.LiveData;import androidx.lifecycle.MutableLiveData;import androidx.lifecycle.ViewModel;import java.util.concurrent.Executors;import llc.arma.checklisttag.Event;import llc.arma.checklisttag.SharedHelper;import llc.arma.checklisttag.data.UserWebToSourceMapper;import llc.arma.checklisttag.net.WebUser;import llc.arma.checklisttag.repo.RemoteRepo;public class LoginViewModel extends ViewModel {    public final MutableLiveData<String> login = new MutableLiveData<>();    public final MutableLiveData<String> pass = new MutableLiveData<>();    private final MutableLiveData<Event> showMainEvent = new MutableLiveData<>();    private final MutableLiveData<Boolean> isLoadingState = new MutableLiveData<>();    public LiveData<Event> getShowMainEvent() {        return showMainEvent;    }    public LiveData<Boolean> getIsLoadingState() {        return isLoadingState;    }    public void onLoginClicked(){        if(login.getValue() != null && pass.getValue() != null) {            Executors.newSingleThreadExecutor().execute(() -> {                isLoadingState.postValue(true);                try {                    WebUser webUser = new RemoteRepo().login(login.getValue(), pass.getValue());                    isLoadingState.postValue(false);                    SharedHelper.setUser(new UserWebToSourceMapper().map(webUser));                    SharedHelper.setToken(webUser.getToken());                    showMainEvent.postValue(new Event(new Bundle()));                } catch (RemoteRepo.WrongAuthDataException|RemoteRepo.TimeoutException e) {                    e.printStackTrace();                }                isLoadingState.postValue(false);                login.postValue("");                pass.postValue("");            });        }    }}