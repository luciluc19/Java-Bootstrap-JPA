package exception;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class WedstrijdIdNietGevondenException extends RuntimeException{

    public WedstrijdIdNietGevondenException(int id) {
		super(String.format("Could not find wedstrijd with following id: %s", id));
	}
    
}
