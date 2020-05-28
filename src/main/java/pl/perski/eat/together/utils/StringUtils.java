package pl.perski.eat.together.utils;

public class StringUtils {
    public static String addIdToList(String idList, int id) {
        if (idList == null) {
            idList = ";";
        }
        if (checkIfListContainsId(idList, id)) {
            return idList;
        }
        return String.format("%s%d;", idList, id);
    }

    public static String removeDotsFromEmail(String email) {
        int atPosition = email.indexOf('@');
        return email.substring(0, atPosition).replace(".", "")
                + email.substring(atPosition);

    }

    public static String removeIdFromList(String idList, int id) {
        return idList.replace(String.format(";%d;", id), ";");
    }

    public static boolean checkIfListContainsId(String idList, int id) {
        return idList != null && idList.contains(String.format(";%d;", id));
    }
}
