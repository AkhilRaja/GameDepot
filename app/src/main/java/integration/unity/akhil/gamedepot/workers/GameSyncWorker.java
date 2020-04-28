package integration.unity.akhil.gamedepot.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import integration.unity.akhil.gamedepot.api.GamesRepository;
import integration.unity.akhil.gamedepot.utils.Constants;


public class GameSyncWorker extends Worker {

    public GameSyncWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        GamesRepository.getInstance().
                getPopularGames(Constants.Popular.DATE, Constants.Popular.ORDERING,Constants.PAGE_SIZE);
        GamesRepository.getInstance().
                getAnticipatedGames(Constants.Anticipated.DATE, Constants.Anticipated.ORDERING,Constants.PAGE_SIZE);
        GamesRepository.getInstance().
                getTopGames(Constants.TopRated.DATE, Constants.TopRated.ORDERING,Constants.PAGE_SIZE);

        return Result.success();
    }
}
