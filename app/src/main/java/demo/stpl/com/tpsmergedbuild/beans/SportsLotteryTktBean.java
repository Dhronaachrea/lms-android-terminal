package demo.stpl.com.tpsmergedbuild.beans;


/**
 * Created by stpl on 1/22/2016.
 */
public class SportsLotteryTktBean {

    String currDate;
    String currTime;
    String ticketNo;
    String brCd;
    String trxId;

    public String[] getAllMesage() {
        return allMesage;
    }

    public void setAllMesage(String[] allMesage) {
        this.allMesage = allMesage;
    }

    String[] allMesage;

    String drawDate;
    String drawTime;
    String drawValidity;
    String unitPrice;
    String betAmount;

    public String getBoardAmount() {
        return boardAmount;
    }

    public void setBoardAmount(String boardAmount) {
        this.boardAmount = boardAmount;
    }

    public String getDrawValidity() {
        return drawValidity;
    }

    public void setDrawValidity(String drawValidity) {
        this.drawValidity = drawValidity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(String betAmount) {
        this.betAmount = betAmount;
    }

    String boardAmount;

    public String getDrawName() {
        return drawName;
    }

    public void setDrawName(String drawName) {
        this.drawName = drawName;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public String getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(String drawTime) {
        this.drawTime = drawTime;
    }

    String drawName;

    String ticketAmt;
    String balance;
    String gameId;
    String gameTypeId;
    String gameName;
    String gameTypeName;
    String[] drawInfo;

    public String[] getJackpotMsg() {
        return jackpotMsg;
    }

    public void setJackpotMsg(String[] jackpotMsg) {
        this.jackpotMsg = jackpotMsg;
    }

    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getBrCd() {
        return brCd;
    }

    public void setBrCd(String brCd) {
        this.brCd = brCd;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getTicketAmt() {
        return ticketAmt;
    }

    public void setTicketAmt(String ticketAmt) {
        this.ticketAmt = ticketAmt;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameTypeId() {
        return gameTypeId;
    }

    public void setGameTypeId(String gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameTypeName() {
        return gameTypeName;
    }

    public void setGameTypeName(String gameTypeName) {
        this.gameTypeName = gameTypeName;
    }

    public String[] getDrawInfo() {
        return drawInfo;
    }

    public void setDrawInfo(String[] drawInfo) {
        this.drawInfo = drawInfo;
    }

    String[] jackpotMsg;
}

