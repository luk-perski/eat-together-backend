package pl.perski.eat.together.utils;

public class StringUtils {
    public static String addIdToList(String idList, int id) {
        if (idList == null) {
            idList = "";
        }
        return String.format("%s%d;", idList, id);
    }

    public static String removeDotsFromEmail(String email){
        int atPosition = email.indexOf('@');
        return email.substring(0, atPosition).replace(".", "")
                + email.substring(atPosition);

    }
}
