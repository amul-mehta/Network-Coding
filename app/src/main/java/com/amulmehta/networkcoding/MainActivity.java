package com.amulmehta.networkcoding;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaActionSound;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import android.util.Log;


public class MainActivity extends Activity {
    String msg = "Android : ";
    Button listBtn;
    private BluetoothAdapter myBluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;
    private ListView myListView;
    private ArrayAdapter<String> BTArrayAdapter;
    private String btname;
    private String name;
    private String btadd;
    private OutputStream outputStream;
    private InputStream inStream;
    private UUID appUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothServerSocket mmServerSocket;
    private BluetoothSocket mmSocket;
    private BluetoothSocket sock;
    private BluetoothDevice mmDevice;
    private BluetoothSocket mmSSocket;
    BluetoothDevice myd;
    BluetoothDevice device;
    ConnectedThread ct2;
    ConnectedThread ct1;
    protected static final String TAG = "Network Coding";
    public String rec1;
    public static String receive;
    private ListView mConversationView;
    private ArrayAdapter<String> mConversationArrayAdapter;
    public static int Bnchk;
    public static String n1;
    public static String n2;
    public static String r1;
    public static String f1;

    public static int flg;
//------------------------------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(msg, "The onCreate() event");
        name = "Network Coding";
        Toast.makeText(this, "Application Started", Toast.LENGTH_LONG).show();
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mConversationArrayAdapter = new ArrayAdapter<String>(this,
                R.layout.message);
        mConversationView = (ListView) findViewById(R.id.in);
        mConversationView.setAdapter(mConversationArrayAdapter);
        MainActivity.flg=0;
        //listBtn = (Button) findViewById(R.id.button);
                // TODO Auto-generated method stub
                client();

    }

    public void client() {
        // get paired devices
        pairedDevices = myBluetoothAdapter.getBondedDevices();

        // put it's one to the adapter
        for (BluetoothDevice device : pairedDevices) {
            btname = device.getName();
            btadd = device.getAddress();
            myd = device;
        }

    /*public static void DisplayStr(String ss){

       /// Toast.makeText(, ss, Toast.LENGTH_SHORT).show();

    }*/
/*        BluetoothSocket tmp = null;
        mmDevice = myd;

        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = myd.createRfcommSocketToServiceRecord(appUUID);

        }

        catch (IOException e) {
        }
        mmSocket = tmp;
        myBluetoothAdapter.cancelDiscovery();

        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            mmSocket.connect();
            Toast.makeText(getApplicationContext(),"Connected",
                    Toast.LENGTH_SHORT).show();
            sock=mmSocket;
        }
        catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            }
            catch (IOException closeException) {
            }
        }*/

    }
   /* public void Sendmsg(View v){
       try{
        outputStream = mmSSocket.getOutputStream();
        EditText ListB = (EditText) findViewById(R.id.editText);
        String s = ListB.getText().toString();
        outputStream.write(s.getBytes());
               Log.v(TAG,s);
           Toast.makeText(getApplicationContext(),"Message Sent",
                   Toast.LENGTH_SHORT).show();

       }
       catch (IOException ioe){}



}
*/
@Override
  public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
   /* public void OnServ(View v)
    {
        Toast.makeText(getApplicationContext(), "Server", Toast.LENGTH_SHORT).show();

        AcceptThread ath = new AcceptThread();
        ath.start();


    }
    public void OnClient(View v) {
        Toast.makeText(getApplicationContext(), "Client", Toast.LENGTH_SHORT).show();

        ConnectThread cth = new ConnectThread(myd);
        cth.start();


    }*/
        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Created By:\n"+"Amul.Mehta         12BCE043\n"+
                                               "Ria.Mirchandani  12BCE047\n"+
                                               "Jayeta.Matreja    12BCE061\n\nImplementing Network Coding On a ButterFly Network" );
            builder.setTitle("About Us");
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    dialog.dismiss();
                }
            });
            //   to_bina(result1.getBytes());
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void DispMsg(View V) {
        Toast.makeText(getApplicationContext(), MainActivity.receive, Toast.LENGTH_SHORT).show();
        Log.v(TAG, MainActivity.receive);

        mConversationArrayAdapter.add(MainActivity.receive);
        if (MainActivity.Bnchk == 31) {
            if (MainActivity.flg == 0) {
                MainActivity.n1 = MainActivity.receive;
                Log.v(TAG, MainActivity.n1);
                Log.v(TAG, MainActivity.receive);
                MainActivity.flg = 1;
            } else if (MainActivity.flg == 1) {
                MainActivity.n2 = MainActivity.receive;
                Log.v(TAG, MainActivity.n2);
                Log.v(TAG, MainActivity.receive);
                MainActivity.flg = 0;
            }
        }
        else if (MainActivity.Bnchk == 41){
                MainActivity.r1 = MainActivity.receive;
                Log.v(TAG, MainActivity.r1);
                Log.v(TAG,"HELLO WORLD");
                Log.v(TAG, MainActivity.receive);

            }

        }

    public void OnOne (View v){
        MainActivity.Bnchk=1;
        ConnectThread cth = new ConnectThread(myd);
        cth.start();


    }
    public void OnTwo (View v){
        MainActivity.Bnchk=2;
        ConnectThread cth = new ConnectThread(myd);
        cth.start();


    }

    public void OnThreeS (View v){
        MainActivity.Bnchk=31;
        AcceptThread ath = new AcceptThread();
        ath.start();


    }
    public void OnThreeC (View v){
        MainActivity.Bnchk=32;
        ConnectThread cth = new ConnectThread(myd);
        cth.start();

    }

    public void OnFourC (View v){
        MainActivity.Bnchk=42;
        ConnectThread cth = new ConnectThread(myd);
        cth.start();


    }

    public void OnFourS (View v){
        MainActivity.Bnchk=41;
        AcceptThread ath = new AcceptThread();
        ath.start();


    }

    public void OnFive (View v){
        MainActivity.Bnchk=5;
        AcceptThread ath = new AcceptThread();
        ath.start();




    }
    public void OnSix (View v){
        MainActivity.Bnchk=6;
        AcceptThread ath = new AcceptThread();
        ath.start();


    }

    /*  public void OnServ(View v)
      {
          String st="NetworkCoding";
          BluetoothServerSocket tmp = null;
          try {
              tmp=myBluetoothAdapter.listenUsingRfcommWithServiceRecord(st, appUUID);
              mmServerSocket = tmp;
              BluetoothSocket socket = null;
              // Keep listening until exception occurs or a socket is returned
              while (true) {
                  try {
                      socket = mmServerSocket.accept();
                  } catch (IOException e) {
                      break;
                  }
                  // If a connection was accepted
                  if (socket != null) {
                      // Do work to manage the connection (in a separate thread)
                    //  manageConnectedSocket(socket);
                      mmSSocket=socket;
                      mmServerSocket.close();
                      Toast.makeText(getApplicationContext(),"Connected",
                             Toast.LENGTH_SHORT).show();
                      //recv();

                      break;
                  }
              }
          }
          catch (IOException ioe){


          }

      }
      public void recvmsg(View v)
      {
          final int BUFFER_SIZE = 1024;
          byte[] buffer = new byte[BUFFER_SIZE];
          int bytes = 0;
          int b = BUFFER_SIZE;

          while (true) {
              try {
                  inStream = mmSSocket.getInputStream();
                  //bytes = inStream.read(buffer, bytes, BUFFER_SIZE - bytes);
                  bytes = inStream.read(buffer);

                  String readMessage = new String(buffer, 0, bytes);
                  Log.i(TAG,"Yo Dude");
               //   Toast.makeText(getApplicationContext(),readMessage,Toast.LENGTH_SHORT).show();

              } catch (IOException e) {
                  e.printStackTrace();
              }
          }



      }*/
    public class ConnectThread extends Thread {

        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(appUUID);
            } catch (IOException e) {
            }
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            myBluetoothAdapter.cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mmSocket.connect();
              //  Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                }
                return;
            }

            // Do work to manage the connection (in a separate thread)
            ct1 = new ConnectedThread(mmSocket);
            ct1.start();
        }

        /**
         * Will cancel an in-progress connection, and close the socket
         */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }

    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;
        public String st;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = myBluetoothAdapter.listenUsingRfcommWithServiceRecord(name, appUUID);
            } catch (IOException e) {
            }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket.accept();
