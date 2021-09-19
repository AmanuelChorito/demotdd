package com.tdd.demotdd;

import com.tdd.demotdd.utils.TodoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TodoValidatorTest {
    private TodoValidator todoValidator;
    @BeforeEach
    void setUp() {
        todoValidator= new TodoValidator();
    }

    @Test
    void itShouldvalidateInvalidTitleLength() {
//given
        String title="amamamamamamawe";
        //when
        boolean isVlaid= todoValidator.validateTitlelength(title);

        //then

        assertThat(isVlaid).isTrue();
    }
    @Test
    void itShouldvalidateValidTitleLength() {
//given
        String title="amamamamama";
        //when
        boolean isVlaid= todoValidator.validateTitlelength(title);

        //then

        assertThat(isVlaid).isTrue();
    }

}
