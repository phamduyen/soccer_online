package bean;

import java.io.Serializable;

/**
 * Created by Thanh Than on 27/04/2016.
 */
public class Pitch implements Serializable {
    private int IDPitch;
    private User user;
    public String Name ;
    private  int Cost;
    private  int Players;




    public Pitch() {
    }

    /*public Pitch(int costs, int iDPitch, int iDUser, int images, String name, int players) {
        this.costs = costs;
        this.iDPitch = iDPitch;
        this.iDUser = iDUser;
        this.images = images;
        this.name = name;
        this.players = players;
    }*/


    public int getIDPitch() {
        return IDPitch;
    }

    public void setIDPitch(int IDPitch) {
        this.IDPitch = IDPitch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public int getPlayers() {
        return Players;
    }

    public void setPlayers(int players) {
        Players = players;
    }
}
