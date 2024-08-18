package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.EbetSlipBean;
import demo.stpl.com.tpsmergedbuild.beans.SportsSaleBean;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;
import demo.stpl.com.tpsmergedbuild.interfaces.Tps515PrintCall;

//import skilrock.com.tpsgame.R;
//import tpsgames.HttpRequest;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.EbetSlipBean;
//import tpsgames.beans.SportsSaleBean;
//import tpsgames.interfaces.ResponseLis;

/**
 * Created by stpl on 9/14/2016.
 */
public class ActivityEbetSlipTpsGame extends Activity implements ResponseLis ,Tps515PrintCall{

    private GridView grid_view;
    LayoutInflater inflater;
    private HashMap<String, ArrayList<String>> adapterValues;
    private List<String> usedIndex = new ArrayList<>();
    private Integer pos;
    private TextView ebet, slip, user_name, user_balance;
    private ImageView flip_image;
    private String tps515Response,tps515Method;
    ArrayList<EbetSlipBean.ResponseData> responseDatasAdapter = new ArrayList<>();

    String saleResponseSle = "currDate:2016-09-22|currTime:12:06:45|ticketNo:525562266171280030|brCd:525562266171280030|trxId:50054|ticketAmt:16.00|balance:9974107.57|gameId:1|gameTypeId:1|gameName:Soccer|gameTypeName:Soccer 13|drawInfo:2016-09-25,06:00:00,SUNDAY-S13,909,15,1.0,1.00,16.00,16~AL--vs-GUA@A#AL--vs-BEI@H,A#ARS-vs-BEI@H#ARS-vs-GUA@A#AL -vs-GUA@H,A#AL -vs-AL-@H#FC -vs-BEI@A#BEI-vs-AL-@H,A#AL--vs-GUA@H#AL -vs-ARS@A#AL -vs-ARS@H,A#AL--vs-FC @H#AL--vs-BEI@AjackpotMsg:Current Jackpot Fund@(All Prizes Parimutuel)~13 out Of 13 Match@10000.00 USD|";


    String responseSports = "{\"serviceCode\":\"SLE\",\"deviceId\":\"da1ebc67ca99a3ee\",\"saleData\":\"AL--vs-GUA&AL--vs-BEI&ARS-vs-BEI&ARS-vs-GUA&AL -vs-GUA&AL -vs-AL-&FC -vs-BEI&BEI-vs-AL-&AL--vs-GUA&AL -vs-ARS&AL -vs-ARS&AL--vs-FC &AL--vs-BEI$userName=username&gameId=1&gameTypeId=1&drawInfo=957~1~4335@H$4343@H$4337@H$4338@H,A$4333@H$4334@H,D$4346@H$4340@H$4341@H$4336@H,D,A$4342@H$4339@H$4344@H$&drawCount=1&ticketAmt=1.0&merCode=RMS&LSTktNo=0&sessId=&slLstTxnId=&CID=48581&LAC=617&simType=ECONET&deviceType=TPS580&reqCounter=31&respCounter=31&time=21-09-2016&date=&random=0\"}";
//    String json = "{\n" +
//            "\t\"errorCode\": 0,\n" +
//            "\t\"errorMsg\": \"\",\n" +
//            "\t\"IsSuccess\": \"true\",\n" +
//            "\t\"responseData\": [{\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 02:15:27.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 02:05:27.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"07\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"12\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":4,\\\"totalPurchaseAmt\\\":40}\",\n" +
//            "\t\t\"requestId\": 101,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z28\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:07:42.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:57:42.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\n" +
//            "  \"commonSaleData\": {\n" +
//            "    \"isAdvancePlay\": false,\n" +
//            "    \"drawData\": [\n" +
//            "      \n" +
//            "    ],\n" +
//            "    \"noOfDraws\": 1,\n" +
//            "    \"isDrawManual\": true,\n" +
//            "    \"gameName\": \"TwelveByTwentyFour\"\n" +
//            "  },\n" +
//            "  \"betTypeData\": [\n" +
//            "    {\n" +
//            "      \"noPicked\": \"12\",\n" +
//            "      \"betAmtMul\": 20,\n" +
//            "      \"isQp\": true,\n" +
//            "      \"pickedNumbers\": \"02,03,04,06,07,09,12,13,14,18,20,22\",\n" +
//            "      \"betName\": \"Direct12\",\n" +
//            "      \"QPPreGenerated\": true\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"noOfPanel\": 1,\n" +
//            "  \"totalPurchaseAmt\": \"10\"\n" +
//            "}\",\n" +
//            "\t\t\"requestId\": 100,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z27\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:06:53.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:56:53.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"06\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"09\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 99,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z26\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:06:20.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:56:20.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"06\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"09\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 98,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z25\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:06:08.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:56:08.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 97,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z24\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:05:46.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:55:46.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 92,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z19\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:05:46.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:55:46.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 93,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z20\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:05:46.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:55:46.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 94,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z21\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:05:46.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:55:46.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 95,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z22\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:05:46.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:55:46.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 96,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z23\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:05:45.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:55:45.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 90,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z17\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:05:45.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:55:45.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 91,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z18\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:05:44.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:55:44.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 89,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z16\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:01:15.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:51:15.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"09\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"10\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":4,\\\"totalPurchaseAmt\\\":40}\",\n" +
//            "\t\t\"requestId\": 88,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z15\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 01:01:06.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:51:06.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"09\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"10\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":4,\\\"totalPurchaseAmt\\\":40}\",\n" +
//            "\t\t\"requestId\": 87,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z14\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 00:50:41.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:40:41.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"06\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"08\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 86,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z13\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 00:49:28.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:39:28.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"02\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"06\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"10\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"12\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":4,\\\"totalPurchaseAmt\\\":40}\",\n" +
//            "\t\t\"requestId\": 85,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z12\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 00:46:59.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:36:59.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"08\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"09\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 84,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z11\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-18 00:46:12.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-18 00:36:12.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"14\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"08\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"09\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":50}\",\n" +
//            "\t\t\"requestId\": 83,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z10\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 22:38:27.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 22:28:27.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"TwelveByTwentyFour\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"12\\\",\\\"betAmtMul\\\":20,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"02,03,04,06,0  7,09,12,13,14,18,20,22\\\",\\\"betName\\\":\\\"Direct12\\\",\\\"QPPreGenerated\\\":true}],\\\"noOf  Panel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 82,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z09\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 21:06:34.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 21:05:34.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"kenoSix\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"10\\\",\\\"betAmtMul\\\":1,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"01,09,21,39,42,44,49,63,66,74\\\",\\\"betName\\\":\\\"Direct10\\\",\\\"QPPreGenerated\\\":true}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 81,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z08\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 21:05:50.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 21:04:50.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"kenoSix\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"10\\\",\\\"betAmtMul\\\":1,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"01,09,21,39,42,44,49,63,66,74\\\",\\\"betName\\\":\\\"Direct10\\\",\\\"QPPreGenerated\\\":true}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 80,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z07\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 21:03:14.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 21:02:14.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"kenoSix\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"10\\\",\\\"betAmtMul\\\":1,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"01,09,21,39,42,44,49,63,66,74\\\",\\\"betName\\\":\\\"Direct10\\\",\\\"QPPreGenerated\\\":true}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 79,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z06\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 21:01:56.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 21:00:56.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"kenoSix\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"10\\\",\\\"betAmtMul\\\":1,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"01,09,21,39,42,44,49,63,66,74\\\",\\\"betName\\\":\\\"Direct10\\\",\\\"QPPreGenerated\\\":true}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 78,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z05\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Z\",\n" +
//            "\t\t\"deviceId\": \"6\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 21:01:50.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 21:00:50.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 1,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"kenoSix\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"10\\\",\\\"betAmtMul\\\":1,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"01,09,21,39,42,44,49,63,66,74\\\",\\\"betName\\\":\\\"Direct10\\\",\\\"QPPreGenerated\\\":true}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 75,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Z02\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Y\",\n" +
//            "\t\t\"deviceId\": \"7\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 20:36:25.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 20:35:25.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 2,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"kenoSix\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"10\\\",\\\"betAmtMul\\\":1,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"01,09,21,39,42,44,49,63,66,74\\\",\\\"betName\\\":\\\"Direct10\\\",\\\"QPPreGenerated\\\":true}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 67,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Y05\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Y\",\n" +
//            "\t\t\"deviceId\": \"7\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 20:36:23.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 20:35:23.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 2,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"kenoSix\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"10\\\",\\\"betAmtMul\\\":1,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"01,09,21,39,42,44,49,63,66,74\\\",\\\"betName\\\":\\\"Direct10\\\",\\\"QPPreGenerated\\\":true}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 66,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Y04\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Y\",\n" +
//            "\t\t\"deviceId\": \"7\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 20:36:21.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 20:35:21.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 2,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"kenoSix\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"10\\\",\\\"betAmtMul\\\":1,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"01,09,21,39,42,44,49,63,66,74\\\",\\\"betName\\\":\\\"Direct10\\\",\\\"QPPreGenerated\\\":true}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 65,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Y03\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Y\",\n" +
//            "\t\t\"deviceId\": \"7\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 20:36:19.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 20:35:19.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 2,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"kenoSix\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"10\\\",\\\"betAmtMul\\\":1,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"01,09,21,39,42,44,49,63,66,74\\\",\\\"betName\\\":\\\"Direct10\\\",\\\"QPPreGenerated\\\":true}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 64,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Y02\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}, {\n" +
//            "\t\t\"deviceCode\": \"Y\",\n" +
//            "\t\t\"deviceId\": \"7\",\n" +
//            "\t\t\"deviceMapId\": 0,\n" +
//            "\t\t\"ebetExpiryDatetime\": \"2016-09-17 20:36:13.0\",\n" +
//            "\t\t\"ebetRequestDatetime\": \"2016-09-17 20:35:13.0\",\n" +
//            "\t\t\"mobileNumber\": \"\",\n" +
//            "\t\t\"modelId\": 2,\n" +
//            "\t\t\"organizationId\": 3,\n" +
//            "\t\t\"processedDatetime\": \"\",\n" +
//            "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"isDrawManual\\\":true,\\\"gameName\\\":\\\"kenoSix\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"10\\\",\\\"betAmtMul\\\":1,\\\"isQp\\\":true,\\\"pickedNumbers\\\":\\\"01,09,21,39,42,44,49,63,66,74\\\",\\\"betName\\\":\\\"Direct10\\\",\\\"QPPreGenerated\\\":true}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"10\\\"}\",\n" +
//            "\t\t\"requestId\": 63,\n" +
//            "\t\t\"saleType\": \"DGE\",\n" +
//            "\t\t\"status\": \"\",\n" +
//            "\t\t\"tokenId\": \"Y01\",\n" +
//            "\t\t\"userId\": 11004,\n" +
//            "\t\t\"userName\": \"\"\n" +
//            "\t}]\n" +
//            "}";

