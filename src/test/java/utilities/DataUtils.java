package utilities;

import java.util.Random;

public class DataUtils {

    /*
    This method will generate random email
     */



    public static String generateEmail(){
        Random random = new Random();
        int emailId = random.nextInt(100000);
        String email = "abcsd"+emailId+"@gmail.com";
        return email;

    }
    /*
    this method return random number between 0 and range
     */
    public static int generateRandomNumber(int range){
        Random random = new Random();
        int randomNum = random.nextInt(range);
        return randomNum;

    }

    /**
     * this method convert string to double and remove $ sign
     * and will return double
     */

    public static double remove$AndConvertToDouble(String amount){

        amount=amount.substring(1);
        return Double.parseDouble(amount);

    }
}
