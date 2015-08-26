import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;

/**
 * Created by Rauca on 26.08.2015.
 */
public class TestRedisValues implements Runnable {
    DatahubClientConnector connector = new DatahubClientConnector();

    public static void main(String[] args) {
        new Thread(new TestRedisValues()).start();
    }

    @Override
    public void run() {
        while (true) {
            connector.setIntValueForKey("redstone_value", 1);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            connector.setIntValueForKey("redstone_value", 0);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
