package integration.unity.akhil.gamedepot.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.databinding.FragmentMainDetailBinding;
import integration.unity.akhil.gamedepot.models.GameDetail;
import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.viewmodel.GameDetailViewModel;
import integration.unity.akhil.gamedepot.viewmodel.GameViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainDetailFragment extends Fragment {

///Havent touched this file yet..

    private int id;
    FragmentMainDetailBinding binding;

    public void setID(int id){
        this.id = id;
    }

    public MainDetailFragment() {
        // Required empty public constructor
    }


    public static MainDetailFragment newInstance(String param1, String param2) {
        MainDetailFragment fragment = new MainDetailFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GameDetailViewModel.Factory factory = new GameDetailViewModel.Factory(
                getActivity().getApplication(),id);

        final GameDetailViewModel viewModel = ViewModelProviders.of(this, factory)
                .get(GameDetailViewModel.class);

        observeViewModel(viewModel);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainDetailBinding.inflate(inflater, container, false);
        id = MainDetailFragmentArgs.fromBundle(getArguments()).getGameid();
        Log.d("Game Depot", "Id is  :" + id);
        return binding.getRoot();
    }

    private void observeViewModel(GameDetailViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getObservableGameDetail().observe(getViewLifecycleOwner(), new Observer<GameDetail>() {
            @Override
            public void onChanged(@Nullable GameDetail games) {
                if (games!= null) {
                    Log.d("Game Depot : " , "Loaded Values" + games.getName());
                    binding.setGame(games);
                }
            }
        });
    }
}
