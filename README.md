Kairos
======

A Java-based interpreter for Whitespace and WhitespaceAssembler



Part 1: WhitespaceAssembler command overview
------


**Stack** operations

Operation|Breakdown|Aliases
---------|---------|---------
'push X'|push value X onto stack|#X
'dupl'|duplicate value on top of stack|duplicate, dupe, cc
'copy X'|copy X-th value onto stack|cp X
'swap'|swap top two items of stack|sw
'pop'|discard top item from stack|p
'slide X'|discard X items after top item from stack|sl X



**Arithmetic** operations (*Note*: Removes top two values from stack, places result of operation on top.)

Operation|Breakdown|Aliases
---------|---------|---------
'add'|add top two items|+
'sub'|subtract top item from second to top item|subtract, -
'mul'|multiply top two items|multiply, *
'div'|divide second to top item by top item|divide, /
'mod'|modulo compute remainder of the division operation of top two items|modulo, %



**Heap** operations (*Note*: Requires address on top of stack, and a value on top of that in case of 'store'. Consumes both, if applicable; places retrieved value on top of stack in case of 'get'.)

Operation|Breakdown|Aliases
---------|---------|---------
'store X'|store value X in heap|@X
'get'|get value from heap|?



**Flow control** operations

Operation|Breakdown|Aliases
---------|---------|---------
'label X'|create label X at this point|:X
'call X'|call subroutine X|X()
'jump X'|jump to label X|go to X, goto X, => X
'jzero X'|jump to label X if top item on stack is zero|0=> X
'jneg X'|jump to label X if top item on stack is negative|-=> X
'ret'|return from subroutine|return
'exit'|exit program



**Input/Output** operations

Operation|Breakdown|Aliases
---------|---------|---------
'printc'|print character|pc
'printn'|print number|pn
'readc'|read character|rc
'readn'|read number|rn



**Meta** operations (*Note*: These are not part of the Whitespace specification; they currently can only be resolved by the Kairos interpreter and will be lost in translation to Whitespace proper.)

Operation|Breakdown|Aliases
---------|---------|---------
'import X'|import WhitespaceAssembler function X into current code; duplicate imports have no effect|require

