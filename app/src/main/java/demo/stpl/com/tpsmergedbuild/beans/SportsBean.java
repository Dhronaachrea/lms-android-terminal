package demo.stpl.com.tpsmergedbuild.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by stpl on 9/19/2016.
 */
public class SportsBean implements Serializable {

    private int responseCode;
    private String responseMsg;
    private SleData sleData;

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public SleData getSleData() {
        return sleData;
    }

    public static class SleData {
        private List<GameData> gameData;

        public List<GameData> getGameData() {
            return gameData;
        }
    }

    public static class GameData {
        private int maxEventCount;
        private String gameDisplayName;
        private double tktMaxAmt;
        private int gameId;
        private int minEventCount;
        private String gameDevname;
        private double tktThresholdAmt;
        private List<GameTypeData> gameTypeData;

        public int getMaxEventCount() {
            return maxEventCount;
        }

        public String getGameDisplayName() {
            return gameDisplayName;
        }

        public double getTktMaxAmt() {
            return tktMaxAmt;
        }

        public int getGameId() {
            return gameId;
        }

        public int getMinEventCount() {
            return minEventCount;
        }

        public String getGameDevname() {
            return gameDevname;
        }

        public double getTktThresholdAmt() {
            return tktThresholdAmt;
        }

        public List<GameTypeData> getGameTypeData() {
            return gameTypeData;
        }
    }

    public static class GameTypeData {
        private String gameTypeDevName;
        private int gameTypeId;
        private List<DrawData> drawData;
        private String eventType;
        private String gameTypeDisplayName;
        private String upcomingDrawStartTime;
        private boolean areEventsMappedForUpcomingDraw;
        private String eventSelectionType;
        private double gameTypeMaxBetAmtMultiple;
        private double gameTypeUnitPrice;

        public String getGameTypeDevName() {
            return gameTypeDevName;
        }

        public int getGameTypeId() {
            return gameTypeId;
        }

        public List<DrawData> getDrawData() {
            return drawData;
        }

        public String getEventType() {
            return eventType;
        }

        public String getGameTypeDisplayName() {
            return gameTypeDisplayName;
        }

        public String getUpcomingDrawStartTime() {
            return upcomingDrawStartTime;
        }

        public boolean isAreEventsMappedForUpcomingDraw() {
            return areEventsMappedForUpcomingDraw;
        }

        public String getEventSelectionType() {
            return eventSelectionType;
        }

        public double getGameTypeMaxBetAmtMultiple() {
            return gameTypeMaxBetAmtMultiple;
        }

        public double getGameTypeUnitPrice() {
            return gameTypeUnitPrice;
        }
    }

    public static class DrawData {
        private int drawId;
        private String drawDisplayString;
        private String drawDateTime;
        private int drawNumber;
        private String ftg;
        private List<EventData> eventData;

        public int getDrawId() {
            return drawId;
        }

        public String getDrawDisplayString() {
            return drawDisplayString;
        }

        public String getDrawDateTime() {
            return drawDateTime;
        }

        public int getDrawNumber() {
            return drawNumber;
        }

        public String getFtg() {
            return ftg;
        }

        public List<EventData> getEventData() {
            return eventData;
        }
    }

    public static class EventData {
        private String eventLeague;
        private String favTeam;
        private String eventVenue;
        private String awayTeamOdds;
        private String eventCodeAway;
        private String eventDisplayHome;
        private String eventDate;
        private int eventId;
        private String homeTeamOdds;
        private String drawOdds;
        private String eventDisplayAway;
        private String eventCodeHome;

        public String getEventLeague() {
            return eventLeague;
        }

        public String getFavTeam() {
            return favTeam;
        }

        public String getEventVenue() {
            return eventVenue;
        }

        public String getAwayTeamOdds() {
            return awayTeamOdds;
        }

        public String getEventCodeAway() {
            return eventCodeAway;
        }

        public String getEventDisplayHome() {
            return eventDisplayHome;
        }

        public String getEventDate() {
            return eventDate;
        }

        public int getEventId() {
            return eventId;
        }

        public String getHomeTeamOdds() {
            return homeTeamOdds;
        }

        public String getDrawOdds() {
            return drawOdds;
        }

        public String getEventDisplayAway() {
            return eventDisplayAway;
        }

        public String getEventCodeHome() {
            return eventCodeHome;
        }
    }
}