    protected void fetchEbet() {
        String url = Utility.baseUrl + Utility.portNumber + Utility.eBetSlipUrl;
        HttpRequest httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(url, this, "fetchingdadtaebetslip", this, "fetchEbetSlip", Utility.eBetSlipHeader);
        TpsGamesClass.getInstance().startLoader(ActivityEbetSlipTpsGame.this);
        httpRequest.executeTask();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fetchEbet();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        if (TpsGamesClass.getInstance().getDeviceName().contains("TPS550") && TpsGamesClass.getInstance().getDeviceName().contains("hdx")) {
                    setContentView(R.layout.layout_ebet_slip_550);
        }else if(TpsGamesClass.getInstance().getDeviceName().contains("390")){
            setContentView(R.layout.layout_ebet_slip_tps390);
        }
        else {
            setContentView(R.layout.layout_ebet_slip);
        }

        Bitmap bitmap1 = BitmapFactory.decodeResource(ActivityEbetSlipTpsGame.this.getResources(), R.drawable.ebet_slip_banner_tousei);

        TpsGamesClass.getInstance().displayImagebyAbsolutePath("", bitmap1);
        Bundle bundle = getIntent().getExtras();
       if(bundle!=null)
        pos = bundle.getInt("selectedPosition");
        TpsGamesClass.getInstance().closeScreen(this);

        TpsGamesClass.getInstance().saveImage("ebet_slip.png", this);
        String url = "/sdcard/ebet_slip.png";
        TpsGamesClass.getInstance().pcsoIcon(url);
        TpsGamesClass.getInstance().displayScreen(this);

        ebet = (TextView) findViewById(R.id.ebet_btn);
        slip = (TextView) findViewById(R.id.slip);

        user_name = (TextView) findViewById(R.id.user_name);
        user_balance = (TextView) findViewById(R.id.user_balance);


        flip_image = (ImageView) findViewById(R.id.flip_betslip);

        ebet.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.MEDIUM));
        slip.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));


        fetchEbet();
        inflater = LayoutInflater.from(this);
        grid_view = (GridView) findViewById(R.id.grid_view);

        flip_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        user_name.setText(TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
        String balance = TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance() + "";

        if (balance.contains(".")) {
            String[] decimalAmount = balance.split("[.]");
            balance = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        user_balance.setText("$ " + balance);
//        user_balance.setText("$ " + TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TpsGamesClass.getInstance().closeScreen(this);
        if(TpsGamesClass.getInstance().getDeviceName().contains("7003")) {
            TpsGamesClass.getInstance().changeBackScreen(pos);
        }
        overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
    }

    @Override
    public void onResponse(String response, String requestedMethod) {

        isPurchCall = false;

        //response
//        response = "{\n" +
//                "\t\"errorCode\": 0,\n" +
//                "\t\"errorMsg\": \"\",\n" +
//                "\t\"isSuccess\": \"true\",\n" +
//                "\t\"responseData\": [{\n" +
//                "\t\t\"deviceCode\": \"A\",\n" +
//                "\t\t\"deviceId\": \"7f572f48d612d993\",\n" +
//                "\t\t\"deviceMapId\": 0,\n" +
//                "\t\t\"ebetExpiryDatetime\": \"2016-09-22 11:01:51.0\",\n" +
//                "\t\t\"ebetRequestDatetime\": \"2016-09-22 10:31:51.0\",\n" +
//                "\t\t\"mobileNumber\": \"\",\n" +
//                "\t\t\"modelId\": 1,\n" +
//                "\t\t\"organizationId\": 3,\n" +
//                "\t\t\"processedDatetime\": \"\",\n" +
//                "\t\t\"requestData\": \"{\\\"data\\\":\\\"sports 13%AL--vs-GUA&AL--vs-BEI&ARS-vs-BEI&ARS-vs-GUA&AL -vs-GUA&AL -vs-AL-&FC -vs-BEI&BEI-vs-AL-&AL--vs-GUA&AL -vs-ARS&AL -vs-ARS&AL--vs-FC &AL--vs-BEI$userName=username&gameId=1&gameTypeId=1&drawInfo=957~1~4335@H$4343@H,D,A$4337@H$4338@H$4333@H,A$4334@H$4346@H,D$4340@H$4341@H$4336@H$4342@H$4339@H$4344@H$&drawCount=1&ticketAmt=1.0&merCode=RMS&LSTktNo=0&sessId=&slLstTxnId=&CID=48581&LAC=617&simType=ECONET&deviceType=TPS580&reqCounter=31&respCounter=31&time=22-09-2016&date=&random=0\\\"}\",\n" +
//                "\t\t\"requestId\": 279,\n" +
//                "\t\t\"saleType\": \"SLE\",\n" +
//                "\t\t\"status\": \"\",\n" +
//                "\t\t\"tokenId\": \"A02\",\n" +
//                "\t\t\"userId\": 11004,\n" +
//                "\t\t\"userName\": \"\"\n" +
//                "\t}, {\n" +
//                "\t\t\"deviceCode\": \"A\",\n" +
//                "\t\t\"deviceId\": \"7f572f48d612d993\",\n" +
//                "\t\t\"deviceMapId\": 0,\n" +
//                "\t\t\"ebetExpiryDatetime\": \"2016-09-22 10:52:19.0\",\n" +
//                "\t\t\"ebetRequestDatetime\": \"2016-09-22 10:22:19.0\",\n" +
//                "\t\t\"mobileNumber\": \"\",\n" +
//                "\t\t\"modelId\": 1,\n" +
//                "\t\t\"organizationId\": 3,\n" +
//                "\t\t\"processedDatetime\": \"\",\n" +
//                "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"gameName\\\":\\\"MiniRoulette\\\"},\\\"noOfPanel\\\":3,\\\"betTypeData\\\":[{\\\"unitPrice\\\":10,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":1,\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"10\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":10,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":1,\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":10,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":1,\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"12\\\",\\\"betName\\\":\\\"roulette\\\"}],\\\"totalPurchaseAmt\\\":\\\"30.0\\\"}\",\n" +
//                "\t\t\"requestId\": 278,\n" +
//                "\t\t\"saleType\": \"DGE\",\n" +
//                "\t\t\"status\": \"\",\n" +
//                "\t\t\"tokenId\": \"A01\",\n" +
//                "\t\t\"userId\": 11004,\n" +
//                "\t\t\"userName\": \"\"\n" +
//                "\t}]\n" +
//                "}";
//        response = " {\n" +
//                "\t\"errorCode\": 0,\n" +
//                "\t\"errorMsg\": \"\",\n" +
//                "\t\"IsSuccess\": \"true\",\n" +
//                "\t\"responseData\": [{\n" +
//                "\t\t\"deviceCode\": \"A\",\n" +
//                "\t\t\"deviceId\": \"7f572f48d612d993\",\n" +
//                "\t\t\"deviceMapId\": 0,\n" +
//                "\t\t\"ebetExpiryDatetime\": \"2016-09-18 16:03:40.0\",\n" +
//                "\t\t\"ebetRequestDatetime\": \"2016-09-18 15:48:40.0\",\n" +
//                "\t\t\"mobileNumber\": \"\",\n" +
//                "\t\t\"modelId\": 1,\n" +
//                "\t\t\"organizationId\": 3,\n" +
//                "\t\t\"processedDatetime\": \"\",\n" +
//                "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"113\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":10}\",\n" +
//                "\t\t\"requestId\": 10,\n" +
//                "\t\t\"saleType\": \"DGE\",\n" +
//                "\t\t\"status\": \"\",\n" +
//                "\t\t\"tokenId\": \"A01\",\n" +
//                "\t\t\"userId\": 1004,\n" +
//                "\t\t\"userName\": \"\"\n" +
//                "\t}, {\n" +
//                "\t\t\"deviceCode\": \"C\",\n" +
//                "\t\t\"deviceId\": \"800587c60566abfe\",\n" +
//                "\t\t\"deviceMapId\": 0,\n" +
//                "\t\t\"ebetExpiryDatetime\": \"2016-09-18 16:02:59.0\",\n" +
//                "\t\t\"ebetRequestDatetime\": \"2016-09-18 15:47:59.0\",\n" +
//                "\t\t\"mobileNumber\": \"\",\n" +
//                "\t\t\"modelId\": 1,\n" +
//                "\t\t\"organizationId\": 3,\n" +
//                "\t\t\"processedDatetime\": \"\",\n" +
//                "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"113\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":true,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":10}\",\n" +
//                "\t\t\"requestId\": 9,\n" +
//                "\t\t\"saleType\": \"DGE\",\n" +
//                "\t\t\"status\": \"\",\n" +
//                "\t\t\"tokenId\": \"C02\",\n" +
//                "\t\t\"userId\": 1004,\n" +
//                "\t\t\"userName\": \"\"\n" +
//                "\t}, {\n" +
//                "\t\t\"deviceCode\": \"C\",\n" +
//                "\t\t\"deviceId\": \"800587c60566abfe\",\n" +
//                "\t\t\"deviceMapId\": 0,\n" +
//                "\t\t\"ebetExpiryDatetime\": \"2016-09-18 16:02:26.0\",\n" +
//                "\t\t\"ebetRequestDatetime\": \"2016-09-18 15:47:26.0\",\n" +
//                "\t\t\"mobileNumber\": \"\",\n" +
//                "\t\t\"modelId\": 1,\n" +
//                "\t\t\"organizationId\": 3,\n" +
//                "\t\t\"processedDatetime\": \"\",\n" +
//                "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"113\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":1,\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"06\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":2,\\\"totalPurchaseAmt\\\":20}\",\n" +
//                "\t\t\"requestId\": 8,\n" +
//                "\t\t\"saleType\": \"DGE\",\n" +
//                "\t\t\"status\": \"\",\n" +
//                "\t\t\"tokenId\": \"C01\",\n" +
//                "\t\t\"userId\": 1004,\n" +
//                "\t\t\"userName\": \"\"\n" +
//                "\t}]\n" +
//                "}";
//
//        response = "{\n" +
//                "\t\"errorCode\": 0,\n" +
//                "\t\"errorMsg\": \"\",\n" +
//                "\t\"isSuccess\": \"true\",\n" +
//                "\t\"responseData\": [{\n" +
//                "\t\t\"deviceCode\": \"C\",\n" +
//                "\t\t\"deviceId\": \"800587c60566abfe\",\n" +
//                "\t\t\"deviceMapId\": 0,\n" +
//                "\t\t\"ebetExpiryDatetime\": \"2016-09-21 16:40:02.0\",\n" +
//                "\t\t\"ebetRequestDatetime\": \"2016-09-21 16:10:02.0\",\n" +
//                "\t\t\"mobileNumber\": \"\",\n" +
//                "\t\t\"modelId\": 1,\n" +
//                "\t\t\"organizationId\": 3,\n" +
//                "\t\t\"processedDatetime\": \"\",\n" +
//                "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"306\\\"],\\\"gameName\\\":\\\"TwelveByTwentyFour\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"12\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"01,02,03,04,05,07,09,11,12,16,17,21\\\",\\\"betName\\\":\\\"Direct12\\\"}],\\\"noOfPanel\\\":1,\\\"totalPurchaseAmt\\\":\\\"0.5\\\"}\",\n" +
//                "\t\t\"requestId\": 233,\n" +
//                "\t\t\"saleType\": \"DGE\",\n" +
//                "\t\t\"status\": \"\",\n" +
//                "\t\t\"tokenId\": \"C02\",\n" +
//                "\t\t\"userId\": 11004,\n" +
//                "\t\t\"userName\": \"\"\n" +
//                "\t}, {\n" +
//                "\t\t\"deviceCode\": \"C\",\n" +
//                "\t\t\"deviceId\": \"800587c60566abfe\",\n" +
//                "\t\t\"deviceMapId\": 0,\n" +
//                "\t\t\"ebetExpiryDatetime\": \"2016-09-21 16:38:18.0\",\n" +
//                "\t\t\"ebetRequestDatetime\": \"2016-09-21 16:08:18.0\",\n" +
//                "\t\t\"mobileNumber\": \"\",\n" +
//                "\t\t\"modelId\": 1,\n" +
//                "\t\t\"organizationId\": 3,\n" +
//                "\t\t\"processedDatetime\": \"\",\n" +
//                "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"drawData\\\":[],\\\"noOfDraws\\\":1,\\\"gameName\\\":\\\"MiniRoulette\\\"},\\\"noOfPanel\\\":33,\\\"betTypeData\\\":[{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"06\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"01,03,05,07,09,11\\\",\\\"betName\\\":\\\"allOddNumbers\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"06\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"01,03,05,07,09,11\\\",\\\"betName\\\":\\\"redNumbers\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"06\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"02,04,06,08,10,12\\\",\\\"betName\\\":\\\"allEvenNumbers\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"06\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"02,04,06,08,10,12\\\",\\\"betName\\\":\\\"blackNumbers\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"03\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"01,02,03\\\",\\\"betName\\\":\\\"firstColumn\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"03\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"04,05,06\\\",\\\"betName\\\":\\\"secondColumn\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"03\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"07,08,09\\\",\\\"betName\\\":\\\"thirdColumn\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"03\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"10,11,12\\\",\\\"betName\\\":\\\"fourthColumn\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"04\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"03,06,09,12\\\",\\\"betName\\\":\\\"firstRow\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"04\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"02,05,08,11\\\",\\\"betName\\\":\\\"secondRow\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"04\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"01,04,07,10\\\",\\\"betName\\\":\\\"thirdRow\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"04\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"02,03,05,06\\\",\\\"betName\\\":\\\"firstQuarter\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"04\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"01,02,05,04\\\",\\\"betName\\\":\\\"secondQuarter\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"04\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"05,06,08,09\\\",\\\"betName\\\":\\\"thirdQuarter\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"04\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"04,05,07,08\\\",\\\"betName\\\":\\\"fourthQuarter\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"04\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"07,08,10,11\\\",\\\"betName\\\":\\\"fifthQuarter\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"04\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"08,09,11,12\\\",\\\"betName\\\":\\\"sixthQuarter\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"00\\\",\\\"betName\\\":\\\"zero\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"06\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"01,02,03,04,05,06\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"06\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"04,05,06,07,08,09\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"06\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"07,08,09,10,11,12\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"02\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"06\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"07\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"08\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"09\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"10\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"roulette\\\"},{\\\"unitPrice\\\":100,\\\"noPicked\\\":\\\"01\\\",\\\"noOfLines\\\":\\\"1\\\",\\\"betAmtMul\\\":\\\"1\\\",\\\"isQp\\\":\\\"false\\\",\\\"pickedNumbers\\\":\\\"12\\\",\\\"betName\\\":\\\"roulette\\\"}],\\\"totalPurchaseAmt\\\":\\\"3300.0\\\"}\",\n" +
//                "\t\t\"requestId\": 230,\n" +
//                "\t\t\"saleType\": \"DGE\",\n" +
//                "\t\t\"status\": \"\",\n" +
//                "\t\t\"tokenId\": \"C01\",\n" +
//                "\t\t\"userId\": 11004,\n" +
//                "\t\t\"userName\": \"\"\n" +
//                "\t}, {\n" +
//                "\t\t\"deviceCode\": \"G\",\n" +
//                "\t\t\"deviceId\": \"fa3725e2384284d0\",\n" +
//                "\t\t\"deviceMapId\": 0,\n" +
//                "\t\t\"ebetExpiryDatetime\": \"2016-09-21 16:40:49.0\",\n" +
//                "\t\t\"ebetRequestDatetime\": \"2016-09-21 16:10:49.0\",\n" +
//                "\t\t\"mobileNumber\": \"\",\n" +
//                "\t\t\"modelId\": 1,\n" +
//                "\t\t\"organizationId\": 3,\n" +
//                "\t\t\"processedDatetime\": \"\",\n" +
//                "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"228\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"1\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":9,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":\\\"1\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":9,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"06\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":\\\"1\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":9,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"10\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":\\\"1\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":9,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"11\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":\\\"1\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":9,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"12\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":\\\"450.0\\\"}\",\n" +
//                "\t\t\"requestId\": 235,\n" +
//                "\t\t\"saleType\": \"DGE\",\n" +
//                "\t\t\"status\": \"\",\n" +
//                "\t\t\"tokenId\": \"G02\",\n" +
//                "\t\t\"userId\": 11004,\n" +
//                "\t\t\"userName\": \"\"\n" +
//                "\t}, {\n" +
//                "\t\t\"deviceCode\": \"G\",\n" +
//                "\t\t\"deviceId\": \"fa3725e2384284d0\",\n" +
//                "\t\t\"deviceMapId\": 0,\n" +
//                "\t\t\"ebetExpiryDatetime\": \"2016-09-21 16:40:42.0\",\n" +
//                "\t\t\"ebetRequestDatetime\": \"2016-09-21 16:10:42.0\",\n" +
//                "\t\t\"mobileNumber\": \"\",\n" +
//                "\t\t\"modelId\": 1,\n" +
//                "\t\t\"organizationId\": 3,\n" +
//                "\t\t\"processedDatetime\": \"\",\n" +
//                "\t\t\"requestData\": \"{\\\"commonSaleData\\\":{\\\"isAdvancePlay\\\":false,\\\"isDrawManual\\\":true,\\\"noOfDraws\\\":1,\\\"drawData\\\":[\\\"228\\\"],\\\"gameName\\\":\\\"OneToTwelve\\\"},\\\"betTypeData\\\":[{\\\"noPicked\\\":\\\"1\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"01\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":\\\"1\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"02\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":\\\"1\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"03\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":\\\"1\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"04\\\",\\\"betName\\\":\\\"oneToTwelve\\\"},{\\\"noPicked\\\":\\\"1\\\",\\\"isQp\\\":false,\\\"betAmtMul\\\":1,\\\"QPPreGenerated\\\":false,\\\"pickedNumbers\\\":\\\"05\\\",\\\"betName\\\":\\\"oneToTwelve\\\"}],\\\"noOfPanel\\\":5,\\\"totalPurchaseAmt\\\":\\\"50.0\\\"}\",\n" +
//                "\t\t\"requestId\": 234,\n" +
//                "\t\t\"saleType\": \"DGE\",\n" +
//                "\t\t\"status\": \"\",\n" +
//                "\t\t\"tokenId\": \"G01\",\n" +
//                "\t\t\"userId\": 11004,\n" +
//                "\t\t\"userName\": \"\"\n" +
//                "\t}]\n" +
//                "}";

        TpsGamesClass.getInstance().stopLoader();

        if (requestedMethod.equalsIgnoreCase("fail") || response.equalsIgnoreCase("Failed")) {
            TpsGamesClass.getInstance().stopLoader();
            TpsGamesClass.getInstance().showAToast("Server error", ActivityEbetSlipTpsGame.this, Toast.LENGTH_SHORT);
            return;
        }

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject != null) {
                boolean isSuccess = jsonObject.optBoolean("IsSuccess");

                boolean isSuccess1 = jsonObject.optBoolean("isSuccess");
                if (!isSuccess && !isSuccess1) {
                    TpsGamesClass.getInstance().stopLoader();
                    TpsGamesClass.getInstance().showAToast(jsonObject.optString("errorMsg"), ActivityEbetSlipTpsGame.this, Toast.LENGTH_SHORT);
                    return;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (requestedMethod.equalsIgnoreCase("ebetSlipCancel")) {

            //{"errorCode":0,"errorMsg":"","isSuccess":"true"}
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject != null && jsonObject.optBoolean("isSuccess") == true) {
                    fetchEbet();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (requestedMethod.equalsIgnoreCase("ebetSlipSale")) {
//            Intent intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivityAllGames.class);
//            intent.putExtra("response", response);
//            startActivityForResult(intent, 001);
            Intent intent = null;
            if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("PT7003")) {
                intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivityAllGamesTousei.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            } else if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("TPS515")) {

                TpsGamesClass.getInstance().setTps515PrintCall(this);
                tps515Response = response;
                tps515Method = requestedMethod.toString();
                TpsGamesClass.getInstance().checkUsbPrintPermission(ActivityEbetSlipTpsGame.this);


            } else if (TpsGamesClass.getInstance().getDeviceName().contains("rk30")) {
                TpsGamesClass.getInstance().setPrintResponseForAzt(response);
                intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivityAZT.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            } else {
                intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivityAllGames.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            }
//            if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("PT7003")) {
//                intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivityAllGamesTousei.class);
//                intent.putExtra("response", response);
//                startActivityForResult(intent, 054);
//            } else if (TpsGamesClass.getInstance().getDeviceName().contains("rk30")) {
//                TpsGamesClass.getInstance().setPrintResponseForAzt(response);
//                intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivityAZT.class);
//                intent.putExtra("response", response);
//                startActivityForResult(intent, 054);
//            } else {
//                intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivityAllGames.class);
//                intent.putExtra("response", response);
//                startActivityForResult(intent, 054);
//            }

        }
        if (requestedMethod.equalsIgnoreCase("fetchEbetSlip")) {

//        response = json;
//        EbetSlipBean ebetSlipBean = TpsGamesClass.getInstance().getGson().fromJson(response, EbetSlipBean.class);

            Log.v("", "");
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject == null) {
                    TpsGamesClass.getInstance().stopLoader();
                    TpsGamesClass.getInstance().showAToast("No data", ActivityEbetSlipTpsGame.this, Toast.LENGTH_SHORT);
                    return;
                }
                adapterValues = new HashMap<>();
                EbetSlipBean ebetSlipBean = new EbetSlipBean();
                ebetSlipBean.setErrorCode(jsonObject.optString("errorCode"));
                ebetSlipBean.setErrorMsg(jsonObject.optString("errorMsg"));
                ebetSlipBean.setIsSuccess(jsonObject.optBoolean("isSuccess"));
                if (!ebetSlipBean.isSuccess()) {
                    TpsGamesClass.getInstance().stopLoader();
                    TpsGamesClass.getInstance().showAToast(ebetSlipBean.getErrorMsg(), ActivityEbetSlipTpsGame.this, Toast.LENGTH_SHORT);
                    return;
                }

                JSONArray jsonArray = jsonObject.optJSONArray("responseData");

                ArrayList<EbetSlipBean.ResponseData> responseDatas = new ArrayList<>();
                ArrayList<SportsSaleBean> sportsSaleBeans = new ArrayList<>();
                EbetSlipBean.ResponseData responseData;
                for (int i = 0; i < jsonArray.length(); i++) {
                    responseData = new EbetSlipBean.ResponseData();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    if (jsonObject1 != null) {
                        responseData.setDeviceCode(jsonObject1.optString("deviceCode"));
                        responseData.setDeviceId(jsonObject1.optString("deviceId"));
                        ArrayList<String> id = adapterValues.get(responseData.getDeviceId());
                        if (id == null || id.size() == 0) {

                            id = new ArrayList<>();
                            id.add(i + "");
                            adapterValues.put(responseData.getDeviceId(), id);
                        } else {
                            if (id != null && id.size() == 1) {

                                id.add(i + "");
                                adapterValues.put(responseData.getDeviceId(), id);
                            }
                        }
                        responseData.setDeviceMapId(jsonObject1.optString("deviceMapId"));
                        responseData.setEbetExpiryDatetime(jsonObject1.optString("ebetExpiryDatetime"));
                        responseData.setEbetRequestDatetime(jsonObject1.optString("ebetRequestDatetime"));
                        responseData.setMobileNumber(jsonObject1.optString("mobileNumber"));
                        responseData.setModelId(jsonObject1.optString("modelId"));
                        responseData.setOrganizationId(jsonObject1.optString("organizationId"));
                        responseData.setProcessedDatetime(jsonObject1.optString("processedDatetime"));
                        responseData.setRequestId(jsonObject1.optString("requestId"));
                        responseData.setSaleType(jsonObject1.optString("saleType"));
                        responseData.setStatus(jsonObject1.optString("status"));
                        responseData.setTokenId(jsonObject1.optString("tokenId"));
                        responseData.setUserId(jsonObject1.optString("userId"));
                        responseData.setUserName(jsonObject1.optString("userName"));
                        responseDatas.add(responseData);
                        String buyJson = jsonObject1.optString("requestData");
                        if (responseData.getSaleType().equalsIgnoreCase("DGE")) {
                            JSONObject jsonObjecto = new JSONObject(jsonObject1.optString("requestData"));
                            if (jsonObjecto != null) {
                                JSONObject commonSale = jsonObjecto.optJSONObject("commonSaleData");

                                EbetSlipBean.ResponseData.CommonSaleDataEbet commonSaleDataEbet = new EbetSlipBean.ResponseData.CommonSaleDataEbet();
                                commonSaleDataEbet.setIsAdvancePlay(commonSale.optBoolean("isAdvancePlay"));
                                commonSaleDataEbet.setIsDrawManual(commonSale.optBoolean("isDrawManual"));

                                commonSaleDataEbet.setNoOfDraws(commonSale.optInt("noOfDraws"));

                                if (commonSale != null) {
                                    JSONArray jsonArray1 = commonSale.optJSONArray("drawData");
                                    if (jsonArray1 != null) {
                                        List<String> draws = new ArrayList<>();
                                        for (int j = 0; j < jsonArray1.length(); j++) {
                                            draws.add(jsonArray1.get(j).toString());
                                        }
                                        commonSaleDataEbet.setDrawData(draws);
                                    }

                                    commonSaleDataEbet.setGameName(commonSale.optString("gameName"));
                                }
//                            int noOf = jsonObjecto.optInt("noOfDraws");
                                JSONArray betType = jsonObjecto.optJSONArray("betTypeData");
                                if (betType != null) {
                                    List<EbetSlipBean.ResponseData.CommonSaleDataEbet.BetTypeData> betTypeDatas = new ArrayList<>();
                                    for (int k = 0; k < betType.length(); k++) {
                                        JSONObject betData = betType.optJSONObject(k);

                                        if (betData != null) {

                                            EbetSlipBean.ResponseData.CommonSaleDataEbet.BetTypeData betTypeData = new EbetSlipBean.ResponseData.CommonSaleDataEbet.BetTypeData();
                                            betTypeData.setNoPicked(betData.optInt("noPicked"));
                                            betTypeData.setIsQp(betData.optBoolean("isQp"));
                                            betTypeData.setBetAmtMul(betData.optInt("betAmtMul"));
                                            betTypeData.setQPPreGenerated(betData.optBoolean("QPPreGenerated"));
                                            betTypeData.setPickedNumbers(betData.optString("pickedNumbers"));
                                            betTypeData.setBetName(betData.optString("betName"));
                                            betTypeData.setUnitPrice(betData.optDouble("unitPrice"));
                                            betTypeData.setNoOfLines(betData.optString("noOfLines"));
                                            betTypeDatas.add(betTypeData);
                                        }
                                    }
                                    commonSaleDataEbet.setBetTypeData(betTypeDatas);
                                }
                                commonSaleDataEbet.setNoOfPanel(jsonObjecto.optInt("noOfPanel"));
                                commonSaleDataEbet.setTotalPurchaseAmt(jsonObjecto.optDouble("totalPurchaseAmt"));
                                responseData.setRequestData(commonSaleDataEbet);
                            }
                        } else {

                            SportsSaleBean sportsSaleBean = TpsGamesClass.getInstance().getSportsBean(buyJson);
                            sportsSaleBeans.add(sportsSaleBean);


                            Log.i("", buyJson);
                        }


                        Log.v("", "");

                    }

                }
                ebetSlipBean.setResponseData(responseDatas);
                ebetSlipBean.setResponseDataSale(sportsSaleBeans);

//            Iterator it = adapterValues.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry pair = (Map.Entry) it.next();
//                System.out.println(pair.getKey() + " = " + pair.getValue());
//                responseDatasAdapter.add(ebetSlipBean.getResponseData().get(Integer.parseInt(pair.getValue() + "")));
////                        it.remove(); // avoids a ConcurrentModificationException
//            }
                grid_view.setAdapter(new EbetSlipAdapter(ebetSlipBean));
                TpsGamesClass.getInstance().stopLoader();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (requestedMethod.equalsIgnoreCase("ebetSlipSaleSports")) {


            Intent intent = null;
//            if (TpsGamesClass.getInstance().getDeviceName().contains("rk30")) {
//                intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivitySportsAzt.class);
//            } else {
//                intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivitySportsGame.class);
//            }

            if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("TPS515")) {
                TpsGamesClass.getInstance().setTps515PrintCall(this);
                tps515Response = response;
                tps515Method = requestedMethod.toString();
                TpsGamesClass.getInstance().checkUsbPrintPermission(ActivityEbetSlipTpsGame.this);
            } else{
                if (TpsGamesClass.getInstance().getDeviceName().contains("rk30")) {
                    intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivitySportsAzt.class);
                } else if(TpsGamesClass.getInstance().getDeviceName().contains("7003")){
                    intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivitySportsGameTousei.class);
                }
                else {
                    intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivitySportsGame.class);
                }
                intent.putExtra("response", response);
                startActivityForResult(intent, 001);
            }

        }

        TpsGamesClass.getInstance().stopLoader();
        Log.v("", "");

    }

    private boolean isPurchCall = false;

    private void purchase(EbetSlipBean.ResponseData responseData) {
        if (isPurchCall) {
            return;
        }
        if (responseData.getSaleType().equalsIgnoreCase("dge")) {
            isPurchCall = true;
            EbetSlipBean.ResponseData.CommonSaleDataEbet commonSaleDataEbet = responseData.getRequestData();
            try {

                JSONObject jsonObject = new JSONObject();


                JSONObject commonData = new JSONObject();
                // default value need to be fixed by backend
                commonData.put("isAdvancePlay", false);
                commonData.put("isDrawManual", commonSaleDataEbet.isDrawManual());
                commonData.put("noOfDraws", commonSaleDataEbet.getNoOfDraws());
                List<String> draws = commonSaleDataEbet.getDrawData();
                JSONArray drawArray = new JSONArray();
                for (String s : draws) {
                    JSONObject jsonObject1 = new JSONObject(s);
                    drawArray.put(jsonObject1);
                }
                commonData.put("drawData", drawArray);
                commonData.put("gameName", commonSaleDataEbet.getGameName());
                jsonObject.put("commonSaleData", commonData);
                List<EbetSlipBean.ResponseData.CommonSaleDataEbet.BetTypeData> betTypeDatas = commonSaleDataEbet.getBetTypeData();

                JSONArray betyTypeArray = new JSONArray();
                for (EbetSlipBean.ResponseData.CommonSaleDataEbet.BetTypeData betTypeData : betTypeDatas) {
                    JSONObject betTypeDataObject = new JSONObject();
                    betTypeDataObject.put("noPicked", betTypeData.getNoPicked() + "");
                    betTypeDataObject.put("isQp", betTypeData.isQp());
                    betTypeDataObject.put("betAmtMul", betTypeData.getBetAmtMul());
                    betTypeDataObject.put("QPPreGenerated", betTypeData.isQPPreGenerated());
                    betTypeDataObject.put("pickedNumbers", betTypeData.getPickedNumbers());
                    betTypeDataObject.put("betName", betTypeData.getBetName());
                    if (!Double.isNaN(betTypeData.getUnitPrice())) {
                        betTypeDataObject.put("unitPrice", betTypeData.getUnitPrice());
                    }
                    if (betTypeData.getNoOfLines() != null && betTypeData.getNoOfLines().trim().length() > 0)
                        betTypeDataObject.put("noOfLines", betTypeData.getNoOfLines());

                    betyTypeArray.put(betTypeDataObject);

                }
                jsonObject.put("betTypeData", betyTypeArray);
                jsonObject.put("noOfPanel", commonSaleDataEbet.getNoOfPanel());
                jsonObject.put("totalPurchaseAmt", commonSaleDataEbet.getTotalPurchaseAmt() + "");
                jsonObject.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
                jsonObject.put("tokenId", responseData.getTokenId());
                jsonObject.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());
                String respo = jsonObject.toString();
                String methodToCall = "";

                if (responseData.getRequestData().getGameName().equalsIgnoreCase("twelveByTwentyFour")) {
                    methodToCall = "twelveByTwentyFourBuy";
                } else if (responseData.getRequestData().getGameName().equalsIgnoreCase("KenoSix")) {
                    methodToCall = "kenoSixBuy";
                } else if (responseData.getRequestData().getGameName().equalsIgnoreCase("MiniRoulette")) {
                    methodToCall = "oneToTwelveRouletteBuy";
                } else if (responseData.getRequestData().getGameName().equalsIgnoreCase("OneToTwelve")) {
                    methodToCall = "oneToTwelveBuy";
                } else if (responseData.getRequestData().getGameName().equalsIgnoreCase("KenoTwo")) {
                    methodToCall = "kenoTwoBuy";
                }
                String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/" + methodToCall + ".action?json=" + respo;
                HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityEbetSlipTpsGame.this, "ebet slip", ActivityEbetSlipTpsGame.this, "ebetSlipSale");
                TpsGamesClass.getInstance().startLoader(ActivityEbetSlipTpsGame.this);
                httpRequest.executeTask();
                Log.e("", "");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
//            SportsSaleBean sportsSaleBean = responseData.getResponseDataSports().get(clickSportIndex);
//            String gameId, String gameTypeId, String drawInfo, String drawCount, String ticketAmt
            if (sportsSaleBean == null) {
                return;
            }
            isPurchCall = true;
            String gameId = sportsSaleBean.getGameId().substring(sportsSaleBean.getGameId().indexOf("=") + 1, sportsSaleBean.getGameId().length());
            String gameTypeId = sportsSaleBean.getGameTypeId().substring(sportsSaleBean.getGameTypeId().indexOf("=") + 1, sportsSaleBean.getGameTypeId().length());
            String drawCoount = sportsSaleBean.getDrawCount().substring(sportsSaleBean.getDrawCount().indexOf("=") + 1, sportsSaleBean.getDrawCount().length());
            String ticketAmount = sportsSaleBean.getTicketAmt().substring(sportsSaleBean.getTicketAmt().indexOf("=") + 1, sportsSaleBean.getTicketAmt().length());
            String url = TpsGamesClass.getInstance().getTicketSaleServicesForSle(ActivityEbetSlipTpsGame.this, TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName()
                    , gameId, gameTypeId, sportsSaleBean.getDrawInfoString().substring(sportsSaleBean.getDrawInfoString().indexOf("=") + 1, sportsSaleBean.getDrawInfoString().length()), drawCoount, ticketAmount, sportsSaleBean);
            url = url + "&tokenId=" + responseData.getTokenId();
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityEbetSlipTpsGame.this, "ebet slip", ActivityEbetSlipTpsGame.this, "ebetSlipSaleSports");
            TpsGamesClass.getInstance().startLoader(ActivityEbetSlipTpsGame.this);
            httpRequest.executeTask();
        }

        Log.e("", "");
    }


    protected void deleteEbetSlip(EbetSlipBean.ResponseData responseData) {

        String url = Utility.baseUrl + Utility.portNumber + "/LMSLinuxNew/rest/tpEBetMgmt/cancelEBetSaleRequest";
        HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityEbetSlipTpsGame.this, "ebet_slip", ActivityEbetSlipTpsGame.this, "ebetSlipCancel", Utility.eBetSlipHeader);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("username", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            jsonObject.put("requestId", responseData.getRequestId());
            httpRequest.setIsParams(true, jsonObject.toString());
        } catch (Exception e) {

        }
        TpsGamesClass.getInstance().startLoader(ActivityEbetSlipTpsGame.this);
        httpRequest.executeTask();
        Log.e("", "");

    }

    SportsSaleBean sportsSaleBean;

    @Override
    public void onUsbPermissionSuccess() {
        Intent intent;
        if(tps515Method.equalsIgnoreCase("ebetSlipSale")){
            intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivityDgeExternalPrinter.class);
            intent.putExtra("response", tps515Response);
            startActivityForResult(intent, 054);
        }else{
            intent = new Intent(ActivityEbetSlipTpsGame.this, PrintActivitySportsExternal.class);
            intent.putExtra("response", tps515Response);
            startActivityForResult(intent, 001);
        }

    }

    @Override
    public void onPermissionDenied() {

    }

    private class EbetSlipAdapter extends BaseAdapter {

        private LinearLayout delete_layout, delete_layout1, buy, buy1, display;
        private TextView buyNow, buyNow1, buyNowSub, buyNowSub1, number, number1, game, game1, tablet_name, deviceid, tablet_name1, gamecode, gamecode1, gametype, gametype1, qp, qp1, price, price1, noDataAvailable, noDataAvailable1, draws, draws1;
        private ListView all_number, all_number1;
        private EbetSlipBean ebetSlipBean;
        private int id = -1, id1 = -1;
        private int position = -1;
        EbetSlipBean.ResponseData responseData, responseData1;
        SportsSaleBean responseSport, responseSport1;

        public EbetSlipAdapter(EbetSlipBean ebetSlipBean) {
            this.ebetSlipBean = ebetSlipBean;
            usedIndex.clear();
        }

        private void init(View view, final int position) {
            delete_layout = (LinearLayout) view.findViewById(R.id.delete_layout);
            delete_layout1 = (LinearLayout) view.findViewById(R.id.delete_layout1);
            tablet_name = (TextView) view.findViewById(R.id.tablet_name);
            tablet_name1 = (TextView) view.findViewById(R.id.tablet_name1);
            gamecode = (TextView) view.findViewById(R.id.gamecode);
            gamecode.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.MEDIUM));
            gamecode1 = (TextView) view.findViewById(R.id.gamecode1);
            gamecode1.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.MEDIUM));
            gametype = (TextView) view.findViewById(R.id.gametype);
            gametype1 = (TextView) view.findViewById(R.id.gametype1);

            buyNow = (TextView) view.findViewById(R.id.buyNow);
            buyNow.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
            buyNow1 = (TextView) view.findViewById(R.id.buyNow1);
            buyNow1.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
            buyNowSub = (TextView) view.findViewById(R.id.buyNowSub);
            buyNowSub.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.LIGHT));
            buyNowSub1 = (TextView) view.findViewById(R.id.buyNowSub1);
            buyNowSub1.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.LIGHT));


            display = (LinearLayout) view.findViewById(R.id.display);

            gametype.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.MEDIUM));
            gametype1.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.MEDIUM));
            qp = (TextView) view.findViewById(R.id.qp);
            qp1 = (TextView) view.findViewById(R.id.qp1);
            price = (TextView) view.findViewById(R.id.price);
            price1 = (TextView) view.findViewById(R.id.price1);
            all_number = (ListView) view.findViewById(R.id.all_number);
            all_number1 = (ListView) view.findViewById(R.id.all_number1);
            noDataAvailable = (TextView) view.findViewById(R.id.noDataAvailable);
            noDataAvailable1 = (TextView) view.findViewById(R.id.noDataAvailable1);
            buy = (LinearLayout) view.findViewById(R.id.buy);
            buy1 = (LinearLayout) view.findViewById(R.id.buy1);
            draws = (TextView) view.findViewById(R.id.draws);
            draws.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.LIGHT));
            draws1 = (TextView) view.findViewById(R.id.draws);
            draws1.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.LIGHT));

            game = (TextView) view.findViewById(R.id.game);
            game.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.LIGHT));
            game1 = (TextView) view.findViewById(R.id.game1);
            game1.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.LIGHT));

            number = (TextView) view.findViewById(R.id.number_ebet);
            number.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.LIGHT));
            number1 = (TextView) view.findViewById(R.id.number1);
            number1.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.LIGHT));


            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int position = 0;
