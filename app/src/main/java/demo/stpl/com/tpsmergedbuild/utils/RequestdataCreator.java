package demo.stpl.com.tpsmergedbuild.utils;

//import com.sparkzeal.sportslottery.beans.SportsLotteryData;

//import com.sparkzeal.sportslottery.beans.SportsLotteryGame;

//import tpsgames.beans.SportsLotteryData;
//import tpsgames.beans.SportsLotteryGame;

import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryData;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryGame;

/**
 * Created by stpl on 9/17/2016.
 */
public class RequestdataCreator {

    public static String getPurchaseString(SportsLotteryData data, int position) {
        StringBuilder requestString = new StringBuilder();
        SportsLotteryGame game = data.getSportsLotteryGames().get(position);
        requestString.append(game.getGameName() + "|");
        {
            for (SportsLotteryGame.EventData event : game.getGameList()) {
                requestString.append(event.getCodeHome() + "-vs-" + event.getCodeAway());
                requestString.append("&");
            }
            requestString.setCharAt(requestString.length() - 1, '$');
        }

        requestString.append(key("userName"));
        requestString.append(value(data.getUserName()));

        requestString.append(key("gameId"));
        requestString.append(value(data.getGameId()));

        requestString.append(key("gameTypeId"));
        requestString.append(value(game.getGameNumber()));

        requestString.append(key("drawInfo"));
        {
            requestString.append(game.getCD() + "~" + game.getBetAmountMul() + "~");
            for (SportsLotteryGame.EventData event : game.getGameList()) {
                requestString.append(event.getEventId() + "@");
                requestString.append(event.isHomeExtra() ? "H%2B," : "");
                requestString.append(event.isHome() ? "H," : "");
                requestString.append(event.isDraw() ? "D," : "");
                requestString.append(event.isAway() ? "A," : "");
                requestString.append(event.isAwayExtra() ? "A%2B," : "");
                requestString.setCharAt(requestString.length() - 1, '$');
            }
            requestString.append("&");
        }

        requestString.append(key("drawCount"));
        requestString.append(value(data.getDrawCount()));

        requestString.append(key("ticketAmt"));
        requestString.append(value(data.getTicketAmt()));

        requestString.append(key("merCode"));
        requestString.append(value(data.getMerCode()));

        requestString.append(key("LSTktNo"));
        requestString.append(value(data.getLstktNo()));

        requestString.append(key("sessId"));
        requestString.append(value(data.getSessId()));

        requestString.append(key("slLstTxnId"));
        requestString.append(value(data.getSlLstTxnId()));

        requestString.append(key("CID"));
        requestString.append(value(data.getCID()));

        requestString.append(key("LAC"));
        requestString.append(value(data.getLAC()));

        requestString.append(key("simType"));
        requestString.append(value(data.getSimType()));

        requestString.append(key("deviceType"));
        requestString.append(value(data.getDeviceType()));

        requestString.append(key("reqCounter"));
        requestString.append(value(data.getReqCounter()));

        requestString.append(key("respCounter"));
        requestString.append(value(data.getRespCounter()));

        requestString.append(key("time"));
        requestString.append(value(data.getTime()));

        requestString.append(key("date"));
        requestString.append(value(data.getDate()));

        requestString.append(key("random"));
        requestString.append(data.getRandom());

        return requestString.toString();
    }

    private static String key(String key) {
        return key + "=";
    }

    private static String value(String value) {
        return value + "&";
    }
}
