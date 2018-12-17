package iut.flavienregis.android_td5_ex3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class SelectionMot {
    
    /**  Tableau par défaut pour les mots corrects */
    private static final String[] MOTS_CORRECTS = {"apparemment", "sabbatique",
                                                   "gaufre", "arrondir", "arracher",
                                                   "annihiler", "affiliation",
                                                   "carrière", "connivence", "débarrasser",
                                                   "démarrer", "délai", "décor", "en détail",
                                                   "désarroi", "cauchemar", "embarras",
                                                   "entonnoir", "erroné", "indépendamment",
                                                   "inclus", "miroir", "tiroir", "nappe",
                                                   "planning", "planifier"};
    
    /** Tableau par défaut pour les mots incorrects */
    private static final String[] MOTS_INCORRECTS = {"apparament", "sabhatique", "sabatique",
                                                     "gauffre", "arondir", "aracher",
                                                     "anihiler", "afiliation", "carière",
                                                     "conivence", "débarasser", "démarer",
                                                     "délais", "décors", "en détails",
                                                     "désaroi", "désarroit", "cauchemard",
                                                     "embaras", "emabarra", "embara",
                                                     "entonoir", "erronné", "éroné",
                                                     "indépendemment", "inclu", "mirroir",
                                                     "tirroir", "nape", "planing", "plannifier"};
    
    /** Liste des mots corrects qui servira de base */
    private ArrayList<String> baseMotCorrect;
    
    /** Liste des mots incorrects qui servira de base */
    private ArrayList<String> baseMotIncorrect;
                                            
    /** 
     * Liste des mots sélectionnés de manière aléatoire
     * L'utilisateur sera interrogé à partir de cette liste
     */
    private ArrayList<String> selection;
    
    /** Liste des mots corrects figurant dans la sélection*/
    private ArrayList<String> selectionMotCorrect;
    
    /** Liste des mots incorrects figurant dans la sélection */
    private ArrayList<String> selectionMotIncorrect;
    
    public SelectionMot() {
        
        // initialisation des 2 listes de base avec les constantes statiques
        baseMotCorrect = new ArrayList<>( Arrays.asList(MOTS_CORRECTS));
        baseMotIncorrect = new ArrayList<>( Arrays.asList(MOTS_INCORRECTS));
        
        // les listes des sélections sont initialisées à vide
        selection = new ArrayList<>();
        selectionMotCorrect = new ArrayList<>();
        selectionMotIncorrect = new ArrayList<>();
    }
    
    
    
    /**
     * Méthode invoquée pour effectuer une sélection de mots à partir
     * des bases de mots corrects et incorrects initialisées dans le constructeur.
     * Le nombre de mots corrects de la liste construite sera compris entre
     * 30 et 70 % de l'effectif de la liste.
     * Les mots sélectionnés sont stockés dans l'objet courant et renvoyés en tant
     * que résultat.
     * @param quantite  nombre de mots à placer dans la liste des mots sélectionnés
     * @return une liste de mots sélectionnés
     */
    public ArrayList<String> selectionnerMot(int quantite) {
        double pourcentageCorrect = 0;       // pourcentage de mots corrects
        int nbMotCorrect = 0;                // nombre de mots corrects
        if (quantite < 0) {   // quantité incorrecte : on la modifie
            quantite = -quantite;
        }
               
        /*
         *  tirage aléatoire du pourcentage de mots corrects que contiendra
         *  la sélection. Il doit être compris entre 30 et 70% 
         */
        do {
           pourcentageCorrect = Math.random();
        } while (pourcentageCorrect < 0.3 || pourcentageCorrect > 0.7);
        
        // calcul du nombre de mots corrects à sélectionner
        nbMotCorrect = (int) (quantite * pourcentageCorrect);
        
        // on mélange aléatoirement les deux listes de mots de la base
        Collections.shuffle(baseMotCorrect);
        Collections.shuffle(baseMotIncorrect);
        
        /*
         *  on construit la liste des mots sélectionnés, en lui ajoutant
         *  d'abord les mots corrects et ensuite les mots incorrects.
         *  On mélange aléatoirement la liste obtenue
         */
        selection.clear();
        selectionMotCorrect.clear();
        selectionMotIncorrect.clear();        
        for (int i = 1; i <= nbMotCorrect; i++) {
            selection.add(baseMotCorrect.get(i));
            selectionMotCorrect.add(baseMotCorrect.get(i));
        }
        for (int i = 1; i <= quantite - nbMotCorrect; i++) {
            selection.add(baseMotIncorrect.get(i));
            selectionMotIncorrect.add(baseMotIncorrect.get(i));
        }
        Collections.shuffle(selection);
        
        return selection;        
    }
    
    
    /**
     * Détermine le nombre de mots corrects et présents dans la sélection
     * @return  le nombre de mots corrects et présents dans la sélection
     */
    public int nbMotCorrectSelection() {
        return selectionMotCorrect.size();
    }
    
    
    /**
     * Renvoie l'élément situé à la position index dans la liste des mots
     * de la sélection
     * @param index  index du mot à renvoyer
     * @return le mot situé à la position index, si celle-ci est valide
     *         sinon une chaîne vide
     */
    public String get(int index) {
    	if (index >= 0 && index < selection.size()) {
    		return selection.get(index);
    	} 
    	return "";
    }
    
    
    /**
     * Détermine si l'ensemble des mots argument est identique à la liste des mots
     * corrects de la sélection
     * @param choixMotCorrect  ensemble de mots jugés corrects
     * @return  un booléean égal à vrai ssi l'ensemble argument et les mots
     *          corrects de la sélection coïncident
     */
    public boolean toutJuste(TreeSet<String> choixMotCorrect) {
        return (choixMotCorrect.containsAll(selectionMotCorrect) 
                && selectionMotCorrect.containsAll(choixMotCorrect));
    }
    
    
    /**
     * Détermine combien de mots corrects sont présents dans l'ensemble arguments
     * @param choixMotCorrect  liste de mots jugés corrects
     * @return  un entier égal au nombre de mots effectivement corrects parmi ceux
     *          de la liste argument
     */
    public int nbMotCorrectTrouve(TreeSet<String> choixMotCorrect) {
        int resultat = 0;           // nombre de mots correctement orthographiés et présents
                                    // dans l'ensemble argument
        for (String aTester : choixMotCorrect) {
            if (selectionMotCorrect.contains(aTester)) {
                resultat++;
            }
        }
        return resultat;
        
    }
    
    
    /**
     * Détermine combien de mots incorrects sont présents dans l'ensemble argument
     * Ou autrement combien de mots incorrects ont été mal classés (ie jugés corrects)
     * @param choixMotCorrect  liste de mots jugés corrects
     * @return  un entier égal au nombre de mots effectivement incorrects parmi ceux
     *          de l'ensemble argument
     */
    public int nbErreurMotIncorrect(TreeSet<String> choixMotCorrect) {
        int resultat = 0;           // nombre de mots correctement orthographiés et présents
                                    // dans l'ensemble argument
        for (String aTester : choixMotCorrect) {
            if (selectionMotIncorrect.contains(aTester)) {
                resultat++;
            }
        }
        return resultat;        
    }
    
   
    /**
     * Détermine combien de mots corrects ne sont pas présents dans l'ensemble
     * Ou autrement dit combien de mots corrects n'ont pas été identifiés. 
     * argument, bien qu présents dans la sélection
     * @param choixMotCorrect  ensemble de mots supposés corrects
     * @return un entier égal au nombre de mots corrects qui ne figurent pas dans l'ensemble
     *         argument
     */
    public int nbErreurMotCorrect(TreeSet<String> choixMotCorrect) {
        int resultat = 0;           // nombre de mots corrects présent dans la sélection
                                    // et qui ne figurent pas dans l'ensemble argument
        for (String aTester : selectionMotCorrect) {
            if (! choixMotCorrect.contains(aTester)) {
                resultat++;
            }
        }
        return resultat;        
    }
    
    
    /**
     * Détermine la liste des mots corrects non trouvés
     * @param choixMotCorrect  ensemble des mots supposés corrects
     * @return une liste contenant des mots corrects qui figurent dans la sélection
     *         et qui ne figurent pas dans l'ensemble argument
     */
    public ArrayList<String>  motCorrectNonTrouve(TreeSet<String> choixMotCorrect) {
        ArrayList<String> resultat = new ArrayList<>();
        for (String aTester : selectionMotCorrect) {
            if (! choixMotCorrect.contains(aTester)) {
               resultat.add(aTester);
            }
        }
        return resultat;   
    }
    
    /**
     * Détermine la liste des mots incorrects présents dans l'ensemble arguemnt
     * @param choixMotCorrect  ensemble des mots supposés corrects
     * @return  une liste contenant tous les mots incorrects qui figurent dans la sélection
     *          et qui ne figurent pas dans l'ensemble arguement
     */
    public ArrayList<String>  motIncorrectNonTrouve(TreeSet<String> choixMotCorrect) {
        ArrayList<String> resultat = new ArrayList<>();
        for (String aTester : choixMotCorrect) {
            if (selectionMotIncorrect.contains(aTester)) {
               resultat.add(aTester);
            }
        }
        return resultat;   
    }

}
