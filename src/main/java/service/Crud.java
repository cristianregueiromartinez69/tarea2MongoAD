package service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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

    public void updateDataPaises(String newPartido){
        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionPaises = mongoDatabase.getCollection("paises");

        collectionPaises.updateOne(
                Filters.eq("nome", "España"),
                

        )
    }


}
