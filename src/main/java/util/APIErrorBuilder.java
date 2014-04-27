package util;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import model.APIError;


public class APIErrorBuilder {
    private APIErrorBuilder() {
    }

    public final static Object buildError(int errorCode, String errorMessage,
            HttpServletRequest request) {

        APIError error = new APIError();
        error.setErrorCode(errorCode);
        error.setErrorMessage(errorMessage);
        if (request.getHeader("Accept").equals(MediaType.APPLICATION_JSON)) {
            return new JAXBElement<APIError>(new QName("error"),
                    APIError.class, error);
        }
        return error;
    }
}