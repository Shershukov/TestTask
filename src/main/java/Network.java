import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Network {

    /**
     * Метод проверки пинга с указанным хостом
     *
     * @param address адрес хоста
     */
    public static void ping(String address) {
        String cmd = "ping " + address;
        Process proccess;
        try {
            proccess = Runtime.getRuntime().exec(cmd);
            proccess.waitFor(10, TimeUnit.SECONDS);
            BufferedReader br = new BufferedReader(new InputStreamReader(proccess.getInputStream(), "CP866"));
            String line;
            while ((line = br.readLine()) != null) {
                line = line + br.readLine();
                System.out.println(line);
            }
            proccess.getInputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
