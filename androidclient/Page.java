package com.example.seethar.dragon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Group22 on 3/22/2017.
 */

public class Page extends Activity implements View.OnClickListener {


    EditText ef1,ef2;
    Button bf1;
    Intent I;
    @Override

    //setting the Layout
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        ef1=(EditText)findViewById(R.id.ef1);
        ef2=(EditText)findViewById(R.id.ef2);
        bf1=(Button)findViewById(R.id.bf1);
        bf1.setOnClickListener(this);
    }

// This onclick handler contains the code to start another activity from the landing page
    @Override
    public void onClick(View v) {

//creating an Intent and passing the ipaddress and port number from this activity to second Activity.
        I=new Intent("com.example.seethar.dragon.MAINACTIVITY");
        I.putExtra("ip",ef1.getText().toString());
        I.putExtra("port",ef2.getText().toString());
        startActivity(I);

    }
}
