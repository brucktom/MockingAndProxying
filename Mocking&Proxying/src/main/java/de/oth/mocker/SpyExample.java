package de.oth.mocker;

public class SpyExample implements ISpyExample{

    @Override
    public void say(String str) {
        System.out.println(str);
    }

    public SpyExample() {}
}
