package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.UUID;


@XStreamAlias("Genre")
public class Genre implements Serializable {

    private static final long SerialVersionUID = -4862921234563433707L;

    private UUID id;
    private String name;

    public Genre() {

    }

    public Genre(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
