public class main {
    public static void main(String[] args) {
        Problem problem = new Problem();

        problem.AddVariable(new Variable("A", 1, 8));
        problem.AddVariable(new Variable("B", 1, 8));
        problem.AddVariable(new Variable("C", 1, 8));
        problem.AddVariable(new Variable("D", 1, 8));
        problem.AddVariable(new Variable("E", 1, 8));
        problem.AddVariable(new Variable("F", 1, 8));
        problem.AddVariable(new Variable("G", 1, 8));
        problem.AddVariable(new Variable("H", 1, 8));

        problem.Initialize(1000);
        problem.Solve();


    }
}
