package threadLocal;

import threadpools.demo1.MyThreadPool;

/**
 * @author Joshua.H.Brooks
 * @description 用 Synchronize 关键字 来解决上例中的线程问题
 * @date 2022-07-28 01:50
 */
public class ThreadLocalDemo {
    String content;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public static void main(String[] args) {
        ThreadLocalDemo tld = new ThreadLocalDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (ThreadLocalDemo.class){
                    //先绑定
                    tld.setContent(Thread.currentThread().getName()+"的数据");
                    System.out.println("-------------------");
                    //再获取
                    System.out.println(Thread.currentThread().getName()+"\t--> "+tld.getContent());
                }
            }, "my-self-defined-thread"+i).start();
        }
    }
}
