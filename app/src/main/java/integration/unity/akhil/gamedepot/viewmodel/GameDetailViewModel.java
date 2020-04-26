package integration.unity.akhil.gamedepot.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import integration.unity.akhil.gamedepot.api.GamesRepository;
import integration.unity.akhil.gamedepot.models.GameDetail;

public class GameDetailViewModel extends ViewModel {
    private final LiveData<GameDetail> gameDetailLiveData;

    public GameDetailViewModel(@NonNull Application application, int id) {
        Log.d("Game Depot : ", "View Model" + id);
        this.gameDetailLiveData = GamesRepository.getInstance()
                .getGameDetail(id);
    }

    public LiveData<GameDetail> getObservableGameDetail() {
        return gameDetailLiveData;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;
        private final int id;
        public Factory(@NonNull Application application,int id) {
            this.application = application;
            this.id = id;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new GameDetailViewModel(application,id);
        }
    }
}
