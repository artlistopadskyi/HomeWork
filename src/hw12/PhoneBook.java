package hw12;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PhoneBook {
    private final ObjectMapper mapper;
    private List<Person> people;

     PhoneBook() {
        this.mapper = new ObjectMapper();

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

    public void updatePerson(int index, Person person) throws Exception {
        this.people.remove(index);
        this.people.add(index, person);
    }

    public int searchPerson(String fio) {
        try {
            return IntStream.range(0, this.people.size())
                    .filter(i -> fio.equals(this.people.get(i).getFio()))
                    .findFirst()
                    .getAsInt();
        } catch (Exception e) {
            return -1;
        }
    }

    public void writeFile(String path) throws Exception {
        this.mapper.writeValue(new FileWriter(path), this.people);
    }

    public List<Person> readFile(String path) throws Exception {
        return this.mapper.readValue(new FileReader(path), new TypeReference<List<Person>>() {});
    }

    public String printFile(String path) throws Exception {
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
        Collections.sort(this.people, Comparator.comparing(Person::getFio));
    }

    public void sortedByAddress()
    {
        setPeople(this.people.stream()
                .sorted(Comparator.comparing(Person::getAddress))
                .collect(Collectors.toList()));
    }

    public void sortedByBirthday()
    {
        Collections.sort(this.people, Comparator.comparing(Person::getBirthday));
    }
}