//                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    ct2 = new ConnectedThread(socket);
                    ct2.start();
                    st=ct2.readMessage;

                    try {
                        mmServerSocket.close();
                        break;
                    } catch (IOException ioe) {
                    }
                }
            }
        }

        /**
         * Will cancel the listening socket, and cause the thread to finish
         */
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) {
            }
        }
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private MainActivity parent;
        public String readMessage;
        public int a;
        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            this.parent = parent;
            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    // mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                    //      .sendToTarget();
                    readMessage = new String(buffer, 0, bytes);
                    Log.v(TAG,readMessage);
                    MainActivity.receive=readMessage;

      //              displayText(readMessage);

                    }
                catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] out) {
            try {
                mmOutStream.write(out);
                displayWrite();
            } catch (IOException e) {
            }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }

    public void displayText(String s1) {
        Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_SHORT).show();

    }

    public void Sendmsg(View v) {

        if(MainActivity.Bnchk == 1 || MainActivity.Bnchk == 2){
        EditText ListB = (EditText) findViewById(R.id.editText);
        String s = ListB.getText().toString();
        byte[] tosend=s.getBytes();
            ct1.write(tosend);
        }
        else if (MainActivity.Bnchk == 32) {
            String s=Net_Code(MainActivity.n1,MainActivity.n2);
            MainActivity.r1=s;
            byte[] tosend=s.getBytes();
            ct1.write(tosend);

        }
        else if (MainActivity.Bnchk == 42){
            String s=MainActivity.r1;
            byte[] tosend=s.getBytes();
            ct1.write(tosend);

        }

        //ConnectedThread r=ct1;
        // Synchronize a copy of the ConnectedThread

        // Perform the write unsynchronized

    }
    public String Net_Code(String s1, String s2)
    {
        StringBuilder sb = new StringBuilder();
        int one=s1.length();
        int two=s2.length();
        Log.v(TAG,"Length :"+one + "   "+ two);
        int len;
        if(one > two )
        {
            Log.v(TAG,"Loop 1");

            int i;
            len=two;
            for(i = 0; i <len; i++)
                sb.append((char)(s1.charAt(i) ^ s2.charAt(i)));
            //Log(i);
            for(int j=i;j<one;j++)
            {
                sb.append((char)s1.charAt(j));
            }


        }
        else if(one < two )
        {
            Log.v(TAG,"Loop 2");
            int i;
            len=one;
            for(i = 0; i <len; i++)
                sb.append((char)(s1.charAt(i) ^ s2.charAt(i)));
            //Log(i);
            for(int j=i;j<two;j++)
            {
                sb.append((char)s2.charAt(j));
            }


        }
        else{
            Log.v(TAG,"Loop 3");
            int i;
            len=one;
            for(i = 0; i <len; i++)
                sb.append((char)(s1.charAt(i) ^ s2.charAt(i)));
            //Log(i);

        }

        Log.v(TAG,"RESULT OF ENCODING :");

        String result = sb.toString();
        Log.v(TAG,result);
       // to_bina(result.getBytes());
        return result;
    }

    public void displayWrite() {
      //  Toast.makeText(getApplicationContext(), "Hello World", Toast.LENGTH_SHORT).show();
        EditText ListB = (EditText) findViewById(R.id.editText);
        String s = ListB.getText().toString();
        String s1="Message Sent :";
        s=s1+s;
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

    }
