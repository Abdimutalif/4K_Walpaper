package com.uz.walpaper.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.uz.walpaper.dizayn.home.BlankFragment;
import com.uz.walpaper.models.Category;

import java.util.List;

public class CategoryAdapter extends FragmentStateAdapter {

    List<Category> categoryList;

    public CategoryAdapter(@NonNull FragmentActivity fragmentActivity, List<Category> categoryList) {
        super(fragmentActivity);
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return BlankFragment.newInstance(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
