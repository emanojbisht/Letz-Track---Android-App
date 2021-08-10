package com.manojbisht.input_order;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.manojbisht.input_order.data.RegisterDataBase;
import com.manojbisht.input_order.model.User;

public class Register extends AppCompatActivity {
    private RegisterDataBase db;
    private EditText registerName;
    private EditText registerUserName;
    private EditText registerPasswod;
    private EditText registerConfirmPassword;
    private Spinner registerFormSpinner;
    private EditText registerSpinnerAnswer;
    private CheckBox registerCheckBox;
    private Button registerButton;
    private Button registerLOginButton;
    public static final String MESSAGE_ID = "message_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new RegisterDataBase(Register.this);

        registerName = findViewById(R.id.registerName);
        registerUserName = findViewById(R.id.registerUserName);
        registerPasswod = findViewById(R.id.registerPassword);
        registerConfirmPassword = findViewById(R.id.registerConfirmPassword);
        registerFormSpinner = findViewById(R.id.register_spinner);
        registerSpinnerAnswer = findViewById(R.id.registerSpinnerAnswer);
        registerCheckBox = findViewById(R.id.register_checkBox);
        registerButton = findViewById(R.id.register_button);
        registerLOginButton = findViewById(R.id.register_login_button);

        //inserting data in spinner
        final ArrayAdapter<String> registerFormArraySpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner_v1));
        registerFormArraySpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        registerFormSpinner.setAdapter(registerFormArraySpinner);

        //Register Button is disabled initially
        registerButton.setEnabled(false);
        registerButton.setBackgroundColor(Color.parseColor("#949494"));
        //Checking confirm password


    }

    //Terms and condition checkBox code - CHECKBOX
    public void disableButton(View view) {
        CheckBox termsCheckBox = (CheckBox) view;
        if (termsCheckBox.isChecked()) {
            registerButton.setBackgroundColor(Color.parseColor("#D50000"));
            registerButton.setEnabled(true);
        } else {
            registerButton.setEnabled(false);
            registerButton.setBackgroundColor(Color.parseColor("#949494"));
        }
    }

    //Register button code so that user can register to the portal - REGISTER BUTTON
    public void registerMe(View view) {
        String name = registerName.getText().toString().trim();
        String userName = registerUserName.getText().toString().toLowerCase().trim();
        String password = registerPasswod.getText().toString();
        String confirmPassword = registerConfirmPassword.getText().toString();
        String question = registerFormSpinner.getSelectedItem().toString();
        String answer = registerSpinnerAnswer.getText().toString();
        if (name.matches("") || userName.matches("") || password.matches("") || confirmPassword.matches("") || answer.matches("")) {
            Snackbar.make(view, "Field cannot be empty!", Snackbar.LENGTH_SHORT).show();
        } else {
            if (!confirmPassword.equals(password)) {
                Toast.makeText(Register.this, "Password does not match!!!", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User();
                user.setrName(name);
                user.setrPassword(password);
                user.setrUserName(userName);
                user.setrQuestion(question);
                user.setrAnswer(answer);
                db.registerUser(user);
                registerUser(userName);
            }

        }
    }

    public void registerUser(String userName){
        //Storing login details in shared preference
        SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("1",""+userName);
        editor.apply();

        Intent intent = new Intent(Register.this,track_home.class);
        intent.putExtra("userName",userName);
        startActivity(intent);
    }

    //Login button code - LOGIN
    public void takeMeToLogin(View view){
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
    }
}