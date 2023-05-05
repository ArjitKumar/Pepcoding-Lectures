import java.util.*;

public class l001_Basic_Maze_Questions {
public static void main( String[] args){
    floodfillAlgo();
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
// better method with void type
// bottom to top
    public static int mazePath_HVD(int sr, int sc, int er, int ec, ArrayList<String> ans, String psf) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        if (sr + 1 <= er)
            count += mazePath_HVD(sr + 1, sc, er, ec, ans, psf + "V");
        if (sr + 1 <= er && sc + 1 <= ec)
            count += mazePath_HVD(sr + 1, sc + 1, er, ec, ans, psf + "D");
        if (sc + 1 <= ec)
            count += mazePath_HVD(sr, sc + 1, er, ec, ans, psf + "H");

        return count;
    }



//BAD RECURSION CALLS -> HANDLING EVERYTHING IN BASE CASE
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


// ============================= MAZE PATH WITH MULTI JUMPS============================
// TOP TO BOTTOM (list return type)


public static ArrayList<String> mazePath_MULTI_HVD(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();
        for(int jump = 1 ;sr + jump <= er ; jump++) {
            ArrayList<String> Vertical = mazePath_MULTI_HVD(sr + jump, sc, er, ec);
            for (String s : Vertical) {
                myAns.add("V" + jump + s);
            }
        }
       for(int jump = 1 ;sr + jump <= er && sc + jump <= ec; jump++) {
            ArrayList<String> Diagonal = mazePath_MULTI_HVD(sr + jump, sc + jump, er, ec);
            for (String s : Diagonal) {
                myAns.add("D"+ jump + s);
            }

        }
        for(int jump = 1 ;sc + jump <= ec ;jump++) {
            ArrayList<String> Horizontal = mazePath_MULTI_HVD(sr, sc + jump, er, ec);
            for (String s : Horizontal) {
                myAns.add("H"+ jump + s);
            }
        }
        return myAns;
      }

// BOTTOM TO TOP      

 public static int mazePath_MULTI_HVD(int sr, int sc, int er, int ec,ArrayList<String> ans, String psf) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        // Vertical
        for(int jump = 1 ;sr + jump <= er ; jump++) {
            count += mazePath_MULTI_HVD(sr + 1, sc , er, ec , ans, "V" + jump + psf);
        }
        // Horizontal
        for(int jump = 1 ;sc + jump <= ec ; jump++) {
            count += mazePath_MULTI_HVD(sr, sc  + 1, er, ec , ans, "H" + jump+ psf);
        }
        // Diagonal
        for(int jump = 1 ;sr + jump <= er && sc + jump <= ec; jump++) {
            count += mazePath_MULTI_HVD(sr + 1, sc + 1, er, ec , ans, "D"+ jump + psf);
        }
        return count;
      }     


// if the number of calls / direction increases then we need to add more calls which will make code lengthy
// so we will make our generic using vectors and direction array

// single HVD
public static int mazePath_HVD_2(int sr, int sc, int er, int ec, ArrayList<String> ans, String psf,int[][] dir, String[] dirS) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        
        for( int d = 0 ; d < dir.length ; d++){
            // calculate x and y
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];
            if( x >= 0 && y >= 0 && x <= er && y <= ec){
                count += mazePath_HVD_2(x,y,er,ec,ans, psf + dirS[d],dir,dirS);
            }
        }

        return count;
    }


// ==================================== FLOOD FILL ALGO ================================
public static void floodfillAlgo(){
    int sr = 0, sc = 0, n = 3 , m = 3;
    boolean[][] vis = new boolean[n][m];
    int[][] dir = { {-1,0} , {-1,1}, {0,1}, {1,1},{1,0},{1,-1},{0,-1},{-1,-1} };
    String[] dirS = {"U" , "E" , "L" ,"S", "D", "N", "R", "W"};
    ArrayList<String> ans = new ArrayList<>();
    System.out.println(floodfillAlgo_multi(0,0,vis,dir,dirS,ans,""));
    System.out.println(ans);
}

