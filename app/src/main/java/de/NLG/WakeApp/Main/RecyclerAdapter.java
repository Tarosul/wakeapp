package de.NLG.WakeApp.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final ArrayList<Object> listRecyclerItem;
    public ArrayList<Object> arraylist;
    private ArrayList<Integer> selectedItems = new ArrayList<>();

    public RecyclerAdapter(Context context, ArrayList<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
        this.arraylist = new ArrayList<Object>();
        this.arraylist.addAll(listRecyclerItem);

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView arrival;
        private TextView timerequired;
        private TextView address;
        private TextView destination;
        private TextView transportOption;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            arrival = (TextView) itemView.findViewById(R.id.arrival);
            timerequired = (TextView) itemView.findViewById(R.id.timeRequired);
            address = (TextView) itemView.findViewById(R.id.address);
            destination = (TextView) itemView.findViewById(R.id.destination);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //HomeFragment.address = address.getText().toString();
                    //HomeFragment.arrival = arrival.getText().toString();
                    HomeFragment.destination = destination.getText().toString();
                    // HomeFragment.timeRequired = timerequired.getText().toString();
                    FragmentManager manager=((AppCompatActivity)context).getSupportFragmentManager();
                    FragmentTransaction Ft=manager.beginTransaction();
                    Ft.replace(R.id.nav_host_fragment_content_main,new HomeFragment());

                    Ft.commit();

                    System.out.println(destination);

                }
            });

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.item_activity, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {


        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                ShowHistory items = (ShowHistory) listRecyclerItem.get(i);

                itemViewHolder.arrival.setText(items.getArrival());
                itemViewHolder.address.setText(items.getAddress());
                itemViewHolder.destination.setText(items.getDestination());
                itemViewHolder.timerequired.setText(items.getTimeRequired());
        }
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }


}