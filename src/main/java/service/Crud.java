package service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import config.MongoDbConnection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Crud {

    public void inserccionesdesdeJson(List<Map<String, Object>> paisesList, List<Map<String, Object>> presidentesList){

        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionPaises = mongoDatabase.getCollection("paises");
        MongoCollection<Document> collectionPresidentes = mongoDatabase.getCollection("presidentes");

        for(Map<String, Object> objectMap : paisesList){
            Document document = new Document(objectMap);
            collectionPaises.insertOne(document);
        }
        System.out.println("Documento de paises insertado correctamente");

        for(Map<String, Object> objectMap : presidentesList){
            Document document = new Document(objectMap);
            collectionPresidentes.insertOne(document);
        }
        System.out.println("Documento de presidentes insertado correctamente");
    }

    public List<Map<String, Object>> getListaDePaises(){
        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionPaises = mongoDatabase.getCollection("paises");

        return collectionPaises.find().into(new ArrayList<>());
    }

    public List<Map<String, Object>> getListaDePresidentes(){
        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionPaises = mongoDatabase.getCollection("presidentes");

        return collectionPaises.find().into(new ArrayList<>());
    }

    public void updateDataPaises(String id, String newPartido, String newOrganicacion){
        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionPaises = mongoDatabase.getCollection("paises");

        Document document = collectionPaises.find(Filters.eq("nome", id)).first();

        if(document != null){
            collectionPaises.updateOne(
                    Filters.eq("nome", id),
                    Updates.combine(
                            Updates.addToSet("partidos", newPartido),
                            Updates.set("organizacion", newOrganicacion)

                    )
            );
            System.out.println("Documento de paises actualizado correctamente");
        }
        else{
            System.out.println("Documento de paises no encontrado en la base de datos");
        }


    }

    public void updateDataPresidentes(String id, int aumentoEdad){
        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionPresidentes = mongoDatabase.getCollection("presidentes");

        Document document = collectionPresidentes.find(Filters.eq("nome", id)).first();

        if(document != null){
            collectionPresidentes.updateOne(
                    Filters.eq("nome", id),
                    Updates.combine(
                            Updates.inc("idade", aumentoEdad)
                    )
            );
            System.out.println("Documento de presidente actualizado correctamente");
        }
        else{
            System.out.println("Documento de presidentes no encontrado en la base de datos");
        }


    }


}
