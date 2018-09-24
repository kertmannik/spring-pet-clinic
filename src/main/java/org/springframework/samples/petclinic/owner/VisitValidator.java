/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * <code>Validator</code> for <code>Pet</code> forms.
 * <p>
 * We're not using Bean Validation annotations here because it is easier to define such validation rule in Java.
 * </p>
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class VisitValidator implements Validator {

    private static final String REQUIRED = "required";
    private static final String BEFORE = "before";

    @Override
    public void validate(Object obj, Errors errors) {
        Visit visit = (Visit) obj;
        String description = visit.getDescription();
        Date date = visit.getDate();

        // description validation
        if (!StringUtils.hasLength(description)) {
            errors.rejectValue("description", REQUIRED, REQUIRED);
        }

        // visit date validation
        if (date == null) {
            errors.rejectValue("date", REQUIRED, REQUIRED);
        }

        else if (date.before(new Date())) {
            errors.rejectValue("date", BEFORE, BEFORE);
        }
    }

    /**
     * This Validator validates *just* Visit instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Visit.class.isAssignableFrom(clazz);
    }


}
