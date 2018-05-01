package net.a6te.lazycoder.savejourney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.a6te.lazycoder.savejourney.database.EmergencyDatabase;
import net.a6te.lazycoder.savejourney.database.UserDatabase;
import net.a6te.lazycoder.savejourney.modelClass.Emergency;
import net.a6te.lazycoder.savejourney.userLogin.InputValidation;
import net.a6te.lazycoder.savejourney.userLogin.LogInfo;
import net.a6te.lazycoder.savejourney.userLogin.UserModelClass;

import java.util.ArrayList;

public class UserAuthentication extends AppCompatActivity {

    private InputValidation inputValidation;
    private TextInputLayout userNameLy,userPassLy;
    private TextInputEditText userNameET,userPassET;
    private UserDatabase userDb;
    private UserModelClass user;
    private LogInfo logInfo;
    private DataSaveToSp statusSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_authentication);
        intiView();
    }


    private void intiView(){
        inputValidation =new InputValidation(this);
        userDb = new UserDatabase(this);
        logInfo = new LogInfo(this);

        userNameET = (TextInputEditText) findViewById(R.id.UserNameLET);
        userPassET = (TextInputEditText) findViewById(R.id.UserPassLET);
        userNameLy = (TextInputLayout) findViewById(R.id.UserNameLLayout);
        userPassLy = (TextInputLayout) findViewById(R.id.UserPassLLayout);
        statusSave = new DataSaveToSp(this);

        if (statusSave.isLogin()){
            startActivity(new Intent(UserAuthentication.this,MainActivity.class));
        }
    }


    public void signUp(View view) {

        View layout = LayoutInflater.from(this).inflate(R.layout.sign_up_layout,null);
        AlertDialog.Builder dialog =new AlertDialog.Builder(this);

        // layout
        dialog.setView(layout);
        final TextInputLayout userNameLy,userEmailLy,userPhoneLy,userPassLy,EmergencyLy,addressLy;
        final TextInputEditText userNameEt,userEmailEt,userPhoneEt,userPassEt,EmergencyEt,addressEt;
        Button cancleBtn,CreateBtn;
        cancleBtn = (Button) layout.findViewById(R.id.cancleBTN);
        CreateBtn = (Button) layout.findViewById(R.id.addBTN);
        //-------------------- Layout ___---------------------------
        userNameLy = (TextInputLayout) layout.findViewById(R.id.UsernameLayout);
        userEmailLy = (TextInputLayout) layout.findViewById(R.id.UseremailLayout);
        userPhoneLy = (TextInputLayout) layout.findViewById(R.id.UserphoneLayout);
        userPassLy = (TextInputLayout) layout.findViewById(R.id.UserpassLayout);

        EmergencyLy = (TextInputLayout) layout.findViewById(R.id.UseremergencyLayout);
        addressLy = (TextInputLayout) layout.findViewById(R.id.UseraddressLayout);
        //------------------------------ Edite Text -----------------------------
        userNameEt = (TextInputEditText) layout.findViewById(R.id.usernameET);
        userEmailEt = (TextInputEditText) layout.findViewById(R.id.useremailET);
        userPhoneEt = (TextInputEditText) layout.findViewById(R.id.userphoneET);
        userPassEt = (TextInputEditText) layout.findViewById(R.id.userpassET);

        EmergencyEt = (TextInputEditText) layout.findViewById(R.id.useremergencyET);
        addressEt = (TextInputEditText) layout.findViewById(R.id.useraddresET);

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserAuthentication.this,UserAuthentication.class);
                startActivity(i);
            }
        });
        userEmailEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValidation.isInputEditTextEmail(userEmailEt, userEmailLy, "Enter Valid Email Address");
            }
        });

        CreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name, Email, Phone, Password,Emergency,Address;
                Name = userNameEt.getText().toString();
                Email = userEmailEt.getText().toString();
                Phone = userPhoneEt.getText().toString();
                Password = userPassEt.getText().toString();
                Emergency = EmergencyEt.getText().toString();
                Address = addressEt.getText().toString();

                if (Name.isEmpty()){

                    userNameLy.setError("Enter USer Name");
                }else if (Email.isEmpty()){

                    userEmailLy.setError("Enter User Email");
                }
                else if (Phone.isEmpty()){
                    userPhoneLy.setError("Enter User Phone");
                }else if (Password.isEmpty()){
                    userPassLy.setError("Enter User Password");
                }else if (Emergency.isEmpty()){
                    EmergencyLy.setError("Enter User Emergency Number");
                }else if (Address.isEmpty()){
                    addressLy.setError("Enter User Address");
                }else {
                    boolean em = inputValidation.isInputEditTextEmail(userEmailEt,userEmailLy,"Enter Valid Email");
                    if (em){

                        user = new UserModelClass(Name, Email, Phone, Password, Emergency, Address);
                        boolean id = userDb.createUser(user);
                        ArrayList< Emergency> emergencyNumbers = new ArrayList<Emergency>();

                        //this is only for testing purpose data
                        try{
                            EmergencyDatabase database = new EmergencyDatabase(UserAuthentication.this);
                            database.getEmergencyData("Mirpur");


                            Log.d("SafeJourney", "onClick: --------------total data-------"+database.getEmergencyData("Mirpur").get(0).getPhoneNo());

                        }catch (Exception e){

                            EmergencyDatabase database = new EmergencyDatabase(UserAuthentication.this);
                            database.setSomeData();
                            Log.d("SafeJourney", "onClick: --------------------"+e);

                        }

                        if (id) {
                            Toast.makeText(UserAuthentication.this, "Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UserAuthentication.this,MainActivity.class));
                        }else {
                            Toast.makeText(UserAuthentication.this, "UnSuccessful", Toast.LENGTH_SHORT).show();

                        }
                    }else {

                    }

                }
            }

        });


        dialog.show();
    }

    public void logIn(View view) {

        String username = userNameET.getText().toString().trim();
        String userpass = userPassET.getText().toString().trim();

        if (username.isEmpty()){
            userNameET.setError("Enter User Name");
        }else if (userpass.isEmpty()){
            userPassET.setError("Enter Password");
        }else {
            if (username != null && userpass != null ){

                int id = userDb.login(username,userpass);
                if (id > 0 & id != 0){
                    statusSave.saveLoginStatus(true);
                    logInfo.addStatus(id);
                    //startActivity(new Intent(UserAuthentication.this,MainActivity.class));
                    Snackbar.make(view, "Login Successful", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else {
                    Snackbar.make(view, "Wrong User Pass", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
            }else {
                Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show();
            }

        }

    }


}
