package demo.stpl.com.tpsmergedbuild.baseClass;

//import tpsgames.interfaces.Game;

import demo.stpl.com.tpsmergedbuild.interfaces.Game;

/**
 * Created by stpl on 9/9/2016.
 */
public class GameSale implements Game {
    public boolean isSuccess() {
        return isSuccess;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private int errorCode;

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public MainData getMainData() {
        return mainData;
    }

    public void setMainData(MainData mainData) {
        this.mainData = mainData;
    }

    private boolean isSuccess;
    private String errorMsg;
    private MainData mainData;



    public static class MainData{
        public double getAvlblCreditAmt() {
            return AvlblCreditAmt;
        }

        public void setAvlblCreditAmt(double avlblCreditAmt) {
            AvlblCreditAmt = avlblCreditAmt;
        }

        private double AvlblCreditAmt;
        public CommonSaleData getCommonSaleData() {
            return commonSaleData;
        }

        public void setCommonSaleData(CommonSaleData commonSaleData) {
            this.commonSaleData = commonSaleData;
        }

        public BetTypeData[] getBetTypeData() {
            return betTypeData;
        }

        public void setBetTypeData(BetTypeData[] betTypeData) {
            this.betTypeData = betTypeData;
        }

        public AdvMsg getAdvMessage() {
            return advMessage;
        }

        public void setAdvMessage(AdvMsg advMessage) {
            this.advMessage = advMessage;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public boolean isPromo() {
            return isPromo;
        }

        public void setIsPromo(boolean isPromo) {
            this.isPromo = isPromo;
        }

        private CommonSaleData commonSaleData;
        private BetTypeData[] betTypeData;
        private AdvMsg advMessage;
        private String orgName;
        private String userName;
        private boolean isPromo;

        public static class CommonSaleData{
            public String getTicketNumber() {
                return ticketNumber;
            }

            public void setTicketNumber(String ticketNumber) {
                this.ticketNumber = ticketNumber;
            }

            public String getGameName() {
                return gameName;
            }

            public void setGameName(String gameName) {
                this.gameName = gameName;
            }

            public String getPurchaseDate() {
                return purchaseDate;
            }

            public void setPurchaseDate(String purchaseDate) {
                this.purchaseDate = purchaseDate;
            }

            public String getPurchaseTime() {
                return purchaseTime;
            }

            public void setPurchaseTime(String purchaseTime) {
                this.purchaseTime = purchaseTime;
            }

            public double getPurchaseAmt() {
                return purchaseAmt;
            }

            public void setPurchaseAmt(double purchaseAmt) {
                this.purchaseAmt = purchaseAmt;
            }

            public DrawData[] getDrawData() {
                return drawData;
            }

            public void setDrawData(DrawData[] drawData) {
                this.drawData = drawData;
            }

            public String getAvlblCreditAmt() {
                return AvlblCreditAmt;
            }

            public void setAvlblCreditAmt(String avlblCreditAmt) {
                AvlblCreditAmt = avlblCreditAmt;
            }

            public String getBarcodeCount() {
                return barcodeCount;
            }

            public void setBarcodeCount(String barcodeCount) {
                this.barcodeCount = barcodeCount;
            }

            private String AvlblCreditAmt;
            private String barcodeCount;
            private String ticketNumber;
            private String gameName;
            private String purchaseDate;
            private String purchaseTime;
            private double purchaseAmt;
            private DrawData[] drawData;

            public static class DrawData{

                public String getDrawId() {
                    return drawId;
                }

                public void setDrawId(String drawId) {
                    this.drawId = drawId;
                }

                public String getDrawDate() {
                    return drawDate;
                }

                public void setDrawDate(String drawDate) {
                    this.drawDate = drawDate;
                }

                public String getDrawName() {
                    return drawName;
                }

                public void setDrawName(String drawName) {
                    this.drawName = drawName;
                }

                public String getDrawTime() {
                    return drawTime;
                }

                public void setDrawTime(String drawTime) {
                    this.drawTime = drawTime;
                }

                private String drawId;
                private String drawDate;
                private String drawName;
                private String drawTime;
            }
        }


        public static class BetTypeData{

            public boolean isQp() {
                return isQp;
            }

            public void setIsQp(boolean isQp) {
                this.isQp = isQp;
            }

            public String getBetName() {
                return betName;
            }

            public void setBetName(String betName) {
                this.betName = betName;
            }

            public String getPickedNumbers() {
                return pickedNumbers;
            }

            public void setPickedNumbers(String pickedNumbers) {
                this.pickedNumbers = pickedNumbers;
            }

            public String getNumberPicked() {
                return numberPicked;
            }

            public void setNumberPicked(String numberPicked) {
                this.numberPicked = numberPicked;
            }

            public double getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(double unitPrice) {
                this.unitPrice = unitPrice;
            }

            public int getNoOfLines() {
                return noOfLines;
            }

            public void setNoOfLines(int noOfLines) {
                this.noOfLines = noOfLines;
            }

            public double getBetAmtMul() {
                return betAmtMul;
            }

            public void setBetAmtMul(double betAmtMul) {
                this.betAmtMul = betAmtMul;
            }

            public double getPanelPrice() {
                return panelPrice;
            }

            public void setPanelPrice(int panelPrice) {
                this.panelPrice = panelPrice;
            }

            private boolean isQp;
            private String betName;
            private String pickedNumbers;
            private String numberPicked;
            private double unitPrice;
            private int noOfLines;
            private double betAmtMul;
            private double panelPrice;
        }

        public static class AdvMsg{
            public String[] getBOTTOM() {
                return BOTTOM;
            }

            public void setBOTTOM(String[] BOTTOM) {
                this.BOTTOM = BOTTOM;
            }

            public String[] getTOP() {
                return TOP;
            }

            public void setTOP(String[] TOP) {
                this.TOP = TOP;
            }

            private String[] BOTTOM;
            private String[] TOP;
        }
    }

    @Override
    public void buildSaleRequest(String request) {

    }

    @Override
    public String getSaleRequest() {
        return null;
    }

    @Override
    public void buildSaleResponse(String response) {

    }
}
