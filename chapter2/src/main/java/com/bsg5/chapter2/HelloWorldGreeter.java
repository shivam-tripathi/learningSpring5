package com.bsg5.chapter2;

import java.io.PrintStream;

public class HelloWorldGreeter implements Greeter {
    private PrintStream ps;
    public void setPrintStream(PrintStream ps) {
        this.ps = ps;
    }

    public void greet() {
        this.ps.print("Hello, World!");
    }
}

