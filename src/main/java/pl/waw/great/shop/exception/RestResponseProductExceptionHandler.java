package pl.waw.great.shop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class RestResponseProductExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final MessageSource messageSource;

    public RestResponseProductExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = {ProductWithGivenIdNotExistsException.class})
    public ResponseEntity<Object> handleProductWithGivenIdNotExistsExceptions(ProductWithGivenIdNotExistsException e) {
        String errorMessage = messageSource.getMessage("productWithGivenIdNotExists", new Object[]{e.getId()}, Locale.getDefault());
        ErrorInfo employeeException = new ErrorInfo(errorMessage, HttpStatus.CONFLICT, LocalDateTime.now());
        log.error(errorMessage);
        return new ResponseEntity<>(employeeException, employeeException.getHttpStatus());
    }

    @ExceptionHandler(value = {ProductWithGivenTitleExists.class})
    public ResponseEntity<Object> handleProductWithGivenTitleExists(ProductWithGivenTitleExists e) {
        String errorMessage = messageSource.getMessage("productWithGivenTitleExists", new Object[]{e.getTitle()}, Locale.getDefault());
        ErrorInfo employeeException = new ErrorInfo(errorMessage, HttpStatus.CONFLICT, LocalDateTime.now());
        log.error(errorMessage);
        return new ResponseEntity<>(employeeException, employeeException.getHttpStatus());
    }

    @ExceptionHandler(value = {ProductWithGivenTitleNotExistsException.class})
    public ResponseEntity<Object> handleProductWithGivenTitleNotExists(ProductWithGivenTitleNotExistsException e) {
        String errorMessage = messageSource.getMessage("productWithGivenTitleNotExists", new Object[]{e.getTitle()}, Locale.getDefault());
        ErrorInfo employeeException = new ErrorInfo(errorMessage, HttpStatus.CONFLICT, LocalDateTime.now());
        log.error(errorMessage);
        return new ResponseEntity<>(employeeException, employeeException.getHttpStatus());
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleEmployeeDtoException(MethodArgumentNotValidException e) {
        ErrorInfo employeeException = new ErrorInfo(getFieldErrorsString(e), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        log.error(getFieldErrorsString(e));
        return new ResponseEntity<>(employeeException, employeeException.getHttpStatus());
    }

    @ExceptionHandler(value = {CategoryWithGivenNameNotExistsException.class})
    public ResponseEntity<Object> handleCategoryWithGivenNameNotExistsException(CategoryWithGivenNameNotExistsException e) {
        String errorMessage = messageSource.getMessage("categoryWithGivenNameNotExists", new Object[]{e.getName()}, Locale.getDefault());
        ErrorInfo employeeException = new ErrorInfo(errorMessage, HttpStatus.CONFLICT, LocalDateTime.now());
        log.error(errorMessage);
        return new ResponseEntity<>(employeeException, employeeException.getHttpStatus());
    }

    @ExceptionHandler(value = {InvalidCommentIndexException.class})
    public ResponseEntity<Object> handleInvalidCommentIndexException(InvalidCommentIndexException e) {
        String errorMessage = messageSource.getMessage("invalidCommentIndex", new Object[]{e.getIndex()}, Locale.getDefault());
        ErrorInfo employeeException = new ErrorInfo(errorMessage, HttpStatus.CONFLICT, LocalDateTime.now());
        log.error(errorMessage);
        return new ResponseEntity<>(employeeException, employeeException.getHttpStatus());
    }

    public String getFieldErrorsString(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        List<FieldError> errors = e.getFieldErrors();
        errors.forEach(fieldError -> sb.append(fieldError.getDefaultMessage()));

        return sb.toString();
    }
}
