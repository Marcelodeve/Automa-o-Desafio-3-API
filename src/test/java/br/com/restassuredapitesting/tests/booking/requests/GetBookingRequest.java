package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {
    @Step("Retorna os Ids da Listagem de Reservas")
    public Response bookingReturnIds(){
        return given()
                .when()
                .get("booking");
    }

    @Step("Retorna os Ids da Listagem de Reservas com filtro")
    public Response bookingReturnFilterFirstName(String firstName){
        return given()
                .queryParams(firstName, "sally")
                .when()
                .log().all()
                .get("booking");
    }
    public Response bookingReturnFilterLastName(String lastName){
        return given()
                .queryParams(lastName, "brown")
                .when()
                .log().all()
                .get("booking");
    }
    public Response bookingReturnFilterCheckin(String checkin){
        return given()
                .queryParams(checkin, "2014-03-13")
                .when()
                .log().all()
                .get("booking");
    }

    public Response bookingReturnFilterCheckout(String checkout){
        return given()
                .queryParams(checkout, "2014-05-21")
                .when()
                .log().all()
                .get("booking");
    }

    @Step("Buscar todas as reservas com filtragem {param1}={name1} , {param2}={name2} e {param3}={name3}")
    public Response allBookings(String param1, String name1, String param2, String name2, String param3, String name3) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .queryParams(param1, name1, param2, name2, param3, name3)
                .get("booking");
    }


}


