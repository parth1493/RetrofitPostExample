package com.parth.retrofitpostexample.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parth.retrofitpostexample.R;
import com.parth.retrofitpostexample.model.User;
import com.parth.retrofitpostexample.service.PostAppService;
import com.parth.retrofitpostexample.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button submit;
    private EditText userName,password;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.submit);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.userPassword);
        result = findViewById(R.id.resultTextView);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult();
            }
        });
    }
    private void getResult(){
        if(!userName.getText().toString().equals("")
        && !password.getText().toString().equals("")){

            PostAppService postAppService = RetrofitInstance.getPostAppService();

            User user = new User();
            user.setUsername(userName.getText().toString());
            user.setPassword(password.getText().toString());

            Call<User> call = postAppService.getResult(user);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.code() ==  201) {
                        User userResponse = response.body();
                        result.setText(userResponse.getId()+"");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }
}
