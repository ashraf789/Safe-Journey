package net.a6te.lazycoder.savejourney.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import net.a6te.lazycoder.savejourney.R;
import net.a6te.lazycoder.savejourney.database.EmergencyDatabase;
import net.a6te.lazycoder.savejourney.database.UserDatabase;
import net.a6te.lazycoder.savejourney.modelClass.Emergency;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEmergency extends Fragment {


    private final String TAG = "SafeJourney";

    private Button emergencyBtn;
    private View v;
    private ArrayList< Emergency> emergencyNumbers;
    private String location = "Suban Bag,Near Prince Plaza";

    private String message;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_fragment_emergency, container, false);
        emergencyBtn = (Button) v.findViewById(R.id.emergencyBtn);
        emergencyNumbers = new ArrayList<Emergency>();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendSms sms= new SendSms();
                sms.execute();
            }
        });
    }


    private UserDatabase userDatabase = new UserDatabase(getActivity());
    class SendSms extends AsyncTask<Void,Void,Boolean>{

        @Override
        protected Boolean doInBackground(Void... params) {

            try{

                EmergencyDatabase database = new EmergencyDatabase(getActivity());
                emergencyNumbers = database.getEmergencyData("Mirpur","Police");
                if (!emergencyNumbers.get(0).getPhoneNo() .equals(null))
                   return true;
                else return false;
            }catch (Exception e){
                Log.d("SafeJourney", "onPostExecute: ------------- database error"+e);
                return false;

            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (!aBoolean){
                return;
            }
            Log.d("SafeJourney", "onPostExecute: ----------------------"+emergencyNumbers.get(0).getPhoneNo());
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.SEND_SMS)) {

                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.SEND_SMS}, 1);
                }
            }else {
                sendSMSMessage();
            }
        }
    }

    protected void sendSMSMessage() {

        boolean messageSending = false;
        for (Emergency emergency: emergencyNumbers){
            message = "Emergency help needed please help me plz \nmy location is "+location;
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(emergency.getPhoneNo(), null, message, null, null);
            messageSending = true;
            Log.d(TAG, "sendSMSMessage: -----------------request send number "+emergency.getPhoneNo());
        }

        if (messageSending){
            Toast.makeText(getActivity(),"Emergency Message is sending",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getActivity(),"Field To send you Message",Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    sendSMSMessage();
                } else {

                    return;
                }
            }
        }

    }

}
