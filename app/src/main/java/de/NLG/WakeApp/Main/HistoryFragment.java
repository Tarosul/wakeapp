package de.NLG.WakeApp.Main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.NLG.WakeApp.Main.databinding.FragmentDataBinding;
import de.NLG.WakeApp.Main.databinding.HistoryDataBinding;

public class HistoryFragment extends Fragment {

    private HistoryDataBinding binding;
    private RecyclerView mRecyclerView;
    private ArrayList<Object> viewItems = new ArrayList<>();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "MainActivity";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.history_data, container, false);
            String url = "https://paygoal.de/wake.php";

            RequestQueue queue = Volley.newRequestQueue(getContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray transactions = new JSONArray(response);

                                mRecyclerView = (RecyclerView) rootView.findViewById(R.id.historyView);
                                mRecyclerView.setHasFixedSize(true);

                                layoutManager = new LinearLayoutManager(rootView.getContext());
                                mRecyclerView.setLayoutManager(layoutManager);

                                mAdapter = new RecyclerAdapter(rootView.getContext(), viewItems);
                                mRecyclerView.setAdapter(mAdapter);

                                for (int i = 0; i < transactions.length(); i++) {
                                    JSONObject transactionObject = transactions.getJSONObject(i);

                                    String address = transactionObject.getString("address");
                                    String arrival = transactionObject.getString("arrival");
                                    String timeRequired = transactionObject.getString("timeRequired");
                                    String destination = transactionObject.getString("destination");
                                    String transportOption = transactionObject.getString("transportOption");

                                    ShowHistory items = new ShowHistory(arrival, timeRequired, address, destination, transportOption);
                                    viewItems.add(items);
                                }


                            } catch (JSONException e) {
                                Log.d(TAG, "addItemsFromJSON: ", e);

                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error.getLocalizedMessage());

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("schedules", "1");
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            stringRequest.setShouldCache(false);
            queue.add(stringRequest);

            Button back = rootView.findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new HomeFragment();
                    FragmentManager fm = getParentFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.nav_host_fragment_content_main, fragment);
                    transaction.commit();
                }
            });
        return rootView;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}