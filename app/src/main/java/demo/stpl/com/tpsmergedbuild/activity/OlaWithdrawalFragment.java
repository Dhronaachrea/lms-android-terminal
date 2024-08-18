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

public class OlaWithdrawalFragment extends Fragment implements View.OnClickListener,ResponseLis {
    CustomEditText weaverId,amount,verification;
    TextView submitButton;
    public static OlaWithdrawalFragment newInstance()
    {
        return new OlaWithdrawalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.ola_withdrawl,container,false);
        weaverId= (CustomEditText) rootView.findViewById(R.id.weaver_id);
        amount= (CustomEditText) rootView.findViewById(R.id.amount);
        verification=(CustomEditText)rootView.findViewById(R.id.verificationCode);
        submitButton= (TextView) rootView.findViewById(R.id.submit);
        submitButton.setOnClickListener(this);
        return rootView;

    }

    public void updateView(){
        if(weaverId != null){
            weaverId.setText("");
            amount.setText("");
            verification.setText("");
        }

    }

    public void setWeaverCard(String number){
        weaverId.setText("");
        weaverId.setText(number);
    }
    public void setWeaverId(String id)
    {
        weaverId.setText(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.submit:
            {
                if(!weaverId.getText().toString().equals(""))
                {
                    if(!amount.getText().toString().equals(""))
                    {
                        if(!amount.getText().toString().equals("0"))
                        {
                            Toast.makeText(getActivity(),"enter amount greater than 0",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            JSONObject withdrawalData=new JSONObject();
                            try {
                                withdrawalData.put("refCode",weaverId.getText().toString());
                                withdrawalData.put("withdrawlAmt",amount.getText().toString());
                                //authenticationCode":1245,"devWalletName":"TabletGaming","retailerName":"testret"};
                                withdrawalData.put("authenticationCode",verification.getText().toString());
                                withdrawalData.put("devWalletName","ALA_WALLET");
                                withdrawalData.put("retailerName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
                                String url = Utility.baseUrl+Utility.portNumber+ Utility.olaWithdrawalUrl+withdrawalData.toString();
                                Log.d("url",url);

                                HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, this, "registration", getActivity(), Utility.olaWithdrawalUrl);
                                TpsGamesClass.getInstance().startLoader(getActivity());
                                httpRequest.executeTask();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    else
                        Toast.makeText(getActivity(),"enter amount",Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(getActivity(),"Enter id/Swipe Card",Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onResponse(String response, String requestedMethod) {

        TpsGamesClass.getInstance().stopLoader();


        try {
            JSONObject jsonObject = new JSONObject(response);
            Log.d("response ",response);
            if (jsonObject.has("responseCode")) {

                TpsGamesClass.getInstance().closeScreen(getActivity());
                if(jsonObject.get("responseCode").equals(0))
                    Toast.makeText(getActivity(), "Money Withdrawn", Toast.LENGTH_SHORT).show();
                else
                Toast.makeText(getActivity(), jsonObject.get("responseMsg").toString(), Toast.LENGTH_SHORT).show();

            }
        } catch (JSONException e1) {
            Toast.makeText(getActivity(),"Error from server",Toast.LENGTH_SHORT).show();
            e1.printStackTrace();
        }
    }

}
