package com.jareer.englishlearningplatform.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class ErrorDTO {
    private String friendlyMessage;
    private String developerMessage;
    private Throwable throwable;

    public ErrorDTO(@NonNull Throwable throwable) {
        this(throwable.getLocalizedMessage(), throwable.getMessage(), throwable);
    }

    public ErrorDTO(String friendlyMessage) {
        this(friendlyMessage, friendlyMessage);
    }

    public ErrorDTO(String friendlyMessage, String developerMessage) {
        this(friendlyMessage, friendlyMessage, null);
    }

    public ErrorDTO(String friendlyMessage, String developerMessage, Throwable throwable) {
        this.friendlyMessage = friendlyMessage;
        this.developerMessage = developerMessage;
        this.throwable = throwable;
    }
}
