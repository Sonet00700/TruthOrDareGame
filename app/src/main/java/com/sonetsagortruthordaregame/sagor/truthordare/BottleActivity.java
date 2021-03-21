package com.sonetsagortruthordaregame.sagor.truthordare;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class BottleActivity extends AppCompatActivity {
ListView listViewbottle;
ImageView bimg;
int[] imagebottle={R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,
        R.drawable.b6,R.drawable.b7,R.drawable.b8,R.drawable.b10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle);
        listViewbottle=findViewById(R.id.bottle_listview_id);
        bimg=findViewById(R.id.bottle_id);

        //creating instance of class MyAdapter
        CustomAdapter2 customAdapter2=new CustomAdapter2();

//set adapter to list
        listViewbottle.setAdapter(customAdapter2);

//set onclicklistener to listview
        listViewbottle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("imagebottle",position);
                setResult(RESULT_OK,intent);
                finish();
                            }
        });
    }

    class CustomAdapter2 extends BaseAdapter {
        @Override
        public int getCount() {
            return imagebottle.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutinflater= (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutinflater.inflate(R.layout.bottle_change_activity,parent,false);
            ImageView ima=view.findViewById(R.id.image_id_bottle);
            ima.setImageResource(imagebottle[position]);
            return view;
        }
    }
}
