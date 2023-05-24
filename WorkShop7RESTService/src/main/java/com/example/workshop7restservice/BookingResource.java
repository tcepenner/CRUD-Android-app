package com.example.workshop7restservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.Agent;
import model.Booking;

import java.lang.reflect.Type;
import java.util.List;

@Path("/booking")
public class BookingResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getallbookings")
    public String getAllBookings() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query= entityManager.createQuery("select b from Booking b");
        List<Booking> list = query.getResultList();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Booking>>(){}.getType();
        entityManager.close();
        return gson.toJson(list, type);
    }
}
