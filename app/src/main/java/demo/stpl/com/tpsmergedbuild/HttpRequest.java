package demo.stpl.com.tpsmergedbuild;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;

//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.interfaces.ResponseLis;


/**
 * Created by stpl on 9/5/2016. network request class
 */
public class HttpRequest {

    private String url;
    private ResponseLis responseLis;
    private String loadingMessage;
    private Context context;
    private URL urlRequest;
    private HttpURLConnection connection;
    private int requestTime = 60000;
    private String responseString;
    private String requestMethod;

    private boolean isConnectToInternet = false;
    private boolean isServerConnected = false;

    private String userName;

    private String headerData;
    private boolean isParams;
    private String params;


    public void setAllParameter(String url, ResponseLis responseLis, String loadingMessage, Context context, String requestMethod) {
        this.responseLis = responseLis;
        this.url = url;
        this.loadingMessage = loadingMessage;
        this.context = context;
        this.requestMethod = requestMethod;
    }

    public void setAllParameter(String url, ResponseLis responseLis, String loadingMessage, Context context, String requestMethod, String headerData) {
        this.responseLis = responseLis;
        this.url = url;
        this.loadingMessage = loadingMessage;
        this.context = context;
        this.requestMethod = requestMethod;
        this.headerData = headerData;
    }

    public void setIsParams(boolean isParams, String params) {

        this.isParams = isParams;
        this.params = params;
    }


    public void executeTask() {
        new UrlRequest().execute();
    }

    // asynctask for netwrok request
    private class UrlRequest extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            userName = TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName();
        }

        @Override
        protected String doInBackground(String... params) {
            if (TpsGamesClass.getInstance().isNetworkAvailable(context)) {
                try {

                    isConnectToInternet = true;
                    urlRequest = new URL(url);
                    Log.i("request url", url);
                    connection = (HttpURLConnection) urlRequest.openConnection();
                    connection.setConnectTimeout(requestTime);
                    connection.setReadTimeout(requestTime);
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setInstanceFollowRedirects(false);
                    connection.setRequestMethod("POST");

                    if (headerData != null && headerData.trim().length() > 0) {
                        String[] header = headerData.split("[|]");
                        for (String data : header) {
                            String[] dataHeader = data.split("[,]");
                            connection.setRequestProperty(dataHeader[0], dataHeader[1]);
                        }
                        OutputStreamWriter writer = null;
                        if (isParams) {
                            writer = new OutputStreamWriter(connection.getOutputStream());
                            writer.write(HttpRequest.this.params);
                            writer.flush();
                            writer.close();
                        } else {
                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("username", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
                            writer = new OutputStreamWriter(connection.getOutputStream());
                            writer.write(jsonParam.toString());
                            writer.flush();
                            writer.close();
                        }

                    } else if (isParams) {
                        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                        writer.write(HttpRequest.this.params);
                        writer.flush();
                        writer.close();
                    }


//                    connection.setUseCaches(false);
                    connection.connect();

                    long start = System.currentTimeMillis();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    responseString = response.toString();
                    isServerConnected = true;
                    Log.i("response", responseString);


                } catch (MalformedURLException e) {
                    isServerConnected = false;
                    responseString = "Failed";
                    e.printStackTrace();
                } catch (IOException e) {
                    isServerConnected = false;
                    responseString = "Failed";
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                    responseString = "Failed";
                }
            } else {
                isConnectToInternet = false;
            }
            if (responseString == null || (responseString != null && (responseString.equalsIgnoreCase("null") || responseString.trim().length() == 0))) {
                responseString = "Failed";
//                requestMethod = "fail";
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            if (!isConnectToInternet || !isServerConnected || responseString.equalsIgnoreCase("Failed")) {
//                if (requestMethod.equalsIgnoreCase("login")) {
//                    result = TpsGamesClass.getInstance().getLoginResponseOffLine();
//                } else if (requestMethod.equalsIgnoreCase("gameData")) {
//                    result = TpsGamesClass.getInstance().getDrawGameFetchDataOffLine();
//                }
//
////                result = result.replaceAll("\\s+", "");
////                result = result.replaceAll("[\t\n]", "");
//
//            }

            headerData = null;
            params = null;
            isParams = false;

            TpsGamesClass.getInstance().setPlayerVerified(false, "");


            if (result != null) {
                responseLis.onResponse(result, requestMethod);
            }
        }

    }

}
