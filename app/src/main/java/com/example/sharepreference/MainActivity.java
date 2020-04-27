package com.example.sharepreference;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText fullNameEt, emailEt;
    View       saveBtn;
    RadioGroup genderRad;
    private UserManager userManager;
    private String      gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userManager = new UserManager(this);

        fullNameEt = findViewById(R.id.et_main_fullName);
        emailEt    = findViewById(R.id.et_main_email);
        saveBtn    = findViewById(R.id.btn_main_save);
        genderRad  = findViewById(R.id.radio_main_gender);

        fullNameEt.setText(userManager.getFullName());
        emailEt.setText(userManager.getEmail());
        gender = userManager.getGender();
        if (gender.equalsIgnoreCase("man")) {genderRad.check(R.id.radioBtn_main_man);}
        else { if (gender.equalsIgnoreCase("woman")) {genderRad.check(R.id.radioBtn_main_female);} }

        genderRad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gender = checkedId == R.id.radioBtn_main_man ? "man" : "woman";
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userManager.saveInformation(getInputET(fullNameEt), getInputET(emailEt), gender);
            }
        });
    }

    private String getInputET(TextInputEditText editText) {
        return editText.getText().toString().trim();
    }
}
