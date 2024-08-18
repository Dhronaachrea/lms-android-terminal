package demo.stpl.com.tpsmergedbuild.beans;

/**
 * Created by stpl on 21-Oct-16.
 */
public class SleGameBean {

    private String responseCode;
    private String responseMsg;

    public SleData getSleData() {
        return sleData;
    }

    public void setSleData(SleData sleData) {
        this.sleData = sleData;
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

    private SleData sleData;



    public static class SleData{

        public GameData[] getGameData() {
            return gameData;
        }

        public void setGameData(GameData[] gameData) {
            this.gameData = gameData;
        }

        private GameData[] gameData;
        public static class GameData{
            private String maxEventCount;
            private String gameDisplayName;
            private String tktMaxAmt;
            private String gameId;
            private String minEventCount;
            private String gameDevname;
            private String tktThresholdAmt;

            public GameTypeData[] getGameTypeData() {
                return gameTypeData;
            }

            public void setGameTypeData(GameTypeData[] gameTypeData) {
                this.gameTypeData = gameTypeData;
            }

            public String getMaxEventCount() {
                return maxEventCount;
            }

            public void setMaxEventCount(String maxEventCount) {
                this.maxEventCount = maxEventCount;
            }

            public String getGameDisplayName() {
                return gameDisplayName;
            }

            public void setGameDisplayName(String gameDisplayName) {
                this.gameDisplayName = gameDisplayName;
            }

            public String getTktMaxAmt() {
                return tktMaxAmt;
            }

            public void setTktMaxAmt(String tktMaxAmt) {
                this.tktMaxAmt = tktMaxAmt;
            }

            public String getGameId() {
                return gameId;
            }

            public void setGameId(String gameId) {
                this.gameId = gameId;
            }

            public String getMinEventCount() {
                return minEventCount;
            }

            public void setMinEventCount(String minEventCount) {
                this.minEventCount = minEventCount;
            }

            public String getGameDevname() {
                return gameDevname;
            }

            public void setGameDevname(String gameDevname) {
                this.gameDevname = gameDevname;
            }

            public String getTktThresholdAmt() {
                return tktThresholdAmt;
            }

            public void setTktThresholdAmt(String tktThresholdAmt) {
                this.tktThresholdAmt = tktThresholdAmt;
            }

            private GameTypeData[] gameTypeData;
            public static class GameTypeData{

                private String gameTypeDevName;
                private String gameTypeId;
                private String eventType;
                private String gameTypeDisplayName;
                private String areEventsMappedForUpcomingDraw;
                private String upcomingDrawStartTime;
                private String eventSelectionType;
                private String gameTypeMaxBetAmtMultiple;
                private String gameTypeUnitPrice;

                public DrawData[] getDrawData() {
                    return drawData;
                }

                public void setDrawData(DrawData[] drawData) {
                    this.drawData = drawData;
                }

                public String getGameTypeDevName() {
                    return gameTypeDevName;
                }

                public void setGameTypeDevName(String gameTypeDevName) {
                    this.gameTypeDevName = gameTypeDevName;
                }

                public String getGameTypeId() {
                    return gameTypeId;
                }

                public void setGameTypeId(String gameTypeId) {
                    this.gameTypeId = gameTypeId;
                }

                public String getEventType() {
                    return eventType;
                }

                public void setEventType(String eventType) {
                    this.eventType = eventType;
                }

                public String getGameTypeDisplayName() {
                    return gameTypeDisplayName;
                }

                public void setGameTypeDisplayName(String gameTypeDisplayName) {
                    this.gameTypeDisplayName = gameTypeDisplayName;
                }

                public String getAreEventsMappedForUpcomingDraw() {
                    return areEventsMappedForUpcomingDraw;
                }

                public void setAreEventsMappedForUpcomingDraw(String areEventsMappedForUpcomingDraw) {
                    this.areEventsMappedForUpcomingDraw = areEventsMappedForUpcomingDraw;
                }

                public String getUpcomingDrawStartTime() {
                    return upcomingDrawStartTime;
                }

                public void setUpcomingDrawStartTime(String upcomingDrawStartTime) {
                    this.upcomingDrawStartTime = upcomingDrawStartTime;
                }

                public String getEventSelectionType() {
                    return eventSelectionType;
                }

                public void setEventSelectionType(String eventSelectionType) {
                    this.eventSelectionType = eventSelectionType;
                }

                public String getGameTypeMaxBetAmtMultiple() {
                    return gameTypeMaxBetAmtMultiple;
                }

                public void setGameTypeMaxBetAmtMultiple(String gameTypeMaxBetAmtMultiple) {
                    this.gameTypeMaxBetAmtMultiple = gameTypeMaxBetAmtMultiple;
                }

                public String getGameTypeUnitPrice() {
                    return gameTypeUnitPrice;
                }

                public void setGameTypeUnitPrice(String gameTypeUnitPrice) {
                    this.gameTypeUnitPrice = gameTypeUnitPrice;
                }

                private DrawData[] drawData;

                public static class DrawData{
                    private String drawId;
                    private String drawDisplayString;
                    private String drawDateTime;
                    private String drawNumber;

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

                    public String getDrawDisplayString() {
                        return drawDisplayString;
                    }

                    public void setDrawDisplayString(String drawDisplayString) {
                        this.drawDisplayString = drawDisplayString;
                    }

                    public String getDrawDateTime() {
                        return drawDateTime;
                    }

                    public void setDrawDateTime(String drawDateTime) {
                        this.drawDateTime = drawDateTime;
                    }

                    public String getDrawNumber() {
                        return drawNumber;
                    }

                    public void setDrawNumber(String drawNumber) {
                        this.drawNumber = drawNumber;
                    }

                    private EventData[] eventData;

                    public static class EventData{
                        private String eventLeague;
                        private String favTeam;
                        private String eventVenue;
                        private String awayTeamOdds;
                        private String eventCodeAway;
                        private String eventDisplayHome;
                        private String eventDate;
                        private String eventId;
                        private String homeTeamOdds;
                        private String drawOdds;
                        private String eventDisplayAway;

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

                        public String getFavTeam() {
                            return favTeam;
                        }

                        public void setFavTeam(String favTeam) {
                            this.favTeam = favTeam;
                        }

                        public String getEventVenue() {
                            return eventVenue;
                        }

                        public void setEventVenue(String eventVenue) {
                            this.eventVenue = eventVenue;
                        }

                        public String getAwayTeamOdds() {
                            return awayTeamOdds;
                        }

                        public void setAwayTeamOdds(String awayTeamOdds) {
                            this.awayTeamOdds = awayTeamOdds;
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

                        public String getEventId() {
                            return eventId;
                        }

                        public void setEventId(String eventId) {
                            this.eventId = eventId;
                        }

                        public String getHomeTeamOdds() {
                            return homeTeamOdds;
                        }

                        public void setHomeTeamOdds(String homeTeamOdds) {
                            this.homeTeamOdds = homeTeamOdds;
                        }

                        public String getDrawOdds() {
                            return drawOdds;
                        }

                        public void setDrawOdds(String drawOdds) {
                            this.drawOdds = drawOdds;
                        }

                        public String getEventDisplayAway() {
                            return eventDisplayAway;
                        }

                        public void setEventDisplayAway(String eventDisplayAway) {
                            this.eventDisplayAway = eventDisplayAway;
                        }

                        private String eventCodeHome;

                    }

                }

            }

        }

    }
}
