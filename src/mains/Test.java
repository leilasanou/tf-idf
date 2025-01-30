package mains;
import classeInitiale.Vocabulary;

import java.util.Scanner;
import classeInitiale.TfIdf;
import classeInitiale.Bm25;
import classeInitiale.Corpus;

import enums.DataSets;
import exception.Bm25Exception;
import exception.CorpusException;

import classeInitiale.TailleDocument;
import classeInitiale.TailleMot;

        	public class Test {
        	    public static void main(String[] args) {
        	        try {
        	            
        	           String cheminF = "C:\\Users\\sanou\\eclipse-workspace\\projetJava\\src\\document.txt";
        	        	
        	            String stop = "C:\\Users\\sanou\\eclipse-workspace\\projetJava\\src\\stop.txt";
        	           
        	            if (cheminF == null || cheminF.isEmpty()) {
        	                throw new CorpusException("Le fichier du corpus est nul");
        	            }

        	            CorpusException.creation(cheminF);
        	            
        	            TfIdf tfidf = new TfIdf(); 
        	            Bm25 bm = new Bm25();
        	            Vocabulary vocabulary = Vocabulary.getUnique();
        	            Corpus corpus = new Corpus(cheminF, DataSets.OUVRAGES,tfidf);
        	            vocabulary.ajoutStopWords(stop);
                
        	            /*
        	             * test de la partie b
        	             */
        	            TailleDocument tailleDoc = new TailleDocument();
        	            TailleMot tailleMot = new TailleMot();
                   
        	            tfidf.vocabulaire(corpus); 
        	            tfidf.processCorpus(corpus);
        	            
        	            System.out.println("Taille du documents : " + corpus.taille(tailleDoc));
        	        
        	            System.out.println("Taille des mots : " + corpus.taille(tailleMot));
        	            //fin de test de la partie b
        	            
        	            //test de la partie A
        	            System.out.println(corpus.toString());
        	         //fin de test de la partie A
              
        	            
        	            
        	             // test pour afficher les mots du vocabulaire
        	            System.out.println("les mots du voc sont : " + vocabulary.getSize());
        	            System.out.println("les mots du voc sont : " + vocabulary.getVocabulary());
        	            //fin 
        	            
        	           // tfidf.vocabulaire(corpus);
        	          //test de la partie c et d
        	            Scanner scanner = new Scanner(System.in);
        	            System.out.println("ENTREZ 1 pour TFIDF, 2 pour BM25");
        	            int choixModele = scanner.nextInt();
        	            scanner.nextLine();
        	            System.out.println("ENTREZ la requete");
        	            String requete = scanner.nextLine();
        	            scanner.nextLine();
        	           
        	            System.out.println("QUELLE EST LE MAX");
        	            int max = scanner.nextInt();
        	            scanner.nextLine();
        	          scanner.close();
        	            if (choixModele == 1) {
        	            	
        	            	 tfidf.processQuery(requete, max);
        	            } else if (choixModele == 2) {
        	            	try {
        	                  
        	                    requete = Bm25Exception.requete(requete);

        	                    if (requete == null || requete.trim().isEmpty()) {
        	                        throw new Bm25Exception(" La requête est vide !");
        	                    }

        	                  
        	                   
        	                } catch (Bm25Exception e) {
        	                    System.err.println("exception : " + e.toString());
        	                }
        	            
        	            	bm.processCorpus(corpus);
        	            	
        	            	
        	            	bm.processQuery(requete, max) ;
        	            } 
                     
        	            
        	        } catch (Exception e) {
        	         
        	            System.err.println("erreur : " + e.getMessage());
        	        }
        	    
        	    }
        	}
        	    
        	 
        	

        	