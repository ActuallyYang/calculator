package v1;

import java.util.HashMap;
import java.util.Map;

public enum EnumStats {
    ROUNDS(105000021),
    FIRE_RATE(105000023),
    FIREARM_ATK(105000026),
    FIREARM_CRITICAL_HIT_RATE(105000030),
    FIREARM_CRITICAL_HIT_DAMAGE(105000031),
    WEAKPOINT_DAMAGE(105000035),
    RELOAD_TIME(105000095);

    public final Integer id;
    private static final Map<Integer, EnumStats> BY_ID = new HashMap<>();

    static {
        for (EnumStats s: values()) {
            BY_ID.put(s.id, s);
        }
    }

    private EnumStats(Integer id){
        this.id  = id;
    }

    public static EnumStats valueOfId(int id) {
        for (EnumStats s : values()) {
            if (s.id.equals(id)) {
                return s;
            }
        }
        return null;
    }

    public static EnumStats getStatByID(int id) {
        return BY_ID.get(id);
    }

    public static boolean statExists(int id) {
        if(BY_ID.get(id) == null){
            return false;
        } else {
            return true;
        }
    }
}
