
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    private String twoOccurrences(String stringA, String stringB) {
        int firstIndex = stringB.indexOf(stringA);
        if (firstIndex == -1) {
            System.out.println("The " + stringA + " is not in the " + stringB);
            return "false";
        }
        int secondIndex = stringB.indexOf(stringA, firstIndex + stringA.length());
        if (secondIndex == -1) {
            System.out.println("The " + stringA + " is only one time in  " + stringB);
            return "false";
        }
        return "true";
    }
    
    public void testing() {
        String stringA = "by";
        String stringB = "A story by Abby Long";
        String result = twoOccurrences(stringA, stringB);
        System.out.println(result);
        stringA = "a";
        stringB = "banana";
        result = twoOccurrences(stringA, stringB);
        System.out.println(result);
        stringA = "atg";
        stringB = "ctgtatgta";
        result = twoOccurrences(stringA, stringB);
        System.out.println(result); 
        stringA = "an";
        stringB = "banana";
        result = lastPart(stringA, stringB);
        System.out.println(result);  
        stringA = "zoo";
        stringB = "forest";
        result = lastPart(stringA, stringB);
        System.out.println(result);  
    }
    
    private String lastPart(String stringA, String stringB) {
        int firstIndex = stringB.indexOf(stringA);
        if (firstIndex == -1) {
            System.out.println("The " + stringA + " is not in the " + stringB);
            return stringB;
        }
        String stringAtoB = stringB.substring(firstIndex+stringA.length());
        return stringAtoB;
    }
}
