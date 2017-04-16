package com.mbortnichuk.phonebook;

/**
 * Created by Mariana on 05-Feb-17.
 */
public class CommandException extends Exception {

    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }
}
