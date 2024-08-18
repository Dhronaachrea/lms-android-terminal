package demo.stpl.com.tpsmergedbuild.utils;

//import com.sparkzeal.sportslottery.beans.SportsBean;
//import com.sparkzeal.sportslottery.beans.SportsLotteryData;
//import com.sparkzeal.sportslottery.beans.SportsLotteryGame;


import java.util.ArrayList;
import java.util.List;

import demo.stpl.com.tpsmergedbuild.beans.SportsBean;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryData;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryGame;

//import tpsgames.beans.SportsBean;
//import tpsgames.beans.SportsLotteryData;
//import tpsgames.beans.SportsLotteryGame;

/**
 * Created by stpl on 9/19/2016.
 */
public class DrawDataCreator {

    public static SportsLotteryData getSportsLottery(SportsBean sportsBean) {
        if (sportsBean.getSleData() == null || sportsBean.getSleData().getGameData() == null || sportsBean.getSleData().getGameData().size() == 0)
            return null;
        SportsLotteryData data = new SportsLotteryData();
        SportsBean.GameData gameData = sportsBean.getSleData().getGameData().get(0);
        List<SportsLotteryGame> sportsLotteryGames = new ArrayList<>();
        data.setGameId(gameData.getGameId() + "");
        if (gameData.getGameTypeData() == null || gameData.getGameTypeData().size() == 0)
            return null;
        for (SportsBean.GameTypeData gameTypeData : gameData.getGameTypeData()) {
            if (gameTypeData.getDrawData() == null || gameTypeData.getDrawData().size() == 0)
                continue;
            SportsBean.DrawData drawData = gameTypeData.getDrawData().get(0);
            if (drawData.getEventData() == null || drawData.getEventData().size() == 0)
                continue;
            List<SportsLotteryGame.EventData> eventDatas = new ArrayList<>();
            SportsLotteryGame game = new SportsLotteryGame();
            game.setGameNumber(gameTypeData.getGameTypeId() + "");
            game.setGameType(gameTypeData.getGameTypeDevName());
            game.setGameName(gameTypeData.getGameTypeDisplayName());
            game.setMin_amount(gameTypeData.getGameTypeUnitPrice() + "");
            game.setMax_amount(gameTypeData.getGameTypeMaxBetAmtMultiple() + "");
            game.setMultiple(gameTypeData.getEventSelectionType());
            game.setGame_date_time(gameTypeData.getUpcomingDrawStartTime());
            game.setMultipeName(gameTypeData.getEventType());

            game.setCD(drawData.getDrawId() + "");
            game.setCurrentDrawName(drawData.getDrawDisplayString());
            for (SportsBean.EventData eventData : drawData.getEventData()) {
                SportsLotteryGame.EventData event = new SportsLotteryGame.EventData();
                event.setTxtHome(eventData.getEventDisplayHome());
                event.setCodeHome(eventData.getEventCodeHome());
                event.setTxtAway(eventData.getEventDisplayAway());
                event.setCodeAway(eventData.getEventCodeAway());
                event.setVenue(eventData.getEventVenue());
                event.setTime(eventData.getEventDate());
                event.setEventId(eventData.getEventId() + "");
                eventDatas.add(event);
            }
            game.setGameList(eventDatas);
            sportsLotteryGames.add(game);
        }
        data.setSportsLotteryGames(sportsLotteryGames);
        return data;
    }

}
