package demo.stpl.com.tpsmergedbuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;
import demo.stpl.com.tpsmergedbuild.utils.CustomEditText;

//import skilrock.com.tpsgame.R;
//import tpsgames.HttpRequest;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.interfaces.ResponseLis;
//import demo.stpl.com.tpsmergedbuild.utils.CustomEditText;

/**
 * Created by stpl on 11/3/2016.
 */

public class OlaDepositFragment extends Fragment implements View.OnClickListener, ResponseLis {
    CustomEditText weaverId, amount, confirmAmount;
    TextView submitButton;

    public static OlaDepositFragment newInstance() {
        return new OlaDepositFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ola_deposit, container, false);
        weaverId = (CustomEditText) rootView.findViewById(R.id.weaver_id);
        amount = (CustomEditText) rootView.findViewById(R.id.amount);
        confirmAmount = (CustomEditText) rootView.findViewById(R.id.confirmAmount);
        submitButton = (TextView) rootView.findViewById(R.id.submit);
        submitButton.setOnClickListener(this);

        return rootView;
    }

    public void refreshView() {
        if (weaverId != null) {
            weaverId.setText("");
            amount.setText("");
            confirmAmount.setText("");
        }

    }

    public void setWeaverCard(String number){
        weaverId.setText("");
        weaverId.setText(number);
    }

    public void setWeaverId(String id) {
        weaverId.setText(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit: {
                if (!weaverId.getText().toString().equals("")) {
                    if (confirmAmount.getText().toString().equals(amount.getText().toString())) {
                        JSONObject data = new JSONObject();
                        try {
                            data.put("refCode", weaverId.getText().toString());
                            data.put("depositAmt", amount.getText().toString());
                            data.put("walletDevName", "ALA_WALLET");
                            data.put("retailerName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
                            String url = Utility.baseUrl+Utility.portNumber+ Utility.olaDepositUrl + data.toString();
                            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, this, "Deposit Amount", getActivity(), Utility.olaDepositUrl);
                            TpsGamesClass.getInstance().startLoader(getActivity());
                            httpRequest.executeTask();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else
                        Toast.makeText(getActivity(), "amount didn't match", Toast.LENGTH_SHORT).show();


                } else
                    Toast.makeText(getActivity(), "Enter id/Swipe Card", Toast.LENGTH_SHORT).show();

            }
        }

    }

    @Override
    public void onResponse(String response, String requestedMethod) {

        TpsGamesClass.getInstance().stopLoader();


        try {
            JSONObject jsonObject = new JSONObject(response);
            Log.d("response ", response);
            if (jsonObject.has("responseCode")) {

                TpsGamesClass.getInstance().closeScreen(getActivity());
                if (jsonObject.get("responseCode").equals(0))
                    Toast.makeText(getActivity(), "Money deposited", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), jsonObject.get("responseMsg").toString(), Toast.LENGTH_SHORT).show();

            }
        } catch (JSONException e1) {
            Toast.makeText(getActivity(), "Error from server", Toast.LENGTH_SHORT).show();

            e1.printStackTrace();
        }
    }

}

