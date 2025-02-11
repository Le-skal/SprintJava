package exoObjet2;

public class Person {
    private String nom;
    private int age;

    public Person() {
        this.nom = "Inconnu";
        this.age = 0;
    }

    public Person(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public void sePresenter() {
        System.out.println("Je m'appelle " + nom + " et j'ai " + age + " ans.");
    }
}
