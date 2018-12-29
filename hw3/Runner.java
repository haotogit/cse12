public class Runner {
    private Deque12<Integer> elDeque12;

    public static void main(String[] args)
    {
        Runner theRunner = new Runner();
        theRunner.runn();
    }

    public void runn()
    {
        elDeque12 = new Deque12<Integer>(10);
        elDeque12.addFront(0);
        elDeque12.addFront(1);
    }
}
