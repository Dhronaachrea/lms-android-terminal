package demo.stpl.com.tpsmergedbuild.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by stpl on 12/29/2015.
 */
public class SportsLotteryGame implements Serializable {


    private String gameNumber = "";
    private String gameType = "";
    private String gameName = "";
    private String min_amount = "";
    private String max_amount = "";
    private int betAmountMul;
    private boolean isMultiple;
    private String game_date_time = "";
    private String total_multiple = "";
    private String drawDate = "";
    private String drawTime = "";
    private String drawName = "";
    private String currentDrawName = "";
    private List<EventData> events;
    private String multipeName = "";
    private String drawId = "";
    private String CD = "";

    public String getDrawId() {
        return drawId;
    }

    public void setDrawId(String drawId) {
        this.drawId = drawId;
    }

    public String getMultipeName() {
        return multipeName;
    }

    public void setMultipeName(String multipeName) {
        this.multipeName = multipeName;
    }

    public int getBetAmountMul() {
        return betAmountMul;
    }

    public void setBetAmountMul(int betAmountMul) {
        this.betAmountMul = betAmountMul;
    }

    public List<EventData> getGameList() {
        return events;
    }

    public void setGameList(List<EventData> events) {
//        events = new ArrayList<>();
//        for (int i = 0; i < gameList.size(); i++) {
//            String[] teams = gameList.get(i).split("-vs-");
//            EventData event = new EventData();
//            event.setTxtHome(teams[0]);
//            event.setTxtAway(teams[1]);
//            events.add(event);
//        }
        this.events = events;
    }

    public String getCurrentDrawName() {
        return currentDrawName;
    }

    public void setCurrentDrawName(String currentDrawName) {
        this.currentDrawName = currentDrawName;
    }


//    private List<GameDrawEventInfo> gameDrawEventInfos;
//
//
//    public List<GameDrawEventInfo> getGameDrawEventInfos(String response) {
//        if (gameDrawEventInfos == null) {
//            gameDrawEventInfos = new ArrayList<>();
//        }
//        String gameWinningList[] = response.split("[~]");
//        for (int i = 0; i < gameWinningList.length; i++) {
//            GameDrawEventInfo gameDrawEventInfo = new GameDrawEventInfo();
//            gameDrawEventInfo.setTeams(gameWinningList[i].substring(0, gameWinningList[i].indexOf("@")));
//            gameDrawEventInfo.setWinner(gameWinningList[i].substring(gameWinningList[i].indexOf("@") + 1, gameWinningList[i].length()));
//            gameDrawEventInfos.add(gameDrawEventInfo);
//        }
//        return gameDrawEventInfos;
//    }

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


    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(String gameNumber) {
        this.gameNumber = gameNumber;
    }

    public String getTotal_multiple() {
        return total_multiple;
    }

    public void setTotal_multiple(String total_multiple) {
        this.total_multiple = total_multiple;
    }

    public String getGame_date_time() {
        return game_date_time;
    }

    public void setGame_date_time(String game_date_time) {
        this.game_date_time = game_date_time;
    }

    public boolean isMultiple() {
        return isMultiple;
    }

    public void setMultiple(String multiple) {
        this.isMultiple = multiple.equalsIgnoreCase("MULTIPLE");
    }

    public String getMax_amount() {
        return max_amount;
    }

    public void setMax_amount(String max_amount) {
        this.max_amount = max_amount;
    }

    public String getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(String min_amount) {
        this.min_amount = min_amount;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getCD() {
        return CD;
    }

    public void setCD(String CD) {
        this.CD = CD;
    }

    public static class EventData implements Serializable {
        private String txtHome;
        private String codeHome;
        private String txtAway;
        private String codeAway;
        private String venue;
        private String time;
        private String eventId;

        private boolean isHomeExtra;
        private boolean isHome;
        private boolean isDraw;
        private boolean isAway;
        private boolean isAwayExtra;

        public String getTxtHome() {
            return txtHome;
        }

        public void setTxtHome(String txtHome) {
            this.txtHome = txtHome;
        }

        public String getCodeHome() {
            return codeHome;
        }

        public void setCodeHome(String codeHome) {
            this.codeHome = codeHome;
        }

        public String getTxtAway() {
            return txtAway;
        }

        public void setTxtAway(String txtAway) {
            this.txtAway = txtAway;
        }

        public String getCodeAway() {
            return codeAway;
        }

        public void setCodeAway(String codeAway) {
            this.codeAway = codeAway;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public boolean isHomeExtra() {
            return isHomeExtra;
        }

        public void setIsHomeExtra(boolean isHomeExtra) {
            this.isHomeExtra = isHomeExtra;
        }

        public boolean isHome() {
            return isHome;
        }

        public void setIsHome(boolean isHome) {
            this.isHome = isHome;
        }

        public boolean isDraw() {
            return isDraw;
        }

        public void setIsDraw(boolean isDraw) {
            this.isDraw = isDraw;
        }

        public boolean isAway() {
            return isAway;
        }

        public void setIsAway(boolean isAway) {
            this.isAway = isAway;
        }

        public boolean isAwayExtra() {
            return isAwayExtra;
        }

        public void setIsAwayExtra(boolean isAwayExtra) {
            this.isAwayExtra = isAwayExtra;
        }
    }
}
