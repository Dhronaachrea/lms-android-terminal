package demo.stpl.com.tpsmergedbuild.beans;

/**
 * Created by stpl on 9/8/2016.
 */
public class GameBean{

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public Games[] getGames() {
        return games;
    }

    private int responseCode;
    private String responseMsg;
    private Games[] games;

    public static class Games{
        public String getGameCode() {
            return gameCode;
        }

        public String getGameDispName() {
            return gameDispName;
        }

        public int getTicketExpiry() {
            return ticketExpiry;
        }

        public double getJackpotLimit() {
            return jackpotLimit;
        }

        public int getGameType() {
            return gameType;
        }

        public String getTimeToFetchUpdatedGameInfo() {
            return timeToFetchUpdatedGameInfo;
        }

        public String getGameId() {
            return gameId;
        }

        public Bets[] getBets() {
            return bets;
        }

        public Draws[] getDraws() {
            return draws;
        }

        public int getDisplayOrder() {
            return displayOrder;
        }

        public String getDrawFrequencyType() {
            return drawFrequencyType;
        }

        public String getLastDrawFreezeTime() {
            return lastDrawFreezeTime;
        }

        public String getLastDrawDateTime() {
            return lastDrawDateTime;
        }

        public LastDrawWinningResult[] getLastDrawWinningResult() {
            return lastDrawWinningResult;
        }

        private String gameCode;
        private String gameDispName;
        private int ticketExpiry;
        private double jackpotLimit;
        private int gameType;
        private String timeToFetchUpdatedGameInfo;
        private String gameId;
        private Bets[] bets;
        private Draws[] draws;
        private int displayOrder;
        private String drawFrequencyType;
        private String lastDrawFreezeTime;
        private String lastDrawDateTime;
        private LastDrawWinningResult[] lastDrawWinningResult;


        public static class Bets{
            public double getUnitPrice() {
                return unitPrice;
            }

            public int getMaxBetAmtMul() {
                return maxBetAmtMul;
            }

            public String getBetDispName() {
                return betDispName;
            }

            public int getBetCode() {
                return betCode;
            }

            public String getBetName() {
                return betName;
            }

            private double unitPrice;
            private int maxBetAmtMul;
            private String betDispName;
            private int betCode;
            private String betName;
        }

        public static class Draws{
            public String getDrawStatus() {
                return drawStatus;
            }

            public int getDrawId() {
                return drawId;
            }

            public String getDrawName() {
                return drawName;
            }

            public String getDrawDateTime() {
                return drawDateTime;
            }

            public String getDrawFreezeTime() {
                return drawFreezeTime;
            }

            private int drawId;
            private String drawName;
            private String drawDateTime;
            private String drawFreezeTime;
            private String drawStatus;

            public boolean isChecked() {
                return isChecked;
            }

            public void setIsChecked(boolean isChecked) {
                this.isChecked = isChecked;
            }

            private boolean isChecked;
        }

        public static class LastDrawWinningResult{

            public String getLastDrawDateTime() {
                return lastDrawDateTime;
            }

            public String getWinningNumber() {
                return winningNumber;
            }

            private String lastDrawDateTime;
            private String winningNumber;
        }

    }
}
