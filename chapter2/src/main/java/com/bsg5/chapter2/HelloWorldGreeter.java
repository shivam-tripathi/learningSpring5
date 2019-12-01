package com.bsg5.chapter2;

import java.io.PrintStream;

public class HelloWorldGreeter implements Greeter {
    private PrintStream printStream;
    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void greet() {
        this.printStream.print("Hello, World!");
    }
}

