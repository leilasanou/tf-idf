Ce projet est une implémentation des algorithmes TF-IDF (Term Frequency - Inverse Document Frequency) et BM25 (Best Matching 25), utilisés dans le domaine du text mining et de la recherche d'information pour évaluer la pertinence des documents par rapport à une requête donnée.

📖 Explication des Algorithmes

1️⃣ TF-IDF (Term Frequency - Inverse Document Frequency)

Le TF-IDF est une métrique qui mesure l'importance d'un mot dans un document en fonction de sa fréquence d'apparition et de sa rareté dans l'ensemble des documents.

📊 Formules :

Fréquence Terme (TF) :



où :

 est le nombre d'occurrences du terme  dans le document .

 est le nombre total de mots dans le document.

Fréquence Inverse du Document (IDF) :



où :

 est le nombre total de documents dans le corpus.

 est le nombre de documents contenant le terme .

Score TF-IDF :



2️⃣ BM25 (Best Matching 25)

L'algorithme BM25 est une amélioration de TF-IDF qui prend en compte la longueur des documents et une saturation de la fréquence du terme pour éviter qu'un mot répété ait trop d'influence.

📊 Formule :



où :

 est la requête.

 est la fréquence inverse du document.

 est la fréquence du terme  dans le document .

 est la longueur du document.

 est la longueur moyenne des documents.

 et  sont des hyperparamètres (typiquement  et ).
