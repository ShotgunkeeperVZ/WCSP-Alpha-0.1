import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

public class FireFly {

    public String flyID = UUID.randomUUID().toString();

    //TODO: REFACTOR!!!!!!
    //TODO: changelog should contain cost and it should be in a firefly not a var instance\
    //TODO: Runner.java performance and correctness
    //TODO: fp logic
    /* TODO:
        1- Vectorize hamming distance calculation
        2- Vectorize Attractiveness Calculation
     */
    public FireFly(ArrayList<Variable> variables) {
        //init all variables to a random number within their space
        for (int i = 0; i < variables.size(); i++) {
            Variable variable = variables.get(i);
            this.variables.add(new Variable(variable.getName(), variable.getFloorRange(), variable.getCeilingRange()));
            this.variables.get(i).setValue(this.getRandomNumber(variable.getFloorRange(), variable.getCeilingRange() + 1));
            this.variables.get(i).changeLog.add(this.variables.get(i).getValue());
        }

//       variables.forEach(variable -> this.variables.add(variable));
//       this.variables.forEach(variable -> variable.setValue();
//       System.out.println("FireFly");
//       this.variables.forEach(variable -> System.out.println("Name: " + variable.getName() + "\tValue: "+variable.getValue()));
    }

    public double hammingDistance(FireFly candidateFireFly) {
        int hammingDistanceValue = 0;
        for (int i = 0; i < this.variables.size(); i++) {

            if (candidateFireFly.variables.get(i).getValue() == (this.variables.get(i).getValue())) {
                continue;
            } else {
                hammingDistanceValue++;
            }


        }

//
//        Stream<Variable> variableStream = candidateFireFly.getVariables().stream();
//        variableStream.forEach()

//        System.out.println("Hamming: "+hammingDistanceValue);
        return hammingDistanceValue;
    }

    //TEMP CONIG
//    will move
    public static double BaseAttraction = 1;
    public static double ConvergenceMultiplier = 0.009;


    public double attractiveness(FireFly candidateFireFly) {
        double distance = this.hammingDistance(candidateFireFly);
//        this.print();
//        candidateFireFly.print();


        return (Math.exp(-FireFly.ConvergenceMultiplier * (Math.pow(distance, 2)))) * FireFly.BaseAttraction;
    }

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void print() {
        System.out.println(flyID);
        for (Variable variable : this.variables) {
            System.out.println(variable.getName() + "\t" + variable.getValue());
            System.out.println("Change Log:\t");
            for (int i = 0; i < variable.changeLog.size(); i++) {
                System.out.print("->" + variable.changeLog.get(i));

            }
            System.out.println("\n");
        }
        System.out.println("Cost of FireFly:\t" + Problem.Cost(this.variables));
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    private final ArrayList<Variable> variables = new ArrayList<>();
}
