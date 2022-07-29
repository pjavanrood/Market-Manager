package costumerAppUI.sample;

import model.Costumer;

import java.io.IOException;

public class HandleCostumerLogin {
    public static int loginRequest(String username, String password){ //-1 if user not registered. -2 if password is wrong. 0 if successful
        Costumer costumer = Costumer.findCostumer(username);
        if(costumer == null)
            return -1;

        if(Costumer.checkPassword(costumer, password))
            return 0;

        return -2;
    }

    public static int registerRequest(String name, String id, String pass, String rePass){ //-1 username already exists. -2 passwords dont match. -3 ERROR. 0 successful
        Costumer costumer = Costumer.findCostumer(id);
        if(costumer != null)
            return -1;

        if(pass.equals(rePass)) {
            try {
                Costumer.addUser(name, id, pass);
                return 0;
            } catch (IOException e) {
                return -3;
            }

        }

        return -2;
    }
}
