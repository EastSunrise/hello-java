package cn.wsg.hello.spring.proxy.state;

/**
 * @author Kingen
 */
public class Cinema implements Movie {

    private final Movie movie;

    public Cinema(Movie movie) {
        this.movie = movie;
    }

    public void play() {
        advertise();
        movie.play();
        advertise();
    }

    private void advertise() {
        System.out.println("Advertising");
    }
}
