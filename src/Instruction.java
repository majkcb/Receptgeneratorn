import java.util.List;

public interface Instruction {
    List<String> getSteps();
    void addStep(String step);
}
