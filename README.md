# FormatStringDelimiter

This helper aims to indent tab string by padding each cell on the max size

```
Scenario: format a 3x3 tab where each cell have different size
Given the following tab
  | color | value | result |
  | yellow | true | foo |
  | red | true | a big result |
When the formatter is called
Then the result is
  | color  | value | result       |
  | yellow | true  | foo          |
  | red    | true  | a big result |
```
