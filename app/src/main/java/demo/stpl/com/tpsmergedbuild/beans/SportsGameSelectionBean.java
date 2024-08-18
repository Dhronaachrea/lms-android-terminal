package demo.stpl.com.tpsmergedbuild.beans;

/**
 * Created by stpl on 21-Oct-16.
 */
public class SportsGameSelectionBean {

    private String homePlus = "";
    private String home = "";
    private String draw = "";
    private String away = "";
    private int totalSize = 0;

    public String getAwayPlus() {
        return awayPlus;
    }

    public void setAwayPlus(String awayPlus) {
        setTotalSize(awayPlus);
        this.awayPlus = awayPlus;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        setTotalSize(home);
        this.home = home;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        setTotalSize(draw);
        this.draw = draw;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        setTotalSize(away);
        this.away = away;
    }

    public String getHomePlus() {
        return homePlus;
    }

    public void setHomePlus(String homePlus) {
        setTotalSize(homePlus);
        this.homePlus = homePlus;
    }

    private void setTotalSize(String value){
        if(value.trim().length() > 0){
            totalSize++;
        }else{
            totalSize--;
        }
    }

    public int getTotalSize(){
        return totalSize;
    }


    private String awayPlus = "";
}
