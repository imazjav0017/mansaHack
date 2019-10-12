package com.mansastudios.mansahack;



import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ConsumerIrManager cm;
    SparseArray<String> irData;
    String profileName,profileId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cm=(ConsumerIrManager)getSystemService(Context.CONSUMER_IR_SERVICE);
        profileName=getIntent().getStringExtra("profileName");
        profileId=getIntent().getStringExtra("profileId");
        setTitle(profileName);
    }
    public void transmitOn(View view) {
        if(!cm.hasIrEmitter())
        {
            Toast.makeText(getApplicationContext(), "No Emmitter", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();
            int[] pattern = {
                    2594, 938, 410, 474, 406, 474, 410, 910,
                    410, 914, 858, 470, 410, 474, 410, 470,
                    410, 474, 410, 474, 406, 474, 410, 478,
                    406, 474, 410, 470, 414, 470, 410, 474,
                    410, 470, 410, 474, 410, 474, 410, 474,
                    406, 474, 854, 470, 410, 910, 410, 474,
                    410, 1000};
            // transmit the pattern at 38.4KHz
            cm.transmit(38400, pattern);
            Log.i("Pattern",""+ Arrays.toString(pattern));
        }
    }
    public void channelUp(View view) {
        int[] pattern = {
                2594, 934, 406, 470, 410, 474, 406, 906,
                410, 910, 878, 442, 414, 470, 406, 474,
                430, 446, 410, 474, 406, 470, 410, 470,
                406, 470, 410, 470, 410, 470, 410, 470,
                410, 470, 434, 446, 434, 442, 854, 902,
                410, 470, 410, 470, 406, 474, 406, 470,
                410, 1000};
        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }


    public void transmit1(View view) {
        int[] pattern = {
                2670, 886, 454, 346, 538, 346, 526, 890,
                430, 922, 838, 322, 558, 350, 530, 418,
                462, 350, 530, 346, 530, 414, 466, 414,
                466, 322, 554, 418, 462, 414, 466, 482,
                394, 362, 518, 334, 546, 430, 446, 414,
                470, 418, 458, 414, 466, 318, 562, 418,
                902, 1000};

        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void transmit2(View view) {
        int[] pattern = {
                2646, 906, 442, 314, 562, 438, 442, 850,
                466, 850, 906, 326, 554, 418, 462, 390,
                490, 346, 534, 410, 466, 410, 474, 390,
                486, 382, 498, 354, 522, 390, 494, 382,
                494, 334, 546, 382, 498, 386, 490, 390,
                490, 362, 518, 414, 466, 390, 930, 878,
                434, 1000};


        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void transmit3(View view) {
        int[] pattern = {
                2562, 962, 466, 402, 474, 418, 382, 934,
                462, 858, 822, 538, 342, 498, 382, 538,
                338, 538, 342, 538, 342, 502, 378, 534,
                342, 502, 378, 498, 382, 538, 342, 534,
                342, 538, 342, 538, 342, 538, 342, 534,
                342, 538, 342, 538, 342, 538, 782, 534,
                342, 1000};


        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void transmit4(View view) {
        int[] pattern = {
                2566, 958, 390, 386, 494, 382, 494, 866,
                450, 870, 894, 386, 494, 322, 554, 350,
                530, 378, 502, 390, 490, 318, 562, 418,
                458, 446, 434, 438, 442, 418, 458, 446,
                514, 386, 414, 350, 530, 350, 530, 346,
                530, 298, 582, 346, 970, 926, 390, 354,
                526, 1000};

        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void transmit5(View view) {
        int[] pattern = {
                2634, 954, 390, 446, 438, 414, 462, 922,
                394, 870, 894, 366, 510, 418, 462, 486,
                394, 426, 450, 430, 454, 414, 462, 418,
                462, 418, 462, 450, 426, 438, 442, 434,
                446, 414, 462, 418, 466, 414, 462, 486,
                394, 434, 446, 418, 902, 918, 834, 1000};

        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void transmit6(View view) {
        int[] pattern = {
                2602, 890, 458, 290, 590, 414, 466, 850,
                466, 862, 898, 318, 562, 350, 530, 314,
                566, 414, 462, 350, 530, 318, 558, 410,
                470, 362, 518, 346, 534, 346, 530, 362,
                518, 318, 562, 318, 562, 314, 562, 346,
                534, 346, 530, 298, 1030, 318, 554, 854,
                462, 1000};

        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void transmit7(View view) {
        int[] pattern = {
                2638, 886, 458, 350, 526, 362, 518, 858,
                458, 866, 898, 350, 526, 322, 558, 350,
                526, 378, 502, 358, 522, 350, 530, 346,
                534, 370, 510, 414, 462, 354, 526, 422,
                458, 418, 462, 366, 510, 418, 462, 414,
                462, 370, 510, 358, 966, 322, 550, 350,
                530, 1000};

        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void transmit8(View view) {
        int[] pattern = {
                2638, 886, 458, 454, 422, 418, 462, 862,
                458, 862, 898, 426, 454, 418, 458, 414,
                466, 354, 526, 410, 466, 418, 466, 342,
                534, 366, 514, 414, 466, 358, 518, 414,
                466, 350, 530, 422, 454, 442, 438, 414,
                466, 418, 902, 858, 458, 414, 462, 414,
                466, 1000};

        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void transmit9(View view) {
        int[] pattern = {
                2666, 886, 458, 350, 606, 314, 486, 834,
                486, 834, 926, 366, 514, 434, 442, 350,
                530, 374, 506, 454, 426, 446, 430, 354,
                526, 382, 498, 422, 458, 370, 510, 418,
                458, 382, 498, 374, 506, 374, 506, 366,
                510, 378, 946, 858, 454, 434, 886, 1000};


        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }

    public void transmit0(View view) {
        int[] pattern =  {
                2638, 886, 462, 390, 486, 346, 534, 886,
                430, 862, 898, 342, 542, 394, 482, 406,
                474, 342, 538, 342, 538, 346, 534, 334,
                542, 398, 482, 346, 534, 406, 474, 334,
                542, 402, 478, 398, 482, 406, 474, 334,
                542, 402, 478, 342, 538, 338, 542, 402,
                478, 394, 482, 1000};


        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void channelDown(View view) {
        int[] pattern =  {
                2646, 878, 458, 350, 530, 442, 442, 854,
                462, 862, 898, 418, 462, 418, 458, 378,
                502, 382, 498, 406, 470, 438, 446, 430,
                446, 410, 474, 414, 462, 438, 442, 418,
                462, 418, 458, 370, 510, 402, 922, 846,
                462, 438, 438, 414, 466, 422, 902, 1000};


        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void volumeUp(View view) {
        int[] pattern = {
                2642, 882, 462, 326, 550, 362, 518, 854,
                462, 862, 902, 402, 474, 318, 562, 370,
                506, 366, 518, 422, 454, 398, 478, 410,
                474, 430, 450, 326, 554, 338, 538, 342,
                538, 334, 546, 366, 514, 338, 538, 346,
                974, 854, 462, 354, 522, 366, 514, 338,
                542, 1000};


        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void volumeDown(View view) {
        int[] pattern = {
                2634, 862, 478, 346, 538, 350, 530, 830,
                486, 838, 922, 370, 510, 378, 502, 394,
                482, 346, 534, 370, 506, 442, 442, 346,
                530, 354, 526, 318, 562, 350, 530, 438,
                438, 354, 526, 362, 518, 370, 510, 354,
                966, 854, 458, 346, 534, 370, 1026, 1000};


        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void transmitBack(View view) {
        int[] pattern = {
                2522, 1006, 346, 422, 454, 482, 402, 918,
                394, 978, 782, 538, 346, 482, 398, 482,
                398, 530, 350, 486, 386, 490, 470, 426,
                378, 550, 318, 546, 346, 490, 390, 330,
                550, 482, 838, 938, 374, 486, 394, 530,
                346, 534, 346, 534, 790, 486, 386, 1000};


        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }
    public void transmitMute(View view) {
        int[] pattern = {
                2642, 882, 458, 410, 474, 330, 546, 858,
                458, 866, 898, 342, 538, 362, 518, 338,
                542, 406, 474, 398, 478, 334, 630, 314,
                482, 410, 470, 402, 478, 330, 550, 430,
                450, 334, 546, 398, 482, 370, 506, 422,
                458, 430, 894, 326, 550, 894, 862, 1000};


        // transmit the pattern at 38.4KHz
        cm.transmit(38000, pattern);
        Log.i("Pattern",""+ Arrays.toString(pattern));
    }








    protected String hex2dec(String irData) {
        List<String> list = new ArrayList<String>(Arrays.asList(irData
                .split(" ")));
        list.remove(0); // dummy
        int frequency = Integer.parseInt(list.remove(0), 16); // frequency
        list.remove(0); // seq1
        list.remove(0); // seq2

        for (int i = 0; i < list.size(); i++) {
            list.set(i, Integer.toString(Integer.parseInt(list.get(i), 16)));
        }

        frequency = (int) (1000000 / (frequency * 0.241246));
        list.add(0, Integer.toString(frequency));

        irData = "";
        for (String s : list) {
            irData += s + ",";
        }
        return irData;
    }
}


