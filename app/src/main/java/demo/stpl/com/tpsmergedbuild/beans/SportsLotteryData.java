package demo.stpl.com.tpsmergedbuild.beans;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stpl on 12/29/2015.
 */
public class SportsLotteryData implements Serializable {

    private String userName = "";
    private String gameId = "";
    private String drawCount = "";
    private String ticketAmt = "";
    private String merCode = "";
    private String lstktNo = "";
    private String sessId = "";
    private String slLstTxnId = "";
    private String CID = "";
    private String LAC = "";
    private String simType = "";
    private String deviceType = "";
    private String reqCounter = "";
    private String respCounter = "";
    private String time = "";
    private String date = "";
    private String random = "";

    private String gamePrivi = "";

    String response = "";

    public List<SportsLotteryGame> sportsLotteryGames;

    public SportsLotteryData(String response) {
        sportsLotteryGames = new ArrayList<>();
        this.response = response;
    }

    public SportsLotteryData() {

    }

    public void buildResponse() {
        if (response.contains("ErrorCode:01")) {
            throw new RuntimeException("Login Time Out! Please Login Again");
        } else {
            response = response.substring(response.indexOf("|") + 1, response.length());
            String responseString[] = response.split("[$]");
            for (int i = 0; i < responseString.length; i++) {
                if (i == 0) {
                    String sportsData[] = responseString[i].split("[,]");
                    setGameId(sportsData[0]);
                } else {
                    String sportsData[] = responseString[i].split("[,]");
                    SportsLotteryGame sportsLotteryGame = new SportsLotteryGame();
                    sportsLotteryGame.setGameNumber(sportsData[0] != null ? sportsData[0] : "");
                    sportsLotteryGame.setGameType(sportsData[1] != null ? sportsData[1] : "");
                    sportsLotteryGame.setGameName(sportsData[2] != null ? sportsData[2] : "");
                    sportsLotteryGame.setMin_amount(sportsData[3] != null ? sportsData[3] : "");
                    sportsLotteryGame.setMax_amount(sportsData[4] != null ? sportsData[4] : "");
                    sportsLotteryGame.setMultiple(sportsData[5] != null ? sportsData[5] : "");
                    sportsLotteryGame.setGame_date_time(sportsData[6] != null ? sportsData[6] : "");
                    String total_multi = "";
                    for (int j = 7; j < sportsData.length; j++) {
                        total_multi = total_multi + sportsData[j];
                    }
//                    responseString[i].split("]")
//                    if(responseString[i].split("\\[")[1].split("]"))
                    sportsLotteryGame.setMultipeName(responseString[i].split("\\[")[1].split("]")[0] != null ? responseString[i].split("\\[")[1].split("]")[0] : "");
//                    responseString[i].split("%")[0].split("]")[1].split(",")[4]

                    String arr[] = responseString[i].split("%");
                    List<SportsLotteryGame.EventData> list = new ArrayList<>();
                    if (arr.length > 1) {
                        String[] extra = responseString[i].split("%")[0].split("]")[1].split(",");
                        sportsLotteryGame.setCD(extra[0] != null ? extra[0].split(":")[1] : "");
                        sportsLotteryGame.setCurrentDrawName(extra[4] != null ? extra[4] : "");
                        for (int k = 1; k < arr.length; k++) {
                            SportsLotteryGame.EventData event = new SportsLotteryGame.EventData();
                            String game = arr[k].split(",")[1];
                            String[] teams = game.split("-vs-");
                            event.setTxtHome(teams[0]);
                            event.setTxtAway(teams[1]);
                            event.setEventId(arr[k].split(",")[0]);
                            list.add(event);
                        }
                        sportsLotteryGame.setDrawId(arr[0].split("CD:")[1] != null ? arr[0].split("CD:")[1] : "");
//                        arr[0].split("CD:")[1].split(",")
                        sportsLotteryGame.setGameList(list);
                    } else {
                        sportsLotteryGame.setCurrentDrawName("");
//                        sportsLotteryGame.setGameList(list);
                    }


                    total_multi = total_multi.substring(1, total_multi.length() - 1);
                    sportsLotteryGame.setTotal_multiple(total_multi);
                    sportsLotteryGames.add(sportsLotteryGame);
                    Log.e("game_data", sportsData.toString());
                }
            }
            Log.d("Sports lottery", responseString.toString());
        }

    }

    public List<SportsLotteryGame> getSportsLotteryGames() {
        return sportsLotteryGames;
    }

    public void setSportsLotteryGames(List<SportsLotteryGame> sportsLotteryGames) {
        this.sportsLotteryGames = sportsLotteryGames;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(String drawCount) {
        this.drawCount = drawCount;
    }

    public String getTicketAmt() {
        return ticketAmt;
    }

    public void setTicketAmt(String ticketAmt) {
        this.ticketAmt = ticketAmt;
    }

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public String getLstktNo() {
        return lstktNo;
    }

    public void setLstktNo(String lstktNo) {
        this.lstktNo = lstktNo;
    }

    public String getSessId() {
        return sessId;
    }

    public void setSessId(String sessId) {
        this.sessId = sessId;
    }

    public String getSlLstTxnId() {
        return slLstTxnId;
    }

    public void setSlLstTxnId(String slLstTxnId) {
        this.slLstTxnId = slLstTxnId;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getLAC() {
        return LAC;
    }

    public void setLAC(String LAC) {
        this.LAC = LAC;
    }

    public String getSimType() {
        return simType;
    }

    public void setSimType(String simType) {
        this.simType = simType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getReqCounter() {
        return reqCounter;
    }

    public void setReqCounter(String reqCounter) {
        this.reqCounter = reqCounter;
    }

    public String getRespCounter() {
        return respCounter;
    }

    public void setRespCounter(String respCounter) {
        this.respCounter = respCounter;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getGamePrivi() {
        return gamePrivi;
    }

    public void setGamePrivi(String gamePrivi) {
        this.gamePrivi = gamePrivi;
    }
}