package com.llama.pics.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.llama.pics.R;
import com.llama.pics.adapters.ImageRecordsAdapter;
import com.llama.pics.model.ImageRecord;
import com.llama.pics.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImagesFragment extends Fragment {

    private ImageRecordsAdapter mAdapter;

    public ImagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new ImageRecordsAdapter(getActivity());

        ListView listView = getView().findViewById(R.id.list);
        listView.setAdapter(mAdapter);

        fetchData();
    }

    private void fetchData() {
        String url = getString(R.string.BASE_URL);
        String part_of_url_to_download_a_file = "resources/?path=Загрузки/";

        final String token = getString(R.string.my_token);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url + part_of_url_to_download_a_file, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do something
                        try {
                            List<ImageRecord> imageRecords = manageResponse(response);
                            mAdapter.swapImageRecords(imageRecords);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError { // <-- TODO: deal with this
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-type", "application/json");
                headers.put("Authorization", "OAuth " + token);
                return headers;
            }
        };

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }

    private List<ImageRecord> manageResponse(JSONObject response) throws JSONException {
        ArrayList<ImageRecord> records = new ArrayList<>();

        JSONObject obj = response.getJSONObject("_embedded");

        JSONArray jsonArray = obj.getJSONArray("items");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.has("mime_type")) {
                String mime_type = jsonObject.getString("mime_type");
                if (mime_type.compareTo("image/jpeg") == 0 || mime_type.compareTo("image/png") == 0) {

                    // just testing
                    // System.out.println(response.toString());

                    String url = jsonObject.getString("file");
                    ImageRecord record = new ImageRecord(url);
                    records.add(record);
                }
            }
        }
        return records;
    }
}
