package hw12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Person {

    private String fio;
    private LocalDate birthday;
    private List<String> phones;
    private String address;
    private LocalDateTime updatedAt;

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void addPhone(String phone) {
        if (this.phones == null) {
            this.setPhones(new ArrayList<>(Arrays.asList()));
        }

        this.phones.add(phone);
    }

    public void updatePhone(int index, String phone) throws Exception {
        this.phones.remove(index);
        this.phones.add(index, phone);
    }

    public void removePhone(int index) {
        this.phones.remove(index);
    }

    public int searchPhone(String phone) {
        try {
            return IntStream.range(0, this.phones.size())
                    .filter(i -> phone.equals(this.phones.get(i)))
                    .findFirst()
                    .getAsInt();
        } catch (Exception e) {
            return -1;
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