public void OnDecode(View v){
    if(MainActivity.Bnchk==5){
        Net_Decode(MainActivity.r1,MainActivity.n1);

    }
    else if (MainActivity.Bnchk == 6){
        Net_Decode(MainActivity.r1, MainActivity.n2);

    }
}
    public void Net_Decode(String result,String s1){
        Log.v(TAG,"RESULT OF DECODING  1 :");
        int len,one,two;
        StringBuilder res2 = new StringBuilder();
        String d1,d2;
        d1=s1;
        d2=result;
        one=d1.length();
        two=d2.length();
        String K=Integer.toString(one);
        String L=Integer.toString(two);
        String R1=K+ "   "+ L;
        Log.v(TAG,R1);
        if(one > two)
        {
            Log.v(TAG, "Loop 1");
            len=two;
            int i;
            for(i = 0; i <len; i++)
                res2.append((char)(d1.charAt(i) ^ d2.charAt(i)));
            for(int j=i;j<one;j++)
            {
                res2.append((char)d1.charAt(j));
            }
        }
        else if (two > one)
        {
            Log.v(TAG, "Loop 2");
            len=one;
            int i;
            for(i = 0; i <len; i++)
                res2.append((char)(d1.charAt(i) ^ d2.charAt(i)));
            for(int j=i;j<two;j++)
            {
                res2.append((char)d2.charAt(j));
            }

        }
        else{
            Log.v(TAG, "Loop 3");
            len=one;
            int i;
            for(i = 0; i <len; i++)
                res2.append((char)(d1.charAt(i) ^ d2.charAt(i)));

        }
        String result1 = res2.toString();
        Log.v(TAG,result1);
        Toast.makeText(getApplicationContext(), (result1+" "+s1), Toast.LENGTH_SHORT).show();

       AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((result1 +"  " +s1));
                builder.setTitle("Resultant Message");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.dismiss();
            }
        });
        //   to_bina(result1.getBytes());
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}

//*/