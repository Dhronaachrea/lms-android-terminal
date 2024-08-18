package demo.stpl.com.tpsmergedbuild;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Printer;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.telpo.tps550.api.TelpoException;
import com.telpo.tps550.api.printer.ThermalPrinter;

import java.util.ArrayList;

/**
 * Created by stpl on 3/14/2016.
 */
public class ActivityPrintRainbowGameJson extends Activity  {

    private Button btnPrint;
    private boolean LowBattery;
    String gameName;
    //    String gameDevName;
    String gameData;
    private Dialog dialog;

    private static String printVersion;
    private int printting = 0;
    private final int PRINTIT = 1;
    private final int ENABLE_BUTTON = 2;
    private final int NOPAPER = 3;
    private final int LOWBATTERY = 4;
    private final int PRINTVERSION = 5;
    private final int PRINTBARCODE = 6;
    private final int PRINTQRCODE = 7;
    private final int PRINTPAPERWALK = 8;
    private final int PRINTCONTENT = 9;
    private final int CANCELPROMPT = 10;
    private final int PRINTERR = 11;
    private final int OVERHEAT = 12;
    private String[] panelDatas;
    private boolean tag = true;
    private String[] panelprice;
    private String[] unitprice;
    private String[] nooflines;
    private String ticketNumber;

    private String requtData = "";

    //    Printer printer;
    private ArrayList<String> printerList = null;
    private ArrayList<String> printerTarget = null;
    String selectedPrinter, selectedTarget;
    private ArrayAdapter printerListAdapter = null;
    String authorizePerson = "";
    private boolean nopaper = false;
    private boolean printFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.layout_splash);
//        SystemUi.enableImmersiveMode(getWindow().getDecorView());

// dialog = new PrepareLoadingAnim(ActivityPrint.this).createStartManualGameDialog("Please wait until Printing is finished");
//        dialog.show();




        try {
            int i = ThermalPrinter.checkStatus();
            System.out.println("Printer Status " + i);
            if (i == 1) {
                ThermalPrinter.stop();
                setResult(104, new Intent());
                finish();
            }
        } catch (Error e) {
            setResult(103, new Intent());
            finish();
        } catch (TelpoException e) {
            e.printStackTrace();
        }


