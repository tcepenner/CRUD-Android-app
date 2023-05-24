package com.example.workshop7restservice;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Agent;
import model.Customer;
import model.Package;

import java.lang.reflect.Type;
import java.util.List;

import static org.eclipse.persistence.expressions.ExpressionOperator.Log;


@Path("/agent")
public class AgentResource {

    @GET
    @Path("/getagent/{ agentid }")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAgent(@PathParam("agentid") int agentId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        Agent agent = em.find(Agent.class, agentId);
        Gson gson = new Gson();

        return gson.toJson(agent);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getagentbyuser/{ agtuserid }")
    public String getAgentByUserId(@PathParam("agtuserid") String agtUserId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
//        Agent agent = entityManager.createQuery("select a from Agent a where a.agtUserId = '" + agtUserId + "'", Agent.class)
//                .getSingleResult();
        Agent agent = entityManager.createQuery("select a from Agent a where a.agtUserId = :agtUserId", Agent.class)
                .setParameter("agtUserId", agtUserId).getSingleResult();
        System.out.println("test" + agtUserId);
        Gson gson = new Gson();
        entityManager.close();
        return gson.toJson(agent);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getallagents")
    public String getAllAgents() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query= entityManager.createQuery("select a from Agent a");
        List<Agent> list = query.getResultList();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Agent>>(){}.getType();
        entityManager.close();
        return gson.toJson(list, type);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getallselectagents")
    public String getAllSelectAgents() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query= entityManager.createQuery("select a from Agent a");
        List<Agent> list = query.getResultList();
        JsonArray jsonArray = new JsonArray();
        for(Agent a : list)
        {
            JsonObject object = new JsonObject();
            object.addProperty("id", a.getId());
            object.addProperty("agtFirstName", a.getAgtFirstName());
            object.addProperty("agtMiddleInitial", a.getAgtMiddleInitial());
            object.addProperty("agtLastName", a.getAgtLastName());
            object.addProperty("agtBusPhone", a.getAgtBusPhone());
            object.addProperty("agtEmail", a.getAgtEmail());
            object.addProperty("agtPosition", a.getAgtPosition());
            object.addProperty("agency", a.getAgency());

            jsonArray.add(object);
        }
        entityManager.close();
        return jsonArray.toString();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON})
    @Path("/postagent")
    public String postAgent(String jsonString) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Gson gson = new Gson();
        //create new Customer with information from the jsonString
        //Agent agent = gson.fromJson(jsonString, Agent.class);
        Type type = new TypeToken<Agent>(){}.getType();
        Agent agent =  gson.fromJson(jsonString, type);
        entityManager.getTransaction().begin();
        entityManager.persist(agent); //persist inserts, then commit makes sure it happens
        if(entityManager.contains(agent))
        {
            entityManager.getTransaction().commit();
            entityManager.close();
            factory.close();
            return "{ message: 'Insert Successful', Agent: '" + agent.toString() + "' }";
        }
        else //update fails
        {
            entityManager.getTransaction().rollback(); //undo changes
            entityManager.close();
            factory.close();
            return "{ message: 'Insert Failed', Agent: '" + agent.toString() + "' }";
        }
    }

    //
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes({ MediaType.APPLICATION_JSON})
//    @Path("/putagent")
//    public String putAgent(String jsonString) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = factory.createEntityManager();
//        Gson gson = new Gson();
//        Agent agent = gson.fromJson(jsonString, Agent.class);
//        entityManager.getTransaction().begin();
//        Agent mergedAgent = entityManager.merge(agent); //modify
//        if(mergedAgent != null)
//        {
//            entityManager.getTransaction().commit();
//            entityManager.close();
//            return "{ message: 'Update Successful', Agent: '" + mergedAgent.toString() + "' }";
//        }
//        else
//        {
//            entityManager.getTransaction().rollback();
//            entityManager.close();
//            return "{ message: 'Update Failed', Agent: '" + agent.toString() + "' }";
//        }
//    }
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON})
    @Path("/putagent")
    public String putAgent(String jsonString) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Gson gson = new Gson();
        Agent agent = gson.fromJson(jsonString, Agent.class);
        entityManager.getTransaction().begin();
        Agent existingAgent = entityManager.find(Agent.class, agent.getId());
        //Agent mergedAgent = entityManager.merge(agent); //modify
        if(existingAgent != null)
        {
            existingAgent.setAgtFirstName(agent.getAgtFirstName());
            existingAgent.setAgtMiddleInitial(agent.getAgtMiddleInitial());
            existingAgent.setAgtLastName(agent.getAgtLastName());
            existingAgent.setAgtBusPhone(agent.getAgtBusPhone());
            existingAgent.setAgtEmail(agent.getAgtEmail());
            existingAgent.setAgtPosition(agent.getAgtPosition());
            existingAgent.setAgency(agent.getAgency());
            Agent mergedAgent = entityManager.merge(existingAgent);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "{ message: 'Update Successful', Agent: '" + mergedAgent.toString() + "' }";
        }
        else
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return "{ message: 'Update Failed', Agent: '" + agent.toString() + "' }";
        }
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteagent/{ agentid }")
    public String deleteCustomer(@PathParam("agentid") int agentId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Agent agent = entityManager.find(Agent.class, agentId);
        entityManager.getTransaction().begin();
        if(agent == null) //customer does not exist
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return"{ message: 'That Customer does not exist!' }";
        }
        else
        {
            entityManager.remove(agent);
            if(entityManager.contains(agent)) //if contains still, then didnt delete
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
}
