package more.thread.example.threadlocal使用场景;

public class SaAa extends SaA{

    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    void so() {
        super.so();
        System.out.println("SaAa....");
    }
}
