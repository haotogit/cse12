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
        for (int i = 0; i < 10; i++) {
            elDeque12.addBack(i);
        }
        //System.out.println("holaa"+elDeque12.peekBack());
        //elDeque12.addFront(111);
        //System.out.println("holaaremoved"+elDeque12.removeBack());
        //System.out.println("holaaremovedsegundo"+elDeque12.removeFront());
        //System.out.println("holaasize"+elDeque12.size());
        //System.out.println("holaaremovedsegundo"+elDeque12.removeBack());
        //System.out.println("holaa"+elDeque12.addFront(999));
    }
}
