package v1;

public class Stat {

    private int stat_id;
    private String stat_name;
    private float stat_value;
    
    public int getStat_id() {
        return stat_id;
    }

    public void setStat_id(int stat_id) {
        this.stat_id = stat_id;
    }

    public String getStat_name() {
        return stat_name;
    }

    public void setStat_name(String stat_name) {
        this.stat_name = stat_name;
    }

    public float getStat_value() {
        return stat_value;
    }

    public void setStat_value(float stat_value) {
        this.stat_value = stat_value;
    }

    public Stat(int stat_id, String stat_name, float stat_value) {
        this.stat_id = stat_id;
        this.stat_name = stat_name;
        this.stat_value = stat_value;
    }
    
}
