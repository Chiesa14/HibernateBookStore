package Models;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;


    @ElementCollection
    @CollectionTable(name = "book_tags", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "tag")
    private Set<String> tags;

    public Book() {
    }

    public Book(Long id, String title, String author, Publisher publisher, Library library,  Set<String> tags) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.library = library;
        this.tags = tags;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }


    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
