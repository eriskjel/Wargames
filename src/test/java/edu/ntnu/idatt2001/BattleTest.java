package edu.ntnu.idatt2001;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    void simulate() {

    }

    @Test
    void checkWin() {
    }

    @Test
    void battleInfo() {
    }

    @Test
    void checkIfUnitIsDead() {
        assertFalse(Battle.isDead(new InfantryUnit("erik", 100)));
    }
    @Test
    void checkIfUnitIsAlive() {
        assertTrue(Battle.isDead(new InfantryUnit("erik", -100)));
    }

    @Nested
    class HealthIsNotSupported{
        @Test
        @DisplayName("Health is not supported.")
        public void healthIsNegative(){
            try{
                Unit unit = new InfantryUnit("Footman", -100);
            } catch (IllegalArgumentException ex){
                assertEquals(ex.getMessage(), "Health must be larger than zero.");
            }
        }
    }

}