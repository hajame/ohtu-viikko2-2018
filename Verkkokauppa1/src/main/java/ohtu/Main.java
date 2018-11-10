package ohtu;

import java.util.List;
import ohtu.verkkokauppa.Kauppa;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new FileSystemXmlApplicationContext
                ("src/main/resources/spring-context.xml");
 
//        Kauppa kauppa = (Kauppa) ctx.getBean("kauppa");
        
        Kauppa kauppa = ctx.getBean(Kauppa.class);

        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");

        // kirjanpito
        List<String> tapahtumat = kauppa.getPankki()
                .getKirjanpito().getTapahtumat();
        for (int i = 0; i < tapahtumat.size(); i++) {
            System.out.println(tapahtumat.get(i));
        }
    }
}
