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
import model.Package;

import java.lang.reflect.Type;
import java.util.List;

@Path("/package")
public class PackageResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getallpackages")
    public String getAllPackages() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query= entityManager.createQuery("select p from Package p");
        List<Package> list = query.getResultList();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Package>>(){}.getType();
        entityManager.close();
        return gson.toJson(list, type);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getallselectpackages")
    public String getAllSelectPackages() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query= entityManager.createQuery("select p from Package p");
        List<Package> list = query.getResultList();
        JsonArray jsonArray = new JsonArray();
        for(Package p : list)
        {
            JsonObject object = new JsonObject();
            object.addProperty("id", p.getId());
            object.addProperty("pkgName", p.getPkgName());
            jsonArray.add(object);
        }
        entityManager.close();
        return jsonArray.toString();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deletepackage/{ pkgid }")
    public String deletePackage(@PathParam("pkgid") int pkgId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Package pkg = entityManager.find(Package.class, pkgId);
        entityManager.getTransaction().begin();
        if(pkg == null)
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return"{ message: 'That Package does not exist!' }";
        }
        else
        {
            entityManager.remove(pkg);
            if(entityManager.contains(pkg)) //if contains still, then didnt delete
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON})
    @Path("/postpackage")
    public String postPackage(String jsonString) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Gson gson = new Gson();
        Package pkg = gson.fromJson(jsonString, Package.class);
        entityManager.getTransaction().begin();
        entityManager.persist(pkg); //persist inserts, then commit makes sure it happens
        if(entityManager.contains(pkg))
        {
            entityManager.getTransaction().commit();
            entityManager.close();
            return "{ message: 'Insert Successful', Package: '" + pkg.toString() + "' }";
        }
        else
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return "{ message: 'Insert Failed', Package: '" + pkg.toString() + "' }";
        }
    }
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON})
    @Path("/putpackage")
    public String putPackage(String jsonString) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Gson gson = new Gson();
        Package pkg = gson.fromJson(jsonString, Package.class);
        entityManager.getTransaction().begin();
        Package mergedPackage = entityManager.merge(pkg); //modify
        if(mergedPackage != null)
        {
            entityManager.getTransaction().commit();
            entityManager.close();
            return "{ message: 'Update Successful', Package: '" + mergedPackage.toString() + "' }";
        }
        else
        {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return "{ message: 'Update Failed', Package: '" + pkg.toString() + "' }";
        }
    }
}
