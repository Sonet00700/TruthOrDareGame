package com.sonetsagortruthordaregame.sagor.truthordare;

import android.content.ActivityNotFoundException;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.Random;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class WelcomeActivity extends AppCompatActivity {


    private static String PKG_NAME;
    private ImageView img;
    private int lastDir;
    private boolean spinning;
    int position1, position2;
    RelativeLayout img2;
    int[] imagebottle = {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5,
            R.drawable.b6, R.drawable.b7, R.drawable.b8, R.drawable.b10};
    int[] imagebg = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4, R.drawable.bg5,
            R.drawable.bg6, R.drawable.bg7, R.drawable.bg8, R.drawable.bg9, R.drawable.bg10,
            R.drawable.bg11, R.drawable.bg12};
    private Random random = new Random();
    MediaPlayer player=null;
    int c = 0;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        PKG_NAME = BuildConfig.APPLICATION_ID;
        img = findViewById(R.id.bottle_id);
        img2 = findViewById(R.id.background_id);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!spinning) {
                    int newDir = random.nextInt(36000);
                    //image ta k middle a fixed rekhe ghuranor jonno
                    float pivotX = img.getWidth() / 2;
                    float pivotY = img.getHeight() / 2;
                    Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);

                    rotate.setDuration(2500);
                    rotate.setFillAfter(true);
                    rotate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            spinning = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            spinning = false;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    lastDir = newDir;
                    img.startAnimation(rotate);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.background_change:
                Intent i = new Intent(WelcomeActivity.this, BackgroundActivity.class);
                startActivityForResult(i, 111);
                return true;
            case R.id.bottle_change:
                Intent i2 = new Intent(WelcomeActivity.this, BottleActivity.class);
                startActivityForResult(i2, 222);
                return true;
            case R.id.about_us:
                Intent i3 = new Intent(WelcomeActivity.this, AboutUsActivity.class);
                startActivity(i3);
                return true;
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String value = "https://play.google.com/store/apps/details?id=" + PKG_NAME;
                intent.putExtra(Intent.EXTRA_TEXT, value);
                startActivity(Intent.createChooser(intent, "Share Via "));
                return true;
            case R.id.rating:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + PKG_NAME)));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + PKG_NAME)));
                }
                return true;
            case R.id.others_app:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Md.+Nayeem+Sagor")));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Md.+Nayeem+Sagor")));
                }
                return true;
            case R.id.update:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + PKG_NAME)));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + PKG_NAME)));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == RESULT_OK) {
            position2 = data.getIntExtra("imagebackground", 0);
            img2.setBackground(ContextCompat.getDrawable(getApplicationContext(), imagebg[position2]));
        }
        if (requestCode == 222 && resultCode == RESULT_OK) {
            position1 = data.getIntExtra("imagebottle", 0);
            img.setImageResource(imagebottle[position1]);
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do You want to Exit?");
        builder.setTitle("EXIT");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.exit_icon);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WelcomeActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}