public class Util {
    public Util() {}
    static void print(Object line)
    {
        String className =  Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName =  Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("**(**Amelia)(" + className + "." + methodName + "()) " + line.toString());
    }
}
