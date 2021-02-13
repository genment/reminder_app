package tl2h.reminder.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import tl2h.reminder.object.AlarmObject;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<AlarmObject> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is home fragment");
    }

    public LiveData<AlarmObject> getText() {
        return mText;
    }
}