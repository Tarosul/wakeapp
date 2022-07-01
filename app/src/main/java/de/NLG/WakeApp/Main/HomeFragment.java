package de.NLG.WakeApp.Main;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.NLG.WakeApp.Main.databinding.FragmentDataBinding;

public class HomeFragment extends Fragment {

    private FragmentDataBinding binding;
    public static String address;
    public static String destination;
    public static String timeRequired;
    public static String arrival;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_data, container, false);

                Button btn = (Button) rootView.findViewById(R.id.fromDate);
                TimePicker timePicker = (TimePicker) rootView.findViewById(R.id.timePicker1);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timePicker.setVisibility(View.VISIBLE);
                    }
                });
               try {
                    TextView destinationView = getView().findViewById(R.id.destination);
                    destinationView.setText(destination);
                } catch (Exception e) {
                   e.printStackTrace();
               }

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