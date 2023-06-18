package com.codestates.pre_project.module.member.exception;

public class MemberDisplayNameAlreadyExistsException extends RuntimeException{
    public MemberDisplayNameAlreadyExistsException(String message) {
        super(message);
    }
}
