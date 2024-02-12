package de.oth.mocker;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static de.oth.mocker.Frequency.*;
import static de.oth.mocker.Mocker.mock;
import static de.oth.mocker.Mocker.spy;
import static de.oth.mocker.MyInvocationHandler.verify;

class MockerTest {
    @Test
    public void mockTest1() {
        ISpyExample example = new SpyExample();
        ISpyExample spy = spy(example);
        ISpyExample mock = mock(ISpyExample.class);
        spy.say("Test");
        verify(spy, atLeast(2)).say("Test");
    }

    //spying only works with the test method above
    @Test
    public void mockTest2() {
        List<String> names = new ArrayList<>();
        List<String> spyList = spy(names);
        spyList.add("John Doe"); // really adds to ArrayList names spyList.add("Max Muster");
        spyList.add("John Doe");
        for (int i=0; i<2; i++) {
            System.out.println(spyList.get(i));
        }
        spyList.size(); // would return 3
        verify(spyList, atMost(2)).add("John Doe");
        verify(spyList, atLeast(1)).add("Max Muster");
        verify(spyList, never()).clear();
        verify(spyList, times(1)).add("Max");
    }
}