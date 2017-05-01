
/*

written by Project Group22
 Activity which receives  and sends the message
 */



package com.example.seethar.dragon;

import android.app.Notification;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Thread t = null;
    TextView t1, t2, t3, t4, t5;
    Socket cs;
    DataInputStream in;
    PrintWriter pr;
    int i = 0;
    EditText et1;
    Button bt;
    String st, rt, gt = "";
    String ip;
    int port;
    Thread at = null;
    TextView sw;
    Handler updatetexthandler;
    Bundle b;
/*

   Setting the layout
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView) findViewById(R.id.tv1);
        t2 = (TextView) findViewById(R.id.tv2);
        t3 = (TextView) findViewById(R.id.tv3);
        t4 = (TextView) findViewById(R.id.tv4);
        t5 = (TextView) findViewById(R.id.tv5);
        et1 = (EditText) findViewById(R.id.et);
        bt = (Button) findViewById(R.id.bt1);
        b = getIntent().getExtras();
        ip = b.getString("ip");
        port = Integer.parseInt(b.getString("port"));

        t = new Thread(new clientConnect());// seperate thread is used to achieve socket connection.
        t.start();
        bt.setOnClickListener(this); // Adding onclick listener to button bt
    }


/*
   onclick event handler which sends the text typed in the text box to the server on clicking the button bt.
   Printwriter is used to write the message to server OutputStream Buffer.
 */

    public void onClick(View v) {

        rt = et1.getText().toString();
        t5.setText("android:" + rt);

        try {
            pr = new PrintWriter(cs.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        t5.setVisibility(View.VISIBLE);
        pr.println(rt);
        et1.setText("");
        pr.flush();
    }
    /*
          This function will close the socket connection,  when the activity gets stopped.
     */
    @Override
    protected void onStop() {
        super.onStop();
        try {
            cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*
        This function contains the code to connect the client to the server,
        This uses the Socket Object which takes server's ip address and portnumber as arguments.
        DataInputStream object is used to read data from the Server's InputStream Buffer.

 */
    public class clientConnect implements Runnable {


        public void run() {

            try {
                cs = new Socket(ip, port);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in = new DataInputStream(cs.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

               while(true)
            {
                try {
                    gt=in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Handler is used to update the message on UI thread from this thread.
                updatetexthandler.post(new Runnable() {
                    @Override
                    public void run() {
                            t1.setText(gt);
                    }
                });
            }



        }


    }
}





