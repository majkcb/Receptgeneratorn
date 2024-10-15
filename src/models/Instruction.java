package models;

import java.util.List;

public interface Instruction {
    List<String> getSteps();
    void addStep(String step);
    void removeStep(String step);
    void setSteps(List<String> steps);
}
