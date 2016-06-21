package bean;

/**
 * Created by Thanh Than on 27/04/2016.
 */
public class RentTime {
    private  int idTime;
    private  String time;

    public RentTime() {
    }

    public RentTime(int iDTime, String time) {
        this.idTime = iDTime;
        this.time = time;
    }

    public int getIdTime() {
        return idTime;
    }

    public void setIdTime(int idTime) {
        this.idTime = idTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
