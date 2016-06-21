package bean;

/**
 * Created by Thanh Than on 27/04/2016.
 */
public class Rent {
    private int idRent;
    private RentTime rentTime;
    private Pitch pitch;
    private  String day;
    private  String name;
    private  String sdt;

    public Rent() {
    }

    /*public Rent(String day, int iDPitch, int iDRent, int iDTime, String name, String sdt) {
        this.day = day;
        this.iDPitch = iDPitch;
        this.iDRent = iDRent;
        this.iDTime = iDTime;
        this.name = name;
        this.sdt = sdt;
    }*/

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public int getIdRent() {
        return idRent;
    }

    public void setIdRent(int idRent) {
        this.idRent = idRent;
    }

    public RentTime getRentTime() {
        return rentTime;
    }

    public void setRentTime(RentTime rentTime) {
        this.rentTime = rentTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
