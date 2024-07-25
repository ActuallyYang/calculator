package v1;

import java.math.BigDecimal;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class Calculator 
{
    public static void main( String[] args )
    {
        HashMap<String, Weapon> weapons = importWeapons();

        weapons.forEach((k, v) -> {
            if (v != null) {
                System.out.println(v.toString());
            } else {
                System.out.println("Weapon for key " + k + " is null");
            }
        });
    }

    // private static HashMap<String, Weapon> importWeapons(){
    //     try {
    //         HashMap<String, Weapon> weaponsMap = new HashMap<>();
    //         JSONArray weapons = JSONReader.readJsonFromUrl("https://open.api.nexon.com/static/tfd/meta/en/weapon.json");

    //         for (int i = 0; i < weapons.length(); i++) {
    //             JSONObject weaponObject  = weapons.getJSONObject(i);
    //                 String abilityName = (weaponObject.get("weapon_perk_ability_name") == null 
    //                 ? "null" 
    //                 : weaponObject.get("weapon_perk_ability_name").toString());
    //                 String abilityDescription = (weaponObject.get("weapon_perk_ability_description") == null
    //                 ? "null"
    //                 : weaponObject.get("weapon_perk_ability_description").toString());

    //             Weapon weapon = new Weapon( weaponObject.getInt("weapon_id"),
    //                 weaponObject.getString("weapon_name"),
    //                 weaponObject.getString("image_url"),
    //                 weaponObject.getString("weapon_type"),
    //                 weaponObject.getString("weapon_tier"),
    //                 weaponObject.getString("weapon_rounds_type"),
    //                 importStats(weaponObject.getJSONArray("base_stat")),
    //                 weaponObject.getJSONArray("firearm_atk"),
    //                 abilityName,
    //                 abilityDescription
    //             );

    //             weaponsMap.put(weapon.getWeapon_name(), weapon);
    //         }

    //         return weaponsMap;

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    private static HashMap<String, Weapon> importWeapons() {
        HashMap<String, Weapon> weaponsMap = new HashMap<>();
        try {
            JSONArray weapons = JSONReader.readJsonFromUrl("https://open.api.nexon.com/static/tfd/meta/en/weapon.json");
    
            for (int i = 0; i < weapons.length(); i++) {
                JSONObject weaponObject = weapons.getJSONObject(i);
    
                String abilityName = weaponObject.optString("weapon_perk_ability_name", "null");
                String abilityDescription = weaponObject.optString("weapon_perk_ability_description", "null");
    
                int weaponId = weaponObject.optInt("weapon_id", -1);
                String weaponName = weaponObject.optString("weapon_name", "unknown");
                String imageUrl = weaponObject.optString("image_url", "");
                String weaponType = weaponObject.optString("weapon_type", "");
                String weaponTier = weaponObject.optString("weapon_tier", "");
                String weaponRoundsType = weaponObject.optString("weapon_rounds_type", "");
                JSONArray baseStatArray = weaponObject.optJSONArray("base_stat");
                JSONArray firearmAtkArray = weaponObject.optJSONArray("firearm_atk");
    
                if (weaponId != -1 && !weaponName.equals("unknown") && baseStatArray != null && firearmAtkArray != null) {
                    Weapon weapon = new Weapon(
                            weaponId,
                            weaponName,
                            imageUrl,
                            weaponType,
                            weaponTier,
                            weaponRoundsType,
                            importStats(baseStatArray),
                            firearmAtkArray,
                            abilityName,
                            abilityDescription
                    );
    
                    weaponsMap.put(weapon.getWeapon_name(), weapon);
                } else {
                    System.err.println("Skipping weapon due to missing essential fields: " + weaponObject.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weaponsMap;
    }

    public static HashMap<String, Stat> importStats(JSONArray statsJSON){
        HashMap<String, Stat> stats = new HashMap<>();

        for (int i = 0; i < statsJSON.length(); i++) {
            JSONObject statObject = statsJSON.getJSONObject(i);
            int id = Integer.parseInt(statObject.get("stat_id").toString());

            // Handle different types for stat_value
            float value;
            Object statValueObj = statObject.get("stat_value");
            if (statValueObj instanceof BigDecimal) {
                value = ((BigDecimal) statValueObj).floatValue();
            } else if (statValueObj instanceof Number) {
                value = ((Number) statValueObj).floatValue();
            } else if (statValueObj instanceof String) {
                try {
                    value = Float.parseFloat((String) statValueObj);
                } catch (NumberFormatException e) {
                    // Handle the case where the string cannot be parsed to a float
                    throw new IllegalArgumentException("Invalid stat_value: " + statValueObj);
                }
            } else {
                throw new IllegalArgumentException("Unexpected type for stat_value: " + statValueObj.getClass().getName());
            }

            if(EnumStats.statExists(id)){
                Stat stat = new Stat(id, EnumStats.getStatByID(id).toString(), value);
                stats.put(EnumStats.getStatByID(id).toString(), stat);
            }
        }

        return stats;
    }
}
