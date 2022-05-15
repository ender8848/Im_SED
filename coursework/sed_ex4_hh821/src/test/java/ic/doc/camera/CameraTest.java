package ic.doc.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {
    private final byte[] IMAGE_DATA = new byte[8];

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    Sensor sensor = context.mock(Sensor.class);
    MemoryCard memoryCard = context.mock(MemoryCard.class);
    Camera camera = new Camera(sensor, memoryCard);

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerUp();
        }});
        camera.powerOn();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        switchOnCamera();
        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerDown();
        }});
        camera.powerOff();
    }

    @Test
    public void pressingTheShutterWhenThePowerIsOffDoesNothing() {
        context.checking(new Expectations() {{
            never(sensor);
            never(memoryCard);
        }});
        camera.pressShutter();
    }

    @Test
    public void pressingTheShutterWithThePowerOnCopiesDataFromTheSensorToTheMemoryCard() {
        switchOnCamera();
        context.checking(new Expectations() {{
            exactly(1).of(sensor).readData();
            will(returnValue(IMAGE_DATA));
            exactly(1).of(memoryCard).write(IMAGE_DATA);
        }});
        camera.pressShutter();
    }

    @Test
    public void doesNotPowerDownUntilWritingISComplete() {
        switchOnCamera();
        context.checking(new Expectations() {{
            exactly(1).of(sensor).readData();
            will(returnValue(IMAGE_DATA));
            exactly(1).of(memoryCard).write(IMAGE_DATA);
        }});
        camera.pressShutter();
        camera.powerOff();

        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerDown();
        }});
        camera.writeComplete();
    }

    private void switchOnCamera() {
        context.checking(new Expectations() {{
            ignoring(sensor).powerUp();
        }});
        camera.powerOn();
    }
}
