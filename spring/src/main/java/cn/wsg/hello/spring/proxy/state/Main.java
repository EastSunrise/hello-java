package cn.wsg.hello.spring.proxy.state;

/**
 * @author Kingen
 */
public class Main {
    public static void main(String[] args) {
        Movie realMovie = new RealMovie();
        Movie movie = new Cinema(realMovie);
        movie.play();
    }
}
