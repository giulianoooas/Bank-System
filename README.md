Tema proiectului este o aplicatie bancara.

Proiectul a fost facut in Eclipse.

# Actiuni/ Interogari
```
  1) Adaugarea unei banci
  2) Schimbarea pinului unui card.
  3) Adaugarea unui cont
  4) Stergerea unui cont
  5) Adaugarea unui card
  6) Stergerea unui card
  7) Creerea unui tranzactii
  8) Imprumutul de bani
  9) Depozitarea de bani
  10) Sa vezi extrasul
  11) Sa trimitem bani
  12) Afisam carduri
```

# Clase
```
  1) Main (clasa de baza ce apeleaza tot)
  2) Banca (clasa ce memoreaza informatiile despre o banca)
  3) Cont (informatiile despre cont)
  4) Card 
  5) Portofel (ce leaga un cont cu mai multe carduri)
  6) Tranzactie
  7) Extras
  8) Serviciu (o interfata ce ma ajuta sa declar serviciile unei banci)
  9) Imprumut (primul serviciu)
  10) Depozit (al doilea serviciu)
  11) AuditWriter (o clasa singleton ce ma ajuta sa scriu in csv-ul Audit.csv auditul)
  12) CsvReader (O clasa utilitara pentru citirea de csv-uri)
```

# Functionalitati:
```
  1) Adaugarea intr-o baza de date usoara a unei banci;
  2) Adaugarea si stergerea usoara din baza de date a unui cont.
  3) Sa poti adauga usor si sa stergi din cardurile unui cont.
  4) Sa poti imprumuta si depozita usor bani din cont in banca.
  5) Gestionarea mai usoara a cardurilor printr-un portofel digital ce are o lista cu cardurile unui cont.
```

# Schema proiectului
```
  1) O banca poate sa aiba mai multe conturi.
  2) Un cont poate sa aiba mai multe carduri. Pentru a nu aparea problema relatiei many-to-many am creat un portofel ce e clasa auxiliara.
  3) Imprumutul si Depozitul sunt clase statice, iar Serviciu este o clasa abstracta ce ne ajuta sa implementam cele 2 clase singleton.
  4) Fiecare Cont poate face mai multe tranzactii, pentru usurinta am creat o clasa de legatura Extras ce imi arata toate tranzactiile facute de un cont. 
```

# Clase cu fisiere CSV pentru etapa a 2-a

```
  1) Banca
  2) Cont
  3) Tranzactie
  4) Card
```

Pentru a face proiectul ordonat am pus toate fisierele csv intr-un folder in pachetul proiect.
Pentru citirea si scrierea in fisiere csv am facut clase speciale ce tratau exceptiile.


Am folosit thread-uri pentru nu bloca sistemul in momentul in care un cont cere sa se faca un imprumut sau un depozit de bani.
In sistemul implementat de mine, fiecare cont are minimum un card in portfelul digital, nu poti sa stergi sa ramai fara niciunul.

#Etapa 3 si modificarile aduse aplicatiei

Pentru aceasta etapa a trebuit sa schimb radical conceptul aplicatiei mele.
Am creat o clasa utilitara pentru a lucru cu baza de date, clasa numica DatabaseConection ce este un singleton, deoarece am nevoie doar de o singura instanta a conexiunii.
In aceasta clasa am implementat actiunile CRUD pe 4 elemente.
```
 1) Banca(#idBanca, Nume, Suma)
 2) Cont(#idCont, Detinator, idBanca, suma, Iban)
 3) Card(#idCard, idCont,Pin)
 4) Tranzactie (#idTranzactie, Cont1,Cont2,Banca1,Banc2, suma)
```
Singura clasa ce nu are posibilitatea de a fi actualizata este Tranzactie, deoarece dupa ce s-a facut o tranzactie, ea nu mai poate fi schimbata.
Pentru a realiza demo-ul, l-am facut pe baza consolei, prin mai multe comenzi si un meniu interactiv.
Prin el poti sa faci urmatoarele actiuni:
```
1) pentru a afisa toate informatiile actuale
2) pentru a adauga o banca
3) pentru a adauga un cont
4) pentru a adauga un card
5) pentru a sterge o banca
6) pentru a sterge un cont
7) Schimba numele unei banci
8) Schimba numele unui posesor de cont
9) Sterge un card
10) Schimba pinul unui card
11) Adauga bani intr-o banca
12) Trimite bani intr-un cont
13) Trimite bani de la cont la banca
14) Trimite bani de la cont la cont
15) Creeaza un depozit
16) Creaza un imprumut
```
Orice instructiune ce nu se potriveste, duce la oprirea programului.
