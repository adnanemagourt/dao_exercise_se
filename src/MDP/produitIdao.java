package MDP;


import java.util.List;

public interface produitIdao extends IDAO<Produit> {
    public List<Produit> getAll(String des);
}
