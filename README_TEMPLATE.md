# Proiect Energy System Etapa 2

## Despre

Proiectare Orientata pe Obiecte, Seriile CA, CD
2020-2021

<https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2>

Student: CAPLAN ROBERT STEFAN, 321CD

## Rulare teste

Clasa Test: main
  * ruleaza solutia pe testele din checker/, comparand rezultatele cu cele de referinta
  * ruleaza checkstyle

Detalii despre teste: checker/README

Biblioteci necesare pentru implementare:
* Jackson Core 
* Jackson Databind 
* Jackson Annotations

Tutorial Jackson JSON: 
<https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-json-jackson>

## Implementare

### Entitati

Clase adaugate fata de etapa 1:

- SearchProducer: Gasesc cei mai buni producatori pentru distribuitori
- GreenStrategy: Implementez metodele specifice strategiei Green
- PriceStrategy: Implementez metodele specifice strategiei Price
- QuantityStrategy: Implementez metodele specifice strategiei Quantity
- ProducerOutput: Clasa care ajuta la scrierea in JSON a producerilor dupa formatul cerut
- DistributorChange: Clasa pentru a implementa modificarile aduse distribuitorilor pe parcurs
- MonthlyStat: Date lunare despre producatori (ID-ul distribuitorilor carora le dau energie si 
                luna respectiva)
- Producer: Clasa pentru producatori
- ProducerChange: Clasa pentru a implementa modificarile aduse producatorilor pe parcurs

### Flow

Flow-ul nu este foarte diferit de etapa 1, deoarece a fost nevoie sa aduc mici modificari pentru 
etapa 2, lucru care a fost o surpriza placuta si pentru mine :). Am adaugat in clasa NewRound 
metode pentru a gasi producatori fiecarui distribuitor si pentru a anunta distribuitorii de 
schimbari ale preturilor producatorilor prin pattern-ul Observer.

### Elemente de design OOP

Am folosit incapsulare pentru pattern-ul Observer, interfete pentru Factory, suprascriere, 
polimorfism, 

### Design patterns

Am folosit patter-ul Observer pentru a anunta distribuitorii de eventuale modificari ale 
preturilor producatorilor, Strategy pentru a aplica anumite strategii distribuitorilor, Factory 
pentru a creea anumite strategii.

### Dificultati intalnite, limitari, probleme

Am intampinat unele dificultati la checkstyle, cum ar fi eroarea importurilor de tip wildcard 
care sunt generate automat de IntelliJ.