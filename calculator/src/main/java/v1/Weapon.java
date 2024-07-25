package v1;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class Weapon {
    private int weapon_id;
    private String weapon_name;
    private String image_url;
    private String weapon_type;
    private String weapon_tier;
    private String weapons_rounds_type;
    private HashMap<String, Stat> stats;
    private JSONArray firearm_atk;
    private String weapon_perk_ability_name;
    private String weapon_perk_ability_description;

    public Weapon(int weapon_id, String weapon_name, String image_url, String weapon_type, String weapon_tier,
            String weapons_rounds_type, HashMap<String, Stat> stats, JSONArray firearm_atk, String weapon_perk_ability_name,
            String weapon_perk_ability_description) {
        this.weapon_id = weapon_id;
        this.weapon_name = weapon_name;
        this.image_url = image_url;
        this.weapon_type = weapon_type;
        this.weapon_tier = weapon_tier;
        this.weapons_rounds_type = weapons_rounds_type;
        this.stats = stats;
        this.firearm_atk = firearm_atk;
        this.weapon_perk_ability_name = weapon_perk_ability_name;
        this.weapon_perk_ability_description = weapon_perk_ability_description;
    }

    public int getWeapon_id() {
        return weapon_id;
    }

    public void setWeapon_id(int weapon_id) {
        this.weapon_id = weapon_id;
    }

    public String getWeapon_name() {
        return weapon_name;
    }

    public void setWeapon_name(String weapon_name) {
        this.weapon_name = weapon_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getWeapon_type() {
        return weapon_type;
    }

    public void setWeapon_type(String weapon_type) {
        this.weapon_type = weapon_type;
    }

    public String getWeapon_tier() {
        return weapon_tier;
    }

    public void setWeapon_tier(String weapon_tier) {
        this.weapon_tier = weapon_tier;
    }

    public String getWeapons_rounds_type() {
        return weapons_rounds_type;
    }

    public void setWeapons_rounds_type(String weapons_rounds_type) {
        this.weapons_rounds_type = weapons_rounds_type;
    }

    public JSONArray getFirearm_atk() {
        return firearm_atk;
    }

    public void setFirearm_atk(JSONArray firearm_atk) {
        this.firearm_atk = firearm_atk;
    }

    public String getWeapon_perk_ability_name() {
        return weapon_perk_ability_name;
    }

    public void setWeapon_perk_ability_name(String weapon_perk_ability_name) {
        this.weapon_perk_ability_name = weapon_perk_ability_name;
    }

    public String getWeapon_perk_ability_description() {
        return weapon_perk_ability_description;
    }

    public void setWeapon_perk_ability_description(String weapon_perk_ability_description) {
        this.weapon_perk_ability_description = weapon_perk_ability_description;
    }

    public HashMap<String, Stat> getStats() {
        return stats;
    }

    public void setStats(HashMap<String, Stat> stats) {
        this.stats = stats;
    }

    public int getAtkAtLvl(int lvl){
        JSONObject lvlObject = getFirearm_atk().getJSONObject(lvl);
        JSONArray temp = lvlObject.getJSONArray("firearm");
        JSONObject NEXONXDJSONObject = temp.getJSONObject(0);
        int atk = NEXONXDJSONObject.getInt("firearm_atk_value");

        return atk;
    }

    @Override
    public String toString() {
        return getWeapon_name() + 
                "\n ATK : " + getAtkAtLvl(100) +
                "\n Fire rate : " + getStats().get("FIRE_RATE").getStat_value() + " RPM" +
                "\n Crit rate : " + (getStats().get("FIREARM_CRITICAL_HIT_RATE").getStat_value() * 100) +  "%" +
                "\n Crit damage : " + getStats().get("FIREARM_CRITICAL_HIT_DAMAGE").getStat_value() + "x" +
                "\n Weakpoint damage : " + getStats().get("WEAKPOINT_DAMAGE").getStat_value() + "x" +
                "\n Rounds : " + getStats().get("Rounds").getStat_value() +
                "\n Reload time : " + getStats().get("RELOAD_TIME").getStat_value() + "s";
    }

}
