package general.models;

/**
 * Created by Daniel on 2016/11/02.
 */
public class Clock
{
    private long whiteTime;
    private long blackTime;
    private long updateTime;

    public Clock(long whiteTime, long blackTime, long updateTime) {
        this.whiteTime = whiteTime;
        this.blackTime = blackTime;
        this.updateTime = updateTime;
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
}
