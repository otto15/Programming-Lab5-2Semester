package com.otto15.client.exceptions;

public class InfiniteRecursionException extends RuntimeException {

    public InfiniteRecursionException(String message) {
        super("Infinite recursion found.");
    }

}
