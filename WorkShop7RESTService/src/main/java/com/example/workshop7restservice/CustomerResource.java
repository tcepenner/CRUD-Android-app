package com.example.workshop7restservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Agent;
import model.Customer;

import java.lang.reflect.Type;
import java.util.List;

@Path("/customer")
public class CustomerResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getallcustomers")
    public String getAllCustomer() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query= entityManager.createQuery("select c from Customer c");
        List<Customer> list = query.getResultList();
        Gson gson = new Gson();
        //Type type = new TypeToken<List<Customer>>(){}.getType();
        entityManager.close();
        //return gson.toJson(list, type);
        return gson.toJson(list);
    }

    @GET
    @Path("/getcustomer/{ customerid }")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomer(@PathParam("customerid") int customerId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        Customer customer = em.find(Customer.class, customerId);
        Gson gson = new Gson();

        return gson.toJson(customer);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deletecustomer/{ custid }")
    public String deleteCustomer(@PathParam("custid") int custId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Customer customer = entityManager.find(Customer.class, custId);
        entityManager.getTransaction().begin();
        if(customer == null) //customer does not exist
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return"{ message: 'That Customer does not exist!' }";
        }
        else
        {
            entityManager.remove(customer);
            if(entityManager.contains(customer)) //if contains still, then didnt delete
            {
                entityManager.getTransaction().rollback();
                entityManager.close();
                return"{ message: 'Delete Failed' }";
            }
            else
            {
                entityManager.getTransaction().commit();
                entityManager.close();
                return"{ message: 'Delete was Successful' }";
            }
        }
    }
    //update customer information
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes({ MediaType.APPLICATION_JSON})
//    @Path("/postcustomer")
//    public String postCustomer(String jsonString) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = factory.createEntityManager();
//        Gson gson = new Gson();
//        //create new Customer with information from the jsonString
//        Customer customer = gson.fromJson(jsonString, Customer.class);
//        entityManager.getTransaction().begin();
//        entityManager.persist(customer); //persist inserts, then commit makes sure it happens
//        if(entityManager.contains(customer)) //update succeeds
//        {
//            entityManager.getTransaction().commit();
//            entityManager.close();
//            return "{ message: 'Insert Successful', Customer: '" + customer.toString() + "' }";
//        }
//        else //update fails
//        {
//            entityManager.getTransaction().rollback(); //undo changes
//            entityManager.close();
//            return "{ message: 'Insert Failed', Customer: '" + customer.toString() + "' }";
//        }
//    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON})
    @Path("/postcustomer")
    public String postCustomer(String jsonString) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Gson gson = new Gson();
        //create new Customer with information from the jsonString
        //Agent agent = gson.fromJson(jsonString, Agent.class);
        Type type = new TypeToken<Customer>(){}.getType();
        Customer customer =  gson.fromJson(jsonString, type);
        entityManager.getTransaction().begin();
        entityManager.persist(customer); //persist inserts, then commit makes sure it happens
        if(entityManager.contains(customer))
        {
            entityManager.getTransaction().commit();
            entityManager.close();
            factory.close();
            return "{ message: 'Insert Successful', Customer: '" + customer.toString() + "' }";
        }
        else //update fails
        {
            entityManager.getTransaction().rollback(); //undo changes
            entityManager.close();
            factory.close();
            return "{ message: 'Insert Failed', Customer: '" + customer.toString() + "' }";
        }
    }

    // Add a Customer
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes({ MediaType.APPLICATION_JSON})
//    @Path("/putcustomer")
//    public String putCustomer(String jsonString) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = factory.createEntityManager();
//        Gson gson = new Gson();
//        Customer customer = gson.fromJson(jsonString, Customer.class);
//        entityManager.getTransaction().begin();
//        Customer mergedCust = entityManager.merge(customer); //modify
//        if(mergedCust != null)
//        {
//            entityManager.getTransaction().commit();
//            entityManager.close();
//            return "{ message: 'Update Successful', Customer: '" + mergedCust.toString() + "' }";
//        }
//        else
//        {
//            entityManager.getTransaction().rollback();
//            entityManager.close();
//            return "{ message: 'Update Failed', Customer: '" + customer.toString() + "' }";
//        }
//    }
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON})
    @Path("/putcustomer")
    public String putAgent(String jsonString) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Gson gson = new Gson();
        Customer customer = gson.fromJson(jsonString, Customer.class);
        entityManager.getTransaction().begin();
        Customer existingCustomer = entityManager.find(Customer.class, customer.getId());
        //Agent mergedAgent = entityManager.merge(agent); //modify
        if(existingCustomer != null)
        {
            existingCustomer.setCustFirstName(customer.getCustFirstName());
            existingCustomer.setCustLastName(customer.getCustLastName());
            existingCustomer.setCustAddress(customer.getCustAddress());
            existingCustomer.setCustCity(customer.getCustCity());
            existingCustomer.setCustProv(customer.getCustProv());
            existingCustomer.setCustPostal(customer.getCustPostal());
            existingCustomer.setCustCountry(customer.getCustCountry());
            existingCustomer.setCustBusPhone(customer.getCustBusPhone());
            existingCustomer.setCustHomePhone(customer.getCustHomePhone());
            existingCustomer.setCustEmail(customer.getCustEmail());
            existingCustomer.setAgent(customer.getAgent());
            Customer mergedCustomer = entityManager.merge(existingCustomer);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "{ message: 'Update Successful', Agent: '" + mergedCustomer.toString() + "' }";
        }
        else
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return "{ message: 'Update Failed', Agent: '" + customer.toString() + "' }";
        }
    }
}