//                    String deviceId = deviceid.getText().toString().replace("#", "");
//                    for (EbetSlipBean.ResponseData responseData : ebetSlipBean.getResponseData()) {
//                        if (responseData.getDeviceId().equalsIgnoreCase(deviceId)) {
//                            break;
//                        }
//                        position++;
//                    }
                    Iterator entries = adapterValues.entrySet().iterator();
                    int index = 0;
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        if (index == position) {
                            ArrayList<String> strings = (ArrayList<String>) entry.getValue();

                            try {
                                sportsSaleBean = ebetSlipBean.getResponseDataSale().get(0);
                            } catch (Exception e) {

                            }

                            purchase(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(0))));
                            break;
                        }
                        index++;

                    }


//                        TpsGamesClass.getInstance().showAToast(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(0))).getTokenId()+"", ActivityEbetSlipTpsGame.this,Toast.LENGTH_SHORT);
                }
            });
            buy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int position = 0;
//                    String deviceId = deviceid.getText().toString().replace("#", "");
//                    for (EbetSlipBean.ResponseData responseData : ebetSlipBean.getResponseData()) {
//                        if (responseData.getDeviceId().equalsIgnoreCase(deviceId)) {
//                            break;
//                        }
//                        position++;
//                    }
                    Iterator entries = adapterValues.entrySet().iterator();
                    int index = 0;
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        if (index == position) {
                            ArrayList<String> strings = (ArrayList<String>) entry.getValue();
                            try {
                                sportsSaleBean = ebetSlipBean.getResponseDataSale().get(1);
                                if (sportsSaleBean == null) {
                                    sportsSaleBean = ebetSlipBean.getResponseDataSale().get(0);
                                }
                            } catch (Exception e) {

                                try {

                                } catch (Exception e1) {
                                    sportsSaleBean = ebetSlipBean.getResponseDataSale().get(0);
                                }
                            }

                            if (strings != null && strings.size() > 1) {
                                purchase(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(1))));
                            } else if (strings != null && strings.size() > 0) {
                                purchase(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(0))));
                            }


                            break;
                        }
                        index++;

                    }
