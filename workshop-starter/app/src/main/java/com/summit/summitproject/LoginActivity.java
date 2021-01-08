package com.summit.summitproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.summit.summitproject.prebuilt.login.LoginListener;
import com.summit.summitproject.prebuilt.login.LoginManager;
import com.summit.summitproject.prebuilt.model.Transaction;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText username;

    private EditText password;

    private Button signIn;

    private ProgressBar progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.sign_in);
        progress = findViewById(R.id.progressBar);

        signIn.setEnabled(false);

        username.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtedUsername = username.getText().toString();
                String inputtedPassword = password.getText().toString();

                progress.setVisibility(View.VISIBLE);

                LoginManager loginManager = new LoginManager(inputtedUsername, inputtedPassword, loginListener);
                loginManager.execute();
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            String inputtedUsername = username.getText().toString();
            String inputtedPassword = password.getText().toString();
            boolean enableButton = inputtedUsername.length() > 0 && inputtedPassword.length() > 0;

            signIn.setEnabled(enableButton);
        }
    };

    private LoginListener loginListener = new LoginListener() {
        @Override
        public void onLoginSuccess(String name, String cardNum, ArrayList<Transaction> transactions) {
            progress.setVisibility(View.INVISIBLE);

            Intent intent = new Intent(LoginActivity.this, SummaryActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("cardNum", cardNum);
            intent.putExtra("transactions", transactions);
            startActivity(intent);
        }

        @Override
        public void onLoginError(Exception exception) {
            progress.setVisibility(View.INVISIBLE);
            Toast toast = Toast.makeText(LoginActivity.this, "Logged failed: " + exception.toString(), Toast.LENGTH_LONG);
            toast.show();
        }
    };
}