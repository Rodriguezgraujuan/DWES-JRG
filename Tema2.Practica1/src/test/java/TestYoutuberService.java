import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestYoutuberService {
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void youtuberConMasSeguidoresListaVacia() throws ParseException {

        YoutuberService youtuberS = new YoutuberService();
        List<Youtuber> youtuberList = new ArrayList<>();
        Date fecha = date.parse("2022-09-08");
        youtuberList.add(new Youtuber("Juan", fecha, 10,150));
        youtuberList.add(new Youtuber("Pepe", fecha, 10,100));

        assertEquals(youtuberS.youtuberConMasSeguidores(youtuberList), youtuberList.get(0));
    }
}
