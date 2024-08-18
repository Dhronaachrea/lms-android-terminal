package demo.stpl.com.tpsmergedbuild.beans;

import java.io.Serializable;

/**
 * Created by stpl on 9/12/2016.
 */
public class FatchGameData implements Serializable {
    private Games[] games;

    private String responseCode;

    private String responseMsg;

    public Games[] getGames() {
        return games;
    }

    public void setGames(Games[] games) {
        this.games = games;
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


    public static class Games implements Serializable {
        private String timeToFetchUpdatedGameInfo;

        private String lastDrawFreezeTime;

        private String gameType;

        private String lastDrawResult;

        private Bets[] bets;

        private String gameId;

        private String drawFrequencyType;

        private String ticketExpiry;

        private LastDrawWinningResult[] lastDrawWinningResult;

        private String jackpotLimit;

        private String lastDrawTime;

        private String gameCode;

        private String displayOrder;

        private String lastDrawDateTime;

        private String gameDispName;

        private Draws[] draws;

        public String getTimeToFetchUpdatedGameInfo() {
            return timeToFetchUpdatedGameInfo;
        }

        public void setTimeToFetchUpdatedGameInfo(String timeToFetchUpdatedGameInfo) {
            this.timeToFetchUpdatedGameInfo = timeToFetchUpdatedGameInfo;
        }

        public String getLastDrawFreezeTime() {
            return lastDrawFreezeTime;
        }

        public void setLastDrawFreezeTime(String lastDrawFreezeTime) {
            this.lastDrawFreezeTime = lastDrawFreezeTime;
        }

        public String getGameType() {
            return gameType;
        }

        public void setGameType(String gameType) {
            this.gameType = gameType;
        }

        public String getLastDrawResult() {
            return lastDrawResult;
        }

        public void setLastDrawResult(String lastDrawResult) {
            this.lastDrawResult = lastDrawResult;
        }

        public Bets[] getBets() {
            return bets;
        }

        public void setBets(Bets[] bets) {
            this.bets = bets;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getDrawFrequencyType() {
            return drawFrequencyType;
        }

        public void setDrawFrequencyType(String drawFrequencyType) {
            this.drawFrequencyType = drawFrequencyType;
        }

        public String getTicketExpiry() {
            return ticketExpiry;
        }

        public void setTicketExpiry(String ticketExpiry) {
            this.ticketExpiry = ticketExpiry;
        }

        public LastDrawWinningResult[] getLastDrawWinningResult() {
            return lastDrawWinningResult;
        }

        public void setLastDrawWinningResult(LastDrawWinningResult[] lastDrawWinningResult) {
            this.lastDrawWinningResult = lastDrawWinningResult;
        }

        public String getJackpotLimit() {
            return jackpotLimit;
        }

        public void setJackpotLimit(String jackpotLimit) {
            this.jackpotLimit = jackpotLimit;
        }

        public String getLastDrawTime() {
            return lastDrawTime;
        }

        public void setLastDrawTime(String lastDrawTime) {
            this.lastDrawTime = lastDrawTime;
        }

        public String getGameCode() {
            return gameCode;
        }

        public void setGameCode(String gameCode) {
            this.gameCode = gameCode;
        }

        public String getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(String displayOrder) {
            this.displayOrder = displayOrder;
        }

        public String getLastDrawDateTime() {
            return lastDrawDateTime;
        }

        public void setLastDrawDateTime(String lastDrawDateTime) {
            this.lastDrawDateTime = lastDrawDateTime;
        }

        public String getGameDispName() {
            return gameDispName;
        }

        public void setGameDispName(String gameDispName) {
            this.gameDispName = gameDispName;
        }

        public Draws[] getDraws() {
            return draws;
        }

        public void setDraws(Draws[] draws) {
            this.draws = draws;
        }

        public class Bets implements Serializable {
            private String maxBetAmtMul;

            private String betName;

            private String betDispName;

            private String unitPrice;

            private String betCode;

            private boolean isChecked;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public String getMaxBetAmtMul() {
                return maxBetAmtMul;
            }

            public void setMaxBetAmtMul(String maxBetAmtMul) {
                this.maxBetAmtMul = maxBetAmtMul;
            }

            public String getBetName() {
                return betName;
            }

            public void setBetName(String betName) {
                this.betName = betName;
            }

            public String getBetDispName() {
                return betDispName;
            }

            public void setBetDispName(String betDispName) {
                this.betDispName = betDispName;
            }

            public String getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(String unitPrice) {
                this.unitPrice = unitPrice;
            }

            public String getBetCode() {
                return betCode;
            }

            public void setBetCode(String betCode) {
                this.betCode = betCode;
            }

        }

        public class LastDrawWinningResult implements Serializable {
            private String winningNumber;

            private String lastDrawDateTime;

            public String getWinningNumber() {
                return winningNumber;
            }

            public void setWinningNumber(String winningNumber) {
                this.winningNumber = winningNumber;
            }

            public String getLastDrawDateTime() {
                return lastDrawDateTime;
            }

            public void setLastDrawDateTime(String lastDrawDateTime) {
                this.lastDrawDateTime = lastDrawDateTime;
            }

        }

        public class Draws implements Serializable {
            private String drawId;

            private String drawName;

            private String drawFreezeTime;

            private String drawStatus;

            private boolean isChecked;

            private String drawDateTime;

            public String getDrawId() {
                return drawId;
            }

            public void setDrawId(String drawId) {
                this.drawId = drawId;
            }

            public String getDrawName() {
                return drawName;
            }

            public void setDrawName(String drawName) {
                this.drawName = drawName;
            }

            public String getDrawFreezeTime() {
                return drawFreezeTime;
            }

            public void setDrawFreezeTime(String drawFreezeTime) {
                this.drawFreezeTime = drawFreezeTime;
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public String getDrawStatus() {
                return drawStatus;
            }

            public void setDrawStatus(String drawStatus) {
                this.drawStatus = drawStatus;
            }

            public String getDrawDateTime() {
                return drawDateTime;
            }

            public void setDrawDateTime(String drawDateTime) {
                this.drawDateTime = drawDateTime;
            }

        }
    }
}
