package itkmitl.a59070016.healthy.AlarmClock;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import itkmitl.a59070016.healthy.DBHelper;
import itkmitl.a59070016.healthy.R;
import itkmitl.a59070016.healthy.Weight.WeightConfigItem;
import itkmitl.a59070016.healthy.Weight.WeightForm;
import itkmitl.a59070016.healthy.Weight.WeightStore;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SleepFragment extends Fragment {

    private ArrayList<SleepStore> sleepStore = new ArrayList<SleepStore>();
    private DBHelper myDB;
    private List<SleepStore> sleepList = new ArrayList<>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myDB = new DBHelper(getContext());
        sleepList = myDB.getTime();
        final ListView sleepListView = (ListView) getView().findViewById(R.id.fragment_sleep_items_listview);
        final SleepConfigItems sleepConfigItems =  new SleepConfigItems(
                getActivity(),
                R.layout.fragment_sleep_items,
                sleepList
        );

        Button addTimeButton = (Button) getView().findViewById(R.id.fragment_sleep_addtime);
        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepForm()).addToBackStack(null).commit();
            }
        });

        sleepListView.setAdapter(sleepConfigItems);

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep,container,false);
    }
}
