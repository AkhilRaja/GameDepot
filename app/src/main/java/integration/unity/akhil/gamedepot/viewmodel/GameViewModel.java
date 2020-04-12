package integration.unity.akhil.gamedepot.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ConcurrentModificationException;

import integration.unity.akhil.gamedepot.api.GamesRepository;
import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.utils.Constants;

public class GameViewModel extends ViewModel {

    private final LiveData<Games> gamesLiveData;
    public ObservableField<Games> games = new ObservableField<>();

    public GameViewModel(@NonNull Application application) {
        this.gamesLiveData = GamesRepository.getInstance()
                .getPopularGames(Constants.Popular.DATE,Constants.Popular.ORDERING,Constants.PAGE_SIZE);
    }

    public LiveData<Games> getObservableProject() {
        return gamesLiveData;
    }

    public void setGames(Games games) {
        this.games.set(games);
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new GameViewModel(application);
        }
    }
}
