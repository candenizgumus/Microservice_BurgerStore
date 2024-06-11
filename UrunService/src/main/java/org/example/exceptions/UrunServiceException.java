package org.minab.exceptions;

import lombok.Getter;


@Getter
public class UrunServiceException extends RuntimeException
{
    private ErrorType errorType;

    public UrunServiceException(ErrorType errorType)
    {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public UrunServiceException(ErrorType errorType, String customMessage)
    {
        super(customMessage);
        this.errorType = errorType;
    }
}
