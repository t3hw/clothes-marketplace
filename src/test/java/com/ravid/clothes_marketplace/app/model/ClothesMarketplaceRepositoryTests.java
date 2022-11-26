package com.ravid.clothes_marketplace.app.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ClothesMarketplaceRepositoryTests {

    @Autowired ApplicationContext context;

    @Autowired
    PublisherRepository pubRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    GarmentRepository garmentRepo;

    @Test
    void contextLoads() {
    }

	@Test
    void testUserRepository() {
        User user = userRepo.getReferenceById("123456789");
        assertTrue(user.getPublisher().getFullName().equals("Ravid Gontov"));
        assertTrue(user.getPassword().equals("$2a$12$SNVIPA4sxpc.n7nmAtK0AuwrFdgtYLe5P4I2Rpev1Vo6j4uIeva1K"));
    }

    @Test
    void testPublisherRepo() {
        Publisher pub = pubRepo.getReferenceById("123456789");
        assertTrue(pub.getFullName().equals("Ravid Gontov"));
        assertTrue(pub.getAddress().equals("Metzulot Yam 16 Givatayim"));
        assertTrue(pub.getUser().getPassword().equals("$2a$12$SNVIPA4sxpc.n7nmAtK0AuwrFdgtYLe5P4I2Rpev1Vo6j4uIeva1K"));
        assertTrue(pub.getGarments().size() == 2);

        List<Publisher> pubList = pubRepo.findByFullNameContainingIgnoreCase("ravid");
        assertTrue(pubList.size() == 1);
    }

    @Test
    void testGarmentsRepo() {
        Publisher pub = pubRepo.getReferenceById("123456789");

        Garment garm = garmentRepo.getReferenceById(1);
        assertTrue(garm.getDescription().equals("A normal pair of pants"));
        assertTrue(garm.getGarmentType().equals("Pants"));
        assertTrue(garm.getPrice().equals(Float.parseFloat("127.0")));
        assertTrue(garm.getPublisher().getFullName().equals("Ravid Gontov"));
        assertTrue(garm.getSize().equals("XL"));

        // Test repo
        assertTrue(garmentRepo.findAllBySize("M").get(0).getGarmentType().equals("Socks"));
        assertTrue(garmentRepo.findAllByPublisher(pub).size() == 2);
        
        // Named Queries
        assertTrue(garmentRepo.getGarmentsByPublisherPartialName("ravid").size() == 2);
        assertTrue(garmentRepo.getGarmentsByPublisherId("123456789").size() == 2);
        assertTrue(garmentRepo.getGarmentsWithMinPrice(Float.parseFloat("6.5")).size() == 3);
        assertTrue(garmentRepo.getGarmentsWithMaxPrice(Float.parseFloat("6.5")).size() == 2);
    }
}