// single jump
public static int floodfillAlgo(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS, ArrayList<String> ans, String psf){

    int n = vis.length, m = vis[0].length;
    if( sr == n-1 && sc == m-1){
        ans.add(psf);
        return 1;
    }

    int count = 0;
    vis[sr][sc] = true;
    for(int d = 0 ; d < dir.length ; d++){
        int x = sr + dir[d][0];
        int y = sc + dir[d][1];

        if(x>=0 && y >= 0 && x < n && y < m && !vis[x][y]){
            count += floodfillAlgo(x,y,vis,dir, dirS, ans , psf + dirS[d]);
        }
    }
    vis[sr][sc] = false;
    return count;

}

// multi jump
public static int floodfillAlgo_multi(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS, ArrayList<String> ans, String psf){

    int n = vis.length, m = vis[0].length;
    if( sr == n-1 && sc == m-1){
        ans.add(psf);
        return 1;
    }

    int count = 0;
    vis[sr][sc] = true;
    for( int rad = 1 ; rad <= Math.max(n,m);rad++){
        for(int d = 0 ; d < dir.length ; d++){
            int x = sr + rad * dir[d][0];
            int y = sc + rad * dir[d][1];

                if(x>=0 && y >= 0 && x < n && y < m && !vis[x][y]){
                    count += floodfillAlgo_multi(x,y,vis,dir, dirS, ans , psf + dirS[d] + rad);
                }
        }
    }
    vis[sr][sc] = false;
    return count;

}

// better way to write radius loop inside dir so to travel in straight line
public static int floodFill_multi(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS,
            ArrayList<String> ans, String psf) {
        int n = vis.length, m = vis[0].length;

        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;

        for (int d = 0; d < dir.length; d++)
            for (int rad = 1; rad <= Math.max(n, m); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (!vis[r][c])
                        count += floodFill_multi(r, c, vis, dir, dirS, ans, psf + dirS[d] + rad);
                } else
                    break;
            }

        vis[sr][sc] = false;
        return count;
    }



// =============================== PRACTICE QUESTIONS ===========================================

    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1
    // https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/?ref=rp

// Rat in a Maze Problem - I --> https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
class Rat_In_A_MAZE_Problem_1 {
    public static int floodfillAlgo(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS, ArrayList<String> ans, String psf){

    int n = vis.length, m = vis[0].length;
    if( sr == n-1 && sc == m-1){
        ans.add(psf);
        return 1;
    }

    int count = 0;
    vis[sr][sc] = true;
    for(int d = 0 ; d < dir.length ; d++){
        int x = sr + dir[d][0];
        int y = sc + dir[d][1];

        if(x>=0 && y >= 0 && x < n && y < m && !vis[x][y]){
            count += floodfillAlgo(x,y,vis,dir, dirS, ans , psf + dirS[d]);
        }
    }
    vis[sr][sc] = false;
    return count;

}
    public static ArrayList<String> findPath(int[][] mat, int n) {
    ArrayList<String> ans = new ArrayList<>();
    if( mat[0][0] == 0 || mat[n-1][n-1] == 0) return ans;
    int sr = 0, sc = 0;
    boolean[][] vis = new boolean[n][n];
    int[][] dir = { {-1,0} , {0,1}, {1,0}, {0,-1} };
    String[] dirS = {"U" , "R" , "D" ,"L"};
    for( int i = 0 ; i < n ; i++){
        for( int j = 0 ; j < n ; j++){
            if( mat[i][j] == 0) vis[i][j] = true;
        }
    }
    
    floodfillAlgo(0,0,vis,dir,dirS,ans,"");
    return ans;
    }
}



// ========================== LONGEST PATH/ SHORTEST PATH IN MATRIX ============================

// withtout class its not possible 

public static int longestPath(int sr, int sc, int[][] matrix, String psf){

}






}