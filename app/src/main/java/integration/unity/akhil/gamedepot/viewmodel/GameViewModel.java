package integration.unity.akhil.gamedepot.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import integration.unity.akhil.gamedepot.api.GamesRepository;
import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.utils.Constants;

public class GameViewModel extends ViewModel {

    private final LiveData<Games> popularGamesLiveData;
    private final LiveData<Games> anticipatedGamesLiveData;
    private final LiveData<Games> topRatedGamesLiveData;

    public GameViewModel(@NonNull Application application) {
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
