package org.minab.exceptions;

import lombok.Getter;


@Getter
public class SatisServiceException extends RuntimeException
{
    private ErrorType errorType;

    public SatisServiceException(ErrorType errorType)
    {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public SatisServiceException(ErrorType errorType, String customMessage)
    {
        super(customMessage);
        this.errorType = errorType;
    }
}
