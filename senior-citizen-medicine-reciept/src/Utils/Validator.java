package Utils;

public class Validator {

    public static boolean validAge(int age){
        return age > 0 && age < 130;
    }

    public static boolean validHeartRate(int hr){
        return hr > 30 && hr < 220;
    }

    public static boolean notEmpty(String text){
        return text != null && !text.trim().isEmpty();
    }
}
