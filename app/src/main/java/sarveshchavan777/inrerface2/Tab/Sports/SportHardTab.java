package sarveshchavan777.inrerface2.Tab.Sports;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;


import com.beardedhen.androidbootstrap.BootstrapProgressBar;

import java.util.ArrayList;
import java.util.List;

import sarveshchavan777.inrerface2.db.DemoHelperClass;
import sarveshchavan777.inrerface2.Activity.MainGameActivity;
import sarveshchavan777.inrerface2.R;
import sarveshchavan777.inrerface2.Adapter.SportsAdapter;


public class SportHardTab extends Fragment {
    GridView gridview;
    DemoHelperClass demoHelperClass;
    BootstrapProgressBar Progress;
    TextView progressText;

    public SportHardTab() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f_sp_hard, container, false);
        gridview = (GridView) v.findViewById(R.id.gridview);
        Progress = (BootstrapProgressBar) v.findViewById(R.id.Progress);
        progressText = (TextView) v.findViewById(R.id.progressText);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        demoHelperClass = new DemoHelperClass(getActivity().getApplicationContext());


        Integer imageView[] = new Integer[60];
        String s[] = new String[60];

       /* String per[] = {"Football", "Olympic", "Swimming", "Sports", "Cricket",
                "Chess", "Badminton", "Boxing", "Racing", "Athlete", "Baseball", "Tennis"};

        String s1[] = {per[0], per[1], per[10], per[0], per[3], per[1], per[1], per[1], per[1], per[0],
                per[4], per[3], per[3], per[1], per[3], per[0], per[11], per[11], per[1], per[3],
                per[0], per[4], per[3], per[4], per[0], per[3], per[4], per[9], per[9], per[3],
                per[7], per[3], per[0], per[0], per[3], per[0], per[0], per[0], per[0], per[0],
                per[2], per[0], per[0], per[0], per[7], per[3], per[8], per[9], per[4], per[11],
                per[6], per[11], per[0], per[1], per[3], per[3], per[3], per[5], per[3], per[3]};*/


        //Adding the images to Int array
        for (int i = 0; i < imageView.length; i++) {
            imageView[i] = R.drawable.transparent;
        }

        //Adding the text to String array
        for (int i = 0; i < s.length; i++) {
            s[i] = String.valueOf(i + 1);
        }


        //setting the image correct for correct answer and adding value  into list
        List<Integer> list = new ArrayList<>();
        List xyz = demoHelperClass.GetQid();
        if (xyz != null) {
            for (int i = 0; i < xyz.size(); i++) {
                int x = (Integer) xyz.get(i);
                if (x >= 300 && x < 360) {
                    imageView[x - 300] = R.drawable.correctcartoon;
                    list.add(1);
                }
            }
        }


        //setting the progress bar as per the size of list
        Progress.setProgress(list.size());
        progressText.setText(list.size() + "/" + 60);
        list.clear();

        //setting adapter
        SportsAdapter adapter = new SportsAdapter(getActivity(), imageView, s/*, s1*/);
        gridview.setAdapter(adapter);

        //OnClick Listner
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent intent = new Intent(getActivity().getApplicationContext(), MainGameActivity.class);
                intent.putExtra("Key", Integer.toString(position + 300));
                startActivity(intent);
                getActivity().finish();

                /*Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Progress = null;
        demoHelperClass=null;
        gridview.setOnItemClickListener(null);
    }
}
