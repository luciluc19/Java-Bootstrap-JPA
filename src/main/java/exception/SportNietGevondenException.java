package exception;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class SportNietGevondenException extends RuntimeException{

    public SportNietGevondenException(String sport) {
		super(String.format("Could not find wedstrijden with this sport : %s", sport));
	}

	

    
}
