package hibernate;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name ="artykuly")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "Nazwa_produktu")
    private String productName;
    @Column (name = "kod")
    private String code;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(productName, article.productName) &&
                Objects.equals(code, article.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, code);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
