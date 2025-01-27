package controller;

import service.Crud;
import service.LecturaDatosJson;

import java.util.List;
import java.util.Map;

/**
 * Clase controller con la lógica de la aplicación
 * @author cristian
 * @version 1.0
 */
public class Controller {

    /**
     * Metodo principal que realiza los crud a la base de datos de mongoDB
     */
    public void logicaPrograma(){


        //instanciamos los objetos con los metodos para la realización de la tarea
        LecturaDatosJson lecturaDatosJson = new LecturaDatosJson();
        Crud crud = new Crud();


        //creamos listas de Maps con la información almacenada en los .json
        List<Map<String, Object>> paisesListJson = lecturaDatosJson.readDataJson("pais.json");
        List<Map<String, Object>> presidentesListJson = lecturaDatosJson.readDataJson("presidente.json");

        //introducimos las listas de Maps en la base de datos de mongo
        crud.inserccionesdesdeJson(paisesListJson, presidentesListJson);


        //obtenemos una losta de Maps con la información de las bases de datos de mongo
        List<Map<String, Object>> paisesListSinUpdates = crud.getListaDePaises();
        List<Map<String, Object>> presidentesListSinUpdates = crud.getListaDePresidentes();

        //llamamos al metodo que recorre la lista y saca por pantalla la información
        lecturaDatosJson.readValuesFromDb(paisesListSinUpdates);
        lecturaDatosJson.readValuesFromDb(presidentesListSinUpdates);


        //llamamos a los metodos que actualizan la información de la base de datos de mongo
        crud.updateDataPaises("España", "Junts", "dictadura");
        crud.updateDataPresidentes("Pedro Sánchez", 10);

        //volvemos a hacer una lista con la información que hay de la base de datos
        List<Map<String, Object>> paisesListWithUpdates = crud.getListaDePaises();
        List<Map<String, Object>> presidentesListWithUpdates = crud.getListaDePresidentes();

        //sacamos otra vez la información por pantalla
        lecturaDatosJson.readValuesFromDb(paisesListWithUpdates);
        lecturaDatosJson.readValuesFromDb(presidentesListWithUpdates);

        //llamamos al metodo que borra todos los registros de las colecciones de mongo
        crud.deleteDataMongoDB("paises");
        crud.deleteDataMongoDB("presidentes");




    }
}
