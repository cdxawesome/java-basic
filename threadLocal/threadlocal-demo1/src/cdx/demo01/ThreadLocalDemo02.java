package cdx.demo01;

/**
 * 模拟客户端的请求调用服务器上多个服务
 * 服务器接收一个请求，会用一个线程来处理，我们将需要重复使用的数据保存在ThreadLocal中，用于线程共享，避免了参数传递的麻烦。
 */

public class ThreadLocalDemo02 {
    public static void main(String[] args) {
        User user = new User("面条大师");
        new Service1().service1(user);
    }
}

class Service1 {
    public void service1(User user) {
        // 给ThreadLocal赋值，后续的服务直接通过ThreadLocal获取就好了
        UserContextHolder.holder.set(user);
        new Service2().service2();
    }
}

class Service2 {
    public void service2() {
        User user = UserContextHolder.holder.get();
        System.out.println("service2 拿到的用户:" + user.name);
        new Service3().service3();
    }
}

class Service3 {
    public void service3() {
        User user = UserContextHolder.holder.get();
        System.out.println("service3 拿到的用户:" + user.name);

        // 整个流程执行完毕之后，一定要执行remove
        UserContextHolder.holder.remove();
    }
}

class UserContextHolder {
    // 创建ThreadLocal保存User对象
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}
