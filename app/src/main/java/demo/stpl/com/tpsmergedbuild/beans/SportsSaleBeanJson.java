package demo.stpl.com.tpsmergedbuild.beans;

/**
 * Created by stpl on 13-Oct-16.
 */
public class SportsSaleBeanJson {

    private String responseCode;
    private String responseMsg;
    private String sampleStatus;
    private String mode;

    public TktData getTktData() {
        return tktData;
    }

    public void setTktData(TktData tktData) {
        this.tktData = tktData;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(String sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    private TktData tktData;


    public static class TktData{
        private String gameTypeId;
        private String reprintCount;
        private String retailerName;
        private String currSymbol;
        private String gameId;
        private String eventType;
        private String gameName;
        private String orgName;
        private String jackpotMsg;
        private String purchaseTime;
        private String brCd;
        private String balance;
        private String ticketNo;
        private String expiryPeriod;
        private String purchaseDate;
        private String gameTypeName;
        private String trxId;
        private String ticketAmt;

        public BoardData[] getBoardData() {
            return boardData;
        }

        public void setBoardData(BoardData[] boardData) {
            this.boardData = boardData;
        }

        public String getGameTypeId() {
            return gameTypeId;
        }

        public void setGameTypeId(String gameTypeId) {
            this.gameTypeId = gameTypeId;
        }

        public String getReprintCount() {
            return reprintCount;
        }

        public void setReprintCount(String reprintCount) {
            this.reprintCount = reprintCount;
        }

        public String getRetailerName() {
            return retailerName;
        }

        public void setRetailerName(String retailerName) {
            this.retailerName = retailerName;
        }

        public String getCurrSymbol() {
            return currSymbol;
        }

        public void setCurrSymbol(String currSymbol) {
            this.currSymbol = currSymbol;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getJackpotMsg() {
            return jackpotMsg;
        }

        public void setJackpotMsg(String jackpotMsg) {
            this.jackpotMsg = jackpotMsg;
        }

        public String getPurchaseTime() {
            return purchaseTime;
        }

        public void setPurchaseTime(String purchaseTime) {
            this.purchaseTime = purchaseTime;
        }

        public String getBrCd() {
            return brCd;
        }

        public void setBrCd(String brCd) {
            this.brCd = brCd;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getTicketNo() {
            return ticketNo;
        }

        public void setTicketNo(String ticketNo) {
            this.ticketNo = ticketNo;
        }

        public String getExpiryPeriod() {
            return expiryPeriod;
        }

        public void setExpiryPeriod(String expiryPeriod) {
            this.expiryPeriod = expiryPeriod;
        }

        public String getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(String purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public String getGameTypeName() {
            return gameTypeName;
        }

        public void setGameTypeName(String gameTypeName) {
            this.gameTypeName = gameTypeName;
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

        private BoardData[] boardData;
        public static class BoardData{

            private String drawId;
            private String drawTime;
            private String drawName;
            private String unitPrice;
            private String noOfLines;
            private String boardPrice;
            private String drawDate;

            public EventData[] getEventData() {
                return eventData;
            }

            public void setEventData(EventData[] eventData) {
                this.eventData = eventData;
            }

            public String getDrawId() {
                return drawId;
            }

            public void setDrawId(String drawId) {
                this.drawId = drawId;
            }

            public String getDrawTime() {
                return drawTime;
            }

            public void setDrawTime(String drawTime) {
                this.drawTime = drawTime;
            }

            public String getDrawName() {
                return drawName;
            }

            public void setDrawName(String drawName) {
                this.drawName = drawName;
            }

            public String getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(String unitPrice) {
                this.unitPrice = unitPrice;
            }

            public String getNoOfLines() {
                return noOfLines;
            }

            public void setNoOfLines(String noOfLines) {
                this.noOfLines = noOfLines;
            }

            public String getBoardPrice() {
                return boardPrice;
            }

            public void setBoardPrice(String boardPrice) {
                this.boardPrice = boardPrice;
            }

            public String getDrawDate() {
                return drawDate;
            }

            public void setDrawDate(String drawDate) {
                this.drawDate = drawDate;
            }

            private EventData[] eventData;

            public static class EventData{
                private String eventLeague;
                private String eventVenue;
                private String eventCodeAway;
                private String eventDisplayHome;
                private String eventDate;
                private String eventDisplayAway;
                private String selection;

                public String getEventCodeHome() {
                    return eventCodeHome;
                }

                public void setEventCodeHome(String eventCodeHome) {
                    this.eventCodeHome = eventCodeHome;
                }

                public String getEventLeague() {
                    return eventLeague;
                }

                public void setEventLeague(String eventLeague) {
                    this.eventLeague = eventLeague;
                }

                public String getEventVenue() {
                    return eventVenue;
                }

                public void setEventVenue(String eventVenue) {
                    this.eventVenue = eventVenue;
                }

                public String getEventCodeAway() {
                    return eventCodeAway;
                }

                public void setEventCodeAway(String eventCodeAway) {
                    this.eventCodeAway = eventCodeAway;
                }

                public String getEventDisplayHome() {
                    return eventDisplayHome;
                }

                public void setEventDisplayHome(String eventDisplayHome) {
                    this.eventDisplayHome = eventDisplayHome;
                }

                public String getEventDate() {
                    return eventDate;
                }

                public void setEventDate(String eventDate) {
                    this.eventDate = eventDate;
                }

                public String getEventDisplayAway() {
                    return eventDisplayAway;
                }

                public void setEventDisplayAway(String eventDisplayAway) {
                    this.eventDisplayAway = eventDisplayAway;
                }

                public String getSelection() {
                    return selection;
                }

                public void setSelection(String selection) {
                    this.selection = selection;
                }

                private String eventCodeHome;
            }

        }
    }
}
