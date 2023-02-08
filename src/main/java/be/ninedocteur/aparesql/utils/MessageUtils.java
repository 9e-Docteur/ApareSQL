package be.ninedocteur.aparesql.utils;


public class MessageUtils {

    public static void send(String message){
        System.out.println("{ApareSQL} " + message);
    }

    public static void sendSuccess(String message){
        System.out.println("{ApareSQL} " + ColorUtils.GREEN + message);
    }

    public static void sendError(String message){
        System.out.println("{ApareSQL} " + ColorUtils.RED_BOLD + message);
    }

    public static void sendWarn(String message){
        System.out.println("{ApareSQL} " + ColorUtils.YELLOW_BOLD + message);
    }
}
