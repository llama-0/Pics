package com.llama.pics;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.llama.pics.utils.Singleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mTextDisplay;

    private NetworkImageView mImg;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextDisplay = findViewById(R.id.display_json);

        mImg = findViewById(R.id.img);

        mImageLoader = Singleton.getInstance(this).getImageLoader();

        String url = getString(R.string.BASE_URL);
        final String token = getString(R.string.my_token);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do something
                        manageResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-type", "application/json");
                headers.put("Authorization", "OAuth " + token);
                return headers;
            }
        };

        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @SuppressLint("SetTextI18n")
    private void manageResponse(JSONObject response) {
        mTextDisplay.setText("Response: " + response.toString());
        //mImg.setImageUrl("https://cloud-api.yandex.net/v1/disk/resources/download/Skype.jpg", mImageLoader);
    }
}