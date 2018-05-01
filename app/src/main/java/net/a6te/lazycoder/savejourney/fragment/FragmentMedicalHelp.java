package net.a6te.lazycoder.savejourney.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
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
import net.a6te.lazycoder.savejourney.databinding.FragmentMedicalBinding;
import net.a6te.lazycoder.savejourney.modelClass.Emergency;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMedicalHelp extends Fragment {

    private final String TAG = "SafeJourney";

    private Button emergencyBtn;
    private View v;
    private ArrayList< Emergency> emergencyNumbers;
    private String message;
    private FragmentMedicalBinding binding;
    private UserDatabase userDatabase;
    private String location = "Suban Bag,Near Prince Plaza";


    public FragmentMedicalHelp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_medical, container, false);
        userDatabase = new UserDatabase(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.medicalEmergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendSms sms= new SendSms();
                sms.execute();
            }
        });
    }



    class SendSms extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            try{

                EmergencyDatabase database = new EmergencyDatabase(getActivity());
                emergencyNumbers = database.getEmergencyData("Mirpur","Hospital");
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
            message = "I Need emergency Medical Service please help me \n my location is "+location;
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(emergency.getPhoneNo(), null, message, null, null);
            messageSending = true;
            Log.d(TAG, "sendSMSMessage: -----------------request send number "+emergency.getPhoneNo());
        }

        if (messageSending){
            Toast.makeText(getActivity(),"Emergency Medical Request is sending",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getActivity(),"Field To send you request",Toast.LENGTH_LONG).show();

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
