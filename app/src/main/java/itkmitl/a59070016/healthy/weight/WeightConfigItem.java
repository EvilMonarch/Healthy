package itkmitl.a59070016.healthy.Weight;

import android.content.Context;
import android.graphics.Color;
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

public class WeightConfigItem extends ArrayAdapter<WeightStore>{
    TextView status;
    List<WeightStore> weightStore = new ArrayList<>();
    Context context;

    public WeightConfigItem(@NonNull Context context, int resource,List<WeightStore> list) {
        super(context, resource, list);
        this.context = context;
        this.weightStore = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View weightItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_weight_items,
                parent,
                false
        );
        TextView weight = (TextView)weightItem.findViewById(R.id.weight_fragment_weight);
        TextView date = (TextView)weightItem.findViewById(R.id.weight_fragment_date);
        status = (TextView)weightItem.findViewById(R.id.weight_fragment_status);

        weight.setText(weightStore.get(position).getWeight()+" Kg.");
        date.setText(weightStore.get(position).getDate());

        status.setText(weightStore.get(position).getStatus());
        statusColor(weightStore.get(position).getStatus());

        return weightItem;
    }


    private void statusColor(String statusStr){
        if(statusStr.equals("Up")){
            status.setTextColor(Color.parseColor("#FB3353"));
        }else if(statusStr.equals("Down")){
            status.setTextColor(Color.parseColor("#33000000"));
        }else{
            status.setTextColor(Color.parseColor("#33000000"));

        }
    }
}
