package demo.stpl.com.tpsmergedbuild.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stpl on 9/16/2016.
 */
public class EbetSlipBean {

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        IsSuccess = isSuccess;
    }

    private String errorCode;
    private String errorMsg;
    private boolean IsSuccess;

    public ArrayList<ResponseData> getResponseData() {
        return responseData;
    }

    public void setResponseData(ArrayList<ResponseData> responseData) {
        this.responseData = responseData;
    }

    private ArrayList<ResponseData> responseData;

    public ArrayList<SportsSaleBean> getResponseDataSale() {
        return responseDataSale;
    }

    public void setResponseDataSale(ArrayList<SportsSaleBean> responseDataSale) {
        this.responseDataSale = responseDataSale;
    }

    private ArrayList<SportsSaleBean> responseDataSale;






    public static class ResponseData {


        public static class CommonSaleDataEbet {

            private boolean isAdvancePlay;
            private boolean isDrawManual;
            private int noOfDraws;
            private List<String> drawData;
            private String gameName;
            private List<BetTypeData> betTypeData;
            private int noOfPanel;

            public double getTotalPurchaseAmt() {
                return totalPurchaseAmt;
            }

            public void setTotalPurchaseAmt(double totalPurchaseAmt) {
                this.totalPurchaseAmt = totalPurchaseAmt;
            }

            public boolean isAdvancePlay() {
                return isAdvancePlay;
            }

            public void setIsAdvancePlay(boolean isAdvancePlay) {
                this.isAdvancePlay = isAdvancePlay;
            }

            public boolean isDrawManual() {
                return isDrawManual;
            }

            public void setIsDrawManual(boolean isDrawManual) {
                this.isDrawManual = isDrawManual;
            }

            public int getNoOfDraws() {
                return noOfDraws;
            }

            public void setNoOfDraws(int noOfDraws) {
                this.noOfDraws = noOfDraws;
            }

            public List<String> getDrawData() {
                return drawData;
            }

            public void setDrawData(List<String> drawData) {
                this.drawData = drawData;
            }

            public String getGameName() {
                return gameName;
            }

            public void setGameName(String gameName) {
                this.gameName = gameName;
            }

            public List<BetTypeData> getBetTypeData() {
                return betTypeData;
            }

            public void setBetTypeData(List<BetTypeData> betTypeData) {
                this.betTypeData = betTypeData;
            }

            public int getNoOfPanel() {
                return noOfPanel;
            }

            public void setNoOfPanel(int noOfPanel) {
                this.noOfPanel = noOfPanel;
            }

            private double totalPurchaseAmt;

            public static class BetTypeData {
                private int noPicked;
                private boolean isQp;
                private int betAmtMul;
                private boolean QPPreGenerated;
                private String pickedNumbers;

                public String getNoOfLines() {
                    return noOfLines;
                }

                public void setNoOfLines(String noOfLines) {
                    this.noOfLines = noOfLines;
                }

                private String noOfLines;

                public double getUnitPrice() {
                    return unitPrice;
                }

                public void setUnitPrice(double unitPrice) {
                    this.unitPrice = unitPrice;
                }

                private double unitPrice;

                public String getBetName() {
                    return betName;
                }

                public void setBetName(String betName) {
                    this.betName = betName;
                }

                public int getNoPicked() {
                    return noPicked;
                }

                public void setNoPicked(int noPicked) {
                    this.noPicked = noPicked;
                }

                public boolean isQp() {
                    return isQp;
                }

                public void setIsQp(boolean isQp) {
                    this.isQp = isQp;
                }

                public int getBetAmtMul() {
                    return betAmtMul;
                }

                public void setBetAmtMul(int betAmtMul) {
                    this.betAmtMul = betAmtMul;
                }

                public boolean isQPPreGenerated() {
                    return QPPreGenerated;
                }

                public void setQPPreGenerated(boolean QPPreGenerated) {
                    this.QPPreGenerated = QPPreGenerated;
                }

                public String getPickedNumbers() {
                    return pickedNumbers;
                }

                public void setPickedNumbers(String pickedNumbers) {
                    this.pickedNumbers = pickedNumbers;
                }

                private String betName;
            }

        }


        private String deviceCode;
        private String deviceId;
        private String deviceMapId;
        private String ebetExpiryDatetime;
        private String ebetRequestDatetime;
        private String mobileNumber;
        private String modelId;
        private String organizationId;
        private String processedDatetime;
        private CommonSaleDataEbet requestData;

        public ArrayList<SportsSaleBean> getResponseDataSports() {
            return responseDataSports;
        }

        public void setResponseDataSports(ArrayList<SportsSaleBean> responseDataSports) {
            this.responseDataSports = responseDataSports;
        }

        private ArrayList<SportsSaleBean> responseDataSports;

        public SportsSaleBean getRequestDataSports() {
            return requestDataSports;
        }

        public void setRequestDataSports(SportsSaleBean requestDataSports) {
            this.requestDataSports = requestDataSports;
        }

        private SportsSaleBean requestDataSports;
        private String requestId;
        private String saleType;
        private String status;
        private String tokenId;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        private String userName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceMapId() {
            return deviceMapId;
        }

        public void setDeviceMapId(String deviceMapId) {
            this.deviceMapId = deviceMapId;
        }

        public String getEbetExpiryDatetime() {
            return ebetExpiryDatetime;
        }

        public void setEbetExpiryDatetime(String ebetExpiryDatetime) {
            this.ebetExpiryDatetime = ebetExpiryDatetime;
        }

        public String getEbetRequestDatetime() {
            return ebetRequestDatetime;
        }

        public void setEbetRequestDatetime(String ebetRequestDatetime) {
            this.ebetRequestDatetime = ebetRequestDatetime;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getModelId() {
            return modelId;
        }

        public void setModelId(String modelId) {
            this.modelId = modelId;
        }

        public String getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(String organizationId) {
            this.organizationId = organizationId;
        }

        public String getProcessedDatetime() {
            return processedDatetime;
        }

        public void setProcessedDatetime(String processedDatetime) {
            this.processedDatetime = processedDatetime;
        }

        public CommonSaleDataEbet getRequestData() {
            return requestData;
        }

        public void setRequestData(CommonSaleDataEbet requestData) {
            this.requestData = requestData;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getSaleType() {
            return saleType;
        }

        public void setSaleType(String saleType) {
            this.saleType = saleType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }

        private String userId;
    }
}
