import java.util.*;

public class Maze_Questions {
public static void main( String[] args){
    ArrayList<String> res = new ArrayList<>();
    System.out.println(mazePath_HVD_With_Int_Type(0,0,2,2,res,""));
    System.out.println(res);
    }

// ================================= MAZE PATH HVD SINGLE JUMP  ==========================

    // BETTER METHOD CHECK AND THEN MAKE CALL TO AVOID USELESS CALLS
public static ArrayList<String> mazePath_HVD_Better(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        if (sr + 1 <= er) {
            ArrayList<String> Vertical = mazePath_HVD(sr + 1, sc, er, ec);
            for (String s : Vertical) {
                myAns.add("V" + s);
            }
        }

        if (sc + 1 <= ec && sr + 1 <= er) {
            ArrayList<String> Diagonal = mazePath_HVD(sr + 1, sc + 1, er, ec);
            for (String s : Diagonal) {
                myAns.add("D" + s);
            }

        }

        if (sc + 1 <= ec) {
            ArrayList<String> Horizontal = mazePath_HVD(sr, sc + 1, er, ec);
            for (String s : Horizontal) {
                myAns.add("H" + s);
            }
        }

        return myAns;
    }


// top to bottom method -> ArrayList<> return type method

public static ArrayList<String> mazePath_HVD(int sr, int sc, int er, int ec){
    
    if(sr > er || sc > ec ) return new ArrayList<>();
    if( sr == er && sc == ec){ // ALREADY PRESENT SO CAN'T TAKE MOVE
        ArrayList<String> base = new ArrayList<>();
        base.add("");
        return base;
    }

    ArrayList<String> horizontalPaths = mazePath_HVD(sr,sc+1,er,ec);
    ArrayList<String> verticalPaths = mazePath_HVD(sr+1,sc,er,ec);
    ArrayList<String> diagonalPaths = mazePath_HVD(sr+1,sc+1,er,ec);
    // ab in path k age bs h v aur d add krna h 
    ArrayList<String> myAns = new ArrayList<>();
    for(String path : horizontalPaths){
        myAns.add("h" + path);
    }
    for(String path : verticalPaths){
        myAns.add("v" + path);
    }
    for(String path : diagonalPaths){
        myAns.add("d" + path);
    }
    return myAns;
}


// These type are easier to code
// with int (void)return type -> meaing bottom to top

public static int mazePath_HVD_With_Int_Type(int sr, int sc, int er, int ec, ArrayList<String> ans, String res){
  
 if( sr > er || sc > ec) return 0;
 if( sr == er && sc == ec){
    ans.add(res);
    return 1;
 }  
int count = 0;
count += mazePath_HVD_With_Int_Type(sr, sc+1, er, ec, ans,  res + "H");
count += mazePath_HVD_With_Int_Type(sr+1, sc, er, ec, ans,  res + "V");
count += mazePath_HVD_With_Int_Type(sr+1, sc+1, er, ec, ans,  res + "D");
return count;

}



















}