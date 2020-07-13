package modules;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void person_instantiatesCorrectly_true() {
        Person testPerson = new Person("Henry", "test@moringa.com");
        assertEquals(true, testPerson instanceof Person);
    }

    @Test
    public void getEmail_personInstantiatesWithEmail_String() {
        Person testPerson = new Person("Henry", "test@moringa.com");
        assertEquals("test@moringa.com", testPerson.getEmail());
    }

    @Test
    public void equals_returnsTrueIfNameAndEmailAreSame_true() {
        Person firstPerson = new Person("Henry", "test@moringa.com");
        Person otherPerson = new Person("Henry", "test@moringa.com");
        assertTrue(firstPerson.equals(otherPerson));
    }

    @Test
    public void save_InsertsObjectIntoDatabase_Person() {
        Person testPerson = new Person("Henry", "test@moringa.com");
        testPerson.save();
        assertTrue(Person.all().get(0).equals(testPerson));
    }

    @Test
    public void all_returnsAllInstancesOfPerson_true() {
       Person firstPerson = new Person("Henry", "test@moringa.com");
       firstPerson.save();
       Person secondPerson = new Person("Harriet", "hariet@moringa.com");
       secondPerson.save();
       assertEquals(true, Person.all().get(0).equals(firstPerson));
       assertEquals(true, Person.all().get(1).equals(secondPerson));
    }

    @Test
    public void save_assignsIdToObject() {
        Person testPerson = new Person("Henry", "test@moringa.com");
        testPerson.save();
        Person savedPerson = Person.all().get(0);
        assertEquals(testPerson.getId(), savedPerson.getId());
    }

    @Test
    public void find_returnsPersonWithSameId_secondPerson() {
        Person firstPerson = new Person("Henry", "test@moringa.com");
        firstPerson.save();
        Person secondPerson = new Person("Harriet", "hariet@moringa.com");
        secondPerson.save();
        assertEquals(Person.find(secondPerson.getId()), secondPerson);
    }

    @Test
    public void getMonsters_retrievesAllMonstersFromDatabase_monsterList() {
        Person testPerson = new Person("Henry", "test@moringa.com");
        testPerson.save();
        Monster firstMonster = new Monster("Bubbles", testPerson.getId());
        firstMonster.save();
        Monster secondMonster = new Monster("Spud", testPerson.getId());
        secondMonster.save();
        Monster[] monsters = new Monster[] { firstMonster, secondMonster };
        assertTrue(testPerson.getMonsters().containsAll(Arrays.asList(monsters)));
    }
}