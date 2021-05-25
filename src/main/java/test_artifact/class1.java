package test_artifact;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class watering_time {
    int on_off;
    int hours;
    int minutes;


    watering_time() {
        this.on_off = 1;

    }
}

class day {
    int dayOfWeek;
    int timeNum;
    watering_time anyTime;


    day() {
        this.anyTime = new watering_time();

    }
}

class valve {
    int valveNum;
    day day;

    valve() {
        this.day = new day();

    }
}

public class class1 {

    public static void main(String[] args) throws IOException, InterruptedException {

        valve anyValveIO = new valve();
        anyValveIO.valveNum = 31;
        anyValveIO.day.dayOfWeek = 1;
        anyValveIO.day.timeNum = 1;
        anyValveIO.day.anyTime.hours = 1;
        anyValveIO.day.anyTime.minutes = 1;

        List<Integer> arr2C = makeArray2C(anyValveIO);


        System.out.println(arr2C);


        SerialPort sp = SerialPort.getCommPort("COM4");
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (sp.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }
        Thread.sleep(4000);
        for (Integer integer : arr2C) {
            sp.getOutputStream().write(integer);
            sp.getOutputStream().flush();


        }
        System.out.println("Sent number: ");


        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
        }
    }


    private static List<Integer> makeArray2C(valve valve) {
        List<Integer> arr2C = new ArrayList<>();
        arr2C.add(valve.valveNum);
        arr2C.add(valve.day.dayOfWeek);
        arr2C.add(valve.day.timeNum);
        arr2C.add(valve.day.anyTime.hours);
        arr2C.add(valve.day.anyTime.minutes);

        return arr2C;
    }


}







