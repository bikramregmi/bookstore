package com.bikram.bookstore.exceptions;

public class ResourceNotFound extends RuntimeException {
        public ResourceNotFound(String errorMessageOne) {
            super(errorMessageOne);
        }

}
