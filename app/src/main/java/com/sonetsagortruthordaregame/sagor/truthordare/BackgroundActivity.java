package com.sonetsagortruthordaregame.sagor.truthordare;

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

public class BackgroundActivity extends AppCompatActivity {
    ListView listViewbg;
    int[] imagebg={R.drawable.bg1,R.drawable.bg2,R.drawable.bg3,R.drawable.bg4,R.drawable.bg5,
            R.drawable.bg6,R.drawable.bg7,R.drawable.bg8,R.drawable.bg9,R.drawable.bg10,
            R.drawable.bg11,R.drawable.bg12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        listViewbg=findViewById(R.id.background_listview_id);

        //creating instance of class MyAdapter
        CustomAdapter customAdapter=new CustomAdapter();

//set adapter to list
        listViewbg.setAdapter(customAdapter);

//set onclicklistener to listview
      listViewbg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent=new Intent();
              intent.putExtra("imagebackground",position);
              setResult(RESULT_OK,intent);
              finish();
          }
      });

    }


    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return imagebg.length;
        }

        @Override
        public Object getItem(int position) {
            return imagebg[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){
                convertView = LayoutInflater.from(BackgroundActivity.this).inflate(R.layout.background_change_activity,parent,false);
            }
            ImageView im=convertView.findViewById(R.id.image_id_background);
            im.setImageResource(imagebg[position]);
            return convertView;
        }
    }

}