# solid-spring-boot
SOLID exercise for university class project.

1. Mamy następujące klasy

Currency:
- PLN
- GBP

Country:
- Polska, PL
- Wielka Brytania, EN

Item:
- Stolik
- Szafa
- Lustro

Customer
- Tokar Marcin z Polski
- Omiotek Piotr z Polski
- Smith John z Anglii

Order:
Plik z zamówieniami, gdzie linia ma format (zakładamy poprawność i spójność danych):
nazwisko imie id_zamówienia ilość product cena_jedn

Tokar Marcin 1 20 stolik 125
Tokar Marcin 1 3 szafa 500
Tokar Marcin 2 7 lustro 100
Tokar Marcin 2 3 szafa 450
Tokar Marcin 3 7 szafa 400
Smith John 4 15 lustro 120
Smith John 4 1 szafa 800
Smith John 5 1 lustro 80
Smith John 5 1 szafa 600
Smith John 5 2 lustro 90 (tak, drugi raz lustro w zamówieniu w innej cenie)
Omiotek Piotr 6 123 lustro 50
Omiotek Piotr 6 1 szafa 540
Omiotek Piotr 6 1 lustro 100

Napisać program, który wczyta wszystkie zamówienia, wyliczy ich koszt i za nie zapłaci.

Reguły:
- anglicy płacą w funtach
- kurs funta to 6 PLN
- w polsce mamy podatek 20% w anglii 40%
- płatność jest realizowana internetowo (wyświetlamy tekst płace przez internet kwotę X PLN/GBP)
