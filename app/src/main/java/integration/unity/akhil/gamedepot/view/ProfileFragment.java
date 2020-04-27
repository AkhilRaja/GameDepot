package integration.unity.akhil.gamedepot.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.databinding.FragmentProfileBinding;
import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.models.User;
import integration.unity.akhil.gamedepot.viewmodel.GameViewModel;
import integration.unity.akhil.gamedepot.viewmodel.UserViewModel;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.getObservableUser().observe(getViewLifecycleOwner(),new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.setUser(user);
            }
        });
    }
}
