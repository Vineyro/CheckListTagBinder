package llc.arma.checklisttag.repo;import android.util.Pair;import com.google.gson.Gson;import com.google.gson.GsonBuilder;import org.jetbrains.annotations.NotNull;import java.io.IOException;import java.util.concurrent.Executors;import java.util.concurrent.TimeUnit;import androidx.lifecycle.MutableLiveData;import llc.arma.checklisttag.App;import llc.arma.checklisttag.BuildConfig;import llc.arma.checklisttag.SharedHelper;import llc.arma.checklisttag.Utils;import llc.arma.checklisttag.data.Tag;import llc.arma.checklisttag.db.entity.Object;import llc.arma.checklisttag.db.entity.Reg;import llc.arma.checklisttag.db.entity.Value;import llc.arma.checklisttag.db.model.SmartObject;import llc.arma.checklisttag.net.WebApi;import llc.arma.checklisttag.net.WebObject;import llc.arma.checklisttag.net.WebResponse;import okhttp3.OkHttpClient;import retrofit2.Response;import retrofit2.Retrofit;import retrofit2.converter.gson.GsonConverterFactory;public class RemoteRepo {    public static final String SYNC_CONTEXT = "sync/";    public static boolean send(Tag tag){        WebApi webApi = buildRetrofit().create(WebApi.class);        try {            Response<WebResponse> response = webApi.sendObject(SharedHelper.getToken(),                    Utils.getAndroidId(App.getAppComponent().provideContext()),                    new Gson().toJson(tag), "").execute();            return response.isSuccessful();        } catch (IOException e) {            e.printStackTrace();        }        return false;    }    public static void loadTag(){        Executors.newSingleThreadExecutor().execute(() -> {            WebApi webApi = buildRetrofit().create(WebApi.class);            try {                Response<WebObject[]> response = webApi.getTreeData(                        SharedHelper.getToken()).execute();                if(response.body() != null) {                    App.getAppComponent().provideLocalRepo().getAllMarks();                    for (WebObject webObject : response.body()) {                        SmartObject smartObject = webObject.toSmartObject();                        smartObject.setSync(0);                        App.getAppComponent().provideLocalRepo().insert(smartObject);                    }                }            } catch (IOException e) {                e.printStackTrace();            }        });    }    @NotNull    public static MutableLiveData<Pair<Integer, String>> login(String login, String password){        MutableLiveData<Pair<Integer, String>> result = new MutableLiveData<>();        Executors.newSingleThreadExecutor().execute(() -> {            try {                WebApi webApi = buildRetrofit().create(WebApi.class);                Response<WebResponse> response = webApi.auth(login, password,                        Utils.getAndroidId(App.getAppComponent().provideContext())).execute();                if(response.body() != null){                    result.postValue(new Pair<>(response.body().status, response.body().msg));                }else {                    result.postValue(new Pair<>(0, null));                }            } catch (IOException e) {                e.printStackTrace();                result.postValue(new Pair<>(0, null));            }        });        return result;    }    @NotNull    public static Retrofit buildRetrofit(){        Gson gson = new GsonBuilder()                .setLenient()                .create();        OkHttpClient okHttpClient = new OkHttpClient.Builder()                .readTimeout(60, TimeUnit.SECONDS)                .connectTimeout(60, TimeUnit.SECONDS)                .build();        return new Retrofit.Builder()                .baseUrl(BuildConfig.API_URL + RemoteRepo.SYNC_CONTEXT)                .addConverterFactory(GsonConverterFactory.create(gson))                .client(okHttpClient)                .build();    }}