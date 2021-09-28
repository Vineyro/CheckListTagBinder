package llc.arma.checklisttag.ui.add;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import llc.arma.checklisttag.databinding.AddNfcObjectFragmentBinding;

public class AddNfcObjectDialog extends DialogFragment {

    private AddNfcObjectViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this).get(AddNfcObjectViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.start(requireArguments().getString("nfcId", null));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AddNfcObjectFragmentBinding binding = AddNfcObjectFragmentBinding.inflate(inflater,
                container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getCloseEvent().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()){
                requireActivity().onBackPressed();
            }
        });
    }
}
