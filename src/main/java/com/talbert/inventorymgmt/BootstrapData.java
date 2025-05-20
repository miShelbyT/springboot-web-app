package com.talbert.inventorymgmt;

import com.talbert.inventorymgmt.entities.InventoryItem;
import com.talbert.inventorymgmt.entities.Manager;
import com.talbert.inventorymgmt.entities.User;
import com.talbert.inventorymgmt.repository.ManagerRepository;
import com.talbert.inventorymgmt.repository.UserRepository;
import com.talbert.inventorymgmt.repository.InventoryRepository;
import com.talbert.inventorymgmt.services.ManagerService;
import com.talbert.inventorymgmt.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final InventoryRepository inventoryRepository;
    private final ManagerService managerService;
    private final UserService userService;

    private User user;
    private Manager manager;

    public BootstrapData(UserRepository userRepository, ManagerRepository managerRepository, InventoryRepository inventoryRepository, ManagerService managerService, UserService userService) {
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
        this.inventoryRepository = inventoryRepository;
        this.managerService = managerService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(this.userRepository.count() == 0) {
            user = new User();
            user.setName("Shelby");
            user.setEmail("shelby@talbert.com");
            user.setPassword("123456");

            this.userService.addUser(user);
        }

        if(this.managerRepository.count() == 0) {
            manager = new Manager();
            manager.setName("Chris");
            manager.setEmail("chris@talbert.com");
            manager.setPassword("123456");
            this.managerService.addUser(manager);

            Manager manager2 = new Manager();
            manager2.setName("Shelby");
            manager2.setEmail("shelby@talbert.com");
            manager2.setPassword("123456");

            this.managerService.addUser(manager2);
        }



        if(this.inventoryRepository.count() == 0) {
            Manager m1 = managerRepository.findAll().stream().findFirst().orElse(null);

            InventoryItem item = new InventoryItem();
            item.setManager(m1);
            item.setLastUpdatedBy(m1.getId());
            item.setLastUpdatedDate("2025-05-07");
            item.setName("Sandals");
            item.setType("Shoes");
            item.setColor("blue");
            item.setSize("6");
            item.setVendor("Ralph Lauren");
            item.setQuantity(15);
            item.setNumAvailable(15);
            item.setPurchasePrice(49.99f);
            item.setSalesPrice(129.99f);
            item.setOrderDate("2025-05-01");
            this.inventoryRepository.save(item);

            InventoryItem item2 = new InventoryItem();
            item2.setName("Sweater");
            item2.setType("Casual");
            item2.setColor("blue");
            item2.setSize("5");
            item2.setVendor("Zara");
            item2.setManager(m1);
            item2.setQuantity(25);
            item2.setNumAvailable(25);
            item2.setPurchasePrice(49.99f);
            item2.setSalesPrice(129.99f);
            this.inventoryRepository.save(item2);

            InventoryItem item3 = new InventoryItem();
            item3.setName("Cocktail Dress");
            item3.setType("Formal");
            item3.setColor("black");
            item3.setSize("6");
            item3.setVendor("Ralph Lauren");
            item3.setManager(m1);
            item3.setQuantity(10);
            item3.setNumAvailable(10);
            item3.setPurchasePrice(49.99f);
            item3.setSalesPrice(129.99f);
            this.inventoryRepository.save(item3);

            InventoryItem item4 = new InventoryItem();
            item4.setName("Jean Jacket");
            item4.setType("Casual");
            item4.setColor("white");
            item4.setSize("8");
            item4.setVendor("Theory");
            item4.setManager(m1);
            item4.setQuantity(20);
            item4.setNumAvailable(20);
            item4.setPurchasePrice(49.99f);
            item4.setSalesPrice(129.99f);
            this.inventoryRepository.save(item4);

            InventoryItem item5 = new InventoryItem();
            item5.setName("Sundress");
            item5.setType("Casual");
            item5.setColor("yellow/multi");
            item5.setSize("4");
            item5.setVendor("Tommy Hilfiger");
            item5.setManager(m1);
            item5.setQuantity(25);
            item5.setNumAvailable(25);
            item5.setPurchasePrice(49.99f);
            item5.setSalesPrice(129.99f);
            this.inventoryRepository.save(item5);

        }


    }
}
