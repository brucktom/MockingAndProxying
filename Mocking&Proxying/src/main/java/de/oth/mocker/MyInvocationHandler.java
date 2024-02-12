package de.oth.mocker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static de.oth.mocker.FrequencyType.*;

public class MyInvocationHandler<T> implements InvocationHandler {

    static ArrayList<Function> methodList;
    static Frequency frequency;
    T spyObject;
    boolean isSpy = false;

    public MyInvocationHandler () {
        this.methodList = new ArrayList<>();
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //adds method to MethodList if not already there
        String methodName = method.toString();
        Function function = new Function(methodName);

        if (args != null) {
            for (Object arg : args) {
                function.getParameters().add(arg.toString());
            }
        } else {
            function.setParameters(null);
        }

        //if Spy and not called after verify method
        if(isSpy && frequency==null) {
            method.invoke(spyObject, args);
            methodList.add(function);
            return proxy;
        }

        //verify
        if (frequency != null) {
            int ctr=0;
            if(args!=null) {
                ctr = checkFrequency(function);
            } else {
                ctr = checkFrequencyNoParameters(function);
            }
            //comparing ctr with Frequency Object
            switch (frequency.getFrequencyType()) {
                case NEVER -> {
                    if (ctr > 0) {
                        throw new AssertionError("For method: " + methodName + "| Expected: 0 | Result: " + ctr);
                    }
                }
                case ATMOST -> {
                    if (ctr > frequency.getAmount()) {
                        throw new AssertionError("For method: " + methodName + "| Expected: at most " + frequency.getAmount() + " | Result: " + ctr);
                    }
                }
                case ATLEAST -> {
                    if (ctr < frequency.getAmount()) {
                        throw new AssertionError("For method: " + methodName + "| Expected: at least " + frequency.getAmount() + " | Result: " + ctr);
                    }
                }
                case TIMES -> {
                    if (ctr != frequency.getAmount()) {
                        throw new AssertionError("For method: " + methodName + "| Expected: " + frequency.getAmount() + " | Result: " + ctr);
                    } else {
                        System.out.println("Wurde " + ctr + "mal aufgerufen");
                    }
                }
            }
        } else {
            methodList.add(function);
        }
        return proxy;
    }

    public static <T> T verify(T t, Frequency f) {
        frequency = f;
        return t;
    }

    public static <T> T verify(T t) {
        frequency = new Frequency(1, TIMES);
        return t;
    }

    public int checkFrequencyNoParameters(Function f) {
        int ctr = 0;
        for(Function m : methodList) {
            if(m.getMethodName().equals(f.getMethodName())) {
                ctr++;
            }
        }
        return ctr;
    }

    public int checkFrequency(Function f) {
        int ctr = 0;
        for (Function m : methodList) {
            boolean paramsEqual = true;

            //compare method names
            if (m.getMethodName().equals(f.getMethodName())) {
                //compare parameters
                if (m.getParameters().size() == f.getParameters().size()) {
                    for (int i = 0; i < m.getParameters().size(); i++) {
                        //if params are different
                        if (m.getParameters().get(i).equals(f.getParameters().get(i))==false) {
                            paramsEqual = false;
                            break;
                        }
                    }
                }
                if (paramsEqual) {
                    ctr++;
                }
            }
        }
        return ctr;
    }

    public boolean isSpy() {
        return isSpy;
    }

    public void setSpy(boolean spy) {
        this.isSpy = spy;
    }

}
