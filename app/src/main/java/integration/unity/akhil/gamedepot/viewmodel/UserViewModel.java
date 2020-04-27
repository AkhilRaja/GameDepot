package integration.unity.akhil.gamedepot.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import integration.unity.akhil.gamedepot.models.User;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<User> userDetails = new MutableLiveData<>();

    public void select(User user) {
        this.userDetails.setValue(user);
    }

    public LiveData<User> getObservableUser() {
        return userDetails;
    }
}
