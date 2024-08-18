package demo.stpl.com.tpsmergedbuild.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.Preview;
import demo.stpl.com.tpsmergedbuild.baseClass.TPS390Camera;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;
import demo.stpl.com.tpsmergedbuild.utils.CustomEditText;

//import skilrock.com.tpsgame.R;
//import tpsgames.HttpRequest;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.Preview;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TPS390Camera;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.interfaces.ResponseLis;
//import demo.stpl.com.tpsmergedbuild.utils.CustomEditText;

/**
 * Created by stpl on 10/18/2016.
 */

public class OlaRegistrationFragment extends Fragment implements View.OnClickListener, ResponseLis {

    FrameLayout add_view_frame;
    CustomEditText weaverId, mobileNo, idNo;
    CustomEditText amount;
    TextView btn_positive1, btn_negative1;
    Spinner spinner;
    Button capture;
    Preview preview;
    String data[];
    RelativeLayout add_view_frame1;
    ArrayAdapter<String> spinnerAdapter;
    public TPS390Camera tps390Camera ;
    String idType[] = {"Select Id", "UIDAI", "Voter Card", "Passport", "Select ID"};

    public static OlaRegistrationFragment newInstance() {
        return new OlaRegistrationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ola_registration, container, false);
        btn_negative1 = (TextView) rootView.findViewById(R.id.btn_negative1);
        btn_positive1 = (TextView) rootView.findViewById(R.id.btn_positive1);
        amount = (CustomEditText) rootView.findViewById(R.id.amount);
        btn_negative1.setOnClickListener(this);
        btn_positive1.setOnClickListener(this);
        capture = (Button) rootView.findViewById(R.id.capture);
        weaverId = (CustomEditText) rootView.findViewById(R.id.weaver_id);
        mobileNo = (CustomEditText) rootView.findViewById(R.id.mobile);
        idNo = (CustomEditText) rootView.findViewById(R.id.id_no);
        add_view_frame = (FrameLayout) rootView.findViewById(R.id.add_view_frame1);
        add_view_frame.setVisibility(View.VISIBLE);
        add_view_frame1 = (RelativeLayout) rootView.findViewById(R.id.add_view_frame);



        if(TpsGamesClass.getInstance().getIsOlaFirstTime()){
            TpsGamesClass.getInstance().setIsOlaFirstTime(false);
            addCamera();
        }
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
//        spinner.setPrompt("Select Id");
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.id_type, R.id.idtext, idType) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) view.findViewById(R.id.idtext)).setText("");
                    ((TextView) view.findViewById(R.id.idtext)).setHint(getItem(getCount()));
                }
                return view;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };


        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(spinnerAdapter.getCount());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        weaverId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    btn_negative1.setText("Cancel");
                }
               /* else {
                    weaverId.setText(addPadding(" ", s.toString(), 4));
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        spinner.setSelection(0);

        weaverId.requestFocus();

        return rootView;
    }

    public void addCamera(){

        if (TpsGamesClass.getInstance().getIfHasCamera() && !TpsGamesClass.getInstance().getDeviceName().contains("7003")) {
            tps390Camera = new TPS390Camera(getActivity());
            add_view_frame.addView(tps390Camera);
            add_view_frame.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    TpsGamesClass.getInstance().startLoader(getActivity());
                    tps390Camera.takePicture();
                    return false;
                }
            });
        } else {
            add_view_frame1.setVisibility(View.GONE);
        }
    }

    public void refreshContaints() {

        weaverId.setText("");
        mobileNo.setText("");
        idNo.setText("");
        amount.setText("");
        spinner.setAdapter(null);
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.id_type, R.id.idtext, idType) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) view.findViewById(R.id.idtext)).setText("");
                    ((TextView) view.findViewById(R.id.idtext)).setHint(getItem(getCount()));
                }
                return view;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };
        spinner.setAdapter(spinnerAdapter);
    }

    public void setWeaverCard(String number){
        weaverId.setText("");
        weaverId.setText(number);
    }


    public void setTextValue(String textValue) {
        weaverId.setText(textValue);
        btn_negative1.setText("Clear");
    }


    private void doRegistration() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject depositData = new JSONObject();
        LinearLayout linearLayout = (LinearLayout) spinner.getSelectedView();

        try {
            jsonObject.put("phone", mobileNo.getText().toString());
            jsonObject.put("email", "");
            jsonObject.put("walletName", "ALA_WALLET");
            jsonObject.put("username", weaverId.getText().toString());
            depositData.put("refCode", weaverId.getText().toString());
            if(amount.getText().toString().length()==0)
                depositData.put("depositAmt",0);
            else
            depositData.put("depositAmt", amount.getText().toString());
            depositData.put("walletDevName", "ALA_WALLET");

            jsonObject.put("depositData", depositData);
            jsonObject.put("regFieldType", "ALA");
            jsonObject.put("retailerName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            String url = Utility.baseUrl+Utility.portNumber+Utility.olaRegisterUrl + jsonObject.toString();
            Log.d("url", url);

            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, OlaRegistrationFragment.this, "registration", getActivity(), Utility.olaRegisterUrl);
            TpsGamesClass.getInstance().startLoader(getActivity());
            httpRequest.executeTask();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        TpsGamesClass.getInstance().setCameraOpen(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_negative1:
                if (btn_negative1.getText().toString().trim().equals("Clear")) {
                    weaverId.setText("");
                    btn_negative1.setText("Cancel");
                } else {
                    getActivity().finish();
                }
                break;
            case R.id.btn_positive1:


                if (weaverId.getText().toString().equals(""))
                    TpsGamesClass.getInstance().showAToast("Enter card number/Swipe Card",getActivity(),Toast.LENGTH_SHORT);
                 if(mobileNo.getText().toString().length()==0)
                     TpsGamesClass.getInstance().showAToast("Enter mobile no.",getActivity(),Toast.LENGTH_SHORT);

                if (weaverId.getText().toString().trim().length() > 0 && mobileNo.getText().toString().length()>0)
                    doRegistration();


                break;
        }
    }

    @Override
    public void onResponse(String response, String requestedMethod) {
        TpsGamesClass.getInstance().stopLoader();

//        {"isSuccess":false,"errorCode":1008,"errorMsg":"Invalid Ticket"}
        //{"reponse"
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.has("responseCode"))

            {
                if (jsonObject.get("responseCode").equals(0)) {

                    TpsGamesClass.getInstance().closeScreen(getActivity());
                    if (jsonObject.get("responseMsg").equals(""))
                        Toast.makeText(getActivity(), "Player Registered Successfully ", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), jsonObject.get("responseMsg").toString(), Toast.LENGTH_SHORT).show();


                } else {
                    TpsGamesClass.getInstance().closeScreen(getActivity());
                    Toast.makeText(getActivity(), jsonObject.get("responseMsg").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e1) {
            Toast.makeText(getActivity(), "Error from server", Toast.LENGTH_SHORT).show();

            e1.printStackTrace();
        }


    }

    public void setWeaverId(String id) {
        weaverId.setText(id);
    }

    private String addPadding(String t, String s, int num) {
        StringBuilder retVal;

        if (null == s || 0 >= num) {
            throw new IllegalArgumentException("Don't be silly");
        }

        if (s.length() <= num) {
            //String to small, do nothing
            return s;
        }

        retVal = new StringBuilder(s);

        for (int i = retVal.length(); i > 0; i -= num) {
            retVal.insert(i, t);
        }
        return retVal.toString();
    }

}



