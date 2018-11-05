package itkmitl.a59070016.healthy.AlarmClock;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import itkmitl.a59070016.healthy.Count;
import itkmitl.a59070016.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class SleepFragment extends Fragment {

    private ArrayList<SleepStore> sleepStore = new ArrayList<SleepStore>();
    private Count myDB;
    private List<SleepStore> sleepList = new ArrayList<>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myDB = new Count(getContext());
        sleepList = myDB.getTime();
        final ListView sleepListView = (ListView) getView().findViewById(R.id.fragment_sleep_items_listview);
        final SleepConfig sleepConfig =  new SleepConfig(
                getActivity(),
                R.layout.fragment_sleepitems,
                sleepList
        );

        Button addTimeButton = (Button) getView().findViewById(R.id.fragment_sleep_addtime);
        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepForm()).addToBackStack(null).commit();
            }
        });

        sleepListView.setAdapter(sleepConfig);

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep,container,false);
    }
}
