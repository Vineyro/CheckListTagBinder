package llc.arma.checklisttag.repo;import android.content.Context;import android.util.Log;import androidx.annotation.NonNull;import androidx.work.Worker;import androidx.work.WorkerParameters;import llc.arma.checklisttag.App;import llc.arma.checklisttag.data.Tag;public class SendWorker extends Worker {    public SendWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {        super(context, workerParams);    }    @NonNull    @Override    public Result doWork() {        try {            for (Tag tag : App.getAppComponent().provideLocalRepo().getNotSynced()){                if(new RemoteRepo().send(tag)){                    tag.setSync(0);                    App.getAppComponent().provideLocalRepo().insert(tag);                    Log.d("send", tag.getTagName());                }else {                    return Result.retry();                }            }            return Result.success();        }catch (Exception e){            return Result.retry();        }    }}