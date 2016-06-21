package bean;

/**
 * Created by Pham Duyen on 27/04/2016.
 */
public class menuItem {
    private String funtion;
    private String name;
    public menuItem(String funtion, String name) {
        this.funtion = funtion;
        this.name = name;
    }

    public String getFuntion() {
        return funtion;
    }

    public void setFuntion(String funtion) {
        this.funtion = funtion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
