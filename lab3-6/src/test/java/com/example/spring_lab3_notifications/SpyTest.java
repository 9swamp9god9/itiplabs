package com.example.spring_lab3_notifications;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SpyTest {

    @Test
    void shouldSpyOnList() {
        List<String> list = new ArrayList<>();
        List<String> spyList = spy(list);

        spyList.add("Spring");
        spyList.add("Boot");

        verify(spyList).add("Spring");
        verify(spyList).add("Boot");
        verify(spyList, times(2)).add(any());
    }
}