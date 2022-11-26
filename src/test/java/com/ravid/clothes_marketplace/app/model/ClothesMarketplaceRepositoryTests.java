package com.ravid.clothes_marketplace.app.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.ravid.clothes_marketplace.app.db.model.Garment;
import com.ravid.clothes_marketplace.app.db.model.Publisher;
import com.ravid.clothes_marketplace.app.db.model.User;
import com.ravid.clothes_marketplace.app.db.repo.GarmentRepository;
import com.ravid.clothes_marketplace.app.db.repo.PublisherRepository;
import com.ravid.clothes_marketplace.app.db.repo.UserRepository;

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
    @SuppressWarnings({"rawtypes","unchecked"})
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
        
        // Dynamic Query
        // pub name
        Optional empty = Optional.empty();
        List<Garment> garmList = garmentRepo.findGarmentsBySearchParams(Optional.of("ravid"), empty, empty, empty, empty, empty);
        assertTrue(garmList.size() == 2);
        // pub id
        garmList = garmentRepo.findGarmentsBySearchParams(empty, Optional.of("123456789"), empty, empty, empty, empty);
        assertTrue(garmList.size() == 2);
        // type
        garmList = garmentRepo.findGarmentsBySearchParams(empty, empty, Optional.of("Socks"), empty, empty, empty);
        assertTrue(garmList.get(0).getSize().equals("M"));
        // size
        garmList = garmentRepo.findGarmentsBySearchParams(empty, empty, empty, Optional.of("M"), empty, empty);
        assertTrue(garmList.get(0).getGarmentType().equals("Socks"));
        // min price
        garmList = garmentRepo.findGarmentsBySearchParams(empty, empty, empty, empty, Optional.of(Float.parseFloat("6.5")), empty);
        assertTrue(garmList.size() == 3);
        // max price
        garmList = garmentRepo.findGarmentsBySearchParams(empty, empty, empty, empty, empty, Optional.of(Float.parseFloat("6.5")));
        assertTrue(garmList.size() == 2);
        // all at once
        garmList = garmentRepo.findGarmentsBySearchParams(Optional.of("some"), Optional.of("987654321"),
                                                          Optional.of("Socks"), Optional.of("M"), 
                                                          Optional.of(Float.parseFloat("6.5")), 
                                                          Optional.of(Float.parseFloat("90000.0")));
        assertTrue(garmList.size() == 1);
    }
}
