package integration.unity.akhil.gamedepot.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.databinding.FragmentMainListBinding;


public class MainListFragment extends Fragment {

    public static final String TAG = "ArticleListFragment";
    private FragmentMainListBinding binding;
    //TODO: Data Binding with View and Adapter

    public MainListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
