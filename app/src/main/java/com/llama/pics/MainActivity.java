package com.llama.pics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.llama.pics.fragments.ImagesFragment;

public class MainActivity extends AppCompatActivity {

   // private TextView mTextDisplay;

   // private NetworkImageView mImg;
   // private ImageLoader mImageLoader;

    //private String mItemUrl;

    //private String mTmpResponseString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ImagesFragment())
                    .commit();
        }
    }


        // test redundancy
        //mTextDisplay = findViewById(R.id.display_json);
        //mImg = findViewById(R.id.img);
/*
        mImageLoader = Singleton.getInstance(this).getImageLoader();

        String url = getString(R.string.BASE_URL);
        String part_of_url_to_download_a_file = "resources/?path=Загрузки/";

        // TODO: Maybe this all happens in the onStart() method. See the schema on the sheet of paper
        final String token = getString(R.string.my_token);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url + part_of_url_to_download_a_file, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do something
                       // manageResponse(response);
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
        //mTextDisplay.setText("Response: " + response.toString());
        //System.out.println(response.toString());
        ArrayList<ImageRecord> records = new ArrayList<>();
        try {
            JSONObject obj = response.getJSONObject("_embedded");
            JSONArray jsonArray = obj.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                //mTextDisplay.append(name + "\n\n");
                if (jsonObject.has("mime_type")) {
                    String mime_type = jsonObject.getString("mime_type");
                    if (mime_type.compareTo("image/jpeg") == 0 || mime_type.compareTo("image/png") == 0) {
                        //mTextDisplay.append(name + "\n\n");
                        //mTextDisplay.append("Mime_type " + mime_type + "    Da neuzheli " + "\n\n");
                        // TODO: здесь нужно пихать каждое изображение в свой NetworkImageView
                        // TODO: значит нужен, например, список из этих imegaView.

                        String file_url = jsonObject.getString("file");
                        mItemUrl = file_url;
                        ImageRecord record = new ImageRecord(file_url, name);
                        records.add(record);
                        //mTextDisplay.append("file     ...  " + file_url + "\n\n");
                      //  mImg.setImageUrl(file_url, mImageLoader);
                        //String preview = jsonObject.getString("preview");
                        //mImg.setImageUrl(preview, mImageLoader);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //mImg.setImageUrl("https://cloud-api.yandex.net/v1/disk/resources/download/Skype.jpg", mImageLoader);
    }
*/
}