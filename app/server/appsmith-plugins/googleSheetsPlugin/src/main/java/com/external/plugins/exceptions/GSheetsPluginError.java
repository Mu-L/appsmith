package com.external.plugins.exceptions;

import com.appsmith.external.exceptions.AppsmithErrorAction;
import com.appsmith.external.exceptions.pluginExceptions.BasePluginError;
import com.appsmith.external.models.ErrorType;
import lombok.Getter;

@Getter
public enum GSheetsPluginError implements BasePluginError {
    QUERY_EXECUTION_FAILED(
            500,
            "PE-GSH-5000",
            "{0}",
            AppsmithErrorAction.DEFAULT,
            "Query execution error",
            ErrorType.INTERNAL_ERROR,
            "{1}",
            "{2}"),
    RESPONSE_PROCESSING_ERROR(
            500,
            "PE-GSH-5001",
            "Error occurred while processing the response. Please check the error details for more information.",
            AppsmithErrorAction.DEFAULT,
            "Response processing error",
            ErrorType.INTERNAL_ERROR,
            "{1}",
            "{2}");

    private final Integer httpErrorCode;
    private final String appErrorCode;
    private final String message;
    private final String title;
    private final AppsmithErrorAction errorAction;
    private final ErrorType errorType;

    private final String downstreamErrorMessage;

    private final String downstreamErrorCode;

    GSheetsPluginError(
            Integer httpErrorCode,
            String appErrorCode,
            String message,
            AppsmithErrorAction errorAction,
            String title,
            ErrorType errorType,
            String downstreamErrorMessage,
            String downstreamErrorCode) {
        this.httpErrorCode = httpErrorCode;
        this.appErrorCode = appErrorCode;
        this.errorType = errorType;
        this.errorAction = errorAction;
        this.message = message;
        this.title = title;
        this.downstreamErrorMessage = downstreamErrorMessage;
        this.downstreamErrorCode = downstreamErrorCode;
    }

    @Override
    public String getMessage(Object... args) {
        return replacePlaceholderWithValue(this.message, args);
    }

    @Override
    public String getDownstreamErrorMessage(Object... args) {
        return replacePlaceholderWithValue(this.downstreamErrorMessage, args);
    }

    @Override
    public String getDownstreamErrorCode(Object... args) {
        return replacePlaceholderWithValue(this.downstreamErrorCode, args);
    }

    @Override
    public String getErrorType() {
        return this.errorType.toString();
    }
}
