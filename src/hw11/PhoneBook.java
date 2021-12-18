package hw11;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class PhoneBook {
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Person> people;
    private String path;

    PhoneBook() {

        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        mapper.registerModule(module);
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void addPerson(Person person) {
        if (this.people == null) {
            this.setPeople(new ArrayList<>(Collections.emptyList()));
        }

        this.people.add(person);
    }

    public void removePerson(int index) {
        this.people.remove(index);
    }

    public void updatePerson(int index, Person person) {
        this.people.remove(index);
        this.people.add(index, person);
    }

    public int searchPerson(String fio) {
        if (this.people != null) {
            for (int i = 0; i < this.people.size(); i++) {
                if (this.people.get(i).getFio().equals(fio)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public void writeFile(String path) throws Exception {
        this.mapper.writeValue(new FileWriter(path), this.people);
    }

    public List<Person> readFile(String path) throws Exception {
        return this.mapper.readValue(new FileReader(path), new TypeReference<List<Person>>() {});
    }

    public String printFile(String path) throws Exception {
        this.path = path;
        return this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.people);
    }

    public String printPerson(int index) throws Exception {
        return this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.people.get(index));
    }

    public String printPeople() throws Exception {
        return this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.people);
    }

    public void sortedByFio()
    {
        if (this.people.size() > 0) {
            Collections.sort(this.people, new Comparator<Person>() {
                @Override
                public int compare(final Person person1, final Person person2) {
                    return person1.getFio().compareTo(person2.getFio());
                }
            });
        }
    }

    public void sortedByAddress()
    {
        if (this.people.size() > 0) {
            Collections.sort(this.people, new Comparator<Person>() {
                @Override
                public int compare(final Person person1, final Person person2) {
                    return person1.getAddress().compareTo(person2.getAddress());
                }
            });
        }
    }

    public void sortedByBirthday()
    {
        if (this.people.size() > 0) {
            Collections.sort(this.people, new Comparator<Person>() {
                @Override
                public int compare(final Person person1, final Person person2) {
                    return person1.getBirthday().compareTo(person2.getBirthday());
                }
            });
        }
    }
}
