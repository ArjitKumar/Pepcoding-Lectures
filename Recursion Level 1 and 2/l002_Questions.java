import java.util.*;

public class l002_Questions {
public static void main( String[] args){
        
        String str = "abaaaacd";
        // to sort string convert it into char array first
        // System.out.println(str);
        // System.out.println(obj.permutations_with_Boolean("aab",""));
        // System.out.println(string_Permutations("aab",""));
        recusive_Permutations_Array();
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
            count +=string_Permutations_ALL(ros,ans + ch);
        }
        return count;

    }

    // GENERATES ALL UNIQUE PERMUTATIONS ( 2. METHODS )

   static class UNIQUE_PERMUTATIONS_OF_String {

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

// permuation of array -> STRIVER  ----> similar for string permutations
  public static int recusive_Permutations(int[] arr, ArrayList<Integer> ds, ArrayList<ArrayList<Integer>> ans, boolean[] freq){
      if( ds.size() == arr.length){
        ans.add(new ArrayList<>(ds));
        return 1;
      }
      int count = 0;
      for( int i = 0 ; i < arr.length ; i++){
        if(!freq[i]){
            // mark add call then remove and unmark
            freq[i] = true;
            ds.add(arr[i]);
            count += recusive_Permutations(arr,ds,ans,freq);
            ds.remove(ds.size()-1);
            freq[i] = false;
        }
      }
      return count;
  }
  public static void recusive_Permutations_Array(){
    int[] arr = {1,2,3};
    boolean[] freq = new boolean[arr.length];
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    ArrayList<Integer> ds = new ArrayList<>();
    System.out.println(recusive_Permutations(arr, ds, ans, freq));
    for( ArrayList<Integer> list : ans){
        for( int val : list){
            System.out.print(val + ",");
        }
        System.out.println();
    }
    
  }


  // ========================================= NEXT PERMUTATION ===============================
  class NEXT_PERMUTATION{
    public static void swap(List<Integer> arr, int i, int j){
        int temp = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,temp);
    }
    public static void reverse(List<Integer> arr, int low, int high){
        while( low < high){
            swap(arr,low, high);
            low++;
            high--;
        }
    }
    static List<Integer> nextPermutation(int N, int arr[]){
        // code here
        ArrayList<Integer> A = new ArrayList<Integer>();
        for( int val : arr) A.add(val);
        int index = -1;
        int n = A.size();
        // starting from n-2 that could be a possible breakpoint
        for(int i = n-2 ; i >= 0 ; i-- ){
            if( A.get(i) < A.get(i+1)){
                index = i;
                break;
            }
        }
        if( index == -1){ // means array is sorted in decreasing order so just reverse
        Collections.reverse(A);
        return A;
        }
        for( int i = n-1 ; i >= 0 ; i--){
            if( A.get(i) > A.get(index)){
                swap(A,i,index);
                break;
            }
        }
        // now just reverse from index + 1 to last
        reverse(A,index + 1, n-1);
        return A;

    }
}




}