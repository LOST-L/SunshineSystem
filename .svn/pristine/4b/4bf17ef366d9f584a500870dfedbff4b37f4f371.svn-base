import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 白驹
 * Date: 2019/1/15
 * Time: 13:35
 * Description:
 */

public class test {
    @Test
    public static void main(String[] args){
        Integer i = 0;
        while (true) {
            try {
                Document user = Jsoup.connect("http://192.168.1.199/getTestTask.h").cookie("user", "1000000217088775").maxBodySize(0).timeout(30000).ignoreContentType(true).get();
                System.out.println(user.text());
                System.out.println(i++);
            } catch (Exception e){
                System.out.println("呵呵");
            }
        }
    }

}
