package llc.arma.checklisttag.ui.init;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import llc.arma.checklisttag.R;
import llc.arma.checklisttag.databinding.InitFragmentBinding;

public class InitFragment extends Fragment {

    private InitFragmentBinding binding;
    private InitViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(InitViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = InitFragmentBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getShowMainEvent().observe(getViewLifecycleOwner(), event -> {
            if(event.isHandled()){
                Navigation.findNavController(requireActivity(), R.id.mainContainer)
                        .navigate(R.id.action_initFragment_to_tagsFragment);
            }
        });

        viewModel.getShowLoginEvent().observe(getViewLifecycleOwner(), event -> {
            if(event.isHandled()){
                Navigation.findNavController(requireActivity(), R.id.mainContainer)
                        .navigate(R.id.action_initFragment_to_loginFragment);
            }
        });

        viewModel.getShowScannerEvent().observe(getViewLifecycleOwner(), event -> {
            if(event.isHandled()){
                Navigation.findNavController(requireActivity(), R.id.mainContainer)
                        .navigate(R.id.action_initFragment_to_scannerFragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<String> permissions = new ArrayList<>();

        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            permissions.add(Manifest.permission.POST_NOTIFICATIONS);
            permissions.add(Manifest.permission.BLUETOOTH_SCAN);
            permissions.add(Manifest.permission.BLUETOOTH_CONNECT);
        }

        if(requireActivity().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                requireActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            viewModel.start();
        }else {
            requireActivity().requestPermissions(permissions.toArray(new String[0]), 56);
        }
    }
}
