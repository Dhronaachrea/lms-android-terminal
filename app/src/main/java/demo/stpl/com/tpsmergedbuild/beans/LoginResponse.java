package demo.stpl.com.tpsmergedbuild.beans;

/**
 * Created by stpl on 9/9/2016.
 */
public class LoginResponse {

    //    {"isSuccess":false,"errorMsg":"Please Enter Correct Login Name and Password!!","data":""}
    public boolean isSuccess() {
        return isSuccess;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    private boolean isSuccess;
    private String errorMsg;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Data data;

    public static class Data {
        private String retailerName;

        public void setAvailableBalance(double availableBalance) {
            this.availableBalance = availableBalance;
        }

        private double availableBalance;
        private int retailerOrgCode;

        public String getSessionId() {
            return sessionId;
        }

        public String getRetailerName() {
            return retailerName;
        }

        public double getAvailableBalance() {
            String amount = availableBalance + "";
            if (amount.contains(".")) {
                String[] decimalAmount = amount.split("[.]");
                amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
            }
            return Double.parseDouble(amount);
        }

        public int getRetailerOrgCode() {
            return retailerOrgCode;
        }

        private String sessionId;
    }
}
