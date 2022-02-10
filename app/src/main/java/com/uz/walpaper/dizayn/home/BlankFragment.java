package com.uz.walpaper.dizayn.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uz.walpaper.R;
import com.uz.walpaper.adapters.RecyclerAdapter;
import com.uz.walpaper.databinding.FragmentBlankBinding;
import com.uz.walpaper.models.Category;
import com.uz.walpaper.models.ImageData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private Category mParam1;

    public BlankFragment() {
        // Required empty public constructor
    }


    public static BlankFragment newInstance(Category category) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Category) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    private FragmentBlankBinding blankBinding;
    private RecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        blankBinding = FragmentBlankBinding.inflate(inflater, container, false);
        List<ImageData> imageDataList = mParam1.getImageDataList();
        recyclerAdapter = new RecyclerAdapter(imageDataList, new RecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClick(ImageData imageData) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("image", imageData);
                Navigation.findNavController(requireView()).navigate(R.id.imageFragment, bundle);
            }
        });
        blankBinding.recycler.setAdapter(recyclerAdapter);
        return blankBinding.getRoot();
    }
}