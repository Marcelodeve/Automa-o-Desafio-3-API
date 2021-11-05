package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

// Faltou vincular corretamente classes


public class DeleteBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Deletar uma reserva específica com o paramêtro token")
    public Response deleteBookingToken(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .delete("booking/1"+ id);
    }
    @Step("Tentar excluir uma reserva que não existe")
    public Response deleteNoExistBooking(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .delete("booking/1"+ id); //NullValue()
    }
    @Step("Tentar excluir uma reserva sem autorização")
    public Response deleteBookingWithoutToken(int id) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .delete("booking/1" + id);
    }
}

