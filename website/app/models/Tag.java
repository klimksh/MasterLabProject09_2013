package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Tags")
public class Tag extends Model {

    // because of many to many relationship
    @ManyToMany(mappedBy = "tags")
    public List<Note> notes;
    @ManyToMany(mappedBy = "tags")
    public List<Video> videos;
    public String title;

    public Tag() {
        super();
        this.notes = new ArrayList<Note>();
        this.videos = new ArrayList<Video>();
        this.title = "";
    }

    public Tag(String title) {
        this.title = title;
        this.notes = new ArrayList<Note>();
        this.videos = new ArrayList<Video>();
    }

    public Tag(String title, Note note, Video video) {
        if (this.notes == null) {
            this.notes = new ArrayList<Note>();
        }
        this.notes.add(note);

        if (this.videos == null) {
            this.videos = new ArrayList<Video>();
        }
        this.videos.add(video);

        this.title = title;
    }

    public static Tag findByTitle(String title) {
        return find("title", title).first();
    }
}
