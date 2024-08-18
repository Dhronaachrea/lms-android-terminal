package demo.stpl.com.tpsmergedbuild.utils.widgets;

//import com.sparkzeal.sportslottery.beans.SportsLotteryData;
//import com.sparkzeal.sportslottery.beans.SportsLotteryGame;

//import tpsgames.beans.SportsLotteryData;
//import tpsgames.beans.SportsLotteryGame;

import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryData;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryGame;

/**
 * Created by stpl on 9/24/2016.
 */
public class SoccerPhysical {

    public static int position;

    public static SportsLotteryGame getSportsGame(SportsLotteryData mainData, String gameType) {
        for (int i = 0; i < mainData.getSportsLotteryGames().size(); i++) {
            SportsLotteryGame game = mainData.getSportsLotteryGames().get(i);
            if (game.getGameType().equalsIgnoreCase(gameType)) {
                position = i;
                return game;
            }
        }

        return null;
    }

    public static int getPosition(){
        return position;
    }
}
