package dev.samuk.auth.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
//@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "E-mail já existente";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}