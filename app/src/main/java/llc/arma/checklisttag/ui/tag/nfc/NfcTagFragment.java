package llc.arma.checklisttag.ui.tag.nfc;import android.content.Context;import android.os.Bundle;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.Toast;import androidx.annotation.NonNull;import androidx.annotation.Nullable;import androidx.databinding.DataBindingUtil;import androidx.fragment.app.Fragment;import androidx.lifecycle.ViewModelProvider;import androidx.navigation.Navigation;import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;import llc.arma.checklisttag.R;import llc.arma.checklisttag.data.Tag;import llc.arma.checklisttag.data.TagDiffUtil;import llc.arma.checklisttag.databinding.NfcTagsFragmentBinding;import llc.arma.checklisttag.ui.tag.TagActionsListener;import llc.arma.checklisttag.ui.tag.TagAdapter;public class NfcTagFragment extends Fragment implements TagActionsListener,        SwipeRefreshLayout.OnRefreshListener {    private NfcTagsFragmentBinding binding;    private NfcTagsViewModel viewModel;    private TagAdapter adapter;    @Override    public void onCreate(@Nullable Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        adapter = new TagAdapter(new TagDiffUtil(), this);    }    @Nullable    @Override    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,                             @Nullable Bundle savedInstanceState) {        binding = DataBindingUtil.inflate(inflater, R.layout.nfc_tags_fragment, container,                false);        binding.setLifecycleOwner(getViewLifecycleOwner());        binding.setModel(viewModel);        return binding.getRoot();    }    @Override    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        binding.tagRecycler.setAdapter(adapter);        binding.pullToRefresh.setOnRefreshListener(this);        viewModel.getPagedList().observe(getViewLifecycleOwner(), adapter::submitList);        viewModel.getShowScanEvent().observe(getViewLifecycleOwner(), event -> {            if (event.isHandled()) {                Navigation.findNavController(requireActivity(), R.id.mainContainer)                        .navigate(R.id.action_tagsFragment_to_scanFragment, event.getContent());            }        });        viewModel.getShowLoginEvent().observe(getViewLifecycleOwner(), event -> {            if (event.isHandled()) {                try {                    Navigation.findNavController(requireActivity(), R.id.mainContainer)                            .navigate(R.id.action_tagsFragment_to_loginFragment2);                }catch (Throwable err) {                }            }        });        viewModel.getLoadResult().observe(getViewLifecycleOwner(), aBoolean -> {            if (aBoolean) {                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();            }        });        viewModel.loadTags();    }    @Override    public void onAttach(@NonNull Context context) {        super.onAttach(context);        viewModel = new ViewModelProvider(requireActivity()).get(NfcTagsViewModel.class);    }    @Override    public void onTagClicked(Tag tag) {        viewModel.onTagClicked(tag);    }    @Override    public void onRefresh() {        viewModel.loadTags();        binding.pullToRefresh.setRefreshing(false);    }}