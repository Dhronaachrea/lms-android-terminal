package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.epson.epos2.Epos2Exception;
import com.epson.epos2.discovery.DeviceInfo;
import com.epson.epos2.discovery.Discovery;
import com.epson.epos2.discovery.DiscoveryListener;
import com.epson.epos2.discovery.FilterOption;

import java.util.ArrayList;
import java.util.HashMap;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

/**
 * Created by stpl on 26-Oct-16.
 */
public class ActivityDiscoveryExternalPrinter extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Context mContext = null;
    private ArrayList<HashMap<String, String>> mPrinterList = null;
    private SimpleAdapter mPrinterListAdapter = null;
    private FilterOption mFilterOption = null;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        mContext = this;

        Button button = (Button) findViewById(R.id.btnRestart);
        button.setOnClickListener(this);

        mPrinterList = new ArrayList<HashMap<String, String>>();
        mPrinterListAdapter = new SimpleAdapter(this, mPrinterList, R.layout.list_at,
                new String[]{"PrinterName", "Target"},
                new int[]{R.id.PrinterName, R.id.Target});
        list = (ListView) findViewById(R.id.lstReceiveData);
        list.setAdapter(mPrinterListAdapter);
        list.setOnItemClickListener(this);

        mFilterOption = new FilterOption();
        mFilterOption.setDeviceType(Discovery.TYPE_PRINTER);
        mFilterOption.setEpsonFilter(Discovery.FILTER_NAME);
        try {
            Discovery.start(this, mFilterOption, mDiscoveryListener);
        } catch (Exception e) {
            TpsGamesClass.getInstance().showAToast("start", mContext, Toast.LENGTH_SHORT);
//            ShowMsg.showException(e, "start", mContext);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        while (true) {
            try {
                Discovery.stop();
                break;
            } catch (Epos2Exception e) {
                if (e.getErrorStatus() != Epos2Exception.ERR_PROCESSING) {
                    break;
                }
            }
        }

        mFilterOption = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRestart:
                restartDiscovery();
                break;

            default:
                // Do nothing
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();

        HashMap<String, String> item = mPrinterList.get(position);
        intent.putExtra("Target", item.get("Target"));

        setResult(RESULT_OK, intent);

        finish();
    }

    private void restartDiscovery() {
        while (true) {
            try {
                Discovery.stop();
                break;
            } catch (Epos2Exception e) {
                if (e.getErrorStatus() != Epos2Exception.ERR_PROCESSING) {
                    TpsGamesClass.getInstance().showAToast("stop", mContext, Toast.LENGTH_SHORT);
//                    ShowMsg.showException(e, "stop", mContext);
                    return;
                }
            }
        }

        mPrinterList.clear();
        mPrinterListAdapter.notifyDataSetChanged();


        try {
            Discovery.start(this, mFilterOption, mDiscoveryListener);
        } catch (Exception e) {
            TpsGamesClass.getInstance().showAToast("stop", mContext, Toast.LENGTH_SHORT);
        }
    }

    private DiscoveryListener mDiscoveryListener = new DiscoveryListener() {
        @Override
        public void onDiscovery(final DeviceInfo deviceInfo) {
            runOnUiThread(new Runnable() {
                @Override
                public synchronized void run() {
                    HashMap<String, String> item = new HashMap<String, String>();
                    item.put("PrinterName", deviceInfo.getDeviceName());
                    item.put("Target", deviceInfo.getTarget());
                    mPrinterList.add(item);
                    Intent intent = new Intent();
                    intent.putExtra("Target", item.get("Target"));

                    setResult(RESULT_OK, intent);
                    mPrinterListAdapter.notifyDataSetChanged();
                    finish();


                }
            });
        }
    };
}
