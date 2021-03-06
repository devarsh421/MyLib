package com.sem6_project.mylib.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.sem6_project.mylib.R;
import com.sem6_project.mylib.appcontroller.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
// pull test
public class MainActivity<keeplogin> extends AppCompatActivity {

    public final static String ip = "https://projectmylib.000webhostapp.com/";
    //public final static String ip = "http://192.168.137.133/";
    Button btn_login;
    EditText et_id, et_pass;
    String json1 = ip + "mylib/json/user.php?id=";
    String json2 = "&pas=";
    static String JSON_URL;
    public static String var;
    boolean keeplogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btn_login = findViewById(R.id.btn_login);


        et_id = findViewById(R.id.main_id);
        et_pass = findViewById(R.id.main_password);
        //afdjasdf

        System.out.println(var);


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean value = sharedPreferences.getBoolean("keeplogin",false);


        if(value)
        {
            Intent in = new Intent(getApplicationContext(),DashboardActivity.class);
            startActivity(in);
            finish();
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                var = et_id.getText().toString();

                SharedPreferences sharedPref = getSharedPreferences("variable",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                String userid = var;
                editor.putString("userid",userid);
                editor.apply();

                JSON_URL = json1 + et_id.getText().toString() + json2 + et_pass.getText().toString() ;

                System.out.println(JSON_URL);

                extractCategory();
            }
        });

    }

    public void extractCategory() {


        JsonArrayRequest req = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject userLoginArray = (JSONObject) response.get(0);

//                    
                    if (Integer.parseInt(userLoginArray.getString("value")) == 1){

                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        keeplogin = true;
                        editor.putBoolean("keeplogin",keeplogin);
                        editor.apply();


                        String id = et_id.getText().toString();
                        Intent in = new Intent(getApplicationContext(),DashboardActivity.class);
                        startActivity(in);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                    }


                }catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error Avi", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        AppController.getInstance().addToRequestQueue(req);

    }
}