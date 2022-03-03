package com.address.book.app.utils;

import com.address.book.app.entity.Address;
import com.address.book.app.entity.Contact;
import com.address.book.app.entity.Person;
import com.address.book.app.response.ResponseDto;
import com.google.gson.Gson;

public class TestUtils {

    public static final String API_PERSONS = "/api/v1/person/";
    private static Address address = new Address(1, "Nagole", "Hyderabad", "Telangana", "India", "500068");
    private static Contact contact = new Contact(1, "test@gmail.com", "1234567891", "0401234567");
    public static Person person = new Person(1, "Brahmaiah", "Cheeli", address, contact);
    public static final String PERSON_DELETED_SUCCESSFULLY = "Person deleted successfully";
    public static final String FAILED_TO_DELETE_PERSON = "Failed to delete person";
    public static final ResponseDto successResponse = ResponseDto.prepareResponse(PERSON_DELETED_SUCCESSFULLY, true);
    public static final ResponseDto failureResponse = ResponseDto.prepareResponse(FAILED_TO_DELETE_PERSON, false);
    public static String objectToJson() {
        Gson gson = new Gson();
        return gson.toJson(person);
    }
}