//        gameDevName = getIntent().getStringExtra("GameDevName");
//        TicketBean bean = new TicketBean.Builder("TicketNo:563031292137140070|brCd:563031292137140070|Date:2015-10-19|Time:12:23:34|PlayType:Perm1|Num:07,08,39,47,53,60,63,65,78,79|PanelPrice:3|QP:1|PlayType:Perm2|Num:24,28,33,34,71,73|PanelPrice:4.5|QP:1|TicketCost:7.5|DrawDate:2015-10-19|DrawTime:12:30:00*Top-up Monday23|DrawId:1105|DrawDate:2015-10-19|DrawTime:12:45:00*Top-up Monday24|DrawId:1105|DrawDate:2015-10-19|DrawTime:13:00:00*Top-up Monday25|DrawId:1105|balance:279.00|CRC:1312393416|").build();
//
//         no action Perform on Low Battery if Battery out than automatically cancel the ticket.
//        IntentFilter pIntentFilter = new IntentFilter();
//        pIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
//        registerReceiver(printReceive, pIntentFilter);
//        if (LowBattery) {
//            if (Config.connectivityExists(this)) {
//                new LMSTask(this, "TICKETCANCEL", LMSCommunicationService.getCancelTicketServices(this, ticketNumber,
//                        VariableStorage.GlobalPref.getStringData(this, VariableStorage.GlobalPref.USER_NAME), false, 4), "", "Loading...").execute();
//            } else {
//                GlobalVariables.showDataAlert(this);
//            }
//        }

    }




    public static void printTwoStringBt(String one, String two, Printer printer) throws Exception {

//        printer.addTextAlign(Printer.ALIGN_CENTER);
//        if ((one.length() + two.length()) > 32) {
//            printer.addText(one + "\n\n");
//            printer.addTextAlign(Printer.ALIGN_CENTER);
//            printer.addText(two + "\n\n");
//        } else {
//            StringBuffer str = new StringBuffer();
//            str.append(one);
//            for (int i = one.length(); i < (32 - two.length()); i++) {
//                str.append(" ");
//            }
//            str.append(two + "\n\n");
//            printer.addText(str.toString());
//        }
    }



    private void finalizeObject() {
//        if (printer == null) {
//            return;
//        }
//
//        printer.clearCommandBuffer();
//
//        printer.setReceiveEventListener(null);
//
//        printer = null;
    }

    private boolean printData() {
//        if (printer == null) {
//            return false;
//        }
//
//        if (!connectPrinter()) {
//            return false;
//        }
//
//        PrinterStatusInfo status = printer.getStatus();
//
//
//        if (!isPrintable(status)) {
//            try {
//                printer.disconnect();
//            } catch (Exception ex) {
//                // Do nothing
//            }
//            return false;
//        }
//
//        try {
//            printer.sendData(Printer.PARAM_DEFAULT);
//        } catch (Exception e) {
//            try {
//                printer.disconnect();
//            } catch (Exception ex) {
//                // Do nothing
//            }
//            return false;
//        }
//        try {
//            Discovery.stop();
//        } catch (Epos2Exception e) {
//            e.printStackTrace();
//        }
//        ThermalPrinter.stop();
//        setResult(101, new Intent());
////            unregisterReceiver(printReceive);
//        finish();
        return true;
    }





    public static void printDrawGameDetails(String drawGameName, String drawDate, String drawTime, Printer printer) throws Exception {
//        printer.addTextAlign(Printer.ALIGN_CENTER);
//        printer.addTextSize(1, 2);
//        printer.addText(drawGameName + "\n\n\n");
//        printTwoStringBt(drawDate, drawTime, printer);
    }

    public static void printBetTypeDetails(String betType, int QP, String num, String noofLines, String unitPrice, String panelPrice, String currencyName, Printer printer) throws Exception {
        printTwoStringBt(betType, QP == 1 ? "QP" : "", printer);
        printNum(num, printer);
        printTwoStringBt("No. of Line(s)", noofLines, printer);
        printTwoStringBt("Unit Price(" + currencyName + ")", unitPrice, printer);
        printTwoStringBt("Panel Price(" + currencyName + ")", panelPrice, printer);
    }

    public static void printNum(String num, Printer printer) throws Exception {

//        printer.addTextAlign(Printer.ALIGN_CENTER);
//        printer.addTextFont(2);
//        if (num.length() > 32) {
//            if (num.charAt(31) >= '0' && num.charAt(31) <= '9') {
//                if (num.charAt(32) >= '0' && num.charAt(32) <= '9') {
//                    num = num.substring(0, 31) + "\n" + num.substring(31);
//                }
//            }
//        }
//        printer.addText(num + "\n\n");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (tag) {
            tag = !tag;


//            String response = getIntent().getStringExtra("ticketBean");
//            ArrayList<PurchaseTicketBean> beans = new PurchaseTicketBean.Builder(response).build();
//                    try {
//                        ThermalPrinter.checkStatus();

            new PrepareDataPrinting().execute("");


//                    } catch (Error e) {
//                        setResult(103, new Intent());
////                unregisterReceiver(printReceive);
//                        finish();
//                    } catch (TelpoException e) {
//                        e.printStackTrace();
//                    }
//                    new PrepareDataPrinting().execute((PurchaseTicketBean) getIntent().getSerializableExtra("ticketBean"));

        }

        if (printFinish) {
            printFinished(requtData);
        }
    }

    private BroadcastReceiver printReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_NOT_CHARGING);
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                if (status != BatteryManager.BATTERY_STATUS_CHARGING) {
                    if (level * 5 <= scale) {
                        LowBattery = true;
                    } else {
                        LowBattery = false;
                    }
                } else {
                    LowBattery = false;
                }
            }
        }
    };



    static {
        System.loadLibrary("system_util");
    }

    private boolean connectPrinter() {
//        boolean isBeginTransaction = false;
//
//        if (printer == null) {
//            return false;
//        }
//
//        try {
//            printer.connect(selectedTarget, Printer.PARAM_DEFAULT);
//        } catch (Exception e) {
//            return false;
//        }
//
//        try {
//            printer.beginTransaction();
//            isBeginTransaction = true;
//        } catch (Exception e) {
//        }
//
//        if (isBeginTransaction == false) {
//            try {
//                printer.disconnect();
//            } catch (Epos2Exception e) {
//                // Do nothing
//                return false;
//            }
//        }

        return true;
    }

