package com.talbert.inventorymgmt.databaseTests;

import com.talbert.inventorymgmt.entities.InventoryItem;
import com.talbert.inventorymgmt.entities.Manager;
import com.talbert.inventorymgmt.entities.User;
import com.talbert.inventorymgmt.repository.InventoryRepository;
import com.talbert.inventorymgmt.repository.ManagerRepository;
import com.talbert.inventorymgmt.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersistenceTests {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void setup() {
        User user = new User();
        user.setEmail("alice@example.com");
        user.setName("Alice");
        userRepository.save(user);

        Manager mgr = new Manager();
        if ((long) managerRepository.findAll().size() == 0) {
            mgr.setName("test manager");
            mgr.setEmail("testmgr.com");
            managerRepository.save(mgr);
        }
    }

    @Test
    void testItem(){
        InventoryItem item = new InventoryItem();
        Manager mgr = managerRepository.findAll().get(0);
        item.setManager(mgr);
        InventoryItem saved = inventoryRepository.save(item);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void testFindFirstByEmail_validEmail_returnsUser() {
        Optional<User> result = Optional.ofNullable(userRepository.findFirstByEmail("alice@example.com"));
        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getName());
    }

    @Test
    void testFindFirstByEmail_sqlInjectionInput_returnsEmpty() {
        String maliciousInput = "alice@example.com' OR '1'='1";
        Optional<User> result = Optional.ofNullable(userRepository.findFirstByEmail(maliciousInput));
        assertTrue(result.isEmpty());
//        assertTrue(result.isPresent());
    }
}
