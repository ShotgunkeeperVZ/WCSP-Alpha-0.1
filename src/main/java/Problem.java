import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.cos;

public class Problem {


    private  ArrayList<FireFly> fireFlies;
    private  ArrayList<Variable> variables;
    private  ArrayList<Variable> solution;

    public Problem() {
        this.fireFlies = new ArrayList();
        this.variables = new ArrayList();
        this.solution = new ArrayList();
    }


//Temp static and public

    public static double Cost(ArrayList<Variable> constraintVariables) {
        double cost = 0;
        for (int i = 1; i < constraintVariables.size() - 1; i++) {
            for (int j = i; j < constraintVariables.size(); j++) {
                //System.out.println("Column: "+i+"\t"+"Row: " + constraintVariables.get(i).getValue());
                //System.out.println("Column: "+j+"\t"+"Row: " + constraintVariables.get(j).getValue());
                if (abs(constraintVariables.get(i).getValue() - constraintVariables.get(j).getValue()) == j - i) {
                    cost += 10;

                }

            }
        }
        return cost;
    }

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
//        TEMP
        int k = 0;
//        END TEMP
        while (k < 2000) {
            for (int i = 0; i < this.fireFlies.size(); i++) {
                for (int j = i + 1; j < this.fireFlies.size(); j++) {
                    //Add DP for Cost
                    FireFly explorerFireFly = this.fireFlies.get(i);
                    FireFly candidateFireFly = this.fireFlies.get(j);
                    if (Cost(explorerFireFly.getVariables()) > Cost(this.fireFlies.get(j).getVariables())) {

                        double attractiveness = explorerFireFly.attractiveness(candidateFireFly);

                        for (int l = 0; l < explorerFireFly.getVariables().size(); l++) {
                            Random random = new Random();
                            double cutter = random.nextDouble(1);

                            if (attractiveness < cutter) {
                                explorerFireFly.getVariables().get(l).setValue(candidateFireFly.getVariables().get(l).getValue());
                                explorerFireFly.getVariables().get(l).changeLog.add(candidateFireFly.getVariables().get(l).getValue());
                            }
                        }

//                        System.out.println("attractiveness:\t" + attractiveness);
//                        System.out.println("__________________________________________________");
                    } else {

                    }
                }
            }
            FireFly optimalFireFly = this.fireFlies.get(0);
            double optimalFireFlyCost = Cost(this.fireFlies.get(0).getVariables());
            for (int i = 1; i < this.fireFlies.size(); i++) {
                if (Cost(this.fireFlies.get(i).getVariables()) < optimalFireFlyCost) {
                    optimalFireFlyCost = Cost(this.fireFlies.get(i).getVariables());
                    optimalFireFly = this.fireFlies.get(i);
                }
            }
            System.out.println("Iter:\t" + k + "\t" + optimalFireFlyCost);
            optimalFireFly.print();

            k++;
        }

    }

    public ArrayList<FireFly> getFireFlies() {
        return fireFlies;
    }


}
