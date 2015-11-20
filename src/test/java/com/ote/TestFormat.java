package com.ote;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Olivier on 20/11/2015.
 */
public class TestFormat {

    @Test
    public void tab_should_be_indented_with_max_value() {

        Collection<String> param = Arrays.asList(
                "| color | value | result |",
                "| yellow | true | foo |",
                "| red | true | a big result |");

        Collection<String> res = Arrays.asList(
                "| color  | value | result       |",
                "| yellow | true  | foo          |",
                "| red    | true  | a big result |");

        IndentTabFormat.format(param).stream().
                forEach(System.out::println);

        Assertions.assertThat(IndentTabFormat.format(param)).
                usingFieldByFieldElementComparator().
                containsExactly(res.toArray(new String[0]));
    }
}
