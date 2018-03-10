package com.example.xstrike.facebook_search_by_tag.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xstrike.facebook_search_by_tag.R;
import com.example.xstrike.facebook_search_by_tag.beans.DateOfPlace;

import java.util.ArrayList;
import java.util.List;

public class PreviewFragment extends CoreFragment  {
    private RecyclerView previewRecyclerView;
    private RecyclerView.LayoutManager previewLayoutManager;
    protected RecyclerView.Adapter previewAdapter;
    public List<DateOfPlace> dateOfPlacesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preview_fragment, container, false);
        previewRecyclerView = view.findViewById(R.id.preview_recycler_View);
        previewRecyclerView.setHasFixedSize(true);
        previewLayoutManager = new LinearLayoutManager(getActivity());
        previewRecyclerView.setLayoutManager(previewLayoutManager);

        dateOfPlacesList = new ArrayList<>();
        previewAdapter = new PreviewRecyclerAdapter(dateOfPlacesList, getContext());

        previewRecyclerView.setAdapter(previewAdapter);

        return view;
    }

    public void changeList(List<DateOfPlace> dateOfPlaces) {
        dateOfPlacesList.clear();
        dateOfPlacesList.addAll(dateOfPlaces);
        previewAdapter.notifyDataSetChanged();
    }

}
