package com.code.basic.Unit.junit.thread;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class B {
	
    @Test
    public void c() {
        assertThat(3, greaterThan(1));
    }

    @Test
    public void d() {
        assertThat(3, lessThan(1));
    }
    
}
