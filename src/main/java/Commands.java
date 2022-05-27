import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Commands {
    public Map<String, Method> commandsMap;

    public Commands() {
        commandsMap = new HashMap<String, Method>();
        try {
            populateCommandsHashMap();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private void populateCommandsHashMap() throws NoSuchMethodException {
        commandsMap.put("Create_parking_lot", ParkingLot.class.getMethod("createParkingLot", String.class));
        commandsMap.put("Park", ParkingLot.class.getMethod("park", String.class, String.class));
        commandsMap.put("Leave", ParkingLot.class.getMethod("leave", String.class));
        commandsMap.put("Status", ParkingLot.class.getMethod("status"));
        commandsMap.put("Vehicle_registration_number_for_driver_of_age", ParkingLot.class.getMethod("getRegistrationNumbersFromAge", String.class));
        commandsMap.put("Slot_numbers_for_driver_of_age", ParkingLot.class.getMethod("getSlotNumbersFromAge", String.class));
        commandsMap.put("Slot_number_for_car_with_number", ParkingLot.class.getMethod("getSlotNumberFromRegNo", String.class));
    }
}
