package ar.edu.utn.frba.dds.models.validators;

import ar.edu.utn.frba.dds.models.validators.validation.*;
import lombok.Getter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "passwordValidator")
public class PasswordValidator {

  @Id
  @GeneratedValue
  private Long id;
  // lista con todos los objetos que validan cositas de la contraseña

  @Transient
  List<ValidationType> validations;

  @ElementCollection
  @CollectionTable(name = "password_failures", joinColumns = @JoinColumn(name = "password_validator_id", referencedColumnName = "id"))
  @Column(name = "password_failures")
  List<String> passwordFailures;

  public List<String> validate(String userName, String password) {
    for (ValidationType validation : validations) {
      if (!validation.validatePassword(userName,password)) {// si la contraseña no es correcta bang, guardamos el error
        passwordFailures.add(validation.getErrorMessage());
      }
    }
    return passwordFailures;
  }


  // -------------------- Constructor -------------------- //
  public PasswordValidator() {
    this.passwordFailures  = new ArrayList<>();
    this.validations  = new ArrayList<>();
    addValidations(new PasswordLengthValidation());
    addValidations(new PasswordUsernameValidation());
    addValidations(new Password10000Validation(new FileReader()));
    addValidations(new PasswordConsecutiveCharactersValidation());
    addValidations(new PasswordRepeatCharacters());


    // llenar la lista con los objetos validadores
  }

  public void addValidations(ValidationType validation){
    validations.add(validation);
  }


} // fin clase
