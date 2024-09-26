import java.util.Date;

public record Youtuber(String nombre, Date fecha, int numVideos, int numSegruidores) {

    public double estimatedIncome(){
        return (((long) numVideos * (long) numSegruidores) / 2.0) * 0.002;
        //double op2 = op1 / 2.0;
        //return op2 * 0.002;
    }
}
