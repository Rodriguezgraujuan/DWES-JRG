import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestYoutuberService {
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void youtubersMasAltoConNegativo() throws ParseException {
        YoutuberService youtuberS = new YoutuberService();
        List<Youtuber> youtuberList = new ArrayList<>();
        Date fecha = date.parse("2022-09-08");
        Youtuber y = new Youtuber("p", fecha, 10,10);
        youtuberList.add(y);
        youtuberList.add(new Youtuber("a", fecha, -10,10));
        youtuberList.add(new Youtuber("c", fecha, 5,10));

        assertEquals(y,youtuberS.youtuberConMasSeguidores(youtuberList));
    }

    @Test
    void mediaVideosListaVacia(){
        YoutuberService youtuberS = new YoutuberService();
        List<Youtuber> youtuberList = new ArrayList<>();
        assertThrows(EmptyList.class,() ->youtuberS.youtuberTopIngresos3(youtuberList));
    }

    @Test
    void youtubers2013No2013() throws ParseException {
        YoutuberService youtuberS = new YoutuberService();
        List<Youtuber> youtuberList = new ArrayList<>();
        Date fecha = date.parse("2022-09-08");
        youtuberList.add(new Youtuber("a", fecha, -10,10));
        youtuberList.add(new Youtuber("c", fecha, 5,10));
        assertEquals(new ArrayList<>(),youtuberS.youtubers2013(youtuberList));
    }
}
