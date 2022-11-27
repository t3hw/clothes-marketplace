package com.ravid.clothes_marketplace.app.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.app.security.JwtUtil;
import com.ravid.clothes_marketplace.server.model.AuthenticationRequestDTO;
import com.ravid.clothes_marketplace.server.model.AuthenticationResponseDTO;
import com.ravid.clothes_marketplace.server.model.ClothesResponseDTO;
import com.ravid.clothes_marketplace.server.model.GarmentPOSTRequestDTO;
import com.ravid.clothes_marketplace.server.model.GarmentPUTRequestDTO;
import com.ravid.clothes_marketplace.server.model.GarmentResponseDTO;
import com.ravid.clothes_marketplace.server.model.GarmentSize;
import com.ravid.clothes_marketplace.server.model.GarmentType;

@SpringBootTest
@Transactional
public class serviceTests {

    @Autowired
    ApplicationContext context;
    @Autowired
    JwtUtil jwtUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void testAuth() {
        ResponseEntity<AuthenticationResponseDTO> authres = ((RequestHandler) context.getBean("authenticationPOST",
                new AuthenticationRequestDTO().userId("123456789").password("ravid123"))).handleRequest();

        var token = jwtUtil.decodeToken(authres.getBody().getToken());
        assertTrue(token.getSubject().equals("123456789"));
        assertTrue(!jwtUtil.isTokenExpired(token));
    }

    @Test
    void testQueryApi() {
        assertTrue(runQuery("0").getBody().getPublisherList().get(0).getGarments().size() == 2);
    }


    @Test
    void testAddGarment() {
        runPOST("800000");

        assertTrue(runQuery("70000").getBody().getPublisherList().get(0).getGarments().size() == 1);
    }

    @Test
    void testModifyGarment() {
        ResponseEntity<GarmentResponseDTO> postRes = runPOST("2");

        ((RequestHandler) context.getBean("publisherPUT", postRes.getBody().getGarmentId(),
                new GarmentPUTRequestDTO().price(Float.parseFloat("80000")).garmentSize(GarmentSize.S)
                        .garmentType(GarmentType.SHIRT),
                "123456789")).handleRequest();
        var res = runQuery("70000").getBody().getPublisherList().get(0).getGarments();    
        assertTrue(res.size() == 1);
        assertTrue(res.get(0).getGarmentDescription() == null);
        assertTrue(res.get(0).getGarmentSize() == GarmentSize.S);
        assertTrue(res.get(0).getGarmentType() == GarmentType.SHIRT);

    }

    @Test
    void testDeleteGarment() {
        ResponseEntity<GarmentResponseDTO> postRes = runPOST("20000000");

        ((RequestHandler) context.getBean("publisherDELETE", postRes.getBody().getGarmentId(),
                "123456789")).handleRequest();

        assertTrue(runQuery("70000").getBody().getPublisherList() == null);
    }



    private ResponseEntity<ClothesResponseDTO> runQuery(String minPrice) {
        return ((RequestHandler) context.getBean("queryGET", "ravid",
                "123456789", null, Float.parseFloat(minPrice), null, null)).handleRequest();
    }

    private ResponseEntity<GarmentResponseDTO> runPOST(String price) {
        ResponseEntity<GarmentResponseDTO> postRes = ((RequestHandler) context
                .getBean("publisherPOST",
                        new GarmentPOSTRequestDTO().garmentSize(GarmentSize.L).garmentType(GarmentType.HAT)
                                .price(Float.parseFloat(price)).garmentDescription("testing"),
                        "123456789"))
                .handleRequest();
        return postRes;
    }


}
