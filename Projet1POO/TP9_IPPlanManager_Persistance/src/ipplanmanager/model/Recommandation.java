package ipplanmanager.model;

public class Recommandation {
    private String titre;
    private String priorite;
    private String message;
    
    public Recommandation(String titre, String priorite, String message) {
        if (titre == null || titre.isEmpty()) {
            this.titre = "Recommandation sans titre";
        } else {
            this.titre = titre;
        }
        if (priorite == null || priorite.isEmpty()) {
            this.priorite = "NORMALE";
        } else {
            this.priorite = priorite;
        }
        if (message == null || message.isEmpty()) {
            this.message = "Aucun detail";
        } else {
            this.message = message;
        }
    }
    
    public String getTitre() { return titre; }
    public String getPriorite() { return priorite; }
    public String getMessage() { return message; }
}