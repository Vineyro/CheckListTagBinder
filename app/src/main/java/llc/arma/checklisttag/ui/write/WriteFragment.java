package llc.arma.checklisttag.ui.write;import android.content.Context;import android.os.Bundle;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import androidx.annotation.NonNull;import androidx.annotation.Nullable;import androidx.databinding.DataBindingUtil;import androidx.fragment.app.DialogFragment;import androidx.fragment.app.FragmentResultListener;import androidx.lifecycle.Observer;import androidx.lifecycle.ViewModelProvider;import llc.arma.checklisttag.Event;import llc.arma.checklisttag.R;import llc.arma.checklisttag.databinding.WriteFragmentBinding;public class WriteFragment extends DialogFragment implements FragmentResultListener {    WriteFragmentBinding binding;    WriteViewModel viewModel;    @Nullable    @Override    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,                             @Nullable Bundle savedInstanceState) {        binding = DataBindingUtil.inflate(inflater, R.layout.write_fragment, container,                false);        binding.setLifecycleOwner(getViewLifecycleOwner());        binding.setModel(viewModel);        return binding.getRoot();    }    @Override    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        viewModel.getCloseEvent().observe(getViewLifecycleOwner(), event -> {            if(event.isHandled()){                requireActivity().onBackPressed();            }        });        requireActivity().getSupportFragmentManager()                .setFragmentResultListener("writeNfc", getViewLifecycleOwner(), this);    }    @Override    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {        requireActivity().getSupportFragmentManager().setFragmentResult("write", new Bundle());        requireActivity().onBackPressed();    }    @Override    public void onAttach(@NonNull Context context) {        super.onAttach(context);        viewModel = new ViewModelProvider(requireActivity()).get(WriteViewModel.class);    }}