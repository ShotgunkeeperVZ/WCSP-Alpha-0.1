import java.util.ArrayList;


public class Variable {
    public ArrayList<Integer> changeLog = new ArrayList<>();

    public Variable(String name, double floorRange, double ceilingRange) {

        this.name = name;
        this.floorRange = (int) floorRange;
        this.ceilingRange = (int) ceilingRange;

    }

    public Variable(String name, int floorRange, int ceilingRange) {

        this.name = name;
        this.floorRange = floorRange;
        this.ceilingRange = ceilingRange;

    }

    //copy constructor
    public Variable(Variable variable) {
        this.name = variable.getName();
        this.value = variable.getValue();
        this.floorRange = variable.getFloorRange();
        this.ceilingRange = variable.getCeilingRange();

    }


    private int value;
    private String name;
    private final int floorRange;
    private final int ceilingRange;

    public int getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = (int) value;
    }

    public String getName() {
        return name;
    }

    public int getFloorRange() {
        return floorRange;
    }

    public int getCeilingRange() {
        return ceilingRange;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean exists(Variable variable, ArrayList<Variable> variables) {
        for (Variable item : variables) {
            if (item.getName().equals(variable.getName())) {
                return true;
            }
        }

        return false;

    }

}