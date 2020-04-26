package integration.unity.akhil.gamedepot.view.callback;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.models.Result;

public interface OnClickCallBack {
    public void onGotoDetailView(View view, Result game);
//    {
//        Context context = view.getContext();
//
//        Log.d("Game Depot","Game : " + game.getId());
        //TODO: Modify
//        Intent i = new Intent(context, NewsDetailActivity.class);
//        i.putExtra("url", article.getUrl());
//        context.startActivity(i);
//    }
}