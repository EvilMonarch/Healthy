package itkmitl.a59070016.healthy.Weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import itkmitl.a59070016.healthy.R;

import java.util.ArrayList;
import java.util.Comparator;

public class WeightFragment extends Fragment {
    private String uid;
    private FirebaseUser _user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<WeightStore> weightStore = new ArrayList<WeightStore>();

    private DocumentSnapshot doc;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.uid = _user.getUid();
        final ListView weightList = (ListView) getView().findViewById(R.id.weight_list);
        final WeightConfig weightConfig =  new WeightConfig(
                getActivity(),
                R.layout.fragment_weightitems,
                weightStore
        );

        Button addWeightButton = (Button) getView().findViewById(R.id.add_weight_button);
        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightForm()).addToBackStack(null).commit();
            }
        });
        weightList.setAdapter(weightConfig);
        weightConfig.clear();


        db.collection("myfitness").document(uid).collection("weight").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        weightStore.add(document.toObject(WeightStore.class));
                    }
                }

                weightStore = checkStatus(weightStore);




                weightConfig.sort(new Comparator<WeightStore>() {
                    @Override
                    public int compare(WeightStore o1, WeightStore o2) {
                        return o2.getDate().compareTo(o1.getDate());
                    }
                });

                updateStatusToFirestore(weightStore);
                weightConfig.notifyDataSetChanged();
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight,container,false);
    }

    private ArrayList<WeightStore> checkStatus(ArrayList<WeightStore> weightStore) {
        this.uid = _user.getUid();

        for (int i = 1; i < weightStore.size(); i++) {
            if (weightStore.get(i - 1).getWeight() > weightStore.get(i).getWeight()) {
                weightStore.get(i).setStatus("Down");
            }else if(weightStore.get(i - 1).getWeight() == weightStore.get(i).getWeight()){
                weightStore.get(i).setStatus("-");
            } else {
                weightStore.get(i).setStatus("Up");
            }
        }
        return weightStore;
    }

    private void  updateStatusToFirestore(ArrayList<WeightStore> weightStore){
        uid = _user.getUid().toString();
        this.weightStore = weightStore;
        int index = 0;
        for(WeightStore items: this.weightStore ){
            db.collection("myfitness").document(uid).collection("weight").document(items.getDate()).set(this.weightStore.get(index++));
        }

    }

}
