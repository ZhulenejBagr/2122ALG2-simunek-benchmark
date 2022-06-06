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

### Class Diagram
![class diagram](https://user-images.githubusercontent.com/43739606/172249360-439336ae-0673-49cf-bdde-e762eda782fd.png)

## Popis fungování externí klihovny
- [SciMark 2.0](https://math.nist.gov/scimark2/)
```
int FFT_size = Constants.FFT_SIZE;
int SOR_size =  Constants.SOR_SIZE;
int Sparse_size_M = Constants.SPARSE_SIZE_M;
int Sparse_size_nz = Constants.SPARSE_SIZE_nz;
int LU_size = Constants.LU_SIZE;
 // run the benchmark

double res[] = new double[6];
Random R = new Random(Constants.RANDOM_SEED);

res[1] = kernel.measureFFT( FFT_size, min_time, R);
res[2] = kernel.measureSOR( SOR_size, min_time, R);
res[3] = kernel.measureMonteCarlo(min_time, R);
res[4] = kernel.measureSparseMatmult( Sparse_size_M, 
      Sparse_size_nz, min_time, R);
res[5] = kernel.measureLU( LU_size, min_time, R);


res[0] = (res[1] + res[2] + res[3] + res[4] + res[5]) / 5;

System.out.println("Composite score: " + res[0]);
```
