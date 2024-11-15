package app.use_case.create_event;


import app.entity.Event.Event;

/**
 * Data Access Object for the Event Use Case.
 * TODO: notice that although this file is in Use Case, it is implemented in the data_access
 * folder
 */
public interface EventUserDataAccessInterface {
    /**
     * Saves the event to the database
     * @param event to save onto the database
     */
    void save(Event event);

    /**
     * Checks if the coordinates are valid, True if it is
     * @param latitude the latitude coordinate
     * @param longitude the longitude coordinate
     * @return True if the coordinates are valid
     */
    boolean valid_coordinates(int latitude, int longitude);


    /**
     * Checks if the capacity is valid, True if it is
     * @param capacity the latitude coordinate
     * @return True if the coordinates are valid
     */
    boolean valid_capacity(int capacity);

}