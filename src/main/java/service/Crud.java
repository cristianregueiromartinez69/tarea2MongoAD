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

/**
 * Clase que realiza las operaciones crud en la base de datos de MongoDB
 * @author cristian
 * @version 1.0
 */
public class Crud {

    /**
     * Metodo que insertar unas listas en colecciones de mongoDB
     * @param paisesList la lista de países
     * @param presidentesList la lista de presidentes
     */
    public void inserccionesdesdeJson(List<Map<String, Object>> paisesList, List<Map<String, Object>> presidentesList){

        /*
          Explicación:
          1. Establecemos conexión con la base de datos
          2. obtenemos las colecciones a través de la conexción a la base de datos
          3. recorremos las listas que pasamos por parámetro
          4. creamos un documento que recibe por parámetro los objetos individuales de la lista
          5. insertamos los documentos en la colección
         */
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

    /**
     * Metodo que lee las colecciones de paises mongoDB
     * @return la lista con los datos de la colección
     */
    public List<Map<String, Object>> getListaDePaises(){

        //establecemos la conexión y obtenemos la coleccion para luego que retorne esa colección en un ArrayList
        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionPaises = mongoDatabase.getCollection("paises");

        return collectionPaises.find().into(new ArrayList<>());
    }

    /**
     * Metodo que lee las colecciones de presidentes mongoDB
     * @return la lista con los datos de la colección
     */
    public List<Map<String, Object>> getListaDePresidentes(){

        //establecemos la conexión y obtenemos la coleccion para luego que retorne esa colección en un ArrayList
        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionPaises = mongoDatabase.getCollection("presidentes");

        return collectionPaises.find().into(new ArrayList<>());
    }

    /**
     * Metodo que actualiza información de la colección de países de MongoDB
     * @param id el id para filtrar el campo de la colección a actualizar
     * @param newPartido el nuevo partido a actualizar
     * @param newOrganicacion la nueva organización a actualizar
     */
    public void updateDataPaises(String id, String newPartido, String newOrganicacion){

        //establecemos la conexión y obtenemos la coleccion
        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionPaises = mongoDatabase.getCollection("paises");

        /*
          Explicacion:
          1. Creamos un documento igaul al filtro aplicado sobre la colección
          2. El filtro nos va a devolver el campo que sea igual a la clave(nome) con el valor que pasemos por parámetro
          3. devolverá el primer resultado
          4. si lo encuentra, llamamos al metodo .combine que recibe por parámetro las actualiciones
          5. con este metodo podemos hacer muchos updates en 1 sola línea
          6. el metodo .addToSet, añade un campo a un array
          7. el .set lo que hace es sustituir el valor que había por el pasado por parámetro
         */
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

    /**
     * Metodo que actualiza información de la colección de presidentes de MongoDB
     * @param id el id para filtrar el campo de la colección a actualizar
     * @param aumentoEdad el numero en cuanto va a aumentar la edad
     */
    public void updateDataPresidentes(String id, int aumentoEdad){

        //establecemos la conexión y obtenemos la coleccion
        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionPresidentes = mongoDatabase.getCollection("presidentes");

        /*
          Explicacion:
          1. Creamos un documento igaul al filtro aplicado sobre la colección
          2. El filtro nos va a devolver el campo que sea igual a la clave(nome) con el valor que pasemos por parámetro
          3. devolverá el primer resultado
          4. si lo encuentra, llamamos al metodo .combine que recibe por parámetro las actualiciones
          5. con este metodo podemos hacer muchos updates en 1 sola línea
          6. el metodo .inc, incrementa en el valor que pasemos por parámetro, lo que haya en la base de datos
         */
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

    /**
     * Metodo que borra todos los elementos de la coleccion pasada por parámetro
     * @param nameCollection el nombre de la coleccion para borrar los datos
     */
    public void deleteDataMongoDB(String nameCollection){

        //establecemos la conexión y obtenemos la coleccion
        MongoDatabase mongoDatabase = MongoDbConnection.getConnectionMongoDB();
        MongoCollection<Document> collectionMongo = mongoDatabase.getCollection(nameCollection);

        //llamamos al metodo deleteMany que borra los elementos sin borrar la estructura de la colección
        collectionMongo.deleteMany(new Document());
        System.out.println("coleccion " + nameCollection + " eliminado correctamente");

    }


}
