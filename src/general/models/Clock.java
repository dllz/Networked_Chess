package general.models;

/**
 * Created by Daniel on 2016/11/02.
 */
public class Clock
{
    private long whiteTime;
    private long blackTime;
    private long updateTime;
    private long startTime;

    public Clock(long startTime, int gameLength) {
        this.whiteTime = startTime + (gameLength*60);
        this.blackTime = startTime + (gameLength*60);
        this.updateTime = startTime;
        this.startTime = startTime;
    }

    public long getWhiteTime() {

        return whiteTime;
    }

    public long getBlackTime() {
        return blackTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setWhiteTime(long whiteTime) {
        this.whiteTime = whiteTime;
    }

    public void setBlackTime(long blackTime) {
        this.blackTime = blackTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getWhiteLeft()
    {
        return whiteTime - startTime;
    }
    public long getBlackLeft()
    {
        return whiteTime - startTime;
    }
}
