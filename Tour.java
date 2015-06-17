import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class Tour {
    
    public static int tour(String[] arrFriends, String[][] ftwns, Map<String, Double> h) {
                
        double total = 0.0;
        double prev  = 0.0;
        double curr  = 0.0;
        double theta = 0.0;
        // store distance data in hashmap 
        Map<String,Double[]> coordList = new HashMap<>();
        // store friend:town array in hashmap
        Map<String,String> ftMap = new HashMap<>();
        for(String[] ftown : ftwns) {
          ftMap.put(ftown[0],ftown[1]);
        }
        String lastTown = "";
        for(String[] townArr : ftwns) {
          String town = townArr[1];
          curr = h.get(town);
          //{x,y,d,θ,n}
          Double[] coord = new Double[5];
          coord[0]=Math.cos(theta)*curr;
          coord[1]=Math.sin(theta)*curr;
          coord[2]=Math.sqrt(curr*curr - prev*prev);
          if(prev!=0.0)
            theta += Math.atan2(curr,prev);
          else
            theta += 0.0;
          coord[3]=theta;
          coord[4]=Double.parseDouble(town.substring(1));
          coordList.put(town,coord);         
          prev = curr; 
          if(Arrays.asList(arrFriends).contains(townArr[0]))
            lastTown = town;
        }
        ArrayList<String> bypass = new ArrayList<>();
        for(String[] ft : ftwns){
            if(Arrays.asList(arrFriends).contains(ft[0])){
              if(bypass.peek()>0){
                double totalX = 0.0;
                double totalY = 0.0;
                for(String each : bypass){
                  totalX+=coordList.get(each)[0];
                  totalY+=coordList.get(each)[1];
                }
                bypass.removeAll();
                total+=Math.sqrt(totalX*totalX+totalY+totalY);
              }
              else {
                total+=coordList.get(ft[1]))[2];
                continue;
              }
            }
            else {
              bypass.add(ft[1]);
            }
        } 
        total+=h.get(lastTown);
        return (int)Math.floor(total);
    }
}
