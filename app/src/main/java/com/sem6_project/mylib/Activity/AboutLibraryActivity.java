package com.sem6_project.mylib.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.sem6_project.mylib.R;

import org.w3c.dom.Text;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class AboutLibraryActivity extends AppCompatActivity {

    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_library);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("About Library");

        ImageView iv_backarrow = findViewById(R.id.iv_backarrow);
        iv_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tv_about = findViewById(R.id.tv_about);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_about.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        v_flipper =findViewById(R.id.v_flipper);

        int images[] = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3};

        for(int i:images)
        {
            flipperImage(i);
        }
    }

    public void flipperImage(int image )
    {
        ImageView imageView = new ImageView(this);

        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);


        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);

    }

}