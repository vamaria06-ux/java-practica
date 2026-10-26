package org.example;

import java.util.logging.Logger;

public class Sample {
    private static final Logger LOG = Log.get(Sample.class);

    @Repeat(1)
    public void publicFirst(int a, String s) {
        LOG.info("publicFirst: a=" + a + ", s=" + s);
    }

    public void publicSecond(double d, boolean flag) {
        LOG.info("publicSecond: d=" + d + ", flag=" + flag);
    }

    @Repeat(2)
    protected void protectedFirst(long x, String s) {
        LOG.info("protectedFirst: x=" + x + ", s=" + s);
    }

    @Repeat(3)
    protected void protectedSecond(int a, int b, char c) {
        LOG.info("protectedSecond: a=" + a + ", b=" + b + ", c=" + c);
    }

    @Repeat(2)
    void packageFirst(String s, int n) {
        LOG.info("packageFirst: s=" + s + ", n=" + n);
    }

    void packageSecond(float f, Object o) {
        LOG.info("packageSecond: f=" + f + ", o=" + o);
    }

    @Repeat(3)
    private void privateFirst(String s, double d) {
        LOG.info("privateFirst: s=" + s + ", d=" + d);
    }

    @Repeat(4)
    private void privateSecond(boolean flag, long x, String s) {
        LOG.info("privateSecond: flag=" + flag + ", x=" + x + ", s=" + s);
    }

    static class Address {
        final String street;
        final int number;
        Address(String street, int number) { this.street = street; this.number = number; }
        @Override public String toString() { return "Address[" + street + "," + number + "]"; }
    }

    @Repeat(1)
    private void withObject(Address address, Integer i) {
        LOG.info("withObject: address=" + address + ", i=" + i);
    }

}