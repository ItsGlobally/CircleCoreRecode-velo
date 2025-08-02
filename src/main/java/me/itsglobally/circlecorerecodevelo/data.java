package me.itsglobally.circlecorerecodevelo;

import java.util.HashMap;
import java.util.UUID;

public class data {
    private static final HashMap<UUID, String> prefix = new HashMap<>();
    public static void setPrefix(UUID u, String p) {
        prefix.put(u, p);
    }
    public static String getPrefix(UUID u) {
        return prefix.getOrDefault(u, "§7");
    }
}
