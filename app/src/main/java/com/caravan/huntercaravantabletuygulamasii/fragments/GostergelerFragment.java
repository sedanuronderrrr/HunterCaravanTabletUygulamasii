package com.caravan.huntercaravantabletuygulamasii.fragments;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caravan.huntercaravantabletuygulamasii.MainActivity;
import com.caravan.huntercaravantabletuygulamasii.R;

import java.util.Timer;
import java.util.TimerTask;


public class GostergelerFragment extends Fragment {
    private Handler handler = new Handler();
    Thread Thread_refresh = null;
    ClipDrawable drawable,drawable1;
    TextView cl_water_txt,dt_water_txt,tin_txt, tout_txt,humidity_txt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gostergeler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView temizsuimage =  view.findViewById(R.id.temizsudolum1);
        ImageView atiksuimage = view.findViewById(R.id.atiksudolum);

        cl_water_txt=(TextView)view.findViewById(R.id.textView79);
        dt_water_txt=(TextView)view.findViewById(R.id.textView80);
        tin_txt=(TextView)view.findViewById(R.id.textView72);
        tout_txt=(TextView)view.findViewById(R.id.textView74);
        humidity_txt=(TextView)view.findViewById(R.id.textView77);

        drawable = (ClipDrawable) temizsuimage.getDrawable();
        drawable.setLevel(0);



        drawable1 = (ClipDrawable) atiksuimage.getDrawable();
        drawable1.setLevel(0);
        Thread_refresh = new Thread(new GostergelerFragment.refresh_Task());
        Thread_refresh.start();

    }


    class refresh_Task implements Runnable {
        public void run() {
            while(true) {
                Log.d("Refresh","Göstergeler");
                set_input_views();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void set_input_views()
    {
        handler.post(new Runnable() {
            public void run() {
                drawable.setLevel(MainActivity.cl_water_lvl*100);
                drawable1.setLevel(MainActivity.dt_water_lvl*100);
                cl_water_txt.setText(""+MainActivity.cl_water_lvl);
                dt_water_txt.setText(""+MainActivity.dt_water_lvl);
                String s = String.format("%.0f", (float)MainActivity.dht_temp/10);
                tin_txt.setText(s);
                s = String.format("%.0f", (float)MainActivity.t_out/100);
                tout_txt.setText(s);
                s = String.format("%.0f", (float)MainActivity.dht_humidty/1);
                humidity_txt.setText(s);
            }
        });
    }
}