import java.util.Date;

public record Youtuber(String nombre, Date fecha, int numVideos, int numSegruidores) {

    public double estimatedIncome(){
        return Math.round((double) (numVideos * numSegruidores) /2*0.002);
    }
}