//                    ArrayList<String> strings = adapterValues.get(ebetSlipBean.getResponseData().get(position).getDeviceId());
//                    purchase(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(1))));
//                        TpsGamesClass.getInstance().showAToast(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(1))).getTokenId()+"", ActivityEbetSlipTpsGame.this,Toast.LENGTH_SHORT);
                }
            });

            delete_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int position = 0;
//                    String deviceId = deviceid.getText().toString().replace("#", "");
//                    for (EbetSlipBean.ResponseData responseData : ebetSlipBean.getResponseData()) {
//                        if (responseData.getDeviceId().equalsIgnoreCase(deviceId)) {
//                            break;
//                        }
//                        position++;
//                    }
                    Iterator entries = adapterValues.entrySet().iterator();
                    int index = 0;
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        if (index == position) {
                            ArrayList<String> strings = (ArrayList<String>) entry.getValue();
                            try {
                                sportsSaleBean = ebetSlipBean.getResponseDataSale().get(Integer.parseInt(strings.get(0)));
                            } catch (Exception e) {

                            }

                            deleteEbetSlip(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(0))));
                            break;
                        }
                        index++;

                    }
//                    ArrayList<String> strings = adapterValues.get(ebetSlipBean.getResponseData().get(position).getDeviceId());
//                    deleteEbetSlip(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(0))));
                }
            });
            delete_layout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int position = 0;
