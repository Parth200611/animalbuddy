package com.mountreachsolution.animalbuddy.Rescuser;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.VolleyMultipartRequest;
import com.mountreachsolution.animalbuddy.urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;


public class AddEvent extends Fragment {
     EditText etEventName, etDate, etStartTime, etEndTime, etLocation, etDescription, etEntryFees;
     Button btnAddEvent, btnAddImage;
     ImageView ivImage;
     String eventName, eventDate, eventStartTime, eventEndTime, eventLocation, eventDescription, eventEntryFees;
    Bitmap bitmap;
    Uri filepath;
    private  int pick_image_request=789;

    private static final int PICK_IMAGE_REQUEST_PASS = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_add_event, container, false);
        etEventName = view.findViewById(R.id.etEventName);
        etDate = view.findViewById(R.id.etDate);
        etStartTime = view.findViewById(R.id.etStartTime);
        etEndTime = view.findViewById(R.id.etEndTime);
        etLocation = view.findViewById(R.id.etLocation);
        etDescription = view.findViewById(R.id.etDescription);
        etEntryFees = view.findViewById(R.id.etEnteryFees);
        btnAddEvent = view.findViewById(R.id.btnaddEvent);
        btnAddImage = view.findViewById(R.id.btnaddImage);
        ivImage = view.findViewById(R.id.ivImage);
        etDate.setOnClickListener(v -> showDatePicker());
        etStartTime.setOnClickListener(v -> showTimePicker(etStartTime));
        etEndTime.setOnClickListener(v -> showTimePicker(etEndTime));

        btnAddEvent.setOnClickListener(v -> validateAndSave());
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectUserProfileimage();
            }
        });
        return view;
    }
    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view, year1, month1, dayOfMonth) -> {
            month1 += 1; 
            eventDate = dayOfMonth + "/" + month1 + "/" + year1;
            etDate.setText(eventDate);
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePicker(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), (view, hourOfDay, minute1) -> {
            String amPm;
            int displayHour;

            if (hourOfDay >= 12) {
                amPm = "PM";
                displayHour = (hourOfDay == 12) ? 12 : hourOfDay - 12;
            } else {
                amPm = "AM";
                displayHour = (hourOfDay == 0) ? 12 : hourOfDay;
            }

            String time = String.format("%02d:%02d %s", displayHour, minute1, amPm);
            editText.setText(time);


            if (editText.getId() == R.id.etStartTime) {
                eventStartTime = time;
            } else if (editText.getId() == R.id.etEndTime) {
                eventEndTime = time;
            }
        }, hour, minute, false);

        timePickerDialog.show();
    }


    private void validateAndSave() {
        eventName = etEventName.getText().toString().trim();
        eventDate = etDate.getText().toString().trim();
        eventStartTime = etStartTime.getText().toString().trim();
        eventEndTime = etEndTime.getText().toString().trim();
        eventLocation = etLocation.getText().toString().trim();
        eventDescription = etDescription.getText().toString().trim();
        eventEntryFees = etEntryFees.getText().toString().trim();

        if (TextUtils.isEmpty(eventName) || TextUtils.isEmpty(eventDate) || TextUtils.isEmpty(eventStartTime) ||
                TextUtils.isEmpty(eventEndTime) || TextUtils.isEmpty(eventLocation) || TextUtils.isEmpty(eventDescription) ||
                TextUtils.isEmpty(eventEntryFees)) {
            Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

         AddEventData();
    }

    private void AddEventData() {
        AsyncHttpClient client  = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("name",eventName);
        params.put("location",eventLocation);
        params.put("date",eventDate);
        params.put("startTime",eventStartTime);
        params.put("endTime",eventEndTime);
        params.put("dis",eventDescription);
        params.put("entry",eventEntryFees);
        client.post(urls.addevent,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String status =  response.getString("success");
                    if (status.equals("1")){
                        Toast.makeText(getActivity(), "Event Added!", Toast.LENGTH_SHORT).show();
                        UserImageSaveTodatabase(bitmap,eventName);
                        clearFields();
                    }else{
                        Toast.makeText(getActivity(), "Event Fail to Add", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
    private void clearFields() {
        etEventName.setText("");
        etDate.setText("");
        etStartTime.setText("");
        etEndTime.setText("");
        etLocation.setText("");
        etDescription.setText("");
        etEntryFees.setText("");

        // Reset ImageView to default image (calendar icon)
        ivImage.setImageResource(R.drawable.baseline_camera_alt_24); // Replace with your default image resource
    }
    private void SelectUserProfileimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image For Profil"),pick_image_request);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==pick_image_request && resultCode==RESULT_OK && data!=null){
            filepath=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),filepath);
                ivImage.setImageBitmap(bitmap);
                UserImageSaveTodatabase(bitmap,eventName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void UserImageSaveTodatabase(Bitmap bitmap, String strTitle) {
        VolleyMultipartRequest volleyMultipartRequest =  new VolleyMultipartRequest(Request.Method.POST, urls.eventimage, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                Toast.makeText(getActivity(), "Image Save as Profil "+strTitle, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                String errorMsg = error.getMessage();
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    errorMsg = new String(error.networkResponse.data);
                }
                Log.e("UploadError", errorMsg);
                Toast.makeText(getActivity(), "Upload Error: " + errorMsg, Toast.LENGTH_LONG).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                parms.put("tags", strTitle); // Adjusted to match PHP parameter name
                return parms;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, VolleyMultipartRequest.DataPart> parms = new HashMap<>();
                long imagename = System.currentTimeMillis();
                parms.put("pic",new VolleyMultipartRequest.DataPart(imagename+".jpeg",getfiledatafromBitmap(bitmap)));

                return parms;

            }

        };
        Volley.newRequestQueue(getActivity()).add(volleyMultipartRequest);
    }

    private byte[] getfiledatafromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}