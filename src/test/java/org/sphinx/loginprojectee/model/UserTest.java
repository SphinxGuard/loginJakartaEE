package org.sphinx.loginprojectee.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sphinx.loginprojectee.dto.UserAuthenticationDTO;
import org.sphinx.loginprojectee.util.HibernateUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    //setup default Data in DB
    @BeforeAll
    public static void setupDatabase(){
        // create 5 test users
        User ali = new User("Ali", "Nejati", "AliNejati", "123", "aliNejati@gmail.com", Role.USER);
        User sara = new User("Sara", "Ahmadi", "SaraAh", "123", "sara@gmail.com", Role.USER);
        User reza = new User("Reza", "Karimi", "RezaK", "123", "reza@gmail.com", Role.USER);
        User neda = new User("Neda", "Shahri", "NedaS", "123", "neda@gmail.com", Role.USER);
        User omid = new User("Omid", "Moradi", "OmidM", "123", "omid@gmail.com", Role.USER);
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        //deleting previous Datas in DB
        session.createMutationQuery("delete from User").executeUpdate();
        //inserting managed datas in DB
        session.persist(ali);
        session.persist(sara);
        session.persist(reza);
        session.persist(neda);
        session.persist(omid);
        transaction.commit();
        session.close();
    }

    //User constructor test
    @Test
    void testUser() {
        User user = new User("Ali", "Nejati", "AliNejati",
                "123", "aliNejati@gmail.com", Role.USER);

        assertEquals("Ali", user.getFirstName());
        assertEquals("Nejati", user.getLastName());
        assertEquals("AliNejati", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals("aliNejati@gmail.com", user.getEmail());
        assertEquals(Role.USER, user.getRole());
    }
    //inserting user test
    @Test
    void testSessionFactory(){
        User ali = new User("Ali", "Nejati", "AliNejati",
                "123", "aliNejati@gmail.com" , Role.USER);

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ali);
        transaction.commit();
        session.close();

    }
    //fetching user test
    @Test
    void testFetchingAndCheckingFirstPersonThatIsInsertedShouldReturnAliNejati(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        List<User> users = session.createQuery("from User",User.class).list();
        User user = (User) users.get(0);
        transaction.commit();

        session.close();

        assertEquals("Ali", user.getFirstName());
        assertEquals("Nejati", user.getLastName());
        assertEquals("AliNejati", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals("aliNejati@gmail.com", user.getEmail());
    }


    //lombok test start
    @Test
    public void givenAnnotatedUser_thenHasGettersAndSetters() {
        TestLombok testLombok = new TestLombok();
        testLombok.setFirstName("Test");
        assertEquals(testLombok .getFirstName(), "Test");
    }

    @Getter
    @Setter
    class TestLombok {
        private String firstName;
    }
    //lombok test end

    @Test
    void testAuthenticationDTOBuilder() {
        Long expectedId = 42L;
        Role expectedRole = Role.ADMIN;

        // Use the builder to create the DTO
        UserAuthenticationDTO dto = UserAuthenticationDTO.builder()
                .id(expectedId)
                .role(expectedRole)
                .build();

        // Verify that fields are set correctly
        assertEquals(expectedId, dto.getId(), "ID should match the value set in builder");
        assertEquals(expectedRole, dto.getRole(), "Role should match the value set in builder");
    }
}