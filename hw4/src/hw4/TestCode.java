/**
 *
 */
package hw4;
import api.IComponent;
import api.Util;
//import hw4.MultiComponent;
public class TestCode {
    public static void main(String[] args){
        And1();
        Or();
        Not();
        halfAdder();
        fullAdder();
        Multiplexer();

    }
    public static void And1() {
        System.out.println("AndGate");
        AndGate c = new AndGate();
        Util.setInputs(c, "11");
        c.propagate();
        System.out.println("input:" + Util.toString(c.inputs()));
        System.out.println(Util.toString(c.outputs()) + ": Expected 1"); // prints "1"
        System.out.println("\n");

        Util.setInputs(c, "01");
        c.propagate();
        System.out.println("input:" + Util.toString(c.inputs()));
        System.out.println(Util.toString(c.outputs()) + ": Expected 0"); // prints "0"
        System.out.println("\n");

        c.invalidateOutputs();
        System.out.println(Util.toString(c.outputs())); // prints "-"
        System.out.println("\n");
        Util.setInputs(c, "10");
        c.propagate();
        System.out.println("input:" + Util.toString(c.inputs()));
        System.out.println(Util.toString(c.outputs()) + ": Expected 0");
        System.out.println("\n");

        Util.setInputs(c, "00");
        c.propagate();
        System.out.println("input:" + Util.toString(c.inputs()));
        System.out.println(Util.toString(c.outputs()) + ": Expected 0");
        System.out.println("\n");
    }
    public static void Or() {
        System.out.println("OrGate");
        OrGate d = new OrGate();
        Util.setInputs(d, "00");
        d.propagate();
        System.out.println("input:" + Util.toString(d.inputs()));
        System.out.println(Util.toString(d.outputs()) + ": Expected 0"); // prints "0"
        System.out.println("\n");

        Util.setInputs(d, "01");
        d.propagate();
        System.out.println("input:" + Util.toString(d.inputs()));
        System.out.println(Util.toString(d.outputs()) + ": Expected 1"); // prints "1"
        System.out.println("\n");

        Util.setInputs(d, "10");
        d.propagate();
        System.out.println("input:" + Util.toString(d.inputs()));
        System.out.println(Util.toString(d.outputs()) + ": Expected 1"); // prints "1"
        System.out.println("\n");

        Util.setInputs(d, "11");
        d.propagate();
        System.out.println("input:" + Util.toString(d.inputs()));
        System.out.println(Util.toString(d.outputs()) + ": Expected 1"); // prints "1"
        System.out.println("\n");
    }
    public static void Not() {
        System.out.println("NotGate");
        NotGate n = new NotGate();
        Util.setInputs(n, "0");
        n.propagate();
        System.out.println("input:" + Util.toString(n.inputs()));
        System.out.println(Util.toString(n.outputs()) + ": Expected 1"); // prints "1"
        System.out.println("\n");

        Util.setInputs(n, "1");
        n.propagate();
        System.out.println("input:" + Util.toString(n.inputs()));
        System.out.println(Util.toString(n.outputs()) + ": Expected 0");
        System.out.println("\n");
    }
    public static void halfAdder() {
        System.out.println("HalfAdder");
        IComponent adder = new HalfAdder();
        Util.setInputs(adder, "01");
        adder.propagate();
        System.out.println("input:" + Util.toString(adder.inputs()));
        //remember, we do everything backwards soooo the toString give index 1 THEN index 0
        System.out.println(Util.toString(adder.outputs()) + ": Expected 01");
        System.out.println("\n");

        IComponent adder1 = new HalfAdder();
        Util.setInputs(adder1, "10");
        adder1.propagate();
        System.out.println("input:" + Util.toString(adder1.inputs()));
        //remember, we do everything backwards soooo the toString give index 1 THEN index 0
        System.out.println(Util.toString(adder1.outputs()) + ": Expected 01");
        System.out.println("\n");

        IComponent adder2 = new HalfAdder();
        Util.setInputs(adder2, "11");
        adder2.propagate();
        System.out.println("input:" + Util.toString(adder2.inputs()));
        //remember, we do everything backwards soooo the toString give index 1 THEN index 0
        System.out.println(Util.toString(adder2.outputs()) + ": Expected 10");
        System.out.println("\n");

        IComponent adder3 = new HalfAdder();
        Util.setInputs(adder3, "00");
        adder3.propagate();
        System.out.println("input:" + Util.toString(adder3.inputs()));
        //remember, we do everything backwards soooo the toString give index 1 THEN index 0
        System.out.println(Util.toString(adder3.outputs()) + ": Expected 00");
        System.out.println("\n");

    }
    public static void fullAdder() {
//fullAdder
        System.out.println("FullAdder");
        IComponent fullAdder = new FullAdder();
        Util.setInputs(fullAdder, "010");
        fullAdder.propagate();
        System.out.println("input:" + Util.toString(fullAdder.inputs()));
        System.out.println(Util.toString(fullAdder.outputs()) + ": Expected 01");
        System.out.println("\n");

        IComponent fullAdder1 = new FullAdder();
        Util.setInputs(fullAdder1, "111");
        fullAdder1.propagate();
        System.out.println("input:" + Util.toString(fullAdder1.inputs()));
        System.out.println(Util.toString(fullAdder1.outputs()) + ": Expected 11");
        System.out.println("\n");

        IComponent fullAdder2 = new FullAdder();
        Util.setInputs(fullAdder2, "110");
        fullAdder2.propagate();
        System.out.println("input:" + Util.toString(fullAdder2.inputs()));
        System.out.println(Util.toString(fullAdder2.outputs()) + ": Expected 10");
        System.out.println("\n");

        IComponent fullAdder3 = new FullAdder();
        Util.setInputs(fullAdder3, "101");
        fullAdder3.propagate();
        System.out.println("input:" + Util.toString(fullAdder3.inputs()));
        System.out.println(Util.toString(fullAdder3.outputs()) + ": Expected 10");
        System.out.println("\n");

        IComponent fullAdder4 = new FullAdder();
        Util.setInputs(fullAdder4, "100");
        fullAdder4.propagate();
        System.out.println("input:" + Util.toString(fullAdder4.inputs()));
        System.out.println(Util.toString(fullAdder4.outputs()) + ": Expected 01");
        System.out.println("\n");

        IComponent fullAdder5 = new FullAdder();
        Util.setInputs(fullAdder5, "011");
        fullAdder5.propagate();
        System.out.println("input:" + Util.toString(fullAdder5.inputs()));
        System.out.println(Util.toString(fullAdder5.outputs()) + ": Expected 10");
        System.out.println("\n");

        IComponent fullAdder6 = new FullAdder();
        Util.setInputs(fullAdder6, "001");
        fullAdder6.propagate();
        System.out.println("input:" + Util.toString(fullAdder6.inputs()));
        System.out.println(Util.toString(fullAdder6.outputs()) + ": Expected 01");
        System.out.println("\n");

        IComponent fullAdder7 = new FullAdder();
        Util.setInputs(fullAdder7, "000");
        fullAdder7.propagate();
        System.out.println("input:" + Util.toString(fullAdder7.inputs()));
        System.out.println(Util.toString(fullAdder7.outputs()) + ": Expected 00");
        System.out.println("\n");
    }
    public static void Multiplexer() {
// Multiplexer
        System.out.println("MultiComponent");
        IComponent A = new AndGate();
        IComponent B = new AndGate();
        IComponent C = new AndGate();
        IComponent[] list = {A, B, C};
        IComponent multiComponent = new MultiComponent(list);
        Util.setInputs(multiComponent, "111011");
        multiComponent.propagate();
        System.out.println(Util.toString(multiComponent.outputs()) + ": Expected 101");


        System.out.println("Multiplexer");
        IComponent m = new Multiplexer(3);
        Util.setInputs(m, "11001000000"); // Expecting 1, since 011 is 6, index 5 is one.
//        System.out.println(Util.toString(m.inputs())); //for debugging purposes only
        m.propagate();
        System.out.println(m.outputs()[0].getValue() + ": Expected 1");
        Util.setInputs(m, "10100100000"); // Expecting 1, since 101 is 5, index 5 is 1.
        m.propagate();
        System.out.println(m.outputs()[0].getValue() + ": Expected 1");
        IComponent mplex2 = new Multiplexer(2);
        Util.setInputs(mplex2, "100010"); // Expecting 0, since 10 is 2, index 2 is 0.
        mplex2.propagate();
        System.out.println(mplex2.outputs()[0].getValue() + ": Expected 0");
    }
}