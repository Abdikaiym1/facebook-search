package com.example.xstrike.facebook_search_by_tag.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xstrike.facebook_search_by_tag.R;
import com.example.xstrike.facebook_search_by_tag.beans.DateOfPlace;

import java.util.List;


public abstract class CoreFragment extends Fragment {

    public List<DateOfPlace> dateOfPlacesList;

    public CoreFragment() {
    }

}
