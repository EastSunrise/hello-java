package wsg.java.spring.proxy.state;

/**
 * @author Kingen
 */
public class Cinema implements Movie {
    private Movie movie;

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
