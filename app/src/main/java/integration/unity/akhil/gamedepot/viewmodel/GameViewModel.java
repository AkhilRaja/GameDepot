package integration.unity.akhil.gamedepot.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

import integration.unity.akhil.gamedepot.api.GamesRepository;
import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.models.User;
import integration.unity.akhil.gamedepot.utils.Constants;
import integration.unity.akhil.gamedepot.workers.GameSyncWorker;

public class GameViewModel extends ViewModel {

    private final LiveData<Games> popularGamesLiveData;
    private final LiveData<Games> anticipatedGamesLiveData;
    private final LiveData<Games> topRatedGamesLiveData;
    private final Application application;

    public void syncGameData() {
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest
                .Builder(GameSyncWorker.class,1, TimeUnit.HOURS).build();
        WorkManager.getInstance(application.getApplicationContext())
                .enqueue(workRequest);
    }

    public GameViewModel(@NonNull Application application) {
        this.application = application;
        this.popularGamesLiveData = GamesRepository.getInstance()
                .getGames(Constants.Popular.DATE,Constants.Popular.ORDERING,Constants.PAGE_SIZE);
        this.anticipatedGamesLiveData = GamesRepository.getInstance()
                .getGames(Constants.Anticipated.DATE,Constants.Anticipated.ORDERING,Constants.PAGE_SIZE);
        this.topRatedGamesLiveData = GamesRepository.getInstance()
                .getGames(Constants.TopRated.DATE,Constants.TopRated.ORDERING,Constants.PAGE_SIZE);
    }

    public LiveData<Games> getObservablePopularGames() {
        return popularGamesLiveData;
    }
    public LiveData<Games> getObservableAnticipatedGames() {
        return anticipatedGamesLiveData;
    }
    public LiveData<Games> getObservableTopRatedGames() {
        return topRatedGamesLiveData;
    }



    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new GameViewModel(application);
        }
    }
}
