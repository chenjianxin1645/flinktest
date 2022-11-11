package test;

public class test {
    public static void main(String[] args) {
        String a = "hello";
        String b = new String("hello");
        System.out.println(a == b);//false

        String c = "world";
        System.out.println(c.intern() == c);//true

        String d = new String("mike");
        System.out.println(d.intern() == d);//false

        String e = new String("jo") + new String("hn"); // stringbuffer的append.toString
        System.out.println(e.intern() == e);//true

        String f = new String("ja") + new String("va");
        System.out.println(f.intern() == f);//false

    }
}
