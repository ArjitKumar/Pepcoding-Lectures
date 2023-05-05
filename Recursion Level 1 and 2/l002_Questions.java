import java.util.*;

public class l002_Questions {
public static void main( String[] args){
        
        String str = "abaaaacd";
        // to sort string convert it into char array first
        // System.out.println(str);
        UNIQUE_PERMUTATIONS obj = new UNIQUE_PERMUTATIONS();
        // System.out.println(obj.permutations_with_Boolean("aab",""));
        System.out.println(string_Permutations("aab",""));
    }


public static int equalSet(int[] arr, int idx, int sum1, int sum2, String set1, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }

        int count = 0;
        count += equalSet(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " ", set2);
        count += equalSet(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " ");

        return count;
    }

    public static void equalSet() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        System.out.println(equalSet(arr, 1, arr[0], 0, arr[0] + " ", "")); // no mirror images
        System.out.println(equalSet(arr, 0, 0, 0," ", "")); // generates mirror images
    }


    // ============================= STRING PERMUTATIONS ==================================
    
    // GENERATES ALL PERMUTATIONS 
    public static int string_Permutations_ALL(String str, String ans){
        if( str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
         // extracting char and sending ros 
        int count = 0 ;
        for( int i = 0 ; i < str.length() ; i++){
            char ch = str.charAt(i);
            String ros = str.substring(0,i) + str.substring(i+1);
            count +=string_Permutations(ros,ans + ch);
        }
        return count;

    }

    // GENERATES ALL UNIQUE PERMUTATIONS ( 2. METHODS )

   static class UNIQUE_PERMUTATIONS {

    // NOT IN PREV AND CURR METHOD STRING SHOULD BE SORTED
   public static int permutationsUniqueWithSorting( String str, String ans, List<String> res) {

    if( str.length() == 0) {
        res.add(ans);
        return 1;
    }
    char prev = '$';
    int count = 0;
    for( int i = 0 ; i < str.length() ; i++) {
        char ch = str.charAt(i);
        if(prev != ch){
            String ros = str.substring(0,i) + str.substring(i+1);
            count += permutationsUniqueWithSorting(ros, ans + ch,res);
        }
        // make curr char = prev for next iterataion
        prev = ch;
        
    }
    return count;
  }

  public static int permutations_with_Boolean(String str, String ans){
    if( str.length() == 0) {
        System.out.println(ans);
        return 1;
    }
    boolean[] vis = new boolean[26];
    int count = 0;
    for( int i = 0 ; i < str.length(); i++){ // total number of calls
         char ch = str.charAt(i);
         if(!vis[ch-'a']){
            vis[ch-'a'] = true;
         String ros = str.substring(0,i)+str.substring(i+1);
         count += permutations_with_Boolean(ros,ans + ch);
         }
    }
    return count;
  }
  public List<String> permutationsUniqueWithSorting(String S) {
        // Code here
        List<String> ans = new ArrayList<>();
        char tempArray[] = S.toCharArray();
        Arrays.sort(tempArray);
        String s = new String(tempArray);
        permutationsUniqueWithSorting(s,"",ans);
        return ans;
    }
}




}