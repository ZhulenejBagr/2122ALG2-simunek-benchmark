# Syntetický benchmark pro hardware v Javě

## Zadání

Cílem aplikace je vytvoření programu, který na základě nějaké umělé metriky bude schopný ohodnotit hardware (+ prostředí), na kterém je spuštěný. Testy budou převážně určeny pro CPU a RAM, využije se zde knihovny SciMark 2.0 pro otestování floating-point aritmetiky (možná si zkusím napsat vlastní test na otestování zpoždění do CPU cache a RAM paměti). Výsledky těchto testů bude možné ukládat do lokální databáze, z které taky půjde si uložené výsledky vypsat. Aplikace si bude načítat konfiguraci z textového souboru, který bude obsahovat různé parametry pro jednotlivé testy. Průběžné výsledky testů (logování) se bude zapisovat do souboru.

## Návrh řešení

1. GUI  
Menu - umožňuje spustit testy, vypsat HW počítače, vypsat současnou konfiguraci testů

2. Spuštění testu  
Načtení konfigurace, spuštění testu(ů), zobrazení (uložení) výsledků, loggování do souboru

3. Uložení výsledků do databáze  
Uložení výsledku do databáze, zahrnutí infa o systému, výpis výsledků z databáze (dle kritérií - komparátor, regex)

## TODO

logger, better result, result saving, result display and search, separate inner launcher logic from GUI, change package naming
