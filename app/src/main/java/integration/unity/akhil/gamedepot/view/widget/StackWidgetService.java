package integration.unity.akhil.gamedepot.view.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Debug;
import android.text.Html;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.facebook.internal.ImageDownloader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.api.GamesRepository;
import integration.unity.akhil.gamedepot.api.GamesService;
import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.models.Result;
import integration.unity.akhil.gamedepot.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(getApplicationContext(),intent);
    }
}

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private int appWidgetId;
    private List<Result> results = new ArrayList<>();
    private GamesService gamesService;

    StackRemoteViewsFactory(Context context,Intent intent){
        this.context = context;
        this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        gamesService = GamesRepository.getClient().create(GamesService.class);
        Call<Games> call = gamesService.getGames(Constants.Popular.DATE, Constants.Popular.ORDERING,Constants.PAGE_SIZE);
        try {
            results = Objects.requireNonNull(call.execute().body()).getResults();
        }catch (Exception e) {
            Log.e("Games Depot","Error " + e);
        }
    }

    @Override
    public void onDestroy() {
        //Remove data source connection
    }

    @Override
    public int getCount() {
        return results.size();
    }

    // Helper method to get Image bitmap
    private Bitmap getImageBitmap(String purl) {
        Bitmap bm = null;
        try {
            URL url = new URL(purl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            try {
                InputStream in = new BufferedInputStream( urlConnection.getInputStream() );
                bm = BitmapFactory.decodeStream(in);
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bm;
    }


    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.stack_widget_item);
        if (results.size() > position) {
            Log.d("Games Depot:  ","getView succeess");
            remoteViews.setTextViewText(R.id.widgetitemtext,results.get(position).getName());
            Bitmap bmp = getImageBitmap(results.get(position).getBackgroundImage());
            remoteViews.setImageViewBitmap(R.id.widgetimage,bmp);
        }
        Log.d("Games Depot:  ","Not working");
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
