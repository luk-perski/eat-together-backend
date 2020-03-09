package pl.perski.eat.together.utils;

public class StringUtils {
    public static String addIdToList(String idList, int id) {
        if (idList == null) {
            idList = "";
        }
        return String.format("%s%d;", idList, id);
    }
}
