package service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    

    public int getAantalTicketsPerIdWedstrijdEnUsername(int idWedstrijd, String username);
}
