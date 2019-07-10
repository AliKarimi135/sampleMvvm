package ir.aliprogramer.learnrxjava.viewModel;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.aliprogramer.learnrxjava.model.Personal;


public class MainViewModel extends ViewModel {
    MutableLiveData<String>inputLiveData=null;
    MutableLiveData<List<Personal>>personalMutableLiveData=null;
    List<Personal> personalList=new ArrayList<>();
    public MainViewModel() {
        if(inputLiveData==null)
            inputLiveData=new MutableLiveData<>();
        if(personalMutableLiveData==null)
            personalMutableLiveData=new MutableLiveData<>();
    }

    public MutableLiveData<String> getInputLiveData() {
       /* if(inputLiveData==null)
            inputLiveData=new MutableLiveData<>();*/
        return inputLiveData;
    }

    public MutableLiveData<List<Personal>> getPersonalMutableLiveData() {
        /*if(personalMutableLiveData==null)
            personalMutableLiveData=new MutableLiveData<>();*/
        //doSomeWork();

        personalMutableLiveData.setValue(personalList);


        return personalMutableLiveData;
    }

  
public void deleteItem(int position){
         personalList.remove(position);
         personalMutableLiveData.setValue(personalList);
}
    public void setInputLiveData(Personal personal) {
        if(personalMutableLiveData==null)
            personalMutableLiveData=new MutableLiveData<>();
        personalList.add(0,personal);
        personalMutableLiveData.setValue(personalList);
        inputLiveData.setValue(personal.getName());
    }















/*
  private Observable<List<Personal>> getObservable() {

        Maybe<List<Personal>> todoMaybe=Maybe.create(emitter->{
             try {
                for(int i=1;i<15;i++)
                    personalList.add(new Personal("ali"+i,"karimi"+i));
                SystemClock.sleep(999999999);
                if(personalList!=null && !personalList.isEmpty())
                    emitter.onSuccess(personalList);
                else
                    emitter.onComplete();
            } catch (Exception e) {
                 emitter.onError(e);
            }

        });

return todoMaybe.toObservable();
    }
    private Observer<List<Personal>> getObserver() {
        return new Observer<List<Personal>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Personal> personals) {
              //  for (Personal personal:personals)
                 //   personalList.add(personal);
                personalMutableLiveData.setValue(personalList);
                Log.d("aliiii",personals.get(0).getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("aliiii","complet");
            }
        };
    }
    private void doSomeWork() {
       getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }*/
}
