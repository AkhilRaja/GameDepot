package integration.unity.akhil.gamedepot.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import integration.unity.akhil.gamedepot.adapter.GamesAdapter;
import integration.unity.akhil.gamedepot.databinding.FragmentMainListBinding;
import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.view.callback.OnClickCallBack;
import integration.unity.akhil.gamedepot.viewmodel.GameViewModel;


public class MainListFragment extends Fragment {

    public static final String TAG = "ArticleListFragment";
    private FragmentMainListBinding binding;
    private GamesAdapter popularGamesAdapter;
    private GamesAdapter anticipatedGamesAdapter;
    private GamesAdapter topratedGamesAdapter;

    public static enum GameType {
        Popular,
        Anticipated,
        TopRated
    }
    public MainListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainListBinding.inflate(inflater,container,false);

        //region Setting up the Adapter
        popularGamesAdapter = new GamesAdapter(GameType.Popular);
        popularGamesAdapter.detailCallbackListener = (OnClickCallBack) this.getActivity();

        anticipatedGamesAdapter = new GamesAdapter(GameType.Anticipated);
        anticipatedGamesAdapter.detailCallbackListener = (OnClickCallBack) this.getActivity();

        topratedGamesAdapter = new GamesAdapter(GameType.TopRated);
        topratedGamesAdapter.detailCallbackListener = (OnClickCallBack) this.getActivity();

        binding.gameRV1.setAdapter(popularGamesAdapter);
        binding.gameRV2.setAdapter(anticipatedGamesAdapter);
        binding.gameRV3.setAdapter(topratedGamesAdapter);
        //endregion

        binding.setIsLoading(true);
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GameViewModel.Factory factory = new GameViewModel.Factory(
                getActivity().getApplication());

        final GameViewModel viewModel = ViewModelProviders.of(this, factory)
                .get(GameViewModel.class);

        binding.setIsLoading(true);
        observeViewModel(viewModel);
    }

    private void observeViewModel(GameViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getObservablePopularGames().observe(getViewLifecycleOwner(), new Observer<Games>() {
            @Override
            public void onChanged(@Nullable Games games) {
                if (games!= null) {
                    binding.setIsLoading(false);
                    popularGamesAdapter.setGameList(games.getResults());
                }
            }
        });

        viewModel.getObservableAnticipatedGames().observe(getViewLifecycleOwner(),new Observer<Games>() {
            @Override
            public void onChanged(@Nullable Games games) {
                if(games!= null) {
                    binding.setIsLoading(false);
                    anticipatedGamesAdapter.setGameList(games.getResults());
                }
            }
        });

        viewModel.getObservableTopRatedGames().observe(getViewLifecycleOwner(), new Observer<Games>() {
            @Override
            public void onChanged(Games games) {
                if(games!= null) {
                    binding.setIsLoading(false);
                    topratedGamesAdapter.setGameList(games.getResults());
                }
            }
        });
    }
}
