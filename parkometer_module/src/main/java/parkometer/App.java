package parkometer;

import lombok.val;
import parkometer.client.Cmd;

public class App {

    public static void main(String[] args) {
        val cmd = Cmd.create();
        cmd.showStart();
    }


}
