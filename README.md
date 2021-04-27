Tema proiectului este o aplicatie bancara.

Proiectul a fost facut in Eclipse.

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

Pentru a face proiectul ordonat am pus toate fisierele csv intr-un folder in pachetul proiect.
Pentru citirea si scrierea in fisiere csv am facut clase speciale ce tratau exceptiile.


Am folosit thread-uri pentru nu bloca sistemul in momentul in care un cont cere sa se faca un imprumut sau un depozit de bani.
In sistemul implementat de mine, fiecare cont are minimum un card in portfelul digital, nu poti sa stergi sa ramai fara niciunul.
