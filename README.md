# Acclimatization-conditions
Application for saving and viewing acclimatization conditions in accredited laboratory.

Tytuł:
Acclimatization conditions

Inspiracja:
Potrzeba gromadzenia historii warunków środowiskowych w laboratorium.

Opis:
Jest to aplikacja stworzona do monitorowania warunków środowiskowych w laboratorium akredytowanym.
Działąjąc w tle co godzinę łączy się przez sieć z termohogrobarometrem i odczytuje z niego wartości wilgotności, temperatury i ciśnienia. Nastepnie zapisuje je do pliku txt i csv. Posiada też możliwość przeglądania zapisanych rekordów, a takrze zapisu do pliku csv danych z interesującego nas okresu czasu.

Uruchomienie:

Po ściągnięciu repozytorium należy w naszym środowisku utworzyć projekt za pomocą pliku pom.xml.
Wszystko jest ustawione w taki sposób, że powinno działać bez problemu na testowych danych.

Adres, z którrego pobierane są dane zmieniamy w environmentalConditions -> servis -> EnvironmentalServis w metodzie getData().

Częstotliwość zapisu zmieniamy w environmentalConditions -> controller -> EnvironmentalController
w akcji monitorData.



