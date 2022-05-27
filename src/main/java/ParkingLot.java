import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ParkingLot {
    int MAX_SIZE = 0;
    private class Car {
        String regNo;
        String age;
        public Car(String regNo, String age) {
            this.regNo = regNo;
            this.age = age;
        }
    }
    ArrayList<Integer> availableSlotList;
    Map<String, Car> map1;
    Map<String, String> map2;
    Map<String, ArrayList<String>> map3;

    public void createParkingLot(String lotCount) {
        try {
            this.MAX_SIZE = Integer.parseInt(lotCount);
        } catch (Exception e) {
            System.out.println("Invalid lot count");
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        this.map1 = new HashMap<String, Car>();
        this.map2 = new HashMap<String, String>();
        this.map3 = new HashMap<String, ArrayList<String>>();
        System.out.println("Created parking of " + lotCount + " slots");
    }
    public void park(String regNo, String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.map1.size() == this.MAX_SIZE) {
            System.out.println("Sorry, parking lot is full");
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(regNo, age);
            this.map1.put(slot, car);
            this.map2.put(regNo, slot);
            if (this.map3.containsKey(age)) {
                ArrayList<String> regNoList = this.map3.get(age);
                this.map3.remove(age);
                regNoList.add(regNo);
                this.map3.put(age, regNoList);
            } else {
                ArrayList<String> regNoList = new ArrayList<String>();
                regNoList.add(regNo);
                this.map3.put(age, regNoList);
            }
            System.out.println("Car with vehicle registration number \""+ regNo +"\" has been parked at slot number " + slot);
            availableSlotList.remove(0);
        }
    }
    public void leave(String slotNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.map1.size() > 0) {
            Car carToLeave = this.map1.get(slotNo);
            if (carToLeave != null) {
                this.map1.remove(slotNo);
                this.map2.remove(carToLeave.regNo);
                ArrayList<String> regNoList = this.map3.get(carToLeave.age);
                if (regNoList.contains(carToLeave.regNo)) {
                    regNoList.remove(carToLeave.regNo);
                }
                this.availableSlotList.add(Integer.parseInt(slotNo));
                System.out.println("Slot number " + slotNo + " vacated, the car with vehicle registration number \""+carToLeave.regNo+"\" left the space, the driver of the car was of age"+carToLeave.age);
            } else {
                System.out.println("Slot number " + slotNo + " is already empty");
            }
        } else {
            System.out.println("Parking lot is empty");
        }
    }
    public void status() {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.map1.size() > 0) {
            System.out.println("Slot No.\tRegistration No.\tage");
            Car car;
            for (int i = 1; i <= this.MAX_SIZE; i++) {
                String key = Integer.toString(i);
                if (this.map1.containsKey(key)) {
                    car = this.map1.get(key);
                    System.out.println(i + "\t" + car.regNo + "\t" + car.age);
                }
            }
        } else {
            System.out.println("Parking lot is empty");
        }
    }
    public void getRegistrationNumbersFromAge(String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.map3.containsKey(age)) {
            ArrayList<String> regNoList = this.map3.get(age);
            for (int i=0; i < regNoList.size(); i++) {
                if (!(i==regNoList.size() - 1)){
                    System.out.print(regNoList.get(i) + ",");
                } else {
                    System.out.print(regNoList.get(i));
                }
            }
        } else {
            System.out.println("Not found");
        }
    }
    public void getSlotNumbersFromAge(String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.map3.containsKey(age)) {
            ArrayList<String> regNoList = this.map3.get(age);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            for (int i=0; i < regNoList.size(); i++) {
                slotList.add(Integer.valueOf(this.map2.get(regNoList.get(i))));
            }
            Collections.sort(slotList);
            for (int j=0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + ",");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
        } else {
            System.out.println("Not found");
        }
    }
    public void getSlotNumberFromRegNo(String regNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.map2.containsKey(regNo)) {
            System.out.println(this.map2.get(regNo));
        } else {
            System.out.println("Not found");
        }
    }
}
