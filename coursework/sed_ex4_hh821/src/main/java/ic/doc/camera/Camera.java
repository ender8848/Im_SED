package ic.doc.camera;

public class Camera implements WriteListener {
    private final Sensor sensor;
    private final MemoryCard memoryCard;
    private Boolean powerStatus;
    private Boolean writing;


    public Camera(Sensor sensor, MemoryCard memoryCard) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
        this.powerStatus = false;
        this.writing = false;
    }

    public void pressShutter() {
        if (powerStatus) {
            writing = true;
            memoryCard.write(sensor.readData());
        }
    }

    public void powerOn() {
        powerStatus = true;
        sensor.powerUp();
    }

    public void powerOff() {
        if (!writing) {
            powerStatus = false;
            sensor.powerDown();
        }
        powerStatus = false;
    }

    @Override
    // for sensor and memoryCard to use
    public void writeComplete() {
        writing = false;
        if (!powerStatus) {
            powerOff();
        }
    }
}


