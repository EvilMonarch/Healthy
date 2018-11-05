package itkmitl.a59070016.healthy.AlarmClock;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import itkmitl.a59070016.healthy.R;
import java.util.ArrayList;
import java.util.List;

public class SleepConfig extends ArrayAdapter<SleepStore> {
    private Context context;
    private List<SleepStore> sleepStore = new ArrayList<>();
    public SleepConfig(@NonNull Context context, int resource, List<SleepStore> list) {
        super(context, resource, list);
        this.context = context;
        this.sleepStore = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View sleepItems = LayoutInflater.from(context).inflate(
                R.layout.fragment_sleepitems,
                parent,
                false
        );

        TextView date = (TextView)sleepItems.findViewById(R.id.fragment_sleep_items_datetime);
        TextView sleepTimeLenght = (TextView)sleepItems.findViewById(R.id.fragment_sleep_items_sleeptimelenght);
        TextView differenceTime = (TextView)sleepItems.findViewById(R.id.fragment_sleep_items_differenceTime);
        date.setText(sleepStore.get(position).getDate());
        sleepTimeLenght.setText(sleepStore.get(position).getSleepTime() + " - " + sleepStore.get(position).getWakeUpTime());
        differenceTime.setText(sleepStore.get(position).getDifferenceTime());

        return sleepItems;
    }
}
