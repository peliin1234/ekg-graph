package com.example.realtimegraph;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SparseArray<?> test=new SparseArray<>();
    float[] y1={-12796, -5088, 4179, 12475, 19350, 25224, 29317, 30638, 29813, 28171,26095, 23703,21981, 21589, 21501, 20537, 19350, 19168, 19407, 18597, 17071, 16631, 17621, 18271, 17635,16659, 16202, 15524, 13732, 11347, 9316, 7585, 5728, 4004, 2726, 1403, -241, -1569, -1826, -1837, -2548, -3288, -2889, -1902, -1869, -2860, -3528, -3478, -3732, -4682, -5485, -5716,-5914, -6254, -6182, -5803, -5965, -6529,
            -6316, -5279, -4934, -5841, -6495, -5848, -5171,-5902, -7185, -7305, -6399, -5967, -6302, -6386, -5848, -5382, -5352, -5408, -5431, -5518, -5233, -4085,-2600, -1668, -1012, 322, 2049, 2735, 1989, 1091, 966, 942, 155, -908, -1211, -735, -269, -218, -345,-388, -365, -382, -620, -1249, -2119, -2867, -3429, -4166, -5156, -5780, -5392, -3931, -1708, 1152, 4609,8277, 11502, 13602,
            14067, 12733, 9917, 6269, 2271, -1725, -5112, -7147, -7634, -7069, -5975, -4492, -2809, -1395, -514, 61, 543, 791, 824, 1059, 1672, 2217, 2316, 2323, 2674, 3077, 2982, 2488, 2157, 2045, 1703, 986, 259, -236, -616, -920, -1011, -960, -984, -1031, -877, -678, -852, -1338, -1573, -1331, -1074, -1132, -1157, -739, -131, 132, -40, -297, -474, -738, -1215, -1800,
            -2297, -2595, -2634, -2500, -2397, -2378, -2310, -2110, -1982, -2150, -2487, -2697, -2689, -2655, -2726, -2806, -2779, -2665, -2588, -2664, -2878, -3120, -3295, -3477, -3820, -4305, -4725, -4934, -5007, -5026, -4863, -4402, -3747, -3094, -2548, -2133, -1943, -2041, -2306, -2565, -2768, -2928, -2894, -2493, -1815, -1096, -414, 337, 1154, 1836, 2279, 2571, 2768, 2822, 2691, 2455, 2200, 1875,
            1494, 1155, 893, 586, 206, -16, 116, 592, 1298, 2246, 3440, 4621, 5336, 5241, 4346, 2880, 1185, -214, -702, 253, 2905, 7100, 12243, 17379, 21485, 23793, 23882, 21694, 17576, 12329, 7004, 2534, -519, -2003, -2054, -993, 709, 2548, 4111, 5227, 5945, 6412, 6714, 6805, 6655, 6324, 5827, 5112, 4158, 3096, 2087, 1209, 437, -292, -1024, -1834, -2771, -3767,
            -4716, -5539, -6183, -6571, -6664, -6612, -6615, -6748, -7023, -7432, -8004, -8638, -9165, -9525, -9747, -9893, -9930, -9882, -9856, -9885, -9912, -9877, -9848, -9888, -9956, -10023, -10112, -10284, -10510, -10699, -10827, -10930, -11047, -11182, -11338, -11546, -11758, -11886, -11864, -11636, -11149, -10381, -9428, -8476, -7634, -6940, -6415, -6072, -5854, -5585, -5115, -4378, -3426, -2354, -1251, -235, 582, 1132,
            1408, 1502, 1559, 1714, 2019, 2494, 3155, 3976, 4883, 5759, 6520, 7151, 7660, 8052, 8337, 8544, 8727, 8918, 9168, 9499, 9895, 10323, 10754, 11153, 11426, 11485, 11304, 10919, 10394, 9740, 9004, 8269, 7577, 6943, 6354, 5863, 5532, 5378, 5399, 5562, 5771, 5839, 5546, 4773, 3527, 1974, 452, -557, -513, 955, 3944, 8210, 13176, 18002, 21734, 23583, 23097, 20239};


    private LineGraphSeries<DataPoint> series;
    //Viewport viewport;
    private int lastX=0;
    GraphView graphview;
    ImageView vieww;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vieww=findViewById(R.id.imageView);
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        int w=400,h=500;
        Bitmap img = Bitmap.createBitmap(w, h, conf);
        Canvas c=new Canvas(img);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        lastX=0;
        Path p=new Path();
        for (int i = 0; i < y1.length-1; i++) {
            p.lineTo(lastX,y1[i]/500);
            c.drawPath(p,paint);
            lastX+=10;
        }
        vieww.setImageBitmap(img);
    }

    public void saveImg(){
        Bitmap img = null;
        graphview.setDrawingCacheEnabled(true);
        //vieww.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED));
        // grafigi kaydederken yüksekliğini azaltıyoruz cünkü birlestirdigimiz görsel boyutu daha kücük olmalı
        img = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        //Bitmap.createBitmap(img,10,0,img.getWidth()-10,img.getHeight());
        Canvas c=new Canvas(img);
        graphview.draw(c);

        FileOutputStream outputStream=null;
        String fileName =
                String.valueOf(System.currentTimeMillis()).replaceAll(":", ".") + ".jpg";
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(directory.toString());
        myDir.mkdirs();
        String filename=String.format(fileName);
        String fname = filename;
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("first try",e.toString());
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            img.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("second try",e.toString());
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }
}