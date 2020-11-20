package hw4;

import api.Util;

public class TestComponents
{
    public static void testAndGate()
    {
        AndGate c = new AndGate();
        Util.setInputs(c, "11");
        c.propagate();
        System.out.println(Util.toString(c.outputs())); // prints "1"
        Util.setInputs(c, "01");
        c.propagate();
        System.out.println(Util.toString(c.outputs())); // prints "0"
        c.invalidateOutputs();
        System.out.println(Util.toString(c.outputs())); // prints "-"
    }

    public static void testOrGate()
    {
        OrGate c = new OrGate();
        Util.setInputs(c, "11");
        c.propagate();
        System.out.println(Util.toString(c.outputs())); // Prints "1"
        c = new OrGate();
        Util.setInputs(c, "01");
        c.propagate();
        System.out.println(Util.toString(c.outputs())); // Prints "1"
        c = new OrGate();
        Util.setInputs(c, "10");
        c.propagate();
        System.out.println(Util.toString(c.outputs())); // Prints "1"
        c = new OrGate();
        Util.setInputs(c, "00");
        c.propagate();
        System.out.println(Util.toString(c.outputs())); // Prints "0"
        c.invalidateOutputs();
        System.out.println(Util.toString(c.outputs())); // Prints "-"
        c = new OrGate();
        Util.setInputs(c, "00");
        c.propagate();
        // TODO
        System.out.println(Util.toString(c.outputs())); // Should have issue
    }

    public static void testNotGate()
    {
        NotGate c = new NotGate();
        Util.setInputs(c, "00");
        c.propagate();
        System.out.print(Util.toString(c.outputs()));
        Util.setInputs(c, "10");
        c.propagate();
        System.out.print(Util.toString(c.outputs()));
        Util.setInputs(c, "01");
        c.propagate();
        System.out.print(Util.toString(c.outputs()));
        Util.setInputs(c, "11");
        c.propagate();
        c.propagate();
        System.out.print(Util.toString(c.outputs()));
        c.invalidateInputs();
        c.propagate();
        c.propagate();
        System.out.print(Util.toString(c.outputs()));
        Util.setInputs(c, "11");
        c.propagate();
        c.invalidateOutputs();
        System.out.print(Util.toString(c.outputs()));
    }

    public static void testHalfAdder()
    {
        HalfAdder c = new HalfAdder();
        Util.setInputs(c, "00");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
        Util.setInputs(c, "10");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
        Util.setInputs(c, "01");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
        Util.setInputs(c, "11");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
    }

    public static void testFullAdder()
    {
        FullAdder c = new FullAdder();
        Util.setInputs(c, "000");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
        Util.setInputs(c, "100");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
        Util.setInputs(c, "010");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
        Util.setInputs(c, "110");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
        Util.setInputs(c, "001");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
        Util.setInputs(c, "101");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
        Util.setInputs(c, "011");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
        Util.setInputs(c, "111");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
    }

    public static void testMultiplexer()
    {
        Multiplexer c = new Multiplexer(2);
        Util.setInputs(c, "01");
        c.propagate();
        System.out.println(Util.toString(c.outputs()));
    }

    public static void main(String[] args)
    {
        //testAndGate();
        //testOrGate();
        //testNotGate();
        //testHalfAdder();
        //testFullAdder();
        testMultiplexer();
    }
}
