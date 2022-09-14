import org.apache.commons.lang3.tuple.Pair;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.lang.Math.abs;

public class
Problem {


    private  ArrayList<FireFly> fireFlies;
    private  ArrayList<Variable> variables;
    private  ArrayList<Variable> solution;

    public Problem() {
        this.fireFlies = new ArrayList();
        this.variables = new ArrayList();
        this.solution = new ArrayList();
    }


//Temp static and public



    public boolean AddVariable(Variable variable) {

        //check if var already exists
        if (Variable.exists(variable, this.variables)) {
            throw new IllegalArgumentException("This variable already exists.");
        } else {
            this.variables.add(variable);
        }

        return true;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void Initialize(int fireflyCount) {
        for (int i = 0; i < fireflyCount; i++) {
            this.fireFlies.add(new FireFly(this.variables));
        }

    }

    public void Solve() {

        /*
         * Normally making swarm optimization algorithms run at parallel is difficult,
         * because movements of an agent is determined by other agents location and value of their objective function,
         * Normally these algorithms are run sequentially, meaning that the order at which a firefly moves is determined by
         * the order agents are held in the data structure chosen by the designer.
         *
         * So one can say the order of the agents operation dose significantly  affect the final results.
         * If instead of executing operations on a per-firefly basis we execute movement operations on a pair of agents relations basis,
         * so we are effectively executing a movement operation for every two-membered subset of {x: x > 0 and x <= FireFly Count},
         * hence there will be (n * (n-1))/2 movement operations that need be executed.
         * We can order these subsets in n/2 groups of n-1 size in a way that no pairs of subsets in the group have a non-empty junction set.
         * This way there won't any possibility of collision (a firefly considering movement to another firefly that is already in a movement process)
         * so we can execute all operations on each group in parallel, technically, this approach is not linearly scalable, say if you have initialized the problem with 16 agents,
         * and you are running the program on an 8 Core/16 Thread Machine, you would probably see a big performance regression comparing to running the software sequentially.
         *
         * But you can solve this issue with running more firelies which menas reaching better solutions or reaching them faster.
         *
         * */


//        TEMP
        int k = 0;
        // for some reason this was in the loop?! Why I don't know
        ArrayList<Pair> flyPairs = new ArrayList<>();
        for(int i = 0;i< this.fireFlies.size();i++){
            for(int j = i; j < this.fireFlies.size(); j++){
                flyPairs.add(Pair.of(i,j));
            }
        }
//        END TEMP
        while (k < 50) {

            /*TODO:
            a double loops goes over all possible pairs of fireflies to get their hamming distance relative to each other
            Instead of for looping over all variable instance of a firefly we can do a vector difference and find how many different variables in each pair we have.
            Since we only need the hamming distance and not those who are different, this is sufficient,
            Performance wise it may not be substantial or even regressive at low variable numbers, but as V(the number of variables increase,
            It makes more sense to use this approach.

             As hamming distance calculation are independent from each other, we do them in parallel.4
             */



//            int[][] hammingDistanceMatrix = new int[this.fireFlies.size()][this.fireFlies.size()];


                //Matrix was too much memory!!
            Map<Pair,Double> attractivenessMap = new ConcurrentHashMap<>(flyPairs.size());

//            TODO: change hamming matrix to attractiveness
//        Stream<Pair> variableStream =

            System.out.println("B");
                flyPairs.parallelStream().forEach(pair -> {

                    FireFly explorerFireFly = this.fireFlies.get((int) pair.getLeft());
                    FireFly candidateFireFly = this.fireFlies.get((int) pair.getRight());
                    Double distance = explorerFireFly.hammingDistance(candidateFireFly);
                    Double attractiveness = Math.exp(-FireFly.ConvergenceMultiplier * Math.pow(distance,2)) * FireFly.BaseAttraction;
                    attractivenessMap.put(pair,attractiveness);

                });
            System.out.println("Af");
//                flyPairs.stream().forEach(pair -> {
//                    FireFly leftFireFly = this.fireFlies.get((int) pair.getLeft());
//                    FireFly rightFireFly = this.fireFlies.get((int) pair.getRight());
//
//
//                    for(int i=0;i < leftFireFly.getVariables().size();i++){
//                        Random random = new Random();
//                        double cutter = random.nextDouble(1);
//                        if(attractivenessMap.get(pair) < cutter){
//                            leftFireFly.getVariables().get(i).setValue(rightFireFly.getVariables().get(i).getValue());
//
//                        }
//                    }
//
//                    for(int i=0;i < rightFireFly.getVariables().size();i++){
//                        Random random = new Random();
//                        double cutter = random.nextDouble(1);
//                        if(attractivenessMap.get(pair) < cutter){
//                            rightFireFly.getVariables().get(i).setValue(leftFireFly.getVariables().get(i).getValue());
//                           }
//                    }
//                        });

//            System.out.println(Arrays.deepToString(hammingDistanceMatrix));


//
////            Method1 of Hamming distance
//            for (int i = 0; i < this.fireFlies.size(); i++) {
//                for (int j = i + 1; j < this.fireFlies.size(); j++) {
//                    //Add DP for Cost
//                    FireFly explorerFireFly = this.fireFlies.get(i);
//                    FireFly candidateFireFly = this.fireFlies.get(j);
//                    if (Cost(explorerFireFly.getVariables()) > Cost(this.fireFlies.get(j).getVariables())) {
//
//                        double attractiveness = explorerFireFly.attractiveness(candidateFireFly);
//
//                        for (int l = 0; l < explorerFireFly.getVariables().size(); l++) {
//                            Random random = new Random();
//                            double cutter = random.nextDouble(1);
//
//                            if (attractiveness < cutter) {
//                                explorerFireFly.getVariables().get(l).setValue(candidateFireFly.getVariables().get(l).getValue());
//                                explorerFireFly.getVariables().get(l).changeLog.add(candidateFireFly.getVariables().get(l).getValue());
//                            }
//                        }
//
////                        System.out.println("attractiveness:\t" + attractiveness);
////                        System.out.println("__________________________________________________");
//                    }
//                }
//            }
//            FireFly optimalFireFly = this.fireFlies.get(0);
//            double optimalFireFlyCost = Cost(this.fireFlies.get(0).getVariables());
//            for (int i = 1; i < this.fireFlies.size(); i++) {
//                if (Cost(this.fireFlies.get(i).getVariables()) < optimalFireFlyCost) {
//                    optimalFireFlyCost = Cost(this.fireFlies.get(i).getVariables());
//                    optimalFireFly = this.fireFlies.get(i);
//                }
//            }
//            System.out.println("Iter:\t" + k + "\t" + optimalFireFlyCost);
//            optimalFireFly.print();
            System.out.println(k);
            k++;
        }

    }



}
