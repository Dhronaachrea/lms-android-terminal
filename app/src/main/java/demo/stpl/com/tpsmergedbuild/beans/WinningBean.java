package demo.stpl.com.tpsmergedbuild.beans;

/**
 * Created by stpl on 27-Oct-16.
 */
public class WinningBean {

    private boolean isSuccess;
    private AdvMsg advMsg;
    private String gameName;
    private String gameDevName;
    private String ticketNumber;
    private String barcodeCount;
    private String totalWinAmt;
    private String totalPay;
    private String orgName;
    private String retailerName;
    private String reprintCountPwt;
    private String currencySymbol;

    public DrawData[] getDrawData() {
        return drawData;
    }

    public void setDrawData(DrawData[] drawData) {
        this.drawData = drawData;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public AdvMsg getAdvMsg() {
        return advMsg;
    }

    public void setAdvMsg(AdvMsg advMsg) {
        this.advMsg = advMsg;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameDevName() {
        return gameDevName;
    }

    public void setGameDevName(String gameDevName) {
        this.gameDevName = gameDevName;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getBarcodeCount() {
        return barcodeCount;
    }

    public void setBarcodeCount(String barcodeCount) {
        this.barcodeCount = barcodeCount;
    }

    public String getTotalWinAmt() {
        return totalWinAmt;
    }

    public void setTotalWinAmt(String totalWinAmt) {
        this.totalWinAmt = totalWinAmt;
    }

    public String getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public String getReprintCountPwt() {
        return reprintCountPwt;
    }

    public void setReprintCountPwt(String reprintCountPwt) {
        this.reprintCountPwt = reprintCountPwt;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    private DrawData[] drawData;

    public static class DrawData {
        private String drawDate;
        private String drawTime;
        private String winStatus;

        public String getWinningAmt() {
            return winningAmt;
        }

        public void setWinningAmt(String winningAmt) {
            this.winningAmt = winningAmt;
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

        public String getWinStatus() {
            return winStatus;
        }

        public void setWinStatus(String winStatus) {
            this.winStatus = winStatus;
        }

        private String winningAmt;
    }


    public static class AdvMsg {
        private String[] BOTTOM;

        public String[] getTOP() {
            return TOP;
        }

        public void setTOP(String[] TOP) {
            this.TOP = TOP;
        }

        public String[] getBOTTOM() {
            return BOTTOM;
        }

        public void setBOTTOM(String[] BOTTOM) {
            this.BOTTOM = BOTTOM;
        }

        private String[] TOP;
    }

}
