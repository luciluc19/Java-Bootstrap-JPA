package validator;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springBoot.EwdOlymp.NieuweWedstrijdValidator;

import repository.DisciplineRepository;
import repository.WedstrijdRepository;

public class NieuweWedstrijdValidation implements Validator {

	@Autowired
	private WedstrijdRepository wedstrijdRepository;

	@Autowired
	private DisciplineRepository disciplineRepository;

	@Override
	public boolean supports(Class<?> klass) {
		return NieuweWedstrijdValidator.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NieuweWedstrijdValidator nieuweWedstrijd = (NieuweWedstrijdValidator) target;

		LocalTime aanvangsuur = nieuweWedstrijd.getAanvangsuur();
		LocalDate datum = nieuweWedstrijd.getDatum();
		String olympischNummer1 = nieuweWedstrijd.getOlympischNummer1();
		String olympischNummer2 = nieuweWedstrijd.getOlympischNummer2();
		String discipline1 = nieuweWedstrijd.getDiscipline1();
		String discipline2 = nieuweWedstrijd.getDiscipline2();

		if (aanvangsuur == null) {
			errors.rejectValue("aanvangsuur", "Validation.aanvangsuurNull");
		} else {
			if (aanvangsuur.isBefore(LocalTime.of(8, 0))) {
				errors.rejectValue("aanvangsuur", "Validation.aanvangsuurMinimum",
						"Het aanvangsuur moet na 8 uur zijn");
			}
		}
		if (datum == null) {
			errors.rejectValue("datum", "Validation.datumNull");

		} else {
			if (datum.isAfter(LocalDate.of(2024, 7, 26)) && datum.isBefore(LocalDate.of(2024, 8, 11))) {
			} else {
				errors.rejectValue("datum", "Validation.datumNietJuist");
			}
		}

		if (olympischNummer1.equals(olympischNummer2)) {
			errors.rejectValue("olympischNummer2", "Validatie.olympischNummerNietHetzelfde");
		}

		if (isInteger(olympischNummer1) && isInteger(olympischNummer2)) {
			int num1 = Integer.parseInt(olympischNummer1);
			int num2 = Integer.parseInt(olympischNummer2);
			if (num2 < num1 - 1000 || num2 > num1 + 1000) {
				errors.rejectValue("olympischNummer2", "Validation.olympischNummer2FoutUitrekening");
			}
		} else {
			errors.rejectValue("olympischNummer2", "Validation.olympischNummer2FoutType");
		}

		if (discipline1.equals(discipline2) && !discipline1.isEmpty()) {
			errors.rejectValue("discipline2", "Validation.disciplineHetzelfde");
		}

		if (isInteger(olympischNummer1) && isInteger(olympischNummer2)) {
			int num1 = Integer.parseInt(olympischNummer1);
			int num2 = Integer.parseInt(olympischNummer2);
			if (wedstrijdRepository.findByOlympischNummer1(num1) != null) {
				errors.rejectValue("olympischNummer1", "Validation.bestaandNR1");
			}
			if (wedstrijdRepository.findByOlympischNummer2(num2) != null) {
				errors.rejectValue("olympischNummer2", "Validation.bestaandNR1");
			}
		}

		if (disciplineRepository.findByNaam(discipline1) != null) {
			errors.rejectValue("discipline1", "Validation.bestaandD1");
		}

		if (disciplineRepository.findByNaam(discipline2) != null) {
			errors.rejectValue("discipline2", "Validation.bestaandD1");
		}

	}

	private boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}
}
