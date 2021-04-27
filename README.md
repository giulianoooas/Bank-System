Tema proiectului este o aplicatie bancara.

Proiectul a fost facut in Eclipse.

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
