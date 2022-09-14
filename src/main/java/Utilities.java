import org.apache.commons.lang3.tuple.Pair;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Utilities {
    private class matching{
        public matching(int n) {
            this.existsArray =  new boolean[n];
            Arrays.fill(this.existsArray,false);

        }
        private ArrayList<Pair> pairArray;
        private boolean[] existsArray;

        public boolean addPair(Pair pair){
            if(hasCommon(pair)){
                return false;
            }

            else{
                this.pairArray.add(pair);
                this.existsArray[(int) pair.getLeft()] = true;
                this.existsArray[(int) pair.getRight()] = true;
            }
        }

        private boolean hasCommon(Pair pair){
            if(this.existsArray[(int) pair.getLeft()] || this.existsArray[(int) pair.getRight()]){
                return true;
            }

            else{
                return false;
            }
        }
    }
    public class flySchedule{
        public flySchedule(int fireFlyCount) {
            if(fireFlyCount%4 != 0){
                throw new IllegalArgumentException("Firefly Count Mod 4 should equal zero or one.")
            }

            else{
                flyScheduleArray = new ArrayList<>(fireFlyCount/2);

            }
        }

        public void feedArray(ArrayList<Pair> pairArray){
            pairArray.stream().forEach(pair -> {
                for (int i = 0; i < this.; i++) {

                }
            });
        }


        private ArrayList<matching> flyScheduleArray;



    }
}
