package controller;

import service.Crud;
import service.LecturaDatosJson;

import java.util.List;
import java.util.Map;

public class Controller {

    public void logicaPrograma(){
        LecturaDatosJson lecturaDatosJson = new LecturaDatosJson();
        Crud crud = new Crud();

        List<Map<String, Object>> paisesListJson = lecturaDatosJson.readDataJson("pais.json");
        List<Map<String, Object>> presidentesListJson = lecturaDatosJson.readDataJson("presidente.json");

        crud.inserccionesdesdeJson(paisesListJson, presidentesListJson);


        List<Map<String, Object>> paisesList = crud.getListaDePaises();
        List<Map<String, Object>> presidentesList = crud.getListaDePresidentes();

        lecturaDatosJson.readValuesFromDb(paisesList);
        lecturaDatosJson.readValuesFromDb(presidentesList);


        crud.updateDataPaises("España", "Junts", "dictadura");
        crud.updateDataPresidentes("Pedro Sánchez", 10);


        lecturaDatosJson.readValuesFromDb(paisesList);
        lecturaDatosJson.readValuesFromDb(presidentesList);


        crud.deleteDataMongoDB("paises");
        crud.deleteDataMongoDB("presidentes");







    }
}
