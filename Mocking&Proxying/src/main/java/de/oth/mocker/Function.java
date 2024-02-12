package de.oth.mocker;

import java.util.ArrayList;

public class Function {
    private String methodName;
    private ArrayList<String> parameters;
    private static int occurences = 0;

    public Function(String methodName) {
        this.methodName = methodName;
        this.parameters = new ArrayList<>();
    }

    public Function(String methodName, String... parameters) {
        this.methodName = methodName;
        this.parameters = new ArrayList<>();
        for(String parameter : parameters) {
            this.parameters.add(parameter);
        }
    }

    public int getOccurences() {
        return occurences;
    }

    public void setOccurences(int occurences) {
        this.occurences = occurences;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }
}
