package com.example.fooding.fragments;

import static com.example.fooding.clients.FoodClient.API_KEY;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.fooding.R;
import com.example.fooding.bottomsheets.FilterBottomSheet;
import com.example.fooding.clients.FoodClient;
import com.example.fooding.clients.NetworkCallback;
import com.example.fooding.models.Food;

import java.util.ArrayList;
import java.util.List;

import com.example.fooding.adapters.FoodAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import okhttp3.Headers;

public class SearchFragment extends Fragment {
    public static final String Tag = "SearchFragment";

    private FloatingActionButton filterFab;
    private SearchView recipeSearchView;

    public ArrayList<Food> foods;
    private FoodAdapter foodAdapter;
    public String selectedDiet = null;
    public String selectedMeal = null;
    public String currentSearch = null;

    public static final String TAG = "SearchFragment";

    public SearchFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        foods = new ArrayList<>();
        recipeSearchView = view.findViewById(R.id.recipeSearchView);
        RecyclerView rvRecipes = view.findViewById(R.id.rvSearch);
        filterFab = view.findViewById(R.id.filter_fab);

        recipeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                                    @Override
                                                    public boolean onQueryTextSubmit(String query) {
                                                        currentSearch = recipeSearchView.getQuery().toString();
                                                        Recipes(currentSearch);
                                                        return false;
                                                    }

                                                    @Override
                                                    public boolean onQueryTextChange(String newText) {
                                                        CharSequence searchValue = recipeSearchView.getQuery().toString();
                                                        return false;
                                                    }
                                                }
        );
        foodAdapter = new FoodAdapter(getContext(), foods);
        rvRecipes.setAdapter(foodAdapter);
        rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        setupFAB();
    }

    private void setupFAB() {
        filterFab.setOnClickListener(view -> {
            FilterBottomSheet bottomSheet = new FilterBottomSheet(selectedDiet, selectedMeal);
            bottomSheet.show(getChildFragmentManager(), FilterBottomSheet.TAG);

        });
    }

    public void setVariables(String diet, String meal) {
        this.selectedDiet = diet;
        this.selectedMeal = meal;
        if (currentSearch != null && currentSearch.length() > 0) {
            Recipes(currentSearch);
        }
        recipeSearchView.clearFocus();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchRecipe = menu.findItem(R.id.rvSearch);
        searchRecipe.setVisible(true);
        SearchView searchView = (SearchView) searchRecipe.getActionView();
        searchView.setQueryHint("Search Recipe");

    }

    private boolean Recipes(String searchValue) {
        FoodClient client = new FoodClient();
        client.getIngredients(selectedDiet, selectedMeal, searchValue, new NetworkCallback<List<Food>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(List<Food> data) {
                foods.clear();
                foods.addAll(data);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
        return false;
    }
}