//    private boolean isPrintable(PrinterStatusInfo status) {
//        if (status == null) {
//            return false;
//        }
//
//        if (status.getConnection() == Printer.FALSE) {
//            return false;
//        } else if (status.getOnline() == Printer.FALSE) {
//            return false;
//        } else {
//            ;//print available
//        }
//
//        return true;
//    }

    private void startDiscovery() {

//        try {
//            Discovery.stop();
//        } catch (Epos2Exception e) {
//            e.printStackTrace();
//        }
//
//        FilterOption filterOption = null;
//
//        printerList.clear();
//        printerTarget.clear();
//        printerListAdapter.notifyDataSetChanged();
//
//        filterOption = new FilterOption();
//        filterOption.setPortType(Discovery.PORTTYPE_BLUETOOTH);
//        filterOption.setBroadcast("255.255.255.255");
//        filterOption.setDeviceModel(Discovery.MODEL_ALL);
//        filterOption.setEpsonFilter(Discovery.FILTER_NAME);
//        filterOption.setDeviceType(Discovery.TYPE_ALL);
//
//        try {
//            Discovery.start(this, filterOption, mDiscoveryListener);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    private DiscoveryListener mDiscoveryListener = new DiscoveryListener() {
//        @Override
//        public void onDiscovery(final DeviceInfo deviceInfo) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public synchronized void run() {
//                    dialogHeaderText.setText("Select printer");
//                    HashMap<String, String> item = new HashMap<String, String>();
//                    item.put("PrinterName", deviceInfo.getDeviceName());
//                    item.put("Target", deviceInfo.getTarget());
//                    printerList.add(deviceInfo.getDeviceName());
//                    printerTarget.add(deviceInfo.getTarget());
//                    printerListAdapter.notifyDataSetChanged();
//                }
//            });
//        }
//    };

//    private void disconnectPrinter() {
//        if (printer == null) {
//            return;
//        }
//
//        try {
//            printer.endTransaction();
//        } catch (final Exception e) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public synchronized void run() {
//                }
//            });
//        }
//
//        try {
//            printer.disconnect();
//        } catch (final Exception e) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public synchronized void run() {
//                }
//            });
//        }
//
//        finalizeObject();
//    }

//    @Override
//    public void onPtrReceive(Printer printer, int i, PrinterStatusInfo printerStatusInfo, String s) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public synchronized void run() {
//
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        disconnectPrinter();
//                    }
//                }).start();
//            }
//        });
//    }

//    private void noPaperDlg() {
//
//        new DownloadDialogBox(this, "No paper, please put paper in and retry", "No Paper", false, true, null, null, "Retry", "Cancel");
//
//        View.OnClickListener okClick = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!nopaper) {
//                    handler.sendMessage(handler.obtainMessage(PRINTIT, 1, 0, null));
//                } else {
//                    Toast.makeText(PrinterActivity.this, getString(R.string.ptintInit), Toast.LENGTH_LONG).show();
//                    handler.sendMessage(handler.obtainMessage(ENABLE_BUTTON, 1, 0, null));
//                }
//            }
//        };
//
//
//        dlg.setNegativeButton(R.string.dialog_cancel,new DialogInterface.OnClickListener(){
//@Override
//public void onClick(DialogInterface dialogInterface,int i){
//        handler.sendMessage(handler.obtainMessage(ENABLE_BUTTON,1,0,null));
//        }
//        });
//        dlg.show();
//        }
//        }

    public class PrepareDataPrinting extends
            AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... params) {
            try {
                ThermalPrinter.start();
            } catch (TelpoException e) {
                e.printStackTrace();
            }
            return null;
        }



    }

    protected void printFinished(String result) {
        ThermalPrinter.stop();
        Intent intent = new Intent();
        intent.putExtra("result", result);
        setResult(101, intent);
//            unregisterReceiver(printReceive);

        onBackPressed();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }
}
