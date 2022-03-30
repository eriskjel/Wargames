package edu.ntnu.idatt2001.units;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class InfantryUnitTest {

    /**
     * test that checks every object variable of unit superclass, and for each csv line, with different illegal values, checks
     * if the method throws.
     * There is one illegal value per line, and per object variable
     * meaning every value and exception will be tested
     * @param name name
     * @param health health
     * @param attack attack
     * @param armour armour
     */
    @ParameterizedTest
    @CsvSource({
            "'', 1, 1, 1",
            "invalid health, -1, 1, 1",
            "invalid attack, 1, -1, 1",
            "invalid armour, 1, 1, -1"
    })
    public void constructorThrowsExceptionForNegativeValues(String name, int health, int attack, int armour){
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            Unit infantryUnit = new InfantryUnit(name, health, attack, armour);
        });
    }

    @Test
    void CheckAttackAndResistBonus(){
        Unit testunit = new InfantryUnit("the man", 25);
        assertEquals(1, testunit.getResistBonus());
        assertEquals(2, testunit.getAttackBonus());
    }
}
