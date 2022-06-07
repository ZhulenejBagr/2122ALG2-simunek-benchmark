package com.tul.simunek.semproj.app;

import java.io.Serializable;
import java.time.LocalDateTime;


public interface ITestResult extends Comparable<ITestResult>, Serializable {
    /**
     * Zjištění testu, který přísluší výsledku
     * @return identifikátor příslušného testu
     */
    String getTestID();
    
    /**
     * Zjištění počátečního času
     * @return počáteční čas ve formě instance LocalDateTime
     */
    LocalDateTime getStartTime();
    
    /**
     * Zjištění času doběhnutí testu
     * @return konečný čas ve formě instance LocalDateTime
     */
    LocalDateTime getEndTime();
    
    /**
     * Výpočet milisekund co probíhat test
     * @return čas v milisekundách
     */
    long getTestRunTimeMilis();
    
    /**
     * Zjištění skóre u výsledku
     * @return skóre ve formě čísla s plovoucí des. čárkou
     */
    double getTestScore();
    
    /**
     * Určení, jestli skóre je validní
     * @return validita skóre
     */
    boolean isScoreValid();
    
    
    /**
     * Výpis systémových informací ke konkrétnímu testu
     * Mohou se lišit mezi testy
     * @return pole s elementy představující jednotlivé informace
     */
    String[] getTestSystemInfo();
}