//                    String deviceId = deviceid.getText().toString().replace("#", "");
//                    for (EbetSlipBean.ResponseData responseData : ebetSlipBean.getResponseData()) {
//                        if (responseData.getDeviceId().equalsIgnoreCase(deviceId)) {
//                            break;
//                        }
//                        position++;
//                    }
                    Iterator entries = adapterValues.entrySet().iterator();
                    int index = 0;
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        if (index == position) {
                            ArrayList<String> strings = (ArrayList<String>) entry.getValue();
                            try {
                                sportsSaleBean = ebetSlipBean.getResponseDataSale().get(Integer.parseInt(strings.get(1)));
                            } catch (Exception e) {

                            }
                            if (strings != null && strings.size() > 1) {
                                deleteEbetSlip(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(1))));
                            } else if (strings != null && strings.size() > 0) {
                                deleteEbetSlip(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(0))));
                            }


                            break;
                        }
                        index++;

                    }
//                    ArrayList<String> strings = adapterValues.get(ebetSlipBean.getResponseData().get(position).getDeviceId());
//                    deleteEbetSlip(ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(1))));
                }
            });
            deviceid = (TextView) view.findViewById(R.id.deviceid);
//            noDataAvailable.setVisibility(View.GONE);
//            noDataAvailable1.setVisibility(View.GONE);

            if (ebetSlipBean != null && ebetSlipBean.getResponseData() != null && ebetSlipBean.getResponseData().size() > 0) {

//                    responseData.getSaleType().equalsIgnoreCase("DGE")
                ArrayList<String> strings = null;
                try {
                    Iterator entries = adapterValues.entrySet().iterator();
                    int index = 0;
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        if (index == position) {
                            strings = (ArrayList<String>) entry.getValue();
                            break;
                        }
                        index++;

                    }
//                    strings = adapterValues.get(ebetSlipBean.getResponseData().get(position).getDeviceId());
                    this.position = position;
                } catch (Exception e) {
                    strings = null;
                }

                if ((position + 1) <= adapterValues.keySet().size() && strings != null) {
                    if (strings != null && strings.size() > 0) {
                        id = Integer.parseInt(strings.get(0));
                        display.setVisibility(View.VISIBLE);
                        responseData = ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(0)));
                        try {
                            responseSport = ebetSlipBean.getResponseDataSale().get(0);
                        } catch (Exception e) {

                        }

                        deviceid.setText("#" + responseData.getDeviceCode());
                        deviceid.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
                        tablet_name.setText(responseData.getTokenId());
                        tablet_name.setTypeface(TpsGamesClass.getInstance().getTypeFace("bold"));

                        if (responseData.getSaleType().equalsIgnoreCase("DGE")) {
                            gamecode.setText(responseData.getRequestData().getNoOfDraws() + "");
                            //TwelveByTwentyFour
                            String gameName = responseData.getRequestData().getBetTypeData().get(0).getBetName();
                            if (gameName.equalsIgnoreCase("")) {

                            }
                            if (responseData.getRequestData().getBetTypeData().get(0).isQp()) {
                                qp.setText("(QP)");
                            }
                            gametype.setText(responseData.getRequestData().getGameName());
                            price.setText("$" + responseData.getRequestData().getTotalPurchaseAmt());
                            price.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
//                        all_number.setAdapter(new SportsData(TpsGamesClass.getInstance().getSportsBean(responseSports)));
                            all_number.setAdapter(new NumberListAdapter(responseData.getRequestData().getBetTypeData(), ActivityEbetSlipTpsGame.this));
                        } else if (responseSport != null) {
                            gamecode.setText("1");
                            //TwelveByTwentyFour
//                                String gameName = responseData.getRequestData().getBetTypeData().get(0).getBetName();
//                                if (gameName.equalsIgnoreCase("")) {
//
//                                }
                            gametype.setText("Soccer");
                            String totalAmount = responseSport.getTicketAmt();
                            totalAmount = totalAmount.substring(totalAmount.indexOf("=") + 1, totalAmount.length());
                            price.setText("$" + totalAmount);
                            price.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
//                        all_number.setAdapter(new SportsData(TpsGamesClass.getInstance().getSportsBean(responseSports)));
                            all_number.setAdapter(new SportsData(responseSport));
                        }
                        all_number.setOnTouchListener(new ListView.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                int action = event.getAction();
                                switch (action) {
                                    case MotionEvent.ACTION_DOWN:
                                        // Disallow ScrollView to intercept touch events.
                                        v.getParent().requestDisallowInterceptTouchEvent(true);
                                        break;

                                    case MotionEvent.ACTION_UP:
                                        // Allow ScrollView to intercept touch events.
                                        v.getParent().requestDisallowInterceptTouchEvent(false);
                                        break;
                                }

                                // Handle ListView touch events.
                                v.onTouchEvent(event);
                                return true;
                            }
                        });
                        noDataAvailable.setVisibility(View.GONE);
                    }
                    if (strings != null && strings.size() > 1) {
                        id1 = Integer.parseInt(strings.get(1));
                        display.setVisibility(View.VISIBLE);
                        responseData1 = ebetSlipBean.getResponseData().get(Integer.parseInt(strings.get(1)));
                        try {

                            responseSport1 = ebetSlipBean.getResponseDataSale().get(1);
                        } catch (Exception e) {
                            try {
                                responseSport1 = ebetSlipBean.getResponseDataSale().get(0);
                            } catch (Exception e1) {

                            }

                        }

                        deviceid.setText("#" + responseData1.getDeviceCode());
                        deviceid.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
                        tablet_name1.setText(responseData1.getTokenId());
                        tablet_name1.setTypeface(TpsGamesClass.getInstance().getTypeFace("bold"));
                        if (responseData1.getSaleType().equalsIgnoreCase("dge")) {
                            gamecode1.setText(responseData1.getRequestData().getNoOfDraws() + "");
                            //TwelveByTwentyFour
                            String gameName = responseData1.getRequestData().getBetTypeData().get(0).getBetName();
                            if (gameName.equalsIgnoreCase("")) {

                            }
                            if (responseData1.getRequestData().getBetTypeData().get(0).isQp()) {
                                qp.setText("(QP)");
                            }
                            gametype1.setText(responseData1.getRequestData().getGameName());
                            price1.setText("$" + responseData1.getRequestData().getTotalPurchaseAmt());
                            price1.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
                            all_number1.setAdapter(new NumberListAdapter(responseData1.getRequestData().getBetTypeData(), ActivityEbetSlipTpsGame.this));
                        } else if (responseSport1 != null) {
                            gamecode1.setText("1");
                            //TwelveByTwentyFour
//                                String gameName = responseData.getRequestData().getBetTypeData().get(0).getBetName();
//                                if (gameName.equalsIgnoreCase("")) {
//
//                                }
                            gametype1.setText("Soccer");
                            String totalAmount = responseSport1.getTicketAmt();
                            totalAmount = totalAmount.substring(totalAmount.indexOf("=") + 1, totalAmount.length());
                            price1.setText("$" + totalAmount);
                            price1.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
//                        all_number.setAdapter(new SportsData(TpsGamesClass.getInstance().getSportsBean(responseSports)));
                            all_number1.setAdapter(new SportsData(responseSport1));
                        }
                        all_number1.setOnTouchListener(new ListView.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                int action = event.getAction();
                                switch (action) {
                                    case MotionEvent.ACTION_DOWN:
                                        // Disallow ScrollView to intercept touch events.
                                        v.getParent().requestDisallowInterceptTouchEvent(true);
                                        break;

                                    case MotionEvent.ACTION_UP:
                                        // Allow ScrollView to intercept touch events.
                                        v.getParent().requestDisallowInterceptTouchEvent(false);
                                        break;
                                }

                                // Handle ListView touch events.
                                v.onTouchEvent(event);
                                return true;
                            }
                        });
