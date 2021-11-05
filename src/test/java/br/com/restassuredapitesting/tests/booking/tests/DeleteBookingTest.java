package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

// Não foram finalizados

@Feature("Feature - Deletar Reserva")
public class DeleteBookingTest extends BaseTest {
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Deletar uma reserva específica com o paramêtro token")
    public void deletarUmaReservaEspecificaUtilizandoToken(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[1].bookingid");

        putBookingRequest.updateBookingToken(primeiroId,postAuthRequest.getToken())
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(AllTests.class)
    @DisplayName("Tentar deletar uma reserva que não existe")
    public void deletarUmaReservaQueNaoExiste(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[1].bookingid");

        putBookingRequest.updateBookingToken(primeiroId,postAuthRequest.getToken())
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Tentar deletar uma reserva sem autorização")
    public void deletarUmaReservaSemAutorizacao(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[1].bookingid");

        putBookingRequest.updateBookingToken(primeiroId,postAuthRequest.getToken())
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0));
    }
}

