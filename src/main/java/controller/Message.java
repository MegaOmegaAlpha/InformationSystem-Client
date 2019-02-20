package controller;

import java.io.Serializable;
import java.util.List;

/*
    Combinations:

    client ID_INIT -> trackfile, genrefile
    respond success

    client ID_GET -> page
    respond success, list, end

    client ID_DELETE -> id
    respond success

    client ID_EDIT -> track
    respond success

    client ID_NEW
    respond success, track

    client ID_SAVE
    respond success
 */

public class Message implements Serializable {
    static final long serialVersionUID = 1L;

    public enum ActionID { ID_INIT, ID_GET, ID_DELETE, ID_EDIT, ID_NEW, ID_SAVE }

    public ActionID actionID;

    public int clientID; // не нужен?
    public int page;

    public UITrack track;

    public String id;

    public String trackfile;
    public String genrefile;

    public boolean success;
    public boolean end;

    public List<UITrack> list;

}
