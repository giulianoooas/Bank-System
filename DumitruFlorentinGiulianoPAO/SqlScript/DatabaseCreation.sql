alter table Cont add constraint fkCont foreign key (idBanca) references Banca(idBanca);

alter table Card add constraint fkCard foreign key (idCont) references Card(idCont);

alter table Tranzactie add constraint fkTranzactie1 foreign key (cont1) references Cont(idCont);

alter table Tranzactie add constraint fkTranzactie2 foreign key (cont2) references Cont(idCont);

alter table Tranzactie add constraint fkTranzactie3 foreign key (banca1) references Banca(idBanca);

alter table Tranzactie add constraint fkTranzactie4 foreign key (banca2) references Banca(idBanca);
