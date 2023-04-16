package coding.insight.healthcare.interfaces;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import coding.insight.healthcare.R;
import coding.insight.healthcare.database.Database;


public class RegisterActivity extends AppCompatActivity {
    CircularProgressButton btnRegister;
    TextView tvHaveAnAccount;
    EditText edNewUsername, edPassword, edMobile, edEmail, edConfirmPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        //Anh xa
        btnRegister = findViewById(R.id.btnRegister);
        tvHaveAnAccount = findViewById(R.id.tvHaveAnAccount);
        edNewUsername = findViewById(R.id.edNewUsername);
        edPassword = findViewById(R.id.edPassword);
        edConfirmPassword = findViewById(R.id.edConfirmPassword);
        edMobile = findViewById(R.id.edMobile);
        edEmail = findViewById(R.id.edEmail);

        //set sk cho textView already have an account
        tvHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        //set sk cho btn Register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = edNewUsername.getText().toString();
                String password = edPassword.getText().toString();
                String mobile = edMobile.getText().toString();
                String email = edEmail.getText().toString();
                String confirmPassword = edConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (newUsername.length() == 0 || password.length() == 0 || mobile.length() == 0 ||email.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all detail!", Toast.LENGTH_SHORT).show();
                } else {
                    if(password.compareTo(confirmPassword)==0){
                        if(isValid(password)){
                            db.register(newUsername, email, password);
                            Toast.makeText(getApplicationContext(), "Register Success, Login to start", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
                        }else{
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter, digit and special symbol!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password does not match, olease check your password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String passwordHere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordHere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordHere.length(); p++) {
                if (Character.isLetter(passwordHere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordHere.length(); r++) {
                if (Character.isDigit(passwordHere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordHere.length(); s++) {

                    char c = passwordHere.charAt(s);
                    if (c > 33 && c <= 46 || c == 64) {
                        f3 = 1;
                    }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;
        }
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}