//                        gametype1.setText(responseData1.getRequestData().getBetTypeData().get(0).getBetName());
//                        price1.setText("$" + responseData1.getRequestData().getTotalPurchaseAmt());
//                        price1.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
                        noDataAvailable1.setVisibility(View.GONE);
                    }
                }

            } else {
                TpsGamesClass.getInstance().showAToast("No data Available!", ActivityEbetSlipTpsGame.this, Toast.LENGTH_SHORT);
            }


        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.layout_ebet_slip_items, null);

            init(view, position);
            return view;
        }

        public class NumberListAdapter extends BaseAdapter {
            List<EbetSlipBean.ResponseData.CommonSaleDataEbet.BetTypeData> betTypeData;
            Context context;
            LayoutInflater inflater;

            public NumberListAdapter(List<EbetSlipBean.ResponseData.CommonSaleDataEbet.BetTypeData> betTypeData, Context context) {
                this.betTypeData = betTypeData;
                this.context = context;
                inflater = LayoutInflater.from(context);
            }

            @Override
            public int getCount() {
                if (betTypeData == null) {
                    return 0;
                }
                return betTypeData.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = inflater.inflate(R.layout.layout_listview_adapter, null);
                TextView numbers = (TextView) convertView.findViewById(R.id.numbers);
                numbers.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
                numbers.setText(betTypeData.get(position).getPickedNumbers());
                return convertView;
            }
        }

        public class SportsData extends BaseAdapter {

            SportsSaleBean sportsSaleBean;
            LayoutInflater inflater;
            TextView h_plus, h, d, a, a_plus, teams;

            public SportsData(SportsSaleBean sportsSaleBean) {
                this.sportsSaleBean = sportsSaleBean;
                inflater = LayoutInflater.from(ActivityEbetSlipTpsGame.this);
            }

            @Override
            public int getCount() {
                if (sportsSaleBean == null)
                    return 0;

                return sportsSaleBean.getAllGame().length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = inflater.inflate(R.layout.layout_sport_list_view_adapter, null);

                String winningSelection = "";
                String game = sportsSaleBean.getAllGame()[position];
                String[] winning = sportsSaleBean.getDrawInfo()[position].split("[@]");
                if (winning.length > 1) {
                    winningSelection = winning[1];
                }


                teams = (TextView) convertView.findViewById(R.id.teams);
                teams.setText(game);
                h_plus = (TextView) convertView.findViewById(R.id.h_plus);
                h = (TextView) convertView.findViewById(R.id.h);
                d = (TextView) convertView.findViewById(R.id.d);
                a = (TextView) convertView.findViewById(R.id.a);

                a_plus = (TextView) convertView.findViewById(R.id.a_plus);
//                h.setBackgroundResource(R.drawable.sports_bg_selected);

                if (sportsSaleBean.getGameName().contains("13")) {
                    h_plus.setVisibility(View.GONE);
                    a_plus.setVisibility(View.GONE);
                } else {
                    h_plus.setVisibility(View.VISIBLE);
                    a_plus.setVisibility(View.VISIBLE);
                }
                if (winningSelection.contains(",")) {
                    String[] setAllSelection = winningSelection.split("[,]");
                    for (int k = 0; k < setAllSelection.length; k++) {
                        String setSelectionString = setAllSelection[k];
                        if (setSelectionString.equalsIgnoreCase("H%2B")) {
                            h_plus.setBackgroundResource(R.drawable.sports_bg_selected);
                        } else if (setSelectionString.equalsIgnoreCase("H")) {
                            h.setBackgroundResource(R.drawable.sports_bg_selected);
                        } else if (setSelectionString.equalsIgnoreCase("D")) {
                            d.setBackgroundResource(R.drawable.sports_bg_selected);
                        } else if (setSelectionString.equalsIgnoreCase("A")) {
                            a.setBackgroundResource(R.drawable.sports_bg_selected);
                        } else if (setSelectionString.equalsIgnoreCase("A%2B")) {
                            a_plus.setBackgroundResource(R.drawable.sports_bg_selected);
                        }

                    }
                } else {
                    if (winningSelection.equalsIgnoreCase("H%2B")) {
                        h_plus.setBackgroundResource(R.drawable.sports_bg_selected);
                    } else if (winningSelection.equalsIgnoreCase("H")) {
                        h.setBackgroundResource(R.drawable.sports_bg_selected);
                    } else if (winningSelection.equalsIgnoreCase("D")) {
                        d.setBackgroundResource(R.drawable.sports_bg_selected);
                    } else if (winningSelection.equalsIgnoreCase("A")) {
                        a.setBackgroundResource(R.drawable.sports_bg_selected);
                    } else if (winningSelection.equalsIgnoreCase("A%2B")) {
                        a_plus.setBackgroundResource(R.drawable.sports_bg_selected);
                    }
                }

                return convertView;
            }
        }


    }

}
