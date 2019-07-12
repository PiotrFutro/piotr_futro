# piotr_futro: Salary Calculator for Sonalake

Aplikacja ma za zadanie wyliczyć miesięczny zarobek netto w PLN na kontrakcie w Niemczech, Wielkiej Brytanii i Polsce.


- kontrakt usługi:<br>
http://localhost:8080/api/salary-calculator/v2/api-docs

- UI:<br>
http://localhost:8080/api/salary-calculator/swagger-ui.html


Podatki i koszty stałe dla poszczególnych krajów:
- UK: 25%, 600 GBP
- DE: 20%, 800 EUR
- PL: 19%, 1200 PLN

Te dane w aplikacji prowizorycznie utrwalone są w klasie enum.
Na jej podstawie fabrykowany jest zbiór kalkulatorów dla poszczególnych krajów.

Wywołanie usługi uruchomionej w localhost:<br>
http://localhost:8080/api/salary-calculator/country/{countryCode}?dailySalary=Value

Przykład wywołania dla DE:<br>
http://localhost:8080/api/salary-calculator/country/DE?dailySalary=100

Przykład odpowiedzi:<br>
{
  "monthlyNettoPLN": 687750.68,
  "exchangeRate": 4.7513
}

Aktualny kurs średni wymiany walty odczytywany jest z tabeli kursowej NBP<br>
Przykład wywołania usługi API NBP dla waluty EUR:<br>
http://api.nbp.pl/api/exchangerates/rates/a/eur/<br>
Gdy API nie zwróci danych, aplikacja rzuca wyjątek:<br>
new NbpExeption("Brak kursu wymiany waluty.")

