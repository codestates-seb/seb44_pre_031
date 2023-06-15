package com.codestates.pre_project.member.exception;

public class MemberDisplayNameAlreadyExistsException extends RuntimeException{
    public MemberDisplayNameAlreadyExistsException(String message) {
        super(message);
    }
}