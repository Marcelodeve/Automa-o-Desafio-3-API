package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.runners.AcceptanceTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - Retorno de Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs das reservas")
    public void validaListagemDeIdsDasReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar uma reserva específica")
    public void validaUmaReservaEspecifica(){
        getBookingRequest.bookingReturnIds()    //Variável int primeiroId
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs das reservas com filtro firstname")
    public void returnBookingFilterFirstName() {
        getBookingRequest.bookingReturnFilterFirstName("sally")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs das reservas com filtro lastname")
    public void returnBookingFilterLastName() {
        getBookingRequest.bookingReturnFilterLastName("brown")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs das reservas com filtro checkin")
    public void returnBookingFilterCheckin() {
        getBookingRequest.bookingReturnFilterCheckin("2014-03-13")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs das reservas com filtro checkout (Serve pra Checkout e Checkout, pois se valida uma vez")
    public void returnBookingFilterCheckout() {
        getBookingRequest.bookingReturnFilterCheckout("2014-05-21")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AcceptanceTest.class, AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro name, checkin and checkout date")
    public void validarReservasUtilizandoFiltroCheckinCheckoutEName() throws Exception {
        getBookingRequest.allBookings(
                "checkin", "2014-03-13",
                "checkout", "2019-05-21",
                "name", "Sally").then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o Schema de retorno da listagem de reservas")
    public void validaSchemaDaListagemDeReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar um retorno específico no Schema")
    public void validaUmaReservaEspecificoSchema() {

        getBookingRequest.bookingReturnIds()    //Variável int primeiroId
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))))
                .extract()
                .path("[0].bookingid");
    }
}
