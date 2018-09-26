import { FormControl } from '@angular/forms';

export class BasicValidators {
    static email(control: FormControl) {
        let EMAIL_REGEXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        return EMAIL_REGEXP.test(control.value)
            ? null
            : {
                  validateEmail: {
                      valid: false
                  }
              };
    }

    static cnpj(control: FormControl) {
        let EMAIL_REGEXP = /^([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})$/;

        return EMAIL_REGEXP.test(control.value)
            ? null
            : {
                  validateEmail: {
                      valid: false
                  }
              };
    }

    static cpf(control: FormControl) {
        let EMAIL_REGEXP = /^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}$/;

        return EMAIL_REGEXP.test(control.value)
            ? null
            : {
                  validateEmail: {
                      valid: false
                  }
              };
    }
}
