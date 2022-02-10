package com.uz.walpaper.dizayn.random;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uz.walpaper.MainActivity;
import com.uz.walpaper.R;
import com.uz.walpaper.adapters.RecyclerAdapter;
import com.uz.walpaper.databinding.FragmentRandomBinding;
import com.uz.walpaper.models.Category;
import com.uz.walpaper.models.ImageData;
import com.uz.walpaper.preference.MySharedPreference;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;


public class RandomFragment extends Fragment {


    private FragmentRandomBinding binding;
    private Gson gson = new Gson();
    private MySharedPreference preference;
    private List<Category> categoryList;
    private RecyclerAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRandomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        preference = MySharedPreference.getInstance(requireContext());
        String listString = preference.getNumberListString();
        Type type = new TypeToken<List<Category>>() {
        }.getType();
        ((MainActivity) getActivity()).showBlurView();
        categoryList = gson.fromJson(listString, type);
        List<ImageData> imageDataList = categoryList.get(0).getImageDataList();
        Collections.shuffle(imageDataList);
        adapter = new RecyclerAdapter(imageDataList, new RecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClick(ImageData imageData) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("image", imageData);
                Navigation.findNavController(requireView()).navigate(R.id.action_nav_random_to_imageFragment, bundle);
            }
        });

        binding.recycler.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}