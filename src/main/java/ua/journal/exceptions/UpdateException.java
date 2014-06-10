package ua.journal.exceptions;

/**
 * Created by troll on 25.04.14.
 */
public class UpdateException extends Exception {
    @Override
    public String getMessage() {
        return "Невдалося додати/оновити запис!";
    }

}